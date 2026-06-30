package cl.uchile.dcc
package scorecalculator

import combinations.CombinationEvaluator
import rank.classes.{EvenClass, FigureClass, OddClass}
import rank.numbers.even.*
import rank.numbers.figure.{Jack, King, Queen}
import rank.numbers.odd.*
import suit.{Clovers, Diamonds, Hearts, Spades}

import munit.FunSuite
import card.*
import joker.*
import score.Score

/**
 * Test suite for ScoreCalculator.
 *
 * Tests the full scoring pipeline: combination resolution, base score,
 * per-card contribution (including suit-level joker effects), and
 * per-joker combination-level effects.
 */
class ScoreCalculatorTest extends FunSuite {

  val calculator = new ScoreCalculator(new CombinationEvaluator)

  /**
   * Tests that a High Card hand with no jokers returns the base score
   * plus the chip value of each card.
   *
   * Verifies the simplest end-to-end calculation path.
   */
  test("calculate - High Card with no jokers adds base chips plus card values") {
    val cards  = List(new Card(new Ace, new Hearts), new Card(new King, new Spades))
    val result = calculator.calculate(cards, List())
    // base: 5 chips, x1 multiplier; + 11 (Ace) + 10 (King) = 26 chips
    assertEquals(result.chips, 26)
    assertEquals(result.multiplier, 1.0)
  }

  /**
   * Tests that a Pair with no jokers returns the correct base score and card values.
   *
   * Verifies combination resolution feeds the correct base into the calculator.
   */
  test("calculate - Pair with no jokers adds base chips plus card values") {
    val cards  = List(new Card(new King, new Hearts), new Card(new King, new Spades))
    val result = calculator.calculate(cards, List())
    // base: 10 chips, x2 multiplier; + 10 + 10 = 20 chips
    assertEquals(result.chips, 30)
    assertEquals(result.multiplier, 2.0)
  }

  /**
   * Tests that GreedyJoker adds +3 to the multiplier for each Diamond card played.
   *
   * Verifies the suit-level dispatch: Diamonds responds to GreedyJoker.
   */
  test("calculate - Pair of Diamond Kings with GreedyJoker boosts multiplier") {
    val cards  = List(new Card(new King, new Diamonds), new Card(new King, new Diamonds))
    val result = calculator.calculate(cards, List(new GreedyJoker))
    // base: 10 chips, x2 multiplier; + 10 + 10 (cards) = 30 chips
    // multiplier: 2 + 3 + 3 (one +3 per Diamond card) = 8
    assertEquals(result.chips, 30)
    assertEquals(result.multiplier, 8.0)
  }

  /**
   * Tests that GreedyJoker has no effect on non-Diamond cards.
   *
   * Verifies that the suit-level bonus is exclusive to Diamonds.
   */
  test("calculate - Pair of Heart Kings with GreedyJoker leaves multiplier unchanged") {
    val cards  = List(new Card(new King, new Hearts), new Card(new King, new Hearts))
    val result = calculator.calculate(cards, List(new GreedyJoker))
    // base: 10 chips, x2 multiplier; + 10 + 10 (cards) = 30 chips, multiplier unchanged
    assertEquals(result.chips, 30)
    assertEquals(result.multiplier, 2.0)
  }

  /**
   * Tests that EvenSteven contributes its rank-class bonus through the card pass.
   *
   * Verifies that card-level joker effects are correctly folded into the total.
   */
  test("calculate - Pair of Twos with EvenSteven adds rank-class bonus per card") {
    val cards  = List(new Card(new Two, new Hearts), new Card(new Two, new Spades))
    val result = calculator.calculate(cards, List(new EvenSteven))
    // base: 10 chips; + 2 + 2 (card values) + 4 + 4 (EvenSteven per card) = 22 chips
    assertEquals(result.chips, 22)
    assertEquals(result.multiplier, 2.0)
  }

