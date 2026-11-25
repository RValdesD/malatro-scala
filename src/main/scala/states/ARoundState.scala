package cl.uchile.dcc
package states

import controller.RoundController

/**
 * Abstract base for a round state.
 *
 * Provides default no-op implementations of playCards, discardCards,
 * and onEnter, and implements equals based on state name so tests can
 * assert on state identity without using isInstanceOf.
 *
 * @param controller the RoundController that owns this state.
 */
abstract class ARoundState(protected val controller: RoundController) extends TRoundState {

  def playCards(indices: List[Int]): Unit = {
    controller.setMessage("Action not allowed in the current state.")
  }

  def discardCards(indices: List[Int]): Unit = {
    controller.setMessage("Action not allowed in the current state.")
  }

  def onEnter(): Unit = {}

  override def equals(o: Any): Boolean =
    o.isInstanceOf[TRoundState] && o.asInstanceOf[TRoundState].getName.equals(this.getName)
}

