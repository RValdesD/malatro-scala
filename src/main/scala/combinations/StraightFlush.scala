package cl.uchile.dcc
package combinations
import card.TCard
import joker.{DeviousJoker, GreedyJoker}
import score.Score

/**
 * Straight Flush: 5 consecutive cards of the same suit.
 * Base score: 100 chips, x8 multiplier.
 */
class StraightFlush extends ACombination {
  val chips: Int         = 100
  val multiplier: Double = 8.0

  def isValid(cards: List[TCard]): Boolean =
    cards.size == 5 && allSameSuit(cards) && hasStraight(cards)

  override def applyScoreFromGreedyJoker(joker: GreedyJoker, score: Score): Score =
    score.addChips(3)

  override def applyScoreFromDeviousJoker(joker: DeviousJoker, score: Score): Score =
    score.addChips(100)
}
