package cl.uchile.dcc
package states

/**
 * Represents a state of a round in the State pattern.
 *
 * Each state defines how the controller responds to player events.
 * States are compared by name, allowing equals-based assertions
 * without resorting to isInstanceOf.
 */
trait TRoundState {
  /** @return the unique name identifying this state type. */
  def getName: String

  def playCards(indices: List[Int]): Unit
  def discardCards(indices: List[Int]): Unit
  def onEnter(): Unit
}

