package cl.uchile.dcc
package combinations
import card.TCard

abstract class ACombination extends TCombination {

  /** Orders extracted from the card list, with Ace treated as both 1 and 14. */
  protected def orders(cards: List[TCard]): List[Int] =
    cards.map(_.getRank.order).sorted

  /** Expands Ace (order=1) to also appear as 14 for high-straight detection. */
  protected def ordersWithHighAce(cards: List[TCard]): List[Int] = {
    val base = orders(cards)
    if (base.contains(1)) (base :+ 14).sorted else base
  }

  protected def suits(cards: List[TCard]): List[String] =
    cards.map(_.getSuit.name)

  protected def allSameSuit(cards: List[TCard]): Boolean =
    suits(cards).distinct.size == 1

  /** Returns true if the given sorted integer list forms a consecutive run. */
  protected def isConsecutive(sorted: List[Int]): Boolean =
    sorted.zip(sorted.tail).forall { case (a, b) => b - a == 1 }

  /** True if cards contain a consecutive run of exactly 5 (low or high ace). */
  protected def hasStraight(cards: List[TCard]): Boolean = {
    val base = orders(cards)
    isConsecutive(base) || isConsecutive(ordersWithHighAce(cards).takeRight(5))
  }
}
