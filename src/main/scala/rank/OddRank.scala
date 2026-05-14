package cl.uchile.dcc
package rank
import rank.classes.OddClass

/**
 * Represents a rank belonging to the odd rank class.
 *
 * @param name name of the rank
 */
abstract class OddRank(name: String) extends Rank(name):

  /** Classification associated with this rank. */
  val rankClass: RankClass = new OddClass
