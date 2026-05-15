package cl.uchile.dcc
package combinations
import card.TCard

class ThreeOfAKind extends ACombination {
  val chips: Int         = 30
  val multiplier: Double = 3.0

  def isValid(cards: List[TCard]): Boolean =
    cards.size >= 3 &&
      orders(cards).groupBy(identity).values.exists(_.size >= 3)
}
