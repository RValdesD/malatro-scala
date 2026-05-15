package cl.uchile.dcc
package rank
import rank.classes.{EvenClass, FigureClass, OddClass}
import rank.numbers.even.*
import rank.numbers.figure.{Jack, King, Queen}
import rank.numbers.odd.*
import suit.{Clovers, Hearts, Spades}

import munit.FunSuite

/**
 * Test suite for Rank functionality.
 *
 * Tests order, value, rank class label, name, and toString
 * across all thirteen concrete rank types.
 */
class RankTest extends FunSuite {

  /**
   * Tests that Ace has order 1, value 11, and an Odd rank class.
   *
   * Verifies the three core properties of the Ace rank.
   */
  test("Ace has order 1, value 11, and is Odd") {
    val ace = new Ace
    assertEquals(ace.order, 1)
    assertEquals(ace.value, 11)
    assertEquals(ace.rankClass.toString, "Odd")
  }

  /**
   * Tests that Two has order 2, value 2, and an Even rank class.
   *
   * Verifies the three core properties of the Two rank.
   */
  test("Two has order 2, value 2, and is Even") {
    val two = new Two
    assertEquals(two.order, 2)
    assertEquals(two.value, 2)
    assertEquals(two.rankClass.toString, "Even")
  }

  /**
   * Tests that Jack has order 11, value 10, and a Figure rank class.
   *
   * Verifies the three core properties of the Jack rank.
   */
  test("Jack has order 11, value 10, and is Figure") {
    val jack = new Jack
    assertEquals(jack.order, 11)
    assertEquals(jack.value, 10)
    assertEquals(jack.rankClass.toString, "Figure")
  }

  /**
   * Tests that Queen has order 12 and a Figure rank class.
   *
   * Verifies the order and rank class of the Queen rank.
   */
  test("Queen has order 12 and is Figure") {
    val queen = new Queen
    assertEquals(queen.order, 12)
    assertEquals(queen.rankClass.toString, "Figure")
  }

  /**
   * Tests that King has order 13 and a Figure rank class.
   *
   * Verifies the order and rank class of the King rank.
   */
  test("King has order 13 and is Figure") {
    val king = new King
    assertEquals(king.order, 13)
    assertEquals(king.rankClass.toString, "Figure")
  }

  /**
   * Tests that all even ranks carry the Even rank class label.
   *
   * Verifies that Two, Four, Six, Eight, and Ten all return "Even"
   * from their rankClass toString.
   */
  test("All even ranks carry Even class label") {
    val evens: List[Rank] = List(new Two, new Four, new Six, new Eight, new Ten)
    assert(evens.forall(_.rankClass.toString == "Even"))
  }

  /**
   * Tests that all odd ranks carry the Odd rank class label.
   *
   * Verifies that Ace, Three, Five, Seven, and Nine all return "Odd"
   * from their rankClass toString.
   */
  test("All odd ranks carry Odd class label") {
    val odds: List[Rank] = List(new Ace, new Three, new Five, new Seven, new Nine)
    assert(odds.forall(_.rankClass.toString == "Odd"))
  }

  /**
   * Tests that all 13 ranks are ordered correctly from Ace to King.
   *
   * Verifies that the order values form a consecutive sequence from 1 to 13.
   */
  test("All 13 ranks are ordered correctly from Ace to King") {
    val all: List[Rank] = List(
      new Ace, new Two, new Three, new Four, new Five, new Six, new Seven,
      new Eight, new Nine, new Ten, new Jack, new Queen, new King
    )
    assertEquals(all.map(_.order), (1 to 13).toList)
  }

  /**
   * Tests that each rank name matches its identity.
   *
   * Verifies the name field for a representative sample of ranks.
   */
  test("Rank name matches its identity") {
    assertEquals(new Ace().name,   "Ace")
    assertEquals(new Jack().name,  "Jack")
    assertEquals(new Queen().name, "Queen")
    assertEquals(new King().name,  "King")
    assertEquals(new Ten().name,   "Ten")
  }

  /**
   * Tests that toString includes the name, order, and value.
   *
   * Verifies the format produced by `Rank.toString`.
   */
  test("Rank toString includes name, order, and value") {
    val king = new King
    assert(king.toString.contains("King"))
    assert(king.toString.contains("13"))
    assert(king.toString.contains("10"))
  }
}


