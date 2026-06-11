package cl.uchile.dcc
package combinations

import card.TCard
import joker.GreedyJoker
import score.Score


/**
 * High Card: any non-empty hand.
 * Base score: 5 chips, x1 multiplier.
 */
class HighCard extends ACombination {
  val chips: Int = 5
  val multiplier: Double = 1.0

  def isValid(cards: List[TCard]): Boolean = cards.nonEmpty

  override def applyScoreFromGreedyJoker(joker: GreedyJoker, score: Score): Score =
    score.addChips(3)
}

