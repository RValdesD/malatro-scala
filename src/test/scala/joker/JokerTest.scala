package cl.uchile.dcc
package joker
import munit.FunSuite

import munit.FunSuite

/**
 * Test suite for Joker functionality.
 *
 * Tests joker name and description retrieval, equality, ordering,
 * and toString formatting across all four concrete joker types.
 */
class JokerTest extends FunSuite {
  var greedy:  GreedyJoker  = _
  var devious: DeviousJoker = _
  var steven:  EvenSteven   = _
  var scary:   ScaryFace    = _

  override def beforeEach(context: BeforeEach): Unit = {
    greedy  = new GreedyJoker
    devious = new DeviousJoker
    steven  = new EvenSteven
    scary   = new ScaryFace
  }

  /**
   * Tests that GreedyJoker returns the correct name and description.
   *
   * Verifies both `getName` and `description` for the GreedyJoker type.
   */
  test("GreedyJoker getName and description") {
    assertEquals(greedy.getName,     "Greedy Joker")
    assertEquals(greedy.description, "Played cards each give +3 chips")
  }

  /**
   * Tests that DeviousJoker returns the correct name and description.
   *
   * Verifies both `getName` and `description` for the DeviousJoker type.
   */
  test("DeviousJoker getName and description") {
    assertEquals(devious.getName,     "Devious Joker")
    assertEquals(devious.description, "+100 chips if hand contains a Straight")
  }

  /**
   * Tests that EvenSteven returns the correct name and description.
   *
   * Verifies both `getName` and `description` for the EvenSteven type.
   */
  test("EvenSteven getName and description") {
    assertEquals(steven.getName,     "Even Steven")
    assertEquals(steven.description, "Played even-ranked cards each give +4 chips")
  }

  /**
   * Tests that ScaryFace returns the correct name and description.
   *
   * Verifies both `getName` and `description` for the ScaryFace type.
   */
  test("ScaryFace getName and description") {
    assertEquals(scary.getName,     "Scary Face")
    assertEquals(scary.description, "Played face cards each give +30 chips")
  }

  /**
   * Tests that two jokers of the same type are equal.
   *
   * Verifies that equality is based on name, not instance identity.
   */
  test("Joker equals - same name means equal") {
    assertEquals(greedy, new GreedyJoker)
  }

  /**
   * Tests that jokers of different types are not equal.
   *
   * Verifies that the `equals` method distinguishes between jokers
   * with different names.
   */
  test("Joker equals - different jokers are not equal") {
    assert(!greedy.equals(scary))
  }

  /**
   * Tests that jokers are sorted alphabetically by name.
   *
   * Verifies the `Ordered` trait implementation across all four joker types.
   */
  test("Joker compare - sorted alphabetically by name") {
    val sorted = List(scary, greedy, steven, devious).sorted
    assertEquals(
      sorted.map(_.getName),
      List("Devious Joker", "Even Steven", "Greedy Joker", "Scary Face")
    )
  }

  /**
   * Tests that toString includes both the name and the description.
   *
   * Verifies the format produced by `AJoker.toString`.
   */
  test("Joker toString includes name and description") {
    assert(scary.toString.contains("Scary Face"))
    assert(scary.toString.contains("Played face cards each give +30 chips"))
  }
}

