package cl.uchile.dcc
package joker

/**
 * Represents an abstract base for a joker card.
 *
 * Equality is based on the joker's name. Subclasses implement
 * applyScoreFrom by calling back the matching typed method on the combination.
 *
 * @param name the name of this joker.
 */
abstract class AJoker(private val name: String) extends TJoker {
  /** @return the name of this joker. */
  def getName: String = name

  def description: String

  override def equals(o: Any): Boolean =
    o.isInstanceOf[TJoker] && o.asInstanceOf[TJoker].getName.equals(this.getName)

  def compare(that: TJoker): Int = this.getName.compare(that.getName)

  override def toString: String = s"$name: $description"
}

