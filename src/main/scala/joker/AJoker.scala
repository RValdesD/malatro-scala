package cl.uchile.dcc
package joker

abstract class AJoker(val name: String) extends TJoker {
  def getName: String = name
  def description: String

  override def equals(o: Any): Boolean = o match {
    case that: TJoker => this.getName == that.getName
    case _            => false
  }

  def compare(that: TJoker): Int = this.getName.compare(that.getName)
  override def toString: String  = s"$name: $description"
}