  /**
   * Tests that ScaryFace contributes its rank-class bonus through the card pass
   * on a combination involving face cards.
   *
   * Verifies face-card detection flows correctly through the card pass.
   */
  test("calculate - Pair of Kings with ScaryFace adds figure bonus per card") {
    val cards  = List(new Card(new King, new Hearts), new Card(new King, new Spades))
    val result = calculator.calculate(cards, List(new ScaryFace))
    // base: 10 chips; + 10 + 10 (card values) + 30 + 30 (ScaryFace per card) = 90 chips
    assertEquals(result.chips, 90)
    assertEquals(result.multiplier, 2.0)
  }

  /**
   * Tests the full worked example: Straight Flush of diamonds 2-6 with
   * GreedyJoker and DeviousJoker active.
   *
   * Verifies combination resolution (Straight Flush), card value summation,
   * GreedyJoker's per-Diamond-card multiplier bonus, DeviousJoker's
   * combination-level chip bonus, and the final total calculation.
   */
  test("calculate - Straight Flush with GreedyJoker and DeviousJoker matches worked example") {
    val cards = List(
      new Card(new Two,   new Diamonds),
      new Card(new Three, new Diamonds),
      new Card(new Four,  new Diamonds),
      new Card(new Five,  new Diamonds),
      new Card(new Six,   new Diamonds)
    )
    val jokers = List(new GreedyJoker, new DeviousJoker)
    val result = calculator.calculate(cards, jokers)

    // base: 100 chips, x8 multiplier
    // + card values: 2 + 3 + 4 + 5 + 6 = 20
    // + DeviousJoker combination-level bonus: +100 (Straight Flush)
    // chips = 100 + 20 + 100 = 220
    // multiplier: 8 + 3*5 (GreedyJoker +3 per Diamond card, 5 diamonds) = 23
    // total = 220 * 23 = 5060
    assertEquals(result.chips,      220)
    assertEquals(result.multiplier, 23.0)
    assertEquals(result.total,      5060.0)
  }

  /**
   * Tests that calculate works correctly with a single played card.
   *
   * Verifies the minimum valid play size of 1 card resolves to High Card.
   */
  test("calculate - single card resolves to High Card") {
    val cards  = List(new Card(new Ace, new Spades))
    val result = calculator.calculate(cards, List())
    // base: 5 chips, x1 multiplier; + 11 (Ace) = 16 chips
    assertEquals(result.chips,      16)
    assertEquals(result.multiplier, 1.0)
  }

  /**
   * Tests that calculate works correctly with the maximum of 5 cards
   * and the maximum of 2 jokers together.
   *
   * Verifies the calculator handles the upper bound of both lists.
   */
  test("calculate - five cards and two jokers together") {
    val cards = List(
      new Card(new Two,  new Diamonds),
      new Card(new Two,  new Diamonds),
      new Card(new Two,  new Clovers),
      new Card(new King, new Hearts),
      new Card(new Ace,  new Spades)
    )
    val jokers = List(new GreedyJoker, new EvenSteven)
    val result = calculator.calculate(cards, jokers)
    // ThreeOfAKind base: 30 chips, x3 multiplier
    // card values: 2+2+2+10+11 = 27
    // EvenSteven per card on the three Twos: 4+4+4 = 12
    // GreedyJoker on 2 Diamond Twos: +3 +3 to multiplier = 3 + 6 = 9
    // chips = 30 + 27 + 12 = 69
    assertEquals(result.chips,      69)
    assertEquals(result.multiplier, 9.0)
  }

  /**
   * Tests that calculate does not mutate any input card or joker state.
   *
   * Verifies that the calculation is purely functional with respect to inputs.
   */
  test("calculate - does not mutate input cards or jokers") {
    val card   = new Card(new Ace, new Spades)
    val joker  = new GreedyJoker
    calculator.calculate(List(card), List(joker))
    assertEquals(card.getName,  "Ace of Spades")
    assertEquals(joker.getName, "Greedy Joker")
  }

  /**
   * Tests that the default no-argument constructor works correctly.
   *
   * Verifies that ScoreCalculator can be constructed without an explicit evaluator.
   */
  test("ScoreCalculator default constructor produces correct results") {
    val defaultCalculator = new ScoreCalculator()
    val cards  = List(new Card(new Ace, new Spades))
    val result = defaultCalculator.calculate(cards, List())
    assertEquals(result.chips, 16)
  }
}

