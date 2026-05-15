package cl.uchile.dcc
package hand
import card.Card
import joker.{EvenSteven, GreedyJoker, ScaryFace}
import rank.numbers.even.*
import rank.numbers.figure.{Jack, King, Queen}
import rank.numbers.odd.*
import suit.{Clovers, Diamonds, Hearts, Spades}

import munit.FunSuite
class HandTest extends FunSuite {

  /**
   * Test suite for Hand functionality.
   *
   * Tests card and joker addition, removal, index-based retrieval,
   * bounds checking, bulk collection access, and playing cards by index.
   */
  class HandTest extends FunSuite {

    /**
     * Tests that a newly created hand holds no cards or jokers.
     *
     * Verifies the initial state of both counts.
     */
    test("Hand starts with no cards or jokers") {
      val hand = new Hand
      assertEquals(hand.cardCount, 0)
      assertEquals(hand.jokerCount, 0)
    }

    /**
     * Tests that adding a card increases the card count by one.
     *
     * Verifies that `addCard` correctly appends to the card collection.
     */
    test("addCard increases card count") {
      val hand = new Hand
      hand.addCard(new Card(new Ace, new Spades))
      assertEquals(hand.cardCount, 1)
    }

    /**
     * Tests that removing a card by index decreases the card count by one.
     *
     * Verifies that `removeCard` correctly removes the card at the given index.
     */
    test("removeCard decreases card count") {
      val hand = new Hand
      hand.addCard(new Card(new Ace, new Spades))
      hand.removeCard(0)
      assertEquals(hand.cardCount, 0)
    }

    /**
     * Tests that removing a card by index removes the correct card.
     *
     * Verifies that the card at the specified index is the one removed,
     * leaving the remaining cards intact.
     */
    test("removeCard removes the card at the given index") {
      val hand = new Hand
      val aceOfSpades = new Card(new Ace, new Spades)
      val kingOfHeart = new Card(new King, new Hearts)
      hand.addCard(aceOfSpades)
      hand.addCard(kingOfHeart)
      hand.removeCard(0)
      assertEquals(hand.getCard(0), kingOfHeart)
    }

    /**
     * Tests that adding a joker increases the joker count by one.
     *
     * Verifies that `addJoker` correctly appends to the joker collection.
     */
    test("addJoker increases joker count") {
      val hand = new Hand
      hand.addJoker(new GreedyJoker)
      assertEquals(hand.jokerCount, 1)
    }

    /**
     * Tests that removing a joker by index decreases the joker count by one.
     *
     * Verifies that `removeJoker` correctly removes the joker at the given index.
     */
    test("removeJoker decreases joker count") {
      val hand = new Hand
      hand.addJoker(new GreedyJoker)
      hand.removeJoker(0)
      assertEquals(hand.jokerCount, 0)
    }

    /**
     * Tests that removing a joker by index removes the correct joker.
     *
     * Verifies that the joker at the specified index is the one removed,
     * leaving the remaining jokers intact.
     */
    test("removeJoker removes the joker at the given index") {
      val hand = new Hand
      val greedy = new GreedyJoker
      val steven = new EvenSteven
      hand.addJoker(greedy)
      hand.addJoker(steven)
      hand.removeJoker(0)
      assertEquals(hand.getJoker(0), steven)
    }

    /**
     * Tests that a placed card can be retrieved at its index.
     *
     * Verifies that `getCard` returns the correct card by index.
     */
    test("getCard retrieves the card at the correct index") {
      val hand = new Hand
      val aceOfSpades = new Card(new Ace, new Spades)
      hand.addCard(aceOfSpades)
      assertEquals(hand.getCard(0), aceOfSpades)
    }

    /**
     * Tests that a placed joker can be retrieved at its index.
     *
     * Verifies that `getJoker` returns the correct joker by index.
     */
    test("getJoker retrieves the joker at the correct index") {
      val hand = new Hand
      val steven = new EvenSteven
      hand.addJoker(steven)
      assertEquals(hand.getJoker(0), steven)
    }

    /**
     * Tests that getCard throws when the index is out of bounds.
     *
     * Verifies that accessing an empty hand raises IndexOutOfBoundsException.
     */
    test("getCard throws on out-of-bounds index") {
      val hand = new Hand
      intercept[IndexOutOfBoundsException] {
        hand.getCard(0)
      }
    }

    /**
     * Tests that getJoker throws when the index is out of bounds.
     *
     * Verifies that accessing an empty joker collection raises IndexOutOfBoundsException.
     */
    test("getJoker throws on out-of-bounds index") {
      val hand = new Hand
      intercept[IndexOutOfBoundsException] {
        hand.getJoker(0)
      }
    }

