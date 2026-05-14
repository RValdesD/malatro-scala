package cl.uchile.dcc
package rank.classes

import cl.uchile.dcc.rank.RankClass

/**
 * Represents the classification for odd-numbered ranks.
 */
class OddClass extends RankClass:

  /** @return textual representation of this rank class */
  override def toString: String = "Odd"