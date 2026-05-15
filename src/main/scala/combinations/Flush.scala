package cl.uchile.dcc
package combinations
import card.TCard

/**
 * Represents the Flush card combination.
 *
 * A Flush consists of five cards sharing the same suit.
 */
class Flush extends ACombination:

  /** Base chip value awarded for this combination. */
  val chips: Int = 35

  /** Score multiplier awarded for this combination. */
  val multiplier: Double = 4.0

  /**
   * Determines whether the given cards are a Flush.
   *
   * @param cards cards to evaluate
   * @return `true` if all cards share the same suit
   */
  def isValid(cards: List[TCard]): Boolean =
    cards.size == 5 && allSameSuit(cards)
