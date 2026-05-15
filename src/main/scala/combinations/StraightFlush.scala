package cl.uchile.dcc
package combinations
import card.TCard

/**
 * Represents the Straight Flush card combination.
 *
 * A Straight Flush consists of five consecutive cards
 * sharing the same suit.
 */
class StraightFlush extends ACombination:

  /** Base chip value awarded for this combination. */
  val chips: Int = 100

  /** Score multiplier awarded for this combination. */
  val multiplier: Double = 8.0

  /**
   * Determines whether the given cards actually form a Straight Flush.
   *
   * @param cards cards to evaluate
   * @return `true` if the cards form both a Flush and a Straight
   */
  def isValid(cards: List[TCard]): Boolean =
    cards.size == 5 &&
      allSameSuit(cards) &&
      hasStraight(cards)
