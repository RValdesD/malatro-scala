package cl.uchile.dcc
package joker

abstract class Joker {
  def name: String
  def description: String
  override def toString: String = s"$name: $description"
}
