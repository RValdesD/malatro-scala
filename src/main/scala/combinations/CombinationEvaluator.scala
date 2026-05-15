package cl.uchile.dcc
package combinations
import card.TCard

/**
 * Evaluates a list of cards and determines the highest-priority
 * valid card combination.
 *
 * Combination evaluation follows a predefined priority order,
 * from strongest to weakest.
 */
class CombinationEvaluator:

  /** Ordered list of combinations used during evaluation. */
  private val priority: List[TCombination] = List(
    new StraightFlush,
    new Flush,
    new Straight,
    new ThreeOfAKind,
    new Pair,
    new HighCard
  )

  /**
   * Determines the highest-ranking valid combination
   * for the given cards.
   *
   * @param cards cards to evaluate
   * @return best valid combination
   */
  def evaluate(cards: List[TCard]): TCombination =
    priority.find(_.isValid(cards)).get

