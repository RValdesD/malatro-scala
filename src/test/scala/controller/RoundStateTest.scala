package cl.uchile.dcc
package controller

import munit.FunSuite

/**
 * Test suite for individual TRoundState implementations in isolation.
 *
 * Tests the no-op default behaviours from ARoundState and the specific
 * onEnter overrides of each concrete state. State identity is checked
 * via getName.
 */
class RoundStateTest extends FunSuite {

  /**
   * Tests that VictoryState's playCards is a no-op that updates the message.
   *
   * Verifies a terminal state correctly rejects further play attempts.
   */
  test("VictoryState playCards is a no-op and updates the message") {
    val controller = new RoundController
    new VictoryState(controller).playCards(List(0))
    assertEquals(controller.getMessage, "Round already won.")
  }

  /**
   * Tests that VictoryState's discardCards is a no-op that updates the message.
   *
   * Verifies a terminal state correctly rejects further discard attempts.
   */
  test("VictoryState discardCards is a no-op and updates the message") {
    val controller = new RoundController
    new VictoryState(controller).discardCards(List(0))
    assertEquals(controller.getMessage, "Round already won.")
  }

  /**
   * Tests that VictoryState's onEnter sets a victory message.
   *
   * Verifies the state announces itself correctly upon entry.
   */
  test("VictoryState onEnter sets a victory message") {
    val controller = new RoundController
    new VictoryState(controller).onEnter()
    assert(controller.getMessage.contains("Victory"))
  }

  /**
   * Tests that DefeatState's playCards is a no-op that updates the message.
   *
   * Verifies a terminal state correctly rejects further play attempts.
   */
  test("DefeatState playCards is a no-op and updates the message") {
    val controller = new RoundController
    new DefeatState(controller).playCards(List(0))
    assertEquals(controller.getMessage, "Round over: no plays remaining.")
  }

  /**
   * Tests that DefeatState's discardCards is a no-op that updates the message.
   *
   * Verifies a terminal state correctly rejects further discard attempts.
   */
  test("DefeatState discardCards is a no-op and updates the message") {
    val controller = new RoundController
    new DefeatState(controller).discardCards(List(0))
    assertEquals(controller.getMessage, "Round over: no plays remaining.")
  }

  /**
   * Tests that DefeatState's onEnter sets a defeat message.
   *
   * Verifies the state announces itself correctly upon entry.
   */
  test("DefeatState onEnter sets a defeat message") {
    val controller = new RoundController
    new DefeatState(controller).onEnter()
    assert(controller.getMessage.contains("Defeat"))
  }

  /**
   * Tests that ShowScoreState's onEnter transitions to a valid following state.
   *
   * Verifies the purely transitional role of ShowScoreState: it never
   * stays as the active state after onEnter runs.
   */
  test("ShowScoreState onEnter transitions away immediately") {
    val controller = new RoundController
    val deck = new Deck
    deck.load(List(new Card(new Ace, new Spades)))
    controller.initRound(new Hand, deck, 1000.0)
    controller.transitionTo(new ShowScoreState(controller))
    val validFollowingStates = List("SelectingCardsState", "VictoryState", "DefeatState")
    assert(validFollowingStates.contains(controller.state.getName))
  }

  /**
   * Tests that SelectingCardsState's onEnter sets a prompt message.
   *
   * Verifies the state announces itself correctly upon entry.
   */
  test("SelectingCardsState onEnter sets a prompt message") {
    val controller = new RoundController
    new SelectingCardsState(controller).onEnter()
    assert(controller.getMessage.contains("Choose"))
  }

  /**
   * Tests that SelectingCardsState's playCards stores indices and transitions onward.
   *
   * Verifies the entry point of the play branch records the selection.
   */
  test("SelectingCardsState playCards stores indices and transitions onward") {
    val controller = new RoundController
    val hand = new Hand
    hand.addCard(new Card(new Ace, new Spades))
    val deck = new Deck
    deck.load(List(new Card(new King, new Hearts)))
    controller.initRound(hand, deck, 100000.0)
    controller.playCards(List(0))
    assertEquals(controller.selectedIndices, List(0))
  }

  /**
   * Tests that each concrete state has a unique name.
   *
   * Verifies no two state types share a getName value, which would
   * break the equals-based state identity check.
   */
  test("all concrete states have distinct names") {
    val controller = new RoundController
    val names = List(
      new SelectingCardsState(controller).getName,
      new PlayEvalState(controller).getName,
      new ShowScoreState(controller).getName,
      new DiscardState(controller).getName,
      new ReplenishState(controller).getName,
      new DeckEmptyState(controller).getName,
      new VictoryState(controller).getName,
      new DefeatState(controller).getName
    )
    assertEquals(names.distinct.size, 8)
  }
}

