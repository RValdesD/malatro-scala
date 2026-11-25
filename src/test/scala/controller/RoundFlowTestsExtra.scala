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

class RoundFlowTestsExtra extends FunSuite {  /**
 * Tests that checkRoundStatus reaches DefeatState when both timesPlayed
 * and timesDiscarded are at their limits and the target score is unmet.
 *
 * Covers the branch: timesPlayed >= 3 true AND timesDiscarded >= 3 true.
 */

  private def newController(targetScore: Double): RoundController = {
    val controller = new RoundController
    val hand = new Hand
    hand.addCard(new Card(new Ace, new Spades))
    hand.addCard(new Card(new King, new Hearts))
    hand.addCard(new Card(new Queen, new Clovers))
    val deck = new Deck
    deck.load(List(
      new Card(new Two, new Spades),
      new Card(new Two, new Hearts),
      new Card(new Two, new Clovers),
      new Card(new Two, new Spades),
      new Card(new Two, new Hearts)
    ))
    controller.setTargetHandSize(3)
    controller.initRound(hand, deck, targetScore)
    controller
  }

  test("checkRoundStatus transitions to DefeatState when both play and discard limits are exhausted") {
    val controller = new RoundController
    val hand = new Hand
    // fill hand with 8 cards so we can play/discard 3 times each
    for (_ <- 1 to 8) hand.addCard(new Card(new Ace, new Spades))
    val deck = new Deck
    // enough cards to replenish across 6 actions
    deck.load((1 to 24).toList.map(_ => new Card(new Two, new Hearts)))
    controller.setTargetHandSize(8)
    controller.initRound(hand, deck, 999999.0)
    // exhaust all 3 plays
    controller.playCards(List(0))
    controller.playCards(List(0))
    controller.playCards(List(0))
    // exhaust all 3 discards
    controller.discardCards(List(0))
    controller.discardCards(List(0))
    controller.discardCards(List(0))
    assertEquals(controller.state.getName, "DefeatState")
  }

  /**
   * Tests that checkRoundStatus does not reach DefeatState when only plays
   * are exhausted but discards remain.
   *
   * Covers the branch: timesPlayed >= 3 true AND timesDiscarded >= 3 false
   * — the && short-circuits to false, round continues.
   */
  test("checkRoundStatus stays in SelectingCardsState when plays are exhausted but discards remain") {
    val controller = new RoundController
    val hand = new Hand
    for (_ <- 1 to 8) hand.addCard(new Card(new Ace, new Spades))
    val deck = new Deck
    deck.load((1 to 24).toList.map(_ => new Card(new Two, new Hearts)))
    controller.setTargetHandSize(8)
    controller.initRound(hand, deck, 999999.0)
    // exhaust all 3 plays only
    controller.playCards(List(0))
    controller.playCards(List(0))
    controller.playCards(List(0))
    assertEquals(controller.state.getName, "SelectingCardsState")
  }

  /**
   * Tests that checkRoundStatus does not reach DefeatState when neither
   * plays nor discards are exhausted.
   *
   * Covers the branch: timesPlayed >= 3 false — short-circuits immediately,
   * round falls through to SelectingCardsState.
   */
  test("checkRoundStatus stays in SelectingCardsState when neither limit is exhausted") {
    val controller = newController(999999.0)
    controller.playCards(List(0))
    assertEquals(controller.state.getName, "SelectingCardsState")
    assertEquals(controller.hand.timesPlayed, 1)
  }
}



