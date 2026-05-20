package cl.uchile.dcc
package combinations
import card.TCard

/**
 * Abstract base for a poker combination.
 *
 * Provides shared helper methods for order and suit extraction and
 * consecutive sequence detection. Internal helpers are private;
 * only the higher-level helpers used by subclasses are protected.
 */
abstract class ACombination extends TCombination {

  /** @return the sorted order values extracted from the card list. */
  protected def orders(cards: List[TCard]): List[Int] =
    cards.map(_.getRank.order).sorted

  /**
   * Expands Ace (order=1) to also appear as 14 for high-straight detection.
   * @return sorted order list with Ace duplicated as 14 if present.
   */
  private def ordersWithHighAce(cards: List[TCard]): List[Int] = {
    val base = orders(cards)
    if (base.contains(1)) (base :+ 14).sorted else base
  }

  /** @return the suit names of all cards in the list. */
  protected def suits(cards: List[TCard]): List[String] =
    cards.map(_.getSuit.name)

  /** @return true if all cards share the same suit. */
  protected def allSameSuit(cards: List[TCard]): Boolean =
    suits(cards).distinct.size == 1

  /** @return true if the given sorted integer list forms a consecutive run. */
  private def isConsecutive(sorted: List[Int]): Boolean =
    sorted.zip(sorted.tail).forall { case (a, b) => b - a == 1 }

  /** @return true if the cards contain a consecutive run of exactly 5. */
  protected def hasStraight(cards: List[TCard]): Boolean = {
    val base = orders(cards)
    isConsecutive(base) || isConsecutive(ordersWithHighAce(cards).takeRight(5))
  }
}

