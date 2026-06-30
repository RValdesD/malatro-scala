package cl.uchile.dcc
package scorecalculator
import combinations.CombinationEvaluator
import card.TCard
import joker.TJoker
import score.Score

/**
 * Calculates the final score for a played set of cards and active jokers.
 *
 * The calculation proceeds in three stages:
 *   1. The highest-priority valid combination is determined from the cards.
 *   2. The combination's base score (chips, multiplier) is taken as the start.
 *   3. Each card applies its own contribution (suit, rank class, rank value)
 *      to the chip total, then each joker's combination-level effect is applied.
 *
 * @param evaluator the combination evaluator used to resolve the best combination.
 */
class ScoreCalculator(private val evaluator: CombinationEvaluator) {

  /**
   * Default constructor using a fresh CombinationEvaluator.
   */
  def this() = this(new CombinationEvaluator)

  /**
   * Calculates the final score for the given cards and jokers.
   *
   * @param cards  a list of 1 to 5 played cards.
   * @param jokers a list of 1 to 2 active jokers.
   * @return the final Score after applying the combination, card, and joker contributions.
   */
  def calculate(cards: List[TCard], jokers: List[TJoker]): Score = {
    val combination = evaluator.evaluate(cards)
    var score = new Score(combination.chips, combination.multiplier)

    var i = 0
    while (i < cards.size) {
      score = cards(i).applyScore(score, jokers)
      i += 1
    }

    var j = 0
    while (j < jokers.size) {
      score = combination.applyScore(score, jokers(j))
      j += 1
    }

    score
  }
}
 

