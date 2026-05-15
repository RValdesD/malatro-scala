package cl.uchile.dcc
package combinations
import card.TCard

class Flush extends ACombination {
  val chips: Int         = 35
  val multiplier: Double = 4.0

  def isValid(cards: List[TCard]): Boolean =
    cards.size == 5 && allSameSuit(cards)
}
