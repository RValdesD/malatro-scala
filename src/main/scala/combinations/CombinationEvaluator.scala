package cl.uchile.dcc
package combinations
import card.TCard

class CombinationEvaluator {

  private val priority: List[TCombination] = List(
    new StraightFlush,
    new Flush,
    new Straight,
    new ThreeOfAKind,
    new Pair,
    new HighCard
  )

  def evaluate(cards: List[TCard]): TCombination =
    priority.find(_.isValid(cards)).get
}
 
