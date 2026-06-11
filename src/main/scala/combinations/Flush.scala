package cl.uchile.dcc
package combinations
import card.TCard
import joker.GreedyJoker
import score.Score

/**
 * Flush: 5 cards sharing a suit, any order.
 * Base score: 35 chips, x4 multiplier.
 */
class Flush extends ACombination {
  val chips: Int         = 35
  val multiplier: Double = 4.0

  def isValid(cards: List[TCard]): Boolean =
    cards.size == 5 && allSameSuit(cards)

  override def applyScoreFromGreedyJoker(joker: GreedyJoker, score: Score): Score =
    score.addChips(3)
}
