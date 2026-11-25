package cl.uchile.dcc
package deck

import card.Card
import deck.Deck
import hand.Hand
import rank.numbers.figure.*
import rank.numbers.even.*
import rank.numbers.odd.*
import states.*
import suit.*
import munit.FunSuite

/**
 * Test suite for Deck functionality.
 *
 * Tests loading, draw, isEmpty, remaining, drawUpTo, and discard pile
 * reshuffling.
 */
class DeckTest extends FunSuite {

  /**
   * Tests that a newly loaded deck is not empty.
   *
   * Verifies the isEmpty check against a populated deck.
   */
  test("loaded deck is not empty") {
    val deck = new Deck
    deck.load(List(new Card(new Ace, new Spades)))
    assertEquals(deck.isEmpty, false)
  }

  /**
   * Tests that an unloaded deck is empty.
   *
   * Verifies the default state of a freshly constructed Deck.
   */
  test("unloaded deck is empty") {
    val deck = new Deck
    assertEquals(deck.isEmpty, true)
  }

  /**
   * Tests that remaining reflects the number of loaded cards.
   *
   * Verifies the remaining count after loading.
   */
  test("remaining reflects the number of loaded cards") {
    val deck = new Deck
    deck.load(List(new Card(new Ace, new Spades), new Card(new King, new Hearts)))
    assertEquals(deck.remaining, 2)
  }

  /**
   * Tests that draw removes and returns the top card.
   *
   * Verifies draw decreases remaining by one and returns the correct card.
   */
  test("draw removes and returns the top card") {
    val deck     = new Deck
    val topCard  = new Card(new Ace, new Spades)
    deck.load(List(topCard, new Card(new King, new Hearts)))
    val drawn = deck.draw()
    assertEquals(drawn, topCard)
    assertEquals(deck.remaining, 1)
  }

  /**
   * Tests that drawUpTo fills the hand to the target size when the deck
   * has enough cards.
   *
   * Verifies the happy path of replenishment.
   */
  test("drawUpTo fills the hand to the target size") {
    val deck = new Deck
    deck.load(List(
      new Card(new Ace,   new Spades),
      new Card(new King,  new Hearts),
      new Card(new Queen, new Clovers)
    ))
    val hand = new Hand
    deck.drawUpTo(hand, 3)
    assertEquals(hand.cardCount, 3)
    assertEquals(deck.remaining, 0)
  }

  /**
   * Tests that drawUpTo stops early if the deck runs out before reaching
   * the target size.
   *
   * Verifies the deck-exhausted edge case within drawUpTo.
   */
  test("drawUpTo stops when the deck runs out before reaching target size") {
    val deck = new Deck
    deck.load(List(new Card(new Ace, new Spades)))
    val hand = new Hand
    deck.drawUpTo(hand, 5)
    assertEquals(hand.cardCount, 1)
    assertEquals(deck.isEmpty, true)
  }

  /**
   * Tests that drawUpTo does nothing if the hand already meets the target size.
   *
   * Verifies the no-op branch when no cards need to be drawn.
   */
  test("drawUpTo does nothing if hand already meets target size") {
    val deck = new Deck
    deck.load(List(new Card(new Ace, new Spades)))
    val hand = new Hand
    hand.addCard(new Card(new King, new Hearts))
    hand.addCard(new Card(new Queen, new Clovers))
    deck.drawUpTo(hand, 2)
    assertEquals(hand.cardCount, 2)
    assertEquals(deck.remaining, 1)
  }

  /**
   * Tests that sendToDiscardPile and reshuffleFromDiscardPile together
   * return discarded cards back into the deck.
   *
   * Verifies the full DeckEmpty recovery cycle.
   */
  test("reshuffleFromDiscardPile returns discarded cards to the deck") {
    val deck = new Deck
    val cardA = new Card(new Ace,  new Spades)
    val cardB = new Card(new King, new Hearts)
    deck.sendToDiscardPile(List(cardA, cardB))
    assertEquals(deck.isEmpty, true)
    deck.reshuffleFromDiscardPile()
    assertEquals(deck.remaining, 2)
  }

  /**
   * Tests that reshuffling clears the discard pile so cards aren't
   * duplicated on a second reshuffle.
   *
   * Verifies the discard pile is emptied after reshuffling.
   */
  test("reshuffleFromDiscardPile clears the discard pile after use") {
    val deck = new Deck
    deck.sendToDiscardPile(List(new Card(new Ace, new Spades)))
    deck.reshuffleFromDiscardPile()
    deck.draw()
    deck.reshuffleFromDiscardPile()
    assertEquals(deck.remaining, 0)
  }
}

