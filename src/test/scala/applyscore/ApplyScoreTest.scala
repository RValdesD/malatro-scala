package cl.uchile.dcc
package applyscore

import combinations.*
import joker.{DeviousJoker, EvenSteven, GreedyJoker, ScaryFace}
import rank.classes.{EvenClass, FigureClass, OddClass}
import score.Score
import suit.{Clovers, Diamonds, Hearts, Spades}

import munit.FunSuite

/**
 * Represents a test suite for applyScore's double dispatch.
 *
 * Tests score interactions between combinations, suits, rank classes,
 * and all four joker types.
 */
class ApplyScoreTest extends FunSuite {

  // Combination × Joker

  /**
   * Tests that GreedyJoker adds +3 chips to any combination score.
   *
   * Verifies the GreedyJoker back-call on Pair.
   */
  test("Pair applyScore with GreedyJoker adds 3 chips") {
    val score  = new Score(10, 2.0)
    val result = new Pair().applyScore(score, new GreedyJoker)
    assertEquals(result.chips, 13)
    assertEquals(result.multiplier, 2.0)
  }

  /**
   * Tests that GreedyJoker adds +3 chips on a Straight combination.
   *
   * Verifies GreedyJoker works consistently across combination types.
   */
  test("Straight applyScore with GreedyJoker adds 3 chips") {
    val score  = new Score(30, 4.0)
    val result = new Straight().applyScore(score, new GreedyJoker)
    assertEquals(result.chips, 33)
  }

  /**
   * Tests that DeviousJoker adds +100 chips on a Straight.
   *
   * Verifies the DeviousJoker back-call on Straight.
   */
  test("Straight applyScore with DeviousJoker adds 100 chips") {
    val score  = new Score(30, 4.0)
    val result = new Straight().applyScore(score, new DeviousJoker)
    assertEquals(result.chips, 130)
  }

  /**
   * Tests that DeviousJoker adds +100 chips on a StraightFlush.
   *
   * Verifies the DeviousJoker back-call on StraightFlush.
   */
  test("StraightFlush applyScore with DeviousJoker adds 100 chips") {
    val score  = new Score(100, 8.0)
    val result = new StraightFlush().applyScore(score, new DeviousJoker)
    assertEquals(result.chips, 200)
  }

  /**
   * Tests that DeviousJoker has no effect on a non-straight combination.
   *
   * Verifies the default no-op back-call on Flush.
   */
  test("Flush applyScore with DeviousJoker leaves score unchanged") {
    val score  = new Score(35, 4.0)
    val result = new Flush().applyScore(score, new DeviousJoker)
    assertEquals(result.chips,      35)
    assertEquals(result.multiplier, 4.0)
  }

  /**
   * Tests that EvenSteven has no effect at the combination level.
   *
   * Verifies the default no-op back-call for EvenSteven on HighCard.
   */
  test("HighCard applyScore with EvenSteven leaves score unchanged") {
    val score  = new Score(5, 1.0)
    val result = new HighCard().applyScore(score, new EvenSteven)
    assertEquals(result.chips,      5)
    assertEquals(result.multiplier, 1.0)
  }

  /**
   * Tests that ScaryFace has no effect at the combination level.
   *
   * Verifies the default no-op back-call for ScaryFace on ThreeOfAKind.
   */
  test("ThreeOfAKind applyScore with ScaryFace leaves score unchanged") {
    val score  = new Score(30, 3.0)
    val result = new ThreeOfAKind().applyScore(score, new ScaryFace)
    assertEquals(result.chips,      30)
    assertEquals(result.multiplier, 3.0)
  }

  // Suit × Joker

  /**
   * Tests that all jokers leave suit scores unchanged by default.
   *
   * Verifies that suits have no special joker interactions defined yet.
   */
  test("Hearts applyScore with any joker leaves score unchanged") {
    val score = new Score(10, 2.0)
    assertEquals(new Hearts().applyScore(score, new GreedyJoker).chips,      10)
    assertEquals(new Hearts().applyScore(score, new DeviousJoker).chips,     10)
    assertEquals(new Hearts().applyScore(score, new EvenSteven).chips,       10)
    assertEquals(new Hearts().applyScore(score, new ScaryFace).chips,        10)
  }

  /**
   * Tests that suit applyScore returns the same score for all four suits.
   *
   * Verifies the default no-op across all suit types.
   */
  test("All suits applyScore with GreedyJoker leave score unchanged") {
    val score = new Score(20, 3.0)
    assertEquals(new Spades().applyScore(score, new GreedyJoker).chips,   20)
    assertEquals(new Clovers().applyScore(score, new GreedyJoker).chips,   20)
    assertEquals(new Diamonds().applyScore(score, new GreedyJoker).chips, 20)
  }

  // RankClass × Joker

  /**
   * Tests that EvenSteven adds +4 chips when applied to EvenClass.
   *
   * Verifies the EvenSteven sback-call on EvenClass.
   */
  test("EvenClass applyScore with EvenSteven adds 4 chips") {
    val score  = new Score(10, 2.0)
    val result = new EvenClass().applyScore(score, new EvenSteven)
    assertEquals(result.chips, 14)
  }

  /**
   * Tests that EvenSteven has no effect on OddClass.
   *
   * Verifies the default no-op back-call for EvenSteven on OddClass.
   */
  test("OddClass applyScore with EvenSteven leaves score unchanged") {
    val score  = new Score(10, 2.0)
    val result = new OddClass().applyScore(score, new EvenSteven)
    assertEquals(result.chips, 10)
  }

  /**
   * Tests that ScaryFace adds +30 chips when applied to FigureClass.
   *
   * Verifies the ScaryFace back-call on FigureClass.
   */
  test("FigureClass applyScore with ScaryFace adds 30 chips") {
    val score  = new Score(10, 2.0)
    val result = new FigureClass().applyScore(score, new ScaryFace)
    assertEquals(result.chips, 40)
  }

  /**
   * Tests that ScaryFace has no effect on EvenClass.
   *
   * Verifies the default no-op back-call for ScaryFace on EvenClass.
   */
  test("EvenClass applyScore with ScaryFace leaves score unchanged") {
    val score  = new Score(10, 2.0)
    val result = new EvenClass().applyScore(score, new ScaryFace)
    assertEquals(result.chips, 10)
  }

  /**
   * Tests that GreedyJoker has no effect at the rank class level.
   *
   * Verifies the default no-op back-call for GreedyJoker on all rank classes.
   */
  test("All rank classes applyScore with GreedyJoker leave score unchanged") {
    val score = new Score(10, 2.0)
    assertEquals(new EvenClass().applyScore(score,   new GreedyJoker).chips, 10)
    assertEquals(new OddClass().applyScore(score,    new GreedyJoker).chips, 10)
    assertEquals(new FigureClass().applyScore(score, new GreedyJoker).chips, 10)
  }

  // Immutability

  /**
   * Tests that applyScore returns a new Score without mutating the original.
   *
   * Verifies that Score is immutable across dispatch calls.
   */
  test("applyScore returns a new Score and does not mutate the original") {
    val original = new Score(30, 4.0)
    val result   = new Straight().applyScore(original, new GreedyJoker)
    assertEquals(original.chips, 30)
    assertEquals(result.chips,   33)
  }
}

