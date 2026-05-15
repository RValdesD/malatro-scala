package cl.uchile.dcc
package combinations
import card.TCard


/**
 * Represents the High Card combination.
 *
 * This combination is valid whenever at least one card is present.
 */
class HighCard extends ACombination:

  /** Base chip value awarded for this combination. */
  val chips: Int = 5

  /** Score multiplier awarded for this combination. */
  val multiplier: Double = 1.0

  /**
   * Determines whether the given cards form a HighCard.
   *
   * @param cards cards to evaluate
   * @return `true` if the card list is non-empty
   */
  def isValid(cards: List[TCard]): Boolean =
    cards.nonEmpty
