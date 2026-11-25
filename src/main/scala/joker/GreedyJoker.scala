package cl.uchile.dcc
package joker

import combinations.TCombination
import rank.RankClass
import score.Score
import suit.Suit

/**
 * Greedy Joker: adds +3 to the multiplier for each Diamond card played.
 *
 * Applied at the suit level — Diamonds responds to this joker.
 */
class GreedyJoker extends AJoker("Greedy Joker") {
  val description: String = "Played Diamond cards each give +3 multiplier"

  def applyScoreFrom(combination: TCombination, score: Score): Score =
    combination.applyScoreFromGreedyJoker(this, score)

  def applyScoreFromSuit(suit: Suit, score: Score): Score =
    suit.applyScoreFromGreedyJoker(this, score)

  def applyScoreFromRankClass(rankClass: RankClass, score: Score): Score =
    rankClass.applyScoreFromGreedyJoker(this, score)
}

