package cl.uchile.dcc
package combinations
import card.TCard

/**
 * Represents the Three of a Kind card combination.
 *
 * A Three of a Kind consists of at least three cards
 * sharing the same rank.
 */
class ThreeOfAKind extends ACombination:

  /** Base chip value awarded for this combination. */
  val chips: Int = 30

  /** Score multiplier awarded for this combination. */
  val multiplier: Double = 3.0

  /**
   * Determines whether the given cards actually form a Three of a Kind.
   *
   * @param cards cards to evaluate
   * @return `true` if at least three cards share the same rank
   */
  def isValid(cards: List[TCard]): Boolean =
    cards.size >= 3 &&
      orders(cards).groupBy(identity).values.exists(_.size >= 3)
