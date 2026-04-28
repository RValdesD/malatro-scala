package cl.uchile.dcc
package rank
import rank.classes.FigureClass

abstract class FigureRank(name: String) extends Rank(name) {
  val rankClass: RankClass = new FigureClass
}
