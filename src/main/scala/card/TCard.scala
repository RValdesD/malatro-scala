package cl.uchile.dcc
package card
import rank.Rank
import cl.uchile.dcc.suit.Suit

/**
 * Represents a playing card with a rank and suit.
 *
 * Cards are ordered relative to other cards through the `Ordered`
 * interface implementation.
 */
trait TCard extends Ordered[TCard]:

  /** @return name of the card */
  def getName: String

  /** @return rank of the card */
  def getRank: Rank

  /** @return suit of the card */
  def getSuit: Suit

  /**
   * Compares this card with another card.
   *
   * @param that card to compare against
   * @return
   *   negative value if this card is less than `that`,
   *   zero if both cards are equal,
   *   positive value if this card is greater than `that`
   */
  def compare(that: TCard): Int