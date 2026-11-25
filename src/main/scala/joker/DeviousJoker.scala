package cl.uchile.dcc
package joker

import combinations.TCombination
import rank.RankClass
import score.Score
import suit.Suit

/**
 * Devious Joker: gives +100 chips if the hand contains a Straight or Straight Flush.
 */
class DeviousJoker extends AJoker("Devious Joker") {
  val description: String = "+100 chips if hand contains a Straight"

  def applyScoreFrom(combination: TCombination, score: Score): Score =
    combination.applyScoreFromDeviousJoker(this, score)

  def applyScoreFromSuit(suit: Suit, score: Score): Score =
    suit.applyScoreFromDeviousJoker(this, score)

  def applyScoreFromRankClass(rankClass: RankClass, score: Score): Score =
    rankClass.applyScoreFromDeviousJoker(this, score)
}

