package cl.uchile.dcc
package hand
import card.Card
import joker.{EvenSteven, GreedyJoker, ScaryFace}
import rank.numbers.even.*
import rank.numbers.figure.{Jack, King, Queen}
import rank.numbers.odd.*
import suit.{Clovers, Diamonds, Hearts, Spades}
import exceptions.discard.{CardInvalidListidxDiscardException, CardMaxDiscardException, CardMaxTimesDiscardException, CardMinDiscardException}
import exceptions.play.{CardInvalidListidxPlayedException, CardMaxPlayedException, CardMaxSetPlayedException, CardMinPlayedException}
import cl.uchile.dcc.exceptions.{HandFullException, JokerLimitException}
import munit.FunSuite

/**
 * Test suite for Hand functionality.
 *
 * Tests card and joker addition, index-based retrieval, bounds checking,
 * bulk collection access, playing cards by index, and exception handling
 * for capacity and invalid index violations.
 */
class HandBasicTest extends FunSuite {

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
   * Tests that getCards returns all added cards as an immutable list.
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
   * Tests that getJokers returns all added jokers as an immutable list.
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

  // ── Exception tests ────────────────────────────────────────────────────────

  /**
   * Tests that adding a ninth card throws HandFullException.
   *
   * Verifies that the 8-card limit is enforced by addCard.
   */
  test("addCard throws HandFullException when hand holds 8 cards") {
    val hand = new Hand
    for (_ <- 1 to 8) hand.addCard(new Card(new Ace, new Spades))
    intercept[HandFullException] {
      hand.addCard(new Card(new King, new Hearts))
    }
  }

  /**
   * Tests that the hand correctly accepts exactly 8 cards without throwing.
   *
   * Verifies the boundary just before the limit is reached.
   */
  test("addCard does not throw when adding exactly 8 cards") {
    val hand = new Hand
    for (_ <- 1 to 8) hand.addCard(new Card(new Ace, new Spades))
    assertEquals(hand.cardCount, 8)
  }

  /**
   * Tests that adding a third joker throws JokerLimitException.
   *
   * Verifies that the 2-joker limit is enforced by addJoker.
   */
  test("addJoker throws JokerLimitException when hand holds 2 jokers") {
    val hand = new Hand
    hand.addJoker(new GreedyJoker)
    hand.addJoker(new EvenSteven)
    intercept[JokerLimitException] {
      hand.addJoker(new ScaryFace)
    }
  }

  /**
   * Tests that the hand correctly accepts exactly 2 jokers without throwing.
   *
   * Verifies the boundary just before the joker limit is reached.
   */
  test("addJoker does not throw when adding exactly 2 jokers") {
    val hand = new Hand
    hand.addJoker(new GreedyJoker)
    hand.addJoker(new EvenSteven)
    assertEquals(hand.jokerCount, 2)
  }


  /**
   * Tests that playing an empty index list throws CardMinPlayedException.
   *
   * Verifies the minimum play count of 1 is enforced.
   */
  test("playCards throws CardMinPlayedException for empty index list") {
    val hand = new Hand
    hand.addCard(new Card(new Ace, new Spades))
    intercept[CardMinPlayedException] {
      hand.playCards(List())
    }
  }


  /**
   * Tests that playing more than 5 indices throws CardMaxPlayedException.
   *
   * Verifies the maximum play count of 5 is enforced.
   */
  test("playCards throws CardMaxPlayedException for more than 5 indices") {
    val hand = new Hand
    for (_ <- 1 to 6) hand.addCard(new Card(new Ace, new Spades))
    intercept[CardMaxPlayedException] {
      hand.playCards(List(0, 1, 2, 3, 4, 5))
    }
  }

  /**
   * Tests that playing an out-of-range index throws CardInvalidListidxPlayedException.
   *
   * Verifies that unordered invalid indices are caught correctly.
   */
  test("playCards throws CardInvalidListidxPlayedException for out-of-range index") {
    val hand = new Hand
    hand.addCard(new Card(new Ace, new Spades))
    hand.addCard(new Card(new King, new Hearts))
    intercept[CardInvalidListidxPlayedException] {
      hand.playCards(List(1, 5))
    }
  }

  /**
   * Tests that playing a fourth time throws CardMaxSetPlayedException.
   *
   * Verifies the 3-play limit is enforced across consecutive calls.
   */
  test("playCards throws CardMaxSetPlayedException after 3 plays") {
    val hand = new Hand
    for (_ <- 1 to 8) hand.addCard(new Card(new Ace, new Spades))
    hand.playCards(List(0))
    hand.playCards(List(0))
    hand.playCards(List(0))
    intercept[CardMaxSetPlayedException] {
      hand.playCards(List(0))
    }
  }

  /**
   * Tests that timesPlayed increments correctly after each play.
   *
   * Verifies the play counter is accessible and accurate.
   */
  test("timesPlayed increments after each playCards call") {
    val hand = new Hand
    for (_ <- 1 to 8) hand.addCard(new Card(new Ace, new Spades))
    hand.playCards(List(0))
    hand.playCards(List(0))
    assertEquals(hand.timesPlayed, 2)
  }

  // ── discardCards tests ─────────────────────────────────────────────────────

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