package cl.uchile.dcc
package applyscore

import score.Score
import munit.FunSuite

/**
 * Represents a test suite for Score functionality.
 *
 * Tests all methods on the concrete Score class: total, addChips,
 * addMultiplier, toString, and constructor correctness.
 */
class ScoreTest extends FunSuite {

  /**
   * Tests that chips and multiplier are stored and accessible correctly.
   *
   * Covers the constructor and both accessor vals.
   */
  test("Score stores chips and multiplier correctly") {
    val score = new Score(50, 3.0)
    assertEquals(score.chips, 50)
    assertEquals(score.multiplier, 3.0)
  }

  /**
   * Tests that total returns chips multiplied by multiplier.
   *
   * Covers the total method.
   */
  test("Score total returns chips multiplied by multiplier") {
    val score = new Score(100, 2.5)
    assertEquals(score.total, 250.0)
  }

  /**
   * Tests that total is zero when chips is zero.
   *
   * Covers an edge case of total.
   */
  test("Score total is zero when chips is zero") {
    assertEquals(new Score(0, 5.0).total, 0.0)
  }

  /**
   * Tests that total is zero when multiplier is zero.
   *
   * Covers an edge case of total.
   */
  test("Score total is zero when multiplier is zero") {
    assertEquals(new Score(200, 0.0).total, 0.0)
  }

  /**
   * Tests that addChips returns a new Score with chips increased by the given amount.
   *
   * Covers the addChips method and verifies immutability.
   */
  test("Score addChips returns new Score with increased chips") {
    val original = new Score(10, 2.0)
    val result   = original.addChips(5)
    assertEquals(result.chips, 15)
    assertEquals(result.multiplier, 2.0)
    assertEquals(original.chips, 10)
  }

  /**
   * Tests that addMultiplier returns a new Score with multiplier increased by the given amount.
   *
   * Covers the addMultiplier method and verifies immutability.
   */
  test("Score addMultiplier returns new Score with increased multiplier") {
    val original = new Score(10, 2.0)
    val result   = original.addMultiplier(1.5)
    assertEquals(result.multiplier, 3.5)
    assertEquals(result.chips, 10)
    assertEquals(original.multiplier, 2.0)
  }

  /**
   * Tests that toString includes chips, multiplier, and total.
   *
   * Covers the toString method.
   */
  test("Score toString includes chips, multiplier, and total") {
    val score = new Score(10, 2.0)
    assert(score.toString.contains("10"))
    assert(score.toString.contains("2.0"))
    assert(score.toString.contains("20.0"))
  }
}

