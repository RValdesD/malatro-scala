package cl.uchile.dcc
package rank
import rank.classes.EvenClass

/**
 * Represents a rank belonging to the even rank class.
 *
 * @param name name of the rank
 */
abstract class EvenRank(name: String) extends Rank(name):

  /** Classification associated with this rank. */
  val rankClass: RankClass = new EvenClass
