package cl.uchile.dcc
package rank
import rank.classes.EvenClass

/**
 * Abstract base for even-ranked cards (Two, Four, Six, Eight, Ten).
 *
 * @param name  the display name of this rank.
 * @param order the position of this rank in the deck (1–13).
 * @param value the scoring value of this rank.
 */
abstract class EvenRank(name: String, val order: Int, val value: Int)
  extends Rank(name) {
  val rankClass: RankClass = new EvenClass
}

