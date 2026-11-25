package cl.uchile.dcc
package rank

/**
 * Represents the rank of a playing card.
 *
 * A rank defines ordering, scoring value, and classification.
 *
 * @param name name of the rank
 */
abstract class Rank(val name: String):

  /** @return ordering position of this rank */
  def order: Int

  /** @return scoring value of this rank */
  def value: Int

  /** @return classification associated with this rank */
  def rankClass: RankClass

  /** @return string representation of this rank */
  override def toString: String =
    s"$name(order=$order, value=$value)"