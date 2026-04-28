package cl.uchile.dcc
package card
import suit.Suit
import cl.uchile.dcc.rank.Rank

abstract class ACard(val rank: Rank, val suit: Suit) extends TCard {
  val name: String    = s"${rank.name} of ${suit.name}"
  def getName: String = name
  def getRank: Rank   = rank
  def getSuit: Suit   = suit

  override def equals(o: Any): Boolean = o match {
    case that: TCard => this.getName == that.getName
    case _           => false
  }

  def compare(that: TCard): Int = this.getName.compare(that.getName)
}
