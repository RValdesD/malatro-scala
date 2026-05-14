package cl.uchile.dcc
package rank
import rank.classes.FigureClass

/**
 * Represents a face-card rank.
 *
 * @param name name of the rank
 */
abstract class FigureRank(name: String) extends Rank(name):

  /** Classification associated with this rank. */
  val rankClass: RankClass = new FigureClass
