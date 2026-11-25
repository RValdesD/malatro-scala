package cl.uchile.dcc
package suit
import rank.classes.{EvenClass, FigureClass, OddClass}
import rank.numbers.even.*
import rank.numbers.figure.{Jack, King, Queen}
import rank.numbers.odd.*
import suit.{Clovers, Hearts, Spades}
import cl.uchile.dcc.joker.{DeviousJoker, EvenSteven, GreedyJoker, ScaryFace}
import cl.uchile.dcc.score.Score
import munit.FunSuite



/**
 * Test suite for Suit functionality.
 *
 * Tests name retrieval, toString formatting, and applyScore double dispatch
 * behaviour for all four concrete suit types and all four joker types.
 */
class SuitTest extends FunSuite {

  // ── Identity ───────────────────────────────────────────────────────────────

  /**
   * Tests that all four suits have distinct names.
   *
   * Verifies that no two suits share the same name string.
   */
  test("All four suits have distinct names") {
    val suits: List[Suit] = List(new Hearts, new Spades, new Clovers, new Diamonds)
    assertEquals(suits.map(_.name).distinct.size, 4)
  }

  /**
   * Tests that each suit's toString returns its name.
   *
   * Verifies the toString implementation for all four suit types.
   */
  test("Each suit toString returns its name") {
    assertEquals(new Hearts().toString,   "Hearts")
    assertEquals(new Spades().toString,   "Spades")
    assertEquals(new Clovers().toString,   "Clovers")
    assertEquals(new Diamonds().toString, "Diamonds")
  }

  /**
   * Tests that each suit name matches its identity.
   *
   * Verifies the name field for all four suit types.
   */
  test("Each suit name matches its identity") {
    assertEquals(new Hearts().name,   "Hearts")
    assertEquals(new Spades().name,   "Spades")
    assertEquals(new Clovers().name,   "Clovers")
    assertEquals(new Diamonds().name, "Diamonds")
  }

  /**
   * Tests that two instances of the same suit type share the same name.
   *
   * Verifies that suit instances are independent but equivalent.
   */
  test("Suit instances are independent — same type, same name") {
    val heartsA = new Hearts
    val heartsB = new Hearts
    assertEquals(heartsA.name, heartsB.name)
  }

  // ── applyScore × Joker ────────────────────────────────────────────────────

  /**
   * Tests that Hearts applyScore with GreedyJoker leaves score unchanged.
   *
   * Verifies the default no-op for GreedyJoker at the suit level.
   */
  test("Hearts applyScore with GreedyJoker leaves score unchanged") {
    val score  = new Score(10, 2.0)
    val result = new Hearts().applyScore(score, new GreedyJoker)
    assertEquals(result.chips,      10)
    assertEquals(result.multiplier, 2.0)
  }

  /**
   * Tests that Hearts applyScore with DeviousJoker leaves score unchanged.
   *
   * Verifies the default no-op for DeviousJoker at the suit level.
   */
  test("Hearts applyScore with DeviousJoker leaves score unchanged") {
    val score  = new Score(10, 2.0)
    val result = new Hearts().applyScore(score, new DeviousJoker)
    assertEquals(result.chips,      10)
    assertEquals(result.multiplier, 2.0)
  }

  /**
   * Tests that Hearts applyScore with EvenSteven leaves score unchanged.
   *
   * Verifies the default no-op for EvenSteven at the suit level.
   */
  test("Hearts applyScore with EvenSteven leaves score unchanged") {
    val score  = new Score(10, 2.0)
    val result = new Hearts().applyScore(score, new EvenSteven)
    assertEquals(result.chips,      10)
    assertEquals(result.multiplier, 2.0)
  }

  /**
   * Tests that Hearts applyScore with ScaryFace leaves score unchanged.
   *
   * Verifies the default no-op for ScaryFace at the suit level.
   */
  test("Hearts applyScore with ScaryFace leaves score unchanged") {
    val score  = new Score(10, 2.0)
    val result = new Hearts().applyScore(score, new ScaryFace)
    assertEquals(result.chips,      10)
    assertEquals(result.multiplier, 2.0)
  }

  /**
   * Tests that non-Diamond suits leave the score unchanged for all jokers.
   *
   * Diamonds is excluded since it responds to GreedyJoker specifically.
   * Verifies the default no-op across Hearts, Spades, and Clover.
   */
  test("Non-Diamond suits applyScore with any joker leave score unchanged") {
    val score  = new Score(20, 3.0)
    val jokers = List(new GreedyJoker, new DeviousJoker, new EvenSteven, new ScaryFace)
    val suits: List[Suit] = List(new Hearts, new Spades, new Clovers)
    var i = 0
    while (i < suits.size) {
      var j = 0
      while (j < jokers.size) {
        val result = suits(i).applyScore(score, jokers(j))
        assertEquals(result.chips,      20)
        assertEquals(result.multiplier, 3.0)
        j += 1
      }
      i += 1
    }
  }

  /**
   * Tests that Diamonds adds +3 to the multiplier when GreedyJoker is active.
   *
   * Verifies the Diamonds-specific GreedyJoker interaction is correctly applied.
   */
  test("Diamonds applyScore with GreedyJoker adds 3 to the multiplier") {
    val score  = new Score(20, 3.0)
    val result = new Diamonds().applyScore(score, new GreedyJoker)
    assertEquals(result.chips,      20)
    assertEquals(result.multiplier, 6.0)
  }

  /**
   * Tests that Diamonds leaves the score unchanged for all non-GreedyJoker jokers.
   *
   * Verifies Diamonds only responds to GreedyJoker and no other joker type.
   */
  test("Diamonds applyScore with non-GreedyJoker jokers leaves score unchanged") {
    val score  = new Score(20, 3.0)
    val jokers = List(new DeviousJoker, new EvenSteven, new ScaryFace)
    var j = 0
    while (j < jokers.size) {
      val result = new Diamonds().applyScore(score, jokers(j))
      assertEquals(result.chips,      20)
      assertEquals(result.multiplier, 3.0)
      j += 1
    }
  }

  /**
   * Tests that applyScore does not mutate the original score.
   *
   * Verifies that Score remains immutable through the suit dispatch.
   */
  test("Suit applyScore does not mutate the original score") {
    val original = new Score(10, 2.0)
    new Spades().applyScore(original, new GreedyJoker)
    assertEquals(original.chips,      10)
    assertEquals(original.multiplier, 2.0)
  }
}


