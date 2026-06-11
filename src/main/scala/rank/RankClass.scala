package cl.uchile.dcc
package rank

import score.Score
import joker.{DeviousJoker, EvenSteven, GreedyJoker, ScaryFace, TJoker}

/**
 * Represents the class of a rank (Even, Odd, or Figure) with score interaction
 * via double dispatch.
 *
 * By default, each joker back-call returns the score unchanged.
 * Subclasses override only the interactions relevant to their rank class.
 */
trait RankClass {
  /**
   * Initiates double dispatch with the joker for rank-class-level scoring.
   *
   * @param score the current score.
   * @param joker the joker whose effect is to be applied.
   * @return the updated Score.
   */
  def applyScore(score: Score, joker: TJoker): Score =
    joker.applyScoreFromRankClass(this, score)

  def applyScoreFromGreedyJoker(joker: GreedyJoker, score: Score): Score = score
  def applyScoreFromDeviousJoker(joker: DeviousJoker, score: Score): Score = score
  def applyScoreFromEvenSteven(joker: EvenSteven, score: Score): Score = score
  def applyScoreFromScaryFace(joker: ScaryFace, score: Score): Score = score
}

