package cl.uchile.dcc
package score

trait Score {
  def chips: Int
  def multiplier: Double
  def total: Double = chips * multiplier
}
