package cl.uchile.dcc
package score

/**
 * Represents a score with a chip count and a multiplier.
 *
 * @param chips      the base chip count.
 * @param multiplier the score multiplier.
 */
class Score(val chips: Int, val multiplier: Double) {
  /**
   * Returns the total score as chips multiplied by multiplier.
   * @return the computed total.
   */
  def total: Double = chips * multiplier

  /**
   * Returns a new Score with chips increased by the given amount.
   * @param amount the number of chips to add.
   * @return a new Score with updated chips.
   */
  def addChips(amount: Int): Score = new Score(chips + amount, multiplier)

  /**
   * Returns a new Score with the multiplier increased by the given amount.
   * @param amount the value to add to the multiplier.
   * @return a new Score with updated multiplier.
   */
  def addMultiplier(amount: Double): Score = new Score(chips, multiplier + amount)

  override def toString: String = s"Score(chips=$chips, multiplier=$multiplier, total=$total)"
}
