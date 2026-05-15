package cl.uchile.dcc
package combinations
import card.TCard

/**
 * Represents a playable card combination.
 *
 * A combination defines its score values and the rules
 * required for validation.
 */
trait TCombination:

  /** @return base chip value awarded by this combination */
  def chips: Int

  /** @return score multiplier awarded by this combination */
  def multiplier: Double

  /**
   * Determines whether the given cards satisfy
   * the rules of this combination.
   *
   * @param cards cards to evaluate
   * @return `true` if the combination is valid
   */
  def isValid(cards: List[TCard]): Boolean
