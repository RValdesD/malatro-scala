package cl.uchile.dcc
package combinations
import card.TCard

trait TCombination {
  def chips: Int
  def multiplier: Double
  def isValid(cards: List[TCard]): Boolean
}
