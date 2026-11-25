package cl.uchile.dcc
package hand
import card.Card
import exceptions.discard.{CardInvalidListidxDiscardException, CardMaxDiscardException, CardMaxTimesDiscardException, CardMinDiscardException}
import rank.numbers.even.Ten
import rank.numbers.figure.King
import rank.numbers.odd.Ace
import suit.{Clovers, Hearts, Spades}
import munit.FunSuite

/**
 * Test suite for Hand discard functionality.
 * 
 * Tests discardCards behavior including correct removal, preservation of
 * remaining cards, counter tracking, and all discard-related exceptions.
 */

class HandDiscardTest extends FunSuite{
  /**
   * Tests that discardCards removes the cards at the given indices.
   *
   * Verifies the card count decreases correctly after a discard.
   */
  test("discardCards removes the cards at the given indices") {
    val hand = new Hand
    hand.addCard(new Card(new Ace, new Spades))
    hand.addCard(new Card(new King, new Hearts))
    hand.addCard(new Card(new Ten, new Clovers))
    hand.discardCards(List(0, 2))
    assertEquals(hand.cardCount, 1)
  }

  /**
   * Tests that discardCards leaves the remaining cards intact.
   *
   * Verifies that the card not in the discard list is still in the hand.
   */
  test("discardCards leaves the remaining cards intact") {
    val hand = new Hand
    val kingOfHeart = new Card(new King, new Hearts)
    hand.addCard(new Card(new Ace, new Spades))
    hand.addCard(kingOfHeart)
    hand.addCard(new Card(new Ten, new Clovers))
    hand.discardCards(List(2, 0))
    assertEquals(hand.getCard(0), kingOfHeart)
  }

  /**
   * Tests that discarding an empty index list throws CardMinDiscardException.
   *
   * Verifies the minimum discard count of 1 is enforced.
   */
  test("discardCards throws CardMinDiscardException for empty index list") {
    val hand = new Hand
    hand.addCard(new Card(new Ace, new Spades))
    intercept[CardMinDiscardException] {
      hand.discardCards(List())
    }
  }

  /**
   * Tests that discarding more than 5 indices throws CardMaxDiscardException.
   *
   * Verifies the maximum discard count of 5 is enforced.
   */
  test("discardCards throws CardMaxDiscardException for more than 5 indices") {
    val hand = new Hand
    for (_ <- 1 to 6) hand.addCard(new Card(new Ace, new Spades))
    intercept[CardMaxDiscardException] {
      hand.discardCards(List(0, 1, 2, 3, 4, 5))
    }
  }

  /**
   * Tests that discarding an out-of-range index throws CardInvalidListidxDiscardException.
   *
   * Verifies that unordered invalid indices are caught correctly.
   */
  test("discardCards throws CardInvalidListidxDiscardException for out-of-range index") {
    val hand = new Hand
    hand.addCard(new Card(new Ace, new Spades))
    hand.addCard(new Card(new King, new Hearts))
    intercept[CardInvalidListidxDiscardException] {
      hand.discardCards(List(1, 5))
    }
  }

  /**
   * Tests that discarding a fourth time throws CardMaxTimesDiscardException.
   *
   * Verifies the 3-discard limit is enforced across consecutive calls.
   */
  test("discardCards throws CardMaxTimesDiscardException after 3 discards") {
    val hand = new Hand
    for (_ <- 1 to 8) hand.addCard(new Card(new Ace, new Spades))
    hand.discardCards(List(0))
    hand.discardCards(List(0))
    hand.discardCards(List(0))
    intercept[CardMaxTimesDiscardException] {
      hand.discardCards(List(0))
    }
  }

  /**
   * Tests that timesDiscarded increments correctly after each discard.
   *
   * Verifies the discard counter is accessible and accurate.
   */
  test("timesDiscarded increments after each discardCards call") {
    val hand = new Hand
    for (_ <- 1 to 8) hand.addCard(new Card(new Ace, new Spades))
    hand.discardCards(List(0))
    hand.discardCards(List(0))
    assertEquals(hand.timesDiscarded, 2)
  }
}

