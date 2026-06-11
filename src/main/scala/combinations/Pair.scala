package cl.uchile.dcc
package combinations
import card.TCard

/**
 * Pair: 2 or more cards sharing the same rank.
 * Base score: 10 chips, x2 multiplier.
 */
class Pair extends ACombination {
  val chips: Int         = 10
  val multiplier: Double = 2.0

  def isValid(cards: List[TCard]): Boolean =
    cards.size >= 2 &&
      orders(cards).groupBy(identity).values.exists(_.size >= 2)

  override def applyScoreFromGreedyJoker(joker: GreedyJoker, score: Score): Score =
    score.addChips(3)
}
