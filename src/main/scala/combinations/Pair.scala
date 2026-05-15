package cl.uchile.dcc
package combinations
import card.TCard

/**
 * Represents the Pair card combination.
 *
 * A Pair consists of at least two cards sharing the same rank.
 */
class Pair extends ACombination:

  /** Base chip value awarded for this combination. */
  val chips: Int = 10

  /** Score multiplier awarded for this combination. */
  val multiplier: Double = 2.0

  /**
   * Determines if the given cards form a Pair.
   *
   * @param cards cards to evaluate
   * @return `true` if at least two cards share the same rank
   */
  def isValid(cards: List[TCard]): Boolean =
    cards.size >= 2 &&
      orders(cards).groupBy(identity).values.exists(_.size >= 2)