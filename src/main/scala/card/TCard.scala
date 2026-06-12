package cl.uchile.dcc
package card
import rank.Rank
import cl.uchile.dcc.suit.Suit

/**
 * Represents a playing card with a rank and a suit.
 *
 * Cards are ordered and comparable by their derived name, and participate
 * in score calculation via double dispatch with a list of jokers.
 */
trait TCard extends Ordered[TCard] {
  /** @return the full name of the card, e.g. "Ace of Spades". */
  def getName: String

  /** @return the rank of this card. */
  def getRank: Rank

  /** @return the suit of this card. */
  def getSuit: Suit

  def compare(that: TCard): Int

  /**
   * Applies this card's contribution to the given score.
   *
   * Applies each joker's effect through the suit, then through the rank class,
   * then adds the rank's pip value as chips.
   *
   * @param score  the current score.
   * @param jokers the list of active jokers to apply.
   * @return the updated Score.
   */
  def applyScore(score: Score, jokers: List[TJoker]): Score
}
