package cl.uchile.dcc
package card
import rank.Rank
import cl.uchile.dcc.suit.Suit

trait TCard extends Ordered[TCard] {
  def getName: String
  def getRank: Rank
  def getSuit: Suit
  def compare(that: TCard): Int
}


