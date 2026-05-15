package cl.uchile.dcc
package card
import

trait TCombination {
  def chips: Int
  def multiplier: Double
  def isValid(cards: List[TCard]): Boolean
}
