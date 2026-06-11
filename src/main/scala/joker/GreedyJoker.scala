package cl.uchile.dcc
package joker

import combinations.TCombination
import rank.RankClass
import score.Score
import suit.Suit

/**
 * Greedy Joker: adds +3 chips for every played card at the combination level.
 */
class GreedyJoker extends AJoker("Greedy Joker") {
  val description: String = "Played cards each give +3 chips"

  def applyScoreFrom(combination: TCombination, score: Score): Score =
    combination.applyScoreFromGreedyJoker(this, score)

  def applyScoreFromSuit(suit: Suit, score: Score): Score =
    suit.applyScoreFromGreedyJoker(this, score)

  def applyScoreFromRankClass(rankClass: RankClass, score: Score): Score =
    rankClass.applyScoreFromGreedyJoker(this, score)
}
