package cl.uchile.dcc
package suit

import score.Score
import joker.{DeviousJoker, EvenSteven, GreedyJoker, ScaryFace, TJoker}

/**
 * Represents a playing card suit with score interaction via double dispatch.
 *
 * By default, each joker back-call returns the score unchanged.
 * Concrete suits override only the interactions relevant to them.
 */
trait Suit {
  def name: String
  override def toString: String = name

  /**
   * Initiates double dispatch with the joker for suit-level scoring.
   *
   * @param score the current score.
   * @param joker the joker whose effect is to be applied.
   * @return the updated Score.
   */
  def applyScore(score: Score, joker: TJoker): Score =
    joker.applyScoreFromSuit(this, score)

  def applyScoreFromGreedyJoker(joker: GreedyJoker, score: Score): Score = score
  def applyScoreFromDeviousJoker(joker: DeviousJoker, score: Score): Score = score
  def applyScoreFromEvenSteven(joker: EvenSteven, score: Score): Score = score
  def applyScoreFromScaryFace(joker: ScaryFace, score: Score): Score = score
}

