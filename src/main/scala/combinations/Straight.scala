package cl.uchile.dcc
package combinations
import card.TCard

/**
 * Represents the Straight card combination.
 *
 * A Straight consists of five consecutive card ranks.
 */
class Straight extends ACombination:

  /** Base chip value awarded for this combination. */
  val chips: Int = 30

  /** Score multiplier awarded for this combination. */
  val multiplier: Double = 4.0

  /**
   * Determines whether the given cards form a Straight.
   *
   * @param cards cards to evaluate
   * @return `true` if the cards form a consecutive sequence
   */
  def isValid(cards: List[TCard]): Boolean = cards.size == 5 && hasStraight(cards)
