package cl.uchile.dcc
package combinations
import card.TCard

class Pair extends ACombination {
  val chips: Int         = 10
  val multiplier: Double = 2.0

  def isValid(cards: List[TCard]): Boolean =
    cards.size >= 2 &&
      orders(cards).groupBy(identity).values.exists(_.size >= 2)
}
