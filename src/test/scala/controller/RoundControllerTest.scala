package cl.uchile.dcc
package controller

import card.Card
import deck.Deck
import hand.Hand
import rank.numbers.figure.King
import rank.numbers.odd.Ace
import states.{DefeatState, SelectingCardsState, VictoryState}
import suit.{Hearts, Spades}

import munit.FunSuite

/**
 * Test suite for basic RoundController setup and accessor behavior.
 *
 * Tests initRound, accessor methods, message handling, and score tracking.
 */
class RoundControllerTest extends FunSuite {

  /**
   * Tests that a newly constructed controller starts in SelectingCardsState.
   *
   * Verifies the default state before initRound is called.
   */
  test("controller starts in SelectingCardsState by default") {
    val controller = new RoundController
    assertEquals(controller.state.getName, "SelectingCardsState")
  }

  /**
   * Tests that initRound transitions the controller into SelectingCardsState
   * and resets the total score.
   *
   * Verifies the round setup behavior.
   */
  test("initRound resets total score and enters SelectingCardsState") {
    val controller = new RoundController
    controller.initRound(new Hand, new Deck, 100.0)
    assertEquals(controller.getTotalScore,  0.0)
    assertEquals(controller.getTargetScore, 100.0)
    assertEquals(controller.state.getName,  "SelectingCardsState")
  }

  /**
   * Tests that setMessage updates the message accessible via getMessage.
   *
   * Verifies the message access pair.
   */
  test("setMessage updates the message returned by getMessage") {
    val controller = new RoundController
    controller.setMessage("Custom message")
    assertEquals(controller.getMessage, "Custom message")
  }

  /**
   * Tests that addToTotalScore accumulates across multiple calls.
   *
   * Verifies the running total behaves additively.
   */
  test("addToTotalScore accumulates across multiple calls") {
    val controller = new RoundController
    controller.addToTotalScore(10.0)
    controller.addToTotalScore(5.5)
    assertEquals(controller.getTotalScore, 15.5)
  }

  /**
   * Tests that setSelectedIndices stores the given indices for later use.
   *
   * Verifies the selection access pair.
   */
  test("setSelectedIndices stores indices accessible via selectedIndices") {
    val controller = new RoundController
    controller.setSelectedIndices(List(0, 2, 4))
    assertEquals(controller.selectedIndices, List(0, 2, 4))
  }

  /**
   * Tests that hand and deck accessors reflect the values passed to initRound.
   *
   * Verifies the controller correctly stores its collaborators.
   */
  test("hand and deck accessors reflect initRound arguments") {
    val controller = new RoundController
    val hand = new Hand
    hand.addCard(new Card(new Ace, new Spades))
    val deck = new Deck
    deck.load(List(new Card(new King, new Hearts)))
    controller.initRound(hand, deck, 50.0)
    assertEquals(controller.hand.cardCount, 1)
    assertEquals(controller.deck.remaining, 1)
  }

  /**
   * Tests that transitionTo immediately invokes the new state's onEnter hook.
   *
   * Verifies the state-transition contract using DefeatState, whose onEnter
   * only sets a message and does not chain further.
   */
  test("transitionTo invokes onEnter on the new state immediately") {
    val controller = new RoundController
    controller.transitionTo(new DefeatState(controller))
    assert(controller.getMessage.contains("Defeat"))
  }

  /**
   * Tests that two states with the same name are equal.
   *
   * Verifies the equals implementation on ARoundState.
   */
  test("two states with the same name are equal") {
    val controller = new RoundController
    val stateA = new SelectingCardsState(controller)
    val stateB = new SelectingCardsState(controller)
    assertEquals(stateA, stateB)
  }

  /**
   * Tests that two states with different names are not equal.
   *
   * Verifies the equals implementation distinguishes state types.
   */
  
  test("two states with different names are not equal") {
    val controller = new RoundController
    val selecting  = new SelectingCardsState(controller)
    val victory    = new VictoryState(controller)
    assert(!selecting.equals(victory))
  }
}
