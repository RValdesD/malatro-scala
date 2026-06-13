package cl.uchile.dcc
package combinations

import card.TCard
import joker.{DeviousJoker, EvenSteven, GreedyJoker, ScaryFace, TJoker}
import score.Score

/**
 * Abstract base for a poker combination.
 *
 * Provides shared helper methods and default double-dispatch implementations.
 * By default, each joker back-call returns the score unchanged — subclasses
 * override only the interactions that are meaningful for them.
 */
abstract class ACombination extends TCombination {

  /**
   * Initiates double dispatch: delegates to the joker, passing this combination.
   *
   * @param score the current score.
   * @param joker the joker whose effect is to be applied.
   * @return the updated Score.
   */
  def applyScore(score: Score, joker: TJoker): Score =
    joker.applyScoreFrom(this, score)

  def applyScoreFromGreedyJoker(joker: GreedyJoker, score: Score): Score = score
  def applyScoreFromDeviousJoker(joker: DeviousJoker, score: Score): Score = score
  def applyScoreFromEvenSteven(joker: EvenSteven, score: Score): Score = score
  def applyScoreFromScaryFace(joker: ScaryFace, score: Score): Score = score

  protected def orders(cards: List[TCard]): List[Int] =
    cards.map(_.getRank.order).sorted

  private def ordersWithHighAce(cards: List[TCard]): List[Int] = {
    val base = orders(cards)
    if (base.contains(1)) (base :+ 14).sorted else base
  }

  protected def suits(cards: List[TCard]): List[String] =
    cards.map(_.getSuit.name)

  protected def allSameSuit(cards: List[TCard]): Boolean =
    suits(cards).distinct.size == 1

  private def isConsecutive(sorted: List[Int]): Boolean =
    sorted.zip(sorted.tail).forall { case (a, b) => b - a == 1 }

  protected def hasStraight(cards: List[TCard]): Boolean = {
    val base = orders(cards)
    isConsecutive(base) || isConsecutive(ordersWithHighAce(cards).takeRight(5))
  }
}

