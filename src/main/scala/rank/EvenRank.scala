package cl.uchile.dcc
package rank
import rank.classes.EvenClass

abstract class EvenRank extends Rank {
  val rankClass: RankClass = new EvenClass
}
