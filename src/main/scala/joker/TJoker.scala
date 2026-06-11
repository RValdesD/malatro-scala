package cl.uchile.dcc
package joker

import combinations.TCombination
import score.Score
import rank.RankClass
import suit.Suit

/**
 * Represents a joker card with a name, effect description, and score interactions.
 *
 * Participates in double dispatch for combinations, suits, and rank classes.
 */
trait TJoker extends Ordered[TJoker] {
  def getName: String
  def description: String
  def compare(that: TJoker): Int

  /**
   * Second step of double dispatch for combination scoring.
   * @param combination the combination initiating the dispatch.
   * @param score       the current score.
   * @return the updated Score.
   */
  def applyScoreFrom(combination: TCombination, score: Score): Score

  /**
   * Second step of double dispatch for suit scoring.
   * @param suit  the suit initiating the dispatch.
   * @param score the current score.
   * @return the updated Score.
   */
  def applyScoreFromSuit(suit: Suit, score: Score): Score

  /**
   * Second step of double dispatch for rank class scoring.
   * @param rankClass the rank class initiating the dispatch.
   * @param score     the current score.
   * @return the updated Score.
   */
  def applyScoreFromRankClass(rankClass: RankClass, score: Score): Score
}

