package cl.uchile.dcc
package applyscore

/**
 * Test suite for Card applyScore.
 *
 * Tests that a card correctly applies suit-level scoring, rank-class-level
 * scoring, and its own rank value across combinations of jokers.
 */
class CardApplyScoreTest extends FunSuite {

  /**
   * Tests that applyScore with no jokers adds only the rank value as chips.
   *
   * Verifies the base case: no joker effects, just pip value contribution.
   */
  test("Card applyScore with no jokers adds rank value as chips") {
    val card   = new Card(new Ten, new Hearts)
    val score  = new Score(0, 1.0)
    val result = card.applyScore(score, List())
    assertEquals(result.chips, 10)
  }

  /**
   * Tests that Ace contributes 11 chips with no jokers.
   *
   * Verifies that the Ace value of 11 is used rather than its order of 1.
   */
  test("Ace applyScore with no jokers adds 11 chips") {
    val card   = new Card(new Ace, new Spades)
    val score  = new Score(0, 1.0)
    val result = card.applyScore(score, List())
    assertEquals(result.chips, 11)
  }

  /**
   * Tests that EvenSteven adds +4 chips for an even-ranked card.
   *
   * Verifies rank-class dispatch: EvenClass responds to EvenSteven.
   */
  test("Even-ranked card applyScore with EvenSteven adds rank value plus 4 chips") {
    val card   = new Card(new Two, new Hearts)
    val score  = new Score(0, 1.0)
    val result = card.applyScore(score, List(new EvenSteven))
    assertEquals(result.chips, 6) // +4 from EvenSteven, +2 from rank value
  }

  /**
   * Tests that EvenSteven has no effect on an odd-ranked card.
   *
   * Verifies that OddClass does not respond to EvenSteven.
   */
  test("Odd-ranked card applyScore with EvenSteven adds only rank value") {
    val card   = new Card(new Ace, new Hearts)
    val score  = new Score(0, 1.0)
    val result = card.applyScore(score, List(new EvenSteven))
    assertEquals(result.chips, 11) // no EvenSteven bonus, +11 from rank value
  }

  /**
   * Tests that ScaryFace adds +30 chips for a face card.
   *
   * Verifies rank-class dispatch: FigureClass responds to ScaryFace.
   */
  test("Face card applyScore with ScaryFace adds rank value plus 30 chips") {
    val card   = new Card(new King, new Hearts)
    val score  = new Score(0, 1.0)
    val result = card.applyScore(score, List(new ScaryFace))
    assertEquals(result.chips, 40) // +30 from ScaryFace, +10 from rank value
  }

  /**
   * Tests that ScaryFace has no effect on a non-face card.
   *
   * Verifies that EvenClass and OddClass do not respond to ScaryFace.
   */
  test("Non-face card applyScore with ScaryFace adds only rank value") {
    val card   = new Card(new Ten, new Spades)
    val score  = new Score(0, 1.0)
    val result = card.applyScore(score, List(new ScaryFace))
    assertEquals(result.chips, 10)
  }

  /**
   * Tests that multiple jokers are each applied in sequence.
   *
   * Verifies that EvenSteven and ScaryFace together on a face card only
   * ScaryFace contributes since the card is a Figure, not Even.
   */
  test("Face card applyScore with EvenSteven and ScaryFace applies only ScaryFace") {
    val card   = new Card(new Queen, new Hearts)
    val score  = new Score(0, 1.0)
    val result = card.applyScore(score, List(new EvenSteven, new ScaryFace))
    assertEquals(result.chips, 40) // +30 from ScaryFace, +10 from rank value
  }

  /**
   * Tests that an even-ranked card with both EvenSteven and ScaryFace
   * applies only EvenSteven since it is not a face card.
   *
   * Verifies independent rank-class dispatch per joker.
   */
  test("Even-ranked card applyScore with EvenSteven and ScaryFace applies only EvenSteven") {
    val card   = new Card(new Ten, new Hearts)
    val score  = new Score(0, 1.0)
    val result = card.applyScore(score, List(new EvenSteven, new ScaryFace))
    assertEquals(result.chips, 14) // +4 from EvenSteven, +10 from rank value
  }

  /**
   * Tests that GreedyJoker and DeviousJoker have no effect at the card level.
   *
   * Verifies that card-level applyScore only applies suit and rank class
   * interactions — GreedyJoker and DeviousJoker operate at the combination level.
   */
  test("Card applyScore with GreedyJoker and DeviousJoker adds only rank value") {
    val card   = new Card(new Jack, new Spades)
    val score  = new Score(0, 1.0)
    val result = card.applyScore(score, List(new GreedyJoker, new DeviousJoker))
    assertEquals(result.chips, 10) // +10 from rank value only
  }

  /**
   * Tests that applyScore accumulates correctly on a non-zero starting score.
   *
   * Verifies that the incoming chip count is preserved and added to.
   */
  test("Card applyScore accumulates on a non-zero starting score") {
    val card   = new Card(new Ten, new Hearts)
    val score  = new Score(30, 4.0)
    val result = card.applyScore(score, List(new EvenSteven))
    assertEquals(result.chips, 44) // 30 + 4 (EvenSteven) + 10 (rank value)
    assertEquals(result.multiplier, 4.0)
  }

  /**
   * Tests that applyScore does not mutate the original score.
   *
   * Verifies that Score remains immutable through the card dispatch chain.
   */
  test("Card applyScore does not mutate the original score") {
    val card     = new Card(new Ten, new Hearts)
    val original = new Score(0, 1.0)
    card.applyScore(original, List(new EvenSteven))
    assertEquals(original.chips, 0)
  }
}

