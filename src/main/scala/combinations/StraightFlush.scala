package cl.uchile.dcc
package combinations
import card.TCard

class StraightFlush extends ACombination {
  val chips: Int          = 100
  val multiplier: Double  = 8.0

  def isValid(cards: List[TCard]): Boolean =
    cards.size == 5 && allSameSuit(cards) && hasStraight(cards)
}
