package cl.uchile.dcc
package rank
import rank.classes.EvenClass

abstract class EvenRank(name: String) extends Rank(name) {
  val rankClass: RankClass = new EvenClass
}
