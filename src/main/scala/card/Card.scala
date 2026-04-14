package cl.uchile.dcc
package card
import rank.Rank
import suit.Suit

trait Card {
  def rank: Rank
  def suit: Suit
  override def toString: String = s"${rank.getClass.getSimpleName} of $suit"
}


