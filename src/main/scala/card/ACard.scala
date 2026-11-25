package cl.uchile.dcc
package card

import suit.Suit
import joker.TJoker
import rank.Rank
import score.Score

/**
 * Abstract base for a standard playing card.
 *
 * Derives its name from the rank and suit given at construction.
 * Equality is based solely on the card name.
 *
 * Score is calculated by applying each joker through the suit,
 * then through the rank class, then adding the rank's pip value as chips.
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
    o.isInstanceOf[TCard] && o.asInstanceOf[TCard].getName.equals(this.getName)

  def compare(that: TCard): Int = this.getName.compare(that.getName)

  /**
   * Applies this card's contribution to the given score.
   *
   * For each joker, applies suit-level scoring then rank-class-level scoring.
   * Finally adds this card's rank value as chips.
   *
   * @param score  the current score.
   * @param jokers the list of active jokers to apply.
   * @return the updated Score.
   */
  def applyScore(score: Score, jokers: List[TJoker]): Score = {
    var current = score
    var i = 0
    while (i < jokers.size) {
      current = suit.applyScore(current, jokers(i))
      i += 1
    }
    var j = 0
    while (j < jokers.size) {
      current = rank.rankClass.applyScore(current, jokers(j))
      j += 1
    }
    current.addChips(rank.value)
  }
}



