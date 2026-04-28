package cl.uchile.dcc
package rank

abstract class Rank(val name: String) {
  def order: Int
  def value: Int
  def rankClass: RankClass
  override def toString: String = s"$name(order=$order, value=$value)"
}