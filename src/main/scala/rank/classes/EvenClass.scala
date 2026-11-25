package cl.uchile.dcc
package rank.classes

import rank.RankClass
import joker.EvenSteven
import score.Score

/**
 * Rank class for even-ranked cards (Two, Four, Six, Eight, Ten).
 *
 * Responds to EvenSteven by adding +4 chips.
 */
class EvenClass extends RankClass {
  override def toString: String = "Even"

  override def applyScoreFromEvenSteven(joker: EvenSteven, score: Score): Score =
    score.addChips(4)
}

