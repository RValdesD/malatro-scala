package cl.uchile.dcc
package rank
import rank.classes.FigureClass

/**
 * Abstract base for face cards (Jack, Queen, King).
 *
 * @param name  the display name of this rank.
 * @param order the position of this rank in the deck (1–13).
 * @param value the scoring value of this rank.
 */
abstract class FigureRank(name: String, val order: Int, val value: Int)
  extends Rank(name) {
  val rankClass: RankClass = new FigureClass
}

