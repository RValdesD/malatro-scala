package cl.uchile.dcc
package combinations
import card.TCard


class HighCard extends ACombination {
  val chips: Int         = 5
  val multiplier: Double = 1.0

  def isValid(cards: List[TCard]): Boolean = cards.nonEmpty
}
