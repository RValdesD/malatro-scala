package cl.uchile.dcc
package suit

/**
 * Represents the suit of a playing card.
 */
trait Suit:

  /** @return name of the suit */
  def name: String

  /** @return string representation of this suit */
  override def toString: String = name
