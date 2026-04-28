package cl.uchile.dcc
package rank
import rank.classes.OddClass

abstract class OddRank(name: String) extends Rank(name) {
  val rankClass: RankClass = new OddClass
}
