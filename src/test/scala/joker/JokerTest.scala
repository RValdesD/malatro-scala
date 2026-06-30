package cl.uchile.dcc
package joker
import munit.FunSuite


/**
 * Test suite for Joker functionality.
 *
 * Tests joker name and description retrieval, equality, ordering,
 * and toString formatting across all four concrete joker types.
 */
class JokerTest extends FunSuite {

  /**
   * Tests that GreedyJoker returns the correct name and description.
   *
   * Verifies both `getName` and `description` for the GreedyJoker type.
   */
  test("GreedyJoker getName and description") {
    val greedy = new GreedyJoker
    assertEquals(greedy.getName, "Greedy Joker")
    assertEquals(greedy.description, "Played Diamond cards each give +3 multiplier")
  }

  /**
   * Tests that DeviousJoker returns the correct name and description.
   *
   * Verifies both `getName` and `description` for the DeviousJoker type.
   */
  test("DeviousJoker getName and description") {
    val devious = new DeviousJoker
    assertEquals(devious.getName, "Devious Joker")
    assertEquals(devious.description, "+100 chips if hand contains a Straight")
  }

  /**
   * Tests that EvenSteven returns the correct name and description.
   *
   * Verifies both `getName` and `description` for the EvenSteven type.
   */
  test("EvenSteven getName and description") {
    val steven = new EvenSteven
    assertEquals(steven.getName, "Even Steven")
    assertEquals(steven.description, "Played even-ranked cards each give +4 chips")
  }

  /**
   * Tests that ScaryFace returns the correct name and description.
   *
   * Verifies both `getName` and `description` for the ScaryFace type.
   */
  test("ScaryFace getName and description") {
    val scary = new ScaryFace
    assertEquals(scary.getName, "Scary Face")
    assertEquals(scary.description, "Played face cards each give +30 chips")
  }

  /**
   * Tests that two jokers of the same type are equal.
   *
   * Verifies that equality is based on name, not instance identity.
   */
  test("Joker equals - same name means equal") {
    val greedyA = new GreedyJoker
    val greedyB = new GreedyJoker
    assertEquals(greedyA, greedyB)
  }

  /**
   * Tests that jokers of different types are not equal.
   *
   * Verifies that the `equals` method distinguishes between jokers
   * with different names.
   */
  test("Joker equals - different jokers are not equal") {
    val greedy = new GreedyJoker
    val scary = new ScaryFace
    assert(!greedy.equals(scary))
  }

  /**
   * Tests that jokers are sorted alphabetically by name.
   *
   * Verifies the `Ordered` trait implementation across all four joker types.
   */
  test("Joker compare - sorted alphabetically by name") {
    val sorted = List(new ScaryFace, new GreedyJoker, new EvenSteven, new DeviousJoker).sorted
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
    val scary = new ScaryFace
    assert(scary.toString.contains("Scary Face"))
    assert(scary.toString.contains("Played face cards each give +30 chips"))
  }

  /**
   * Tests the < operator: true when this joker sorts before the other.
   *
   * Covers the true branch of <.
   */
  test("Joker < returns true when this joker sorts before the other") {
    val devious = new DeviousJoker
    val greedy = new GreedyJoker
    assert(devious < greedy)
  }

  /**
   * Tests the < operator: false when this joker sorts after the other.
   *
   * Covers the false branch of <.
   */
  test("Joker < returns false when this joker sorts after the other") {
    val greedy = new GreedyJoker
    val devious = new DeviousJoker
    assert(!(greedy < devious))
  }

  /**
   * Tests the > operator: true when this joker sorts after the other.
   *
   * Covers the true branch of >.
   */
  test("Joker > returns true when this joker sorts after the other") {
    val greedy = new GreedyJoker
    val devious = new DeviousJoker
    assert(greedy > devious)
  }

  /**
   * Tests the > operator: false when this joker sorts before the other.
   *
   * Covers the false branch of >.
   */
  test("Joker > returns false when this joker sorts before the other") {
    val devious = new DeviousJoker
    val greedy = new GreedyJoker
    assert(!(devious > greedy))
  }

  /**
   * Tests the <= operator: true when jokers are equal.
   *
   * Covers the true branch of <= at equality.
   */
  test("Joker <= returns true for equal jokers") {
    val greedyA = new GreedyJoker
    val greedyB = new GreedyJoker
    assert(greedyA <= greedyB)
  }

  /**
   * Tests the >= operator: true when jokers are equal.
   *
   * Covers the true branch of >= at equality.
   */
  test("Joker >= returns true for equal jokers") {
    val greedyA = new GreedyJoker
    val greedyB = new GreedyJoker
    assert(greedyA >= greedyB)
  }
}



