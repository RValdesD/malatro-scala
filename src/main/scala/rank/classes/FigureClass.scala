package cl.uchile.dcc
package rank.classes

import rank.RankClass

/**
 * Represents the classification for face-card ranks.
 */
class FigureClass extends RankClass:

  /** @return textual representation of this rank class */
  override def toString: String = "Figure"
