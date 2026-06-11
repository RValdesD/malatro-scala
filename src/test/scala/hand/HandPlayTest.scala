package cl.uchile.dcc
package hand

import card.Card
import rank.numbers.even.{Ten, Two}
import rank.numbers.figure.{King, Queen}
import rank.numbers.odd.Ace
import suit.{Clovers, Hearts, Spades}
import munit.FunSuite

class HandPlayTest extends FunSuite{
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

  /**
   * Tests that playCards preserves the order of the indices given.
   *
   * Verifies that the returned list reflects the caller's index order,
   * not the sorted removal order.
   */
  test("playCards preserves order of indices given") {
    val hand = new Hand
    val aceOfSpades = new Card(new Ace, new Spades)
    val kingOfHeart = new Card(new King, new Hearts)
    val tenOfClover = new Card(new Ten, new Clovers)
    hand.addCard(aceOfSpades)
    hand.addCard(kingOfHeart)
    hand.addCard(tenOfClover)
    val played = hand.playCards(List(2, 0))
    assertEquals(played, List(tenOfClover, aceOfSpades))
  }

  /**
   * Tests that adding a card after playing keeps the correct order.
   *
   * Verifies that the collection remains consistent after interleaved
   * play and add operations.
   */
  test("addCard after playCards keeps correct order") {
    val hand = new Hand
    val aceOfSpades = new Card(new Ace, new Spades)
    val kingOfHeart = new Card(new King, new Hearts)
    val tenOfClover = new Card(new Ten, new Clovers)
    hand.addCard(aceOfSpades)
    hand.addCard(kingOfHeart)
    hand.playCards(List(0))
    hand.addCard(tenOfClover)
    assertEquals(hand.getCard(0), kingOfHeart)
    assertEquals(hand.getCard(1), tenOfClover)
  }
}
