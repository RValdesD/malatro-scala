package cl.uchile.dcc
package combinations

import card.TCard
import joker.{DeviousJoker, GreedyJoker}
import score.Score

/**
 * Straight: 5 consecutive cards, any suit.
 * Base score: 30 chips, x4 multiplier.
 */
class Straight extends ACombination {
  val chips: Int         = 30
  val multiplier: Double = 4.0

  def isValid(cards: List[TCard]): Boolean =
    cards.size == 5 && hasStraight(cards)

  override def applyScoreFromGreedyJoker(joker: GreedyJoker, score: Score): Score =
    score.addChips(3)

  override def applyScoreFromDeviousJoker(joker: DeviousJoker, score: Score): Score =
    score.addChips(100)
}

