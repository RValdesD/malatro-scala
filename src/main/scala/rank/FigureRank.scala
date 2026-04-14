package cl.uchile.dcc
package rank
import rank.classes.FigureClass

abstract class FigureRank extends Rank {
  val rankClass: RankClass = new FigureClass
}
