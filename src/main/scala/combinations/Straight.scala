package cl.uchile.dcc
package combinations
import card.TCard

class Straight extends ACombination {
  val chips: Int         = 30
  val multiplier: Double = 4.0

  def isValid(cards: List[TCard]): Boolean =
    cards.size == 5 && hasStraight(cards)
}
