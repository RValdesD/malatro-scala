package cl.uchile.dcc
package rank.classes

import rank.RankClass
import joker.ScaryFace
import score.Score

/**
 * Rank class for face cards (Jack, Queen, King).
 *
 * Responds to ScaryFace by adding +30 chips.
 */
class FigureClass extends RankClass {
  override def toString: String = "Figure"

  override def applyScoreFromScaryFace(joker: ScaryFace, score: Score): Score =
    score.addChips(30)
}

