package cl.uchile.dcc
package score

/**
 * Represents a score consisting of chips + multiplier.
 *
 * The total score is computed as the product of both values.
 */
trait Score:

  /** @return number of chips */
  def chips: Int

  /** @return score multiplier */
  def multiplier: Double

  /** @return total computed score */
  def total: Double =
    chips * multiplier
