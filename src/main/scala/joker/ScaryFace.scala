package cl.uchile.dcc
package joker

import score.Score
import combinations.TCombination
import rank.RankClass
import suit.Suit

/**
 * Scary Face: adds +30 chips for every played face card.
 */
class ScaryFace extends AJoker("Scary Face") {
  val description: String = "Played face cards each give +30 chips"

  def applyScoreFrom(combination: TCombination, score: Score): Score =
    combination.applyScoreFromScaryFace(this, score)

  def applyScoreFromSuit(suit: Suit, score: Score): Score =
    suit.applyScoreFromScaryFace(this, score)

  def applyScoreFromRankClass(rankClass: RankClass, score: Score): Score =
    rankClass.applyScoreFromScaryFace(this, score)
}
