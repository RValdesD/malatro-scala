package cl.uchile.dcc
package states

import controller.RoundController

/**
 * The state in which the round's most recent score path is displayed.
 *
 * This is meant as a transient state: performs no calculation, only sends a message
 * to ReplenishState. Kept separate from PlayEvalState so a view layer can
 * distinguish "calculating" from "displaying the result" and avoid possible confusion this way.
 *
 * @param controller the RoundController that owns this state.
 */
class ShowScoreState(controller: RoundController) extends ARoundState(controller) {
  val getName: String = "ShowScoreState"

  override def onEnter(): Unit = {
    controller.transitionTo(new ReplenishState(controller))
  }
}

