package cl.uchile.dcc
package joker

import combinations.TCombination
import score.Score
import rank.RankClass
import suit.Suit

/**
 * Even Steven: adds +4 chips for every played even-ranked card.
 */
class EvenSteven extends AJoker("Even Steven") {
  val description: String = "Played even-ranked cards each give +4 chips"

  def applyScoreFrom(combination: TCombination, score: Score): Score =
    combination.applyScoreFromEvenSteven(this, score)

  def applyScoreFromSuit(suit: Suit, score: Score): Score =
    suit.applyScoreFromEvenSteven(this, score)

  def applyScoreFromRankClass(rankClass: RankClass, score: Score): Score =
    rankClass.applyScoreFromEvenSteven(this, score)
}

