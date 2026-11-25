package cl.uchile.dcc
package rank
import rank.classes.OddClass

/**
 * Abstract base for odd-ranked cards (Ace, Three, Five, Seven, Nine).
 *
 * @param name  the display name of this rank.
 * @param order the position of this rank in the deck (1–13).
 * @param value the scoring value of this rank.
 */
abstract class OddRank(name: String, val order: Int, val value: Int)
  extends Rank(name) {
  val rankClass: RankClass = new OddClass
}

