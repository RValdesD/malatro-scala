package cl.uchile.dcc
package card

import joker.{EvenSteven, ScaryFace}
import rank.numbers.even.{Ten, Two}
import rank.numbers.figure.{Jack, King, Queen}
import rank.numbers.odd.Ace
import score.Score
import suit.{Clovers, Hearts, Spades}
import munit.FunSuite

/**
 * Test suite for Card functionality.
 *
 * Tests basic card operations including name retrieval, rank and suit access,
 * comparison/sorting, equality checking, and applyScore double dispatch
 * across all methods, lines, and branches of ACard.
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
   * Tests getName for a different rank and suit combination.
   *
   * Verifies the name derivation is not hardcoded.
   */
  test("Card getName reflects rank and suit given at construction") {
    val kingOfHearts = new Card(new King, new Hearts)
    assertEquals(kingOfHearts.getName, "King of Hearts")
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
    val aceOfSpades = new Card(new Ace, new Spades)
    val kingOfHearts = new Card(new King, new Hearts)
    val queenOfClover = new Card(new Queen, new Clovers)
    val sorted = List(kingOfHearts, queenOfClover, aceOfSpades).sorted
    assertEquals(sorted, List(aceOfSpades, kingOfHearts, queenOfClover))
  }

  /**
   * Tests that compare returns a negative value when this card sorts before the other.
   *
   * Covers the less-than branch of compare.
   */
  test("Card compare - returns negative when this card sorts before the other") {
    val aceOfSpades = new Card(new Ace, new Spades)
    val kingOfSpades = new Card(new King, new Spades)
    assert(aceOfSpades.compare(kingOfSpades) < 0)
  }

  /**
   * Tests that compare returns zero for two cards with the same name.
   *
   * Covers the equal branch of compare.
   */
  test("Card compare - returns zero for cards with the same name") {
    val cardA = new Card(new Ace, new Spades)
    val cardB = new Card(new Ace, new Spades)
    assertEquals(cardA.compare(cardB), 0)
  }

  /**
   * Tests that compare returns a positive value when this card sorts after the other.
   *
   * Covers the greater-than branch of compare.
   */
  test("Card compare - returns positive when this card sorts after the other") {
    val kingOfSpades = new Card(new King, new Spades)
    val aceOfSpades = new Card(new Ace, new Spades)
    assert(kingOfSpades.compare(aceOfSpades) > 0)
  }

  /**
   * Tests that cards with the same rank and suit are equal.
   *
   * Covers the branch: isInstanceOf true AND names match.
   */
  test("Card equals - same rank and suit are equal") {
    val cardA = new Card(new Ace, new Spades)
    val cardB = new Card(new Ace, new Spades)
    assertEquals(cardA, cardB)
  }

  /**
   * Tests that cards with a different rank or suit are not equal.
   *
   * Covers the branch: isInstanceOf true AND names do not match.
   */
  test("Card equals - different rank or suit are not equal") {
    val aceOfSpades = new Card(new Ace, new Spades)
    val kingOfHearts = new Card(new King, new Hearts)
    assert(!aceOfSpades.equals(kingOfHearts))
  }

  /**
   * Tests that a card is equal to itself.
   *
   * Covers the reflexive case: same instance, isInstanceOf true, names match.
   */
  test("Card equals - card compared to itself is equal") {
    val aceOfSpades = new Card(new Ace, new Spades)
    assertEquals(aceOfSpades, aceOfSpades)
  }

  /**
   * Tests that a card is not equal to a non-card object.
   *
   * Covers the branch: isInstanceOf false (short-circuit).
   */
  test("Card equals - not equal to a non-card object") {
    val aceOfSpades = new Card(new Ace, new Spades)
    assertEquals(aceOfSpades.equals("not a card"), false)
  }

  /**
   * Tests that a card is not equal to null.
   *
   * Covers the branch: isInstanceOf false for null input.
   */
  test("Card equals - not equal to null") {
    val aceOfSpades = new Card(new Ace, new Spades)
    assertEquals(aceOfSpades.equals(null), false)
  }

  /**
   * Tests that applyScore with an empty joker list skips both while loops.
   *
   * Covers the branch where jokers.size == 0: neither loop body executes,
   * only the final addChips call runs.
   */
  test("Card applyScore - empty joker list adds only rank value") {
    val card = new Card(new Ten, new Hearts)
    val score = new Score(0, 1.0)
    val result = card.applyScore(score, List())
    assertEquals(result.chips, 10)
  }

  /**
   * Tests that Ace contributes 11 chips with no jokers.
   *
   * Verifies that rank value (11) is used rather than order (1).
   */
  test("Card applyScore - Ace adds 11 chips with no jokers") {
    val card = new Card(new Ace, new Spades)
    val score = new Score(0, 1.0)
    val result = card.applyScore(score, List())
    assertEquals(result.chips, 11)
  }

  /**
   * Tests that applyScore with one joker executes each loop exactly once.
   *
   * EvenSteven adds +4 on an even-ranked card (Two) via rank class dispatch,
   * then rank value +2 is added.
   */
  test("Card applyScore - EvenSteven adds 4 chips for even-ranked card") {
    val card = new Card(new Two, new Hearts)
    val score = new Score(0, 1.0)
    val result = card.applyScore(score, List(new EvenSteven))
    assertEquals(result.chips, 6)
  }

  /**
   * Tests that EvenSteven has no effect on an odd-ranked card.
   *
   * Covers the rank class dispatch branch where OddClass ignores EvenSteven.
   */
  test("Card applyScore - EvenSteven has no effect on odd-ranked card") {
    val card = new Card(new Ace, new Hearts)
    val score = new Score(0, 1.0)
    val result = card.applyScore(score, List(new EvenSteven))
    assertEquals(result.chips, 11)
  }

  /**
   * Tests that ScaryFace adds +30 chips for a face card.
   *
   * Covers the rank class dispatch branch where FigureClass responds to ScaryFace.
   */
  test("Card applyScore - ScaryFace adds 30 chips for face card") {
    val card = new Card(new Jack, new Hearts)
    val score = new Score(0, 1.0)
    val result = card.applyScore(score, List(new ScaryFace))
    assertEquals(result.chips, 40)
  }

  /**
   * Tests that ScaryFace has no effect on a non-face card.
   *
   * Covers the rank class dispatch branch where EvenClass ignores ScaryFace.
   */
  test("Card applyScore - ScaryFace has no effect on non-face card") {
    val card = new Card(new Ten, new Spades)
    val score = new Score(0, 1.0)
    val result = card.applyScore(score, List(new ScaryFace))
    assertEquals(result.chips, 10)
  }

  /**
   * Tests that applyScore with two jokers iterates both loops twice.
   *
   * Covers the branch where jokers.size > 1. EvenSteven adds +4 on Two,
   * ScaryFace adds nothing on an even card.
   */
  test("Card applyScore - two jokers iterate both loops twice") {
    val card = new Card(new Two, new Hearts)
    val score = new Score(0, 1.0)
    val result = card.applyScore(score, List(new EvenSteven, new ScaryFace))
    assertEquals(result.chips, 6)
  }

  /**
   * Tests that two instances of the same joker each apply their effect.
   *
   * Covers multiple loop iterations with accumulation.
   */
  test("Card applyScore - two EvenSteven jokers each add 4 chips") {
    val card = new Card(new Two, new Hearts)
    val score = new Score(10, 2.0)
    val result = card.applyScore(score, List(new EvenSteven, new EvenSteven))
    assertEquals(result.chips, 20)
    assertEquals(result.multiplier, 2.0)
  }

  /**
   * Tests that the suit applyScore pass executes without affecting the score.
   *
   * Covers the suit.applyScore line — suits currently return score unchanged,
   * verifying the line executes as a no-op before rank class dispatch.
   */
  test("Card applyScore - suit pass executes without affecting score") {
    val card = new Card(new Ten, new Spades)
    val score = new Score(5, 1.0)
    val result = card.applyScore(score, List(new EvenSteven))
    assertEquals(result.chips, 19)
  }

  /**
   * Tests that applyScore does not mutate the original score.
   *
   * Verifies that every addChips and applyScore call returns a new Score,
   * leaving the original unchanged.
   */
  test("Card applyScore - does not mutate the original score") {
    val card = new Card(new Two, new Hearts)
    val original = new Score(0, 1.0)
    card.applyScore(original, List(new EvenSteven))
    assertEquals(original.chips, 0)
    assertEquals(original.multiplier, 1.0)
  }

  /**
   * Tests the first while loop exit: condition re-evaluates to false after one iteration.
   *
   * With exactly one joker that has no suit effect, the suit loop body executes
   * once and then the condition i < jokers.size is false, covering the loop-exit branch.
   */
  test("Card applyScore - first while loop exits after one iteration") {
    val card = new Card(new Ten, new Hearts)
    val score = new Score(0, 1.0)
    val result = card.applyScore(score, List(new ScaryFace))
    assertEquals(result.chips, 10)
  }

  /**
   * Tests the second while loop exit: condition re-evaluates to false after one iteration.
   *
   * With exactly one joker that has no rank class effect, the rank class loop body
   * executes once and then j < jokers.size is false, covering the loop-exit branch.
   */
  test("Card applyScore - second while loop exits after one iteration") {
    val card = new Card(new Ace, new Spades)
    val score = new Score(0, 1.0)
    val result = card.applyScore(score, List(new ScaryFace))
    assertEquals(result.chips, 11)
  }

  /**
   * Tests the equals && short-circuit: right-hand side is unreachable when isInstanceOf is false.
   *
   * Passing an Int (not a TCard) confirms isInstanceOf evaluates to false and
   * the getName comparison on the right is never reached, covering that branch.
   */
  test("Card equals - short-circuits on non-TCard without evaluating getName") {
    val card = new Card(new Ace, new Spades)
    assertEquals(card.equals(42), false)
  }

  /**
   * Tests the <= operator from Ordered: true when this card sorts before the other.
   *
   * Covers the <= branch where compare returns negative.
   */
  test("Card <= returns true when this card sorts before the other") {
    val aceOfSpades = new Card(new Ace, new Spades)
    val kingOfSpades = new Card(new King, new Spades)
    assert(aceOfSpades <= kingOfSpades)
  }

  /**
   * Tests the >= operator from Ordered: true when cards are equal.
   *
   * Covers the >= branch where compare returns 0.
   */
  test("Card >= returns true for equal cards") {
    val cardA = new Card(new Ace, new Spades)
    val cardB = new Card(new Ace, new Spades)
    assert(cardA >= cardB)
  }

  /**
   * Tests the >= operator from Ordered: true when this card sorts after the other.
   *
   * Covers the >= branch where compare returns positive.
   */
  test("Card >= returns true when this card sorts after the other") {
    val kingOfSpades = new Card(new King, new Spades)
    val aceOfSpades = new Card(new Ace, new Spades)
    assert(kingOfSpades >= aceOfSpades)
  }
}


