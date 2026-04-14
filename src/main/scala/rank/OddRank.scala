package cl.uchile.dcc
package rank
import rank.classes.OddClass

abstract class OddRank extends Rank {
  val rankClass: RankClass = new OddClass
}
