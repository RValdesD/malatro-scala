package cl.uchile.dcc
package card
import cl.uchile.dcc.rank.numbers.figure.{Jack, King, Queen}
import cl.uchile.dcc.rank.numbers.odd.Ace
import cl.uchile.dcc.rank.numbers.even.{Two, Four, Six, Eight, Ten}
import cl.uchile.dcc.rank.numbers.odd.{Ace, Three, Five, Seven, Nine}
import cl.uchile.dcc.suit.{Hearts, Spades, Clovers, Diamonds}
import munit.FunSuite

/**
 * Test suite for Card functionality.
 *
 * Tests basic card operations including name retrieval, rank and suit access,
 * comparison/sorting, and equality checking for card instances.
 */
class CardTest extends FunSuite {

  /**
   * Tests that the card name is correctly derived from its rank and suit.
   *
   * Verifies that `getName` returns the expected "Rank of Suit" format.
   */
  test("Card getName returns rank and suit combined") {
    val aceOfSpades = new Card(new Ace, new Spades)
    assertEquals(aceOfSpades.getName, "Ace of Spades")
  }

  /**
   * Tests that the rank returned matches the one given at construction.
   *
   * Verifies that `getRank` returns the correct rank by checking its name.
   */
  test("Card getRank returns the correct rank") {
    val kingOfHearts = new Card(new King, new Hearts)
    assertEquals(kingOfHearts.getRank.name, "King")
  }

  /**
   * Tests that the suit returned matches the one given at construction.
   *
   * Verifies that `getSuit` returns the correct suit by checking its name.
   */
  test("Card getSuit returns the correct suit") {
    val aceOfSpades = new Card(new Ace, new Spades)
    assertEquals(aceOfSpades.getSuit.name, "Spades")
  }

  /**
   * Tests that cards are sorted alphabetically by their derived name.
   *
   * Verifies that the `Ordered` trait implementation sorts cards
   * lexicographically by their "Rank of Suit" name.
   */
  test("Card compare - sorted alphabetically by name") {
    val aceOfSpades   = new Card(new Ace,   new Spades)
    val kingOfHearts  = new Card(new King,  new Hearts)
    val queenOfClover = new Card(new Queen, new Clovers)
    val sorted = List(kingOfHearts, queenOfClover, aceOfSpades).sorted
    assertEquals(sorted, List(aceOfSpades, kingOfHearts, queenOfClover))
  }

  /**
   * Tests that cards with the same rank and suit are equal.
   *
   * Verifies that two different instances representing the same card
   * are considered equal.
   */
  test("Card equals - same rank and suit are equal") {
    val cardA = new Card(new Ace, new Spades)
    val cardB = new Card(new Ace, new Spades)
    assertEquals(cardA, cardB)
  }

  /**
   * Tests that cards with a different rank or suit are not equal.
   *
   * Verifies that the `equals` method correctly distinguishes between
   * cards with different names.
   */
  test("Card equals - different rank or suit are not equal") {
    val aceOfSpades  = new Card(new Ace,  new Spades)
    val kingOfHearts = new Card(new King, new Hearts)
    assert(!aceOfSpades.equals(kingOfHearts))
  }

  /**
   * Tests that a card is equal to itself.
   *
   * Verifies reflexivity of the `equals` method.
   */
  test("Card equals - card compared to itself is equal") {
    val aceOfSpades = new Card(new Ace, new Spades)
    assertEquals(aceOfSpades, aceOfSpades)
  }

  /**
   * Tests that a card is not equal to a non-card object.
   *
   * Verifies that the `equals` method correctly handles comparison
   * with objects of a different type.
   */
  test("Card equals - not equal to a non-card object") {
    val aceOfSpades = new Card(new Ace, new Spades)
    assertEquals(aceOfSpades.equals("not a card"), false)
  }
}

