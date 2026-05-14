package cl.uchile.dcc
package rank.classes

import rank.RankClass

/**
 * Represents the classification for even-numbered ranks.
 */
class EvenClass extends RankClass:

  /** @return textual representation of this rank class */
  override def toString: String = "Even"
