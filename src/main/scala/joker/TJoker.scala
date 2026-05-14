package cl.uchile.dcc
package joker

/**
 * Represents a joker card with a name and gameplay effect.
 *
 * Jokers are ordered relative to other jokers through
 * the `Ordered` interface implementation.
 */
trait TJoker extends Ordered[TJoker]:

  /** @return name of the joker */
  def getName: String

  /** @return description of the joker's effect */
  def description: String

  /**
   * Compares this joker with another joker.
   *
   * @param that joker to compare against
   * @return
   *   negative value if this joker is less than `that`,
   *   zero if both jokers are equal,
   *   positive value if this joker is greater than `that`
   */
  def compare(that: TJoker): Int