    /**
     * Tests that cards and jokers are tracked independently.
     *
     * Verifies that adding multiple cards and jokers yields the correct
     * count for each collection separately.
     */
    test("Hand holds multiple cards and jokers independently") {
      val hand = new Hand
      hand.addCard(new Card(new Ace, new Spades))
      hand.addCard(new Card(new King, new Hearts))
      hand.addCard(new Card(new Ten, new Clovers))
      hand.addJoker(new GreedyJoker)
      hand.addJoker(new ScaryFace)
      assertEquals(hand.cardCount, 3)
      assertEquals(hand.jokerCount, 2)
    }

    /**
     * Tests that getCards returns all added cards.
     *
     * Verifies that the full card collection contains every added card.
     */
    test("getCards contains all added cards") {
      val hand = new Hand
      val aceOfSpades = new Card(new Ace, new Spades)
      val kingOfHeart = new Card(new King, new Hearts)
      hand.addCard(aceOfSpades)
      hand.addCard(kingOfHeart)
      assert(hand.getCards.contains(aceOfSpades))
      assert(hand.getCards.contains(kingOfHeart))
    }

    /**
     * Tests that getJokers returns all added jokers.
     *
     * Verifies that the full joker collection contains every added joker.
     */
    test("getJokers contains all added jokers") {
      val hand = new Hand
      val greedy = new GreedyJoker
      val steven = new EvenSteven
      hand.addJoker(greedy)
      hand.addJoker(steven)
      assert(hand.getJokers.contains(greedy))
      assert(hand.getJokers.contains(steven))
    }

    /**
     * Tests that playCards returns the correct cards for the given indices.
     *
     * Verifies that the returned list matches the cards at the specified positions.
     */
    test("playCards returns the cards at the given indices") {
      val hand = new Hand
      val aceOfSpades = new Card(new Ace, new Spades)
      val twoOfHeart = new Card(new Two, new Hearts)
      val kingOfClover = new Card(new King, new Clovers)
      hand.addCard(aceOfSpades)
      hand.addCard(twoOfHeart)
      hand.addCard(kingOfClover)
      val played = hand.playCards(List(0, 2))
      assertEquals(played, List(aceOfSpades, kingOfClover))
    }

    /**
     * Tests that playCards removes the played cards from the hand.
     *
     * Verifies that the hand count decreases by the number of played cards.
     */
    test("playCards removes the played cards from the hand") {
      val hand = new Hand
      hand.addCard(new Card(new Ace, new Spades))
      hand.addCard(new Card(new Two, new Hearts))
      hand.addCard(new Card(new King, new Clovers))
      hand.playCards(List(0, 2))
      assertEquals(hand.cardCount, 1)
    }

    /**
     * Tests that playCards leaves the unplayed cards intact.
     *
     * Verifies that the card not included in the play indices remains in the hand.
     */
    test("playCards leaves the remaining cards in the hand") {
      val hand = new Hand
      val twoOfHeart = new Card(new Two, new Hearts)
      hand.addCard(new Card(new Ace, new Spades))
      hand.addCard(twoOfHeart)
      hand.addCard(new Card(new King, new Clovers))
      hand.playCards(List(0, 2))
      assertEquals(hand.getCard(0), twoOfHeart)
    }

    /**
     * Tests that playCards works correctly with a single index.
     *
     * Verifies the minimum valid play of one card.
     */
    test("playCards with a single index returns one card and removes it") {
      val hand = new Hand
      val aceOfSpades = new Card(new Ace, new Spades)
      hand.addCard(aceOfSpades)
      hand.addCard(new Card(new King, new Hearts))
      val played = hand.playCards(List(0))
      assertEquals(played, List(aceOfSpades))
      assertEquals(hand.cardCount, 1)
    }

    /**
     * Tests that playCards works correctly with five indices.
     *
     * Verifies the maximum valid play of five cards leaving the hand empty.
     */
    test("playCards with five indices empties the hand") {
      val hand = new Hand
      hand.addCard(new Card(new Ace, new Spades))
      hand.addCard(new Card(new Two, new Hearts))
      hand.addCard(new Card(new King, new Clovers))
      hand.addCard(new Card(new Queen, new Spades))
      hand.addCard(new Card(new Ten, new Hearts))
      hand.playCards(List(0, 1, 2, 3, 4))
      assertEquals(hand.cardCount, 0)
    }
  }
}