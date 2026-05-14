package cl.uchile.dcc
package joker

/**
 * Represents and abstract base of a joker card.
 *
 * Equality is based on the joker's name.
 *
 * @param name the name of this joker.
 */
abstract class AJoker(val name: String) extends TJoker {
  /** @return the name of this joker. */
  def getName: String = name

  def description: String

  override def equals(o: Any): Boolean =
    o.isInstanceOf[TJoker] && o.asInstanceOf[TJoker].getName == this.getName

  def compare(that: TJoker): Int = this.getName.compare(that.getName)

  override def toString: String = s"$name: $description"
}
