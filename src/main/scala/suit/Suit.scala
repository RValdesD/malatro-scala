package cl.uchile.dcc
package suit

trait Suit {
  def name: String
  override def toString: String = name
}
