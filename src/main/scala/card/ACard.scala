package cl.uchile.dcc
package card
import suit.Suit
import cl.uchile.dcc.rank.Rank

/**
 * Represents an abstract base for a standard playing card.
 *
 * Derives its name from the rank and suit given when it is built.
 * Equality is based only on the name of the card.
 *
 * @param rank the rank of this card.
 * @param suit the suit of this card.
 */
abstract class ACard(val rank: Rank, val suit: Suit) extends TCard {
  private val name: String = s"${rank.name} of ${suit.name}"

  /** @return the full name of this card, e.g. "Ace of Spades". */
  def getName: String = name

  /** @return the rank of this card. */
  def getRank: Rank = rank

  /** @return the suit of this card. */
  def getSuit: Suit = suit

  override def equals(o: Any): Boolean =
    o.isInstanceOf[TCard] && o.asInstanceOf[TCard].getName == this.getName

  def compare(that: TCard): Int = this.getName.compare(that.getName)
}

