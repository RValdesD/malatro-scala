package cl.uchile.dcc
package hand

import card.Card
import exceptions.{InvalidCidxException, InvalidJidxException}
import joker.GreedyJoker
import rank.numbers.even.Ten
import rank.numbers.figure.King
import rank.numbers.odd.Ace
import suit.{Hearts, Spades}
import munit.FunSuite

/**
 * A concrete Hand subclass that exposes the protected removeCard and
 * removeJoker methods for coverage testing purposes.
 */
class TestHand extends Hand {
  def exposeRemoveCard(index: Int): Unit  = removeCard(index)
  def exposeRemoveJoker(index: Int): Unit = removeJoker(index)
}

/**
 * Extra test suite for AHand.
 *
 * Targets the branches not reachable through the public API:
 * removeCard and removeJoker (both happy path and both guard sub-conditions),
 * and the allIndicesValid forall short-circuit branches.
 */
class HandExtraTest extends FunSuite {

  /**
   * Tests that removeCard successfully removes a card at a valid index.
   *
   * Covers the false branch of the removeCard guard (no exception thrown)
   * and the cards.remove line.
   */
  test("removeCard happy path removes the card at the given index") {
    val hand = new TestHand
    val aceOfSpades = new Card(new Ace, new Spades)
    val kingOfHeart = new Card(new King, new Hearts)
    hand.addCard(aceOfSpades)
    hand.addCard(kingOfHeart)
    hand.exposeRemoveCard(0)
    assertEquals(hand.cardCount,  1)
    assertEquals(hand.getCard(0), kingOfHeart)
  }

  /**
   * Tests that removeCard throws when given a negative index.
   *
   * Covers the left-true branch of: index < 0 || index >= cards.length.
   */
  test("removeCard throws InvalidCardIndexException for negative index") {
    val hand = new TestHand
    hand.addCard(new Card(new Ace, new Spades))
    intercept[InvalidCidxException] {
      hand.exposeRemoveCard(-1)
    }
  }

  /**
   * Tests that removeCard throws when given an index equal to card count.
   *
   * Covers the left-false + right-true branch of: index < 0 || index >= cards.length.
   */
  test("removeCard throws InvalidCardIndexException for index equal to size") {
    val hand = new TestHand
    hand.addCard(new Card(new Ace, new Spades))
    intercept[InvalidCidxException] {
      hand.exposeRemoveCard(1)
    }
  }

  /**
   * Tests that removeCard throws when given an index beyond card count.
   *
   * Covers the left-false + right-true branch with a larger out-of-range value.
   */
  test("removeCard throws InvalidCardIndexException for index beyond size") {
    val hand = new TestHand
    hand.addCard(new Card(new Ace, new Spades))
    intercept[InvalidCidxException] {
      hand.exposeRemoveCard(5)
    }
  }

  /**
   * Tests that removeJoker successfully removes a joker at a valid index.
   *
   * Covers the false branch of the removeJoker guard (no exception thrown)
   * and the jokers.remove line.
   */
  test("removeJoker happy path removes the joker at the given index") {
    val hand   = new TestHand
    val greedy = new GreedyJoker
    hand.addJoker(greedy)
    hand.exposeRemoveJoker(0)
    assertEquals(hand.jokerCount, 0)
  }

  /**
   * Tests that removeJoker throws when given a negative index.
   *
   * Covers the left-true branch of: index < 0 || index >= jokers.length.
   */
  test("removeJoker throws InvalidJokerIndexException for negative index") {
    val hand = new TestHand
    hand.addJoker(new GreedyJoker)
    intercept[InvalidJidxException] {
      hand.exposeRemoveJoker(-1)
    }
  }

  /**
   * Tests that removeJoker throws when given an index equal to joker count.
   *
   * Covers the left-false + right-true branch of: index < 0 || index >= jokers.length.
   */
  test("removeJoker throws InvalidJokerIndexException for index equal to size") {
    val hand = new TestHand
    hand.addJoker(new GreedyJoker)
    intercept[InvalidJidxException] {
      hand.exposeRemoveJoker(1)
    }
  }

  /**
   * Tests that removeJoker throws when given an index beyond joker count.
   *
   * Covers the left-false + right-true branch with a larger out-of-range value.
   */
  test("removeJoker throws InvalidJokerIndexException for index beyond size") {
    val hand = new TestHand
    hand.addJoker(new GreedyJoker)
    intercept[InvalidJidxException] {
      hand.exposeRemoveJoker(5)
    }
  }

  /**
   * Tests allIndicesValid with a negative index as the first element.
   *
   * Covers the forall short-circuit: i >= 0 is false on the first element,
   * so the right side i < cards.length is never evaluated.
   */
  test("playCards with a negative index triggers allIndicesValid short-circuit") {
    val hand = new TestHand
    hand.addCard(new Card(new Ace,  new Spades))
    hand.addCard(new Card(new King, new Hearts))
    intercept[exceptions.play.CardInvalidListidxPlayedException] {
      hand.playCards(List(-1, 0))
    }
  }

  /**
   * Tests allIndicesValid where the first index is valid but a later one is not.
   *
   * Covers the forall continuing past a valid index then short-circuiting on
   * an invalid one: i >= 0 is true, i < cards.length is false.
   */
  test("playCards with a later invalid index covers mid-list allIndicesValid branch") {
    val hand = new TestHand
    hand.addCard(new Card(new Ace,  new Spades))
    hand.addCard(new Card(new King, new Hearts))
    intercept[exceptions.play.CardInvalidListidxPlayedException] {
      hand.playCards(List(0, 5))
    }
  }

  /**
   * Tests allIndicesValid for discard with a negative index.
   *
   * Covers the same forall short-circuit branch via the discard path.
   */
  test("discardCards with a negative index triggers allIndicesValid short-circuit") {
    val hand = new TestHand
    hand.addCard(new Card(new Ten, new Hearts))
    intercept[exceptions.discard.CardInvalidListidxDiscardException] {
      hand.discardCards(List(-1))
    }
  }
}

