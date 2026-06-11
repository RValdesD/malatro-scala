package cl.uchile.dcc
package combinations

import card.TCard
import joker.GreedyJoker
import score.Score

/**
 * Three of a Kind: 3 or more cards sharing the same rank.
 * Base score: 30 chips, x3 multiplier.
 */
class ThreeOfAKind extends ACombination {
  val chips: Int         = 30
  val multiplier: Double = 3.0

  def isValid(cards: List[TCard]): Boolean =
    cards.size >= 3 &&
      orders(cards).groupBy(identity).values.exists(_.size >= 3)

  override def applyScoreFromGreedyJoker(joker: GreedyJoker, score: Score): Score =
    score.addChips(3)
}

