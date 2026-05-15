package cl.uchile.dcc
package suit
import rank.classes.{EvenClass, FigureClass, OddClass}
import rank.numbers.even.*
import rank.numbers.figure.{Jack, King, Queen}
import rank.numbers.odd.*
import suit.{Clovers, Hearts, Spades}

import munit.FunSuite



/**
 * Test suite for Suit functionality.
 *
 * Tests name retrieval and toString formatting for all four concrete suit types.
 */
class SuitTest extends FunSuite {

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

  test("Each suit name matches its identity") {
    assertEquals(new Hearts().name, "Hearts")
    assertEquals(new Spades().name, "Spades")
    assertEquals(new Clovers().name, "Clover")
    assertEquals(new Diamonds().name, "Diamonds")
  }

  test("Suit instances are independent — same type, same name") {
    val heartsA = new Hearts
    val heartsB = new Hearts
    assertEquals(heartsA.name, heartsB.name)
  }
}
