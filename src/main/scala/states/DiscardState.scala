package cl.uchile.dcc
package states

import controller.RoundController

/**
 * The state in which the player's selected cards are discarded.
 *
 * Removes the selected cards from the hand, then transitions straight to
 * ReplenishState since no score is involved in a discard.
 *
 * @param controller the RoundController that owns this state.
 */
class DiscardState(controller: RoundController) extends ARoundState(controller) {
  val getName: String = "DiscardState"

  override def onEnter(): Unit = {
    controller.hand.discardCards(controller.selectedIndices)
    controller.setMessage("Cards discarded.")
    controller.transitionTo(new ReplenishState(controller))
  }
}

