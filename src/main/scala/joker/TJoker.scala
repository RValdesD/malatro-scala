package cl.uchile.dcc
package joker

trait TJoker extends Ordered[TJoker] {
  def getName: String
  def description: String
  def compare(that: TJoker): Int
}
