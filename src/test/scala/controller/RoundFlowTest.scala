package cl.uchile.dcc
package controller

import card.Card
import deck.Deck
import hand.Hand
import rank.numbers.figure.*
import rank.numbers.even.*
import rank.numbers.odd.*
import states.{DefeatState, SelectingCardsState, VictoryState}
import suit.*
import munit.FunSuite

/**
 * Test suite for the full round state chain.
 *
 * Tests that playing and discarding correctly drive the controller through
 * the PlayEval/ShowScore/Replenish or Discard/Replenish chains, ending at
 * SelectingCardsState, VictoryState, or DefeatState as appropriate.
 */
class RoundFlowTest extends FunSuite {

  private def newController(targetScore: Double): RoundController = {
    val controller = new RoundController
    val hand = new Hand
    hand.addCard(new Card(new Ace,   new Spades))
    hand.addCard(new Card(new King,  new Hearts))
    hand.addCard(new Card(new Queen, new Clovers))
    val deck = new Deck
    deck.load(List(
      new Card(new Two, new Spades),
      new Card(new Two, new Hearts),
      new Card(new Two, new Clovers),
      new Card(new Two, new Spades),
      new Card(new Two, new Hearts)
    ))
    controller.initRound(hand, deck, targetScore)
    controller
  }

  /**
   * Tests that playing cards with a low target score results in VictoryState.
   *
   * Verifies the full PlayEval -> ShowScore -> Replenish -> checkRoundStatus
   * chain reaches VictoryState when the target score is met.
   */
  test("playCards with an easily met target reaches VictoryState") {
    val controller = newController(1.0)
    controller.playCards(List(0))
    assertEquals(controller.state.getName, "VictoryState")
  }

  /**
   * Tests that playing cards with a high target score returns to
   * SelectingCardsState to continue the round.
   *
   * Verifies the chain falls through to SelectingCardsState when neither
   * Victory nor Defeat conditions are met.
   */
  test("playCards with an unmet target returns to SelectingCardsState") {
    val controller = newController(10000.0)
    controller.playCards(List(0))
    assertEquals(controller.state.getName, "SelectingCardsState")
  }

  /**
   * Tests that the total score increases after a play.
   *
   * Verifies PlayEvalState correctly adds the calculated score to the total.
   */
  test("playCards increases the total score") {
    val controller = newController(10000.0)
    controller.playCards(List(0))
    assert(controller.getTotalScore > 0.0)
  }

  /**
   * Tests that discarding cards with an unmet target returns to
   * SelectingCardsState.
   *
   * Verifies the Discard -> Replenish -> checkRoundStatus chain.
   */
  test("discardCards with an unmet target returns to SelectingCardsState") {
    val controller = newController(10000.0)
    controller.discardCards(List(0))
    assertEquals(controller.state.getName, "SelectingCardsState")
  }

  /**
   * Tests that discarding does not change the total score.
   *
   * Verifies DiscardState performs no scoring, unlike PlayEvalState.
   */
  test("discardCards does not change the total score") {
    val controller = newController(10000.0)
    controller.discardCards(List(0))
    assertEquals(controller.getTotalScore, 0.0)
  }

  /**
   * Tests that the hand is replenished back up after a play.
   *
   * Verifies ReplenishState draws from the deck to refill the hand.
   */
  test("hand is replenished back to its prior size after a play") {
    val controller = newController(10000.0)
    val sizeBefore = controller.hand.cardCount
    controller.playCards(List(0))
    assertEquals(controller.hand.cardCount, sizeBefore)
  }

  /**
   * Tests that the deck shrinks after a play replenishes the hand.
   *
   * Verifies the deck and hand stay in sync through ReplenishState.
   */
  test("deck shrinks after replenishment following a play") {
    val controller = newController(10000.0)
    val deckBefore = controller.deck.remaining
    controller.playCards(List(0))
    assert(controller.deck.remaining < deckBefore)
  }

  /**
   * Tests that DeckEmptyState is reached and recovers when the deck has
   * no cards left to replenish from.
   *
   * Verifies the deck-exhausted edge case: an empty deck triggers a
   * reshuffle and the round still reaches a valid following state.
   */
  test("round recovers via DeckEmptyState when the deck is empty") {
    val controller = new RoundController
    val hand = new Hand
    hand.addCard(new Card(new Ace, new Spades))
    val deck = new Deck
    deck.sendToDiscardPile(List(new Card(new King, new Hearts)))
    controller.initRound(hand, deck, 10000.0)
    controller.discardCards(List(0))
    assertEquals(controller.state.getName, "SelectingCardsState")
  }
}

