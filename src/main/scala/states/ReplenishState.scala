package cl.uchile.dcc
package states

import controller.RoundController

/**
 * The state in which the hand is drawn back up to its target size.
 *
 * If the deck runs out of cards mid-draw, transitions to DeckEmptyState
 * to reshuffle before continuing. Otherwise, it transitions directly to the
 * round status check.
 *
 * @param controller the RoundController that owns this state.
 */
class ReplenishState(controller: RoundController) extends ARoundState(controller) {
  val getName: String = "ReplenishState"

  override def onEnter(): Unit = {
    if (controller.deck.isEmpty) {
      controller.transitionTo(new DeckEmptyState(controller))
    } else {
      controller.deck.drawUpTo(controller.hand, controller.targetHandSize)
      controller.checkRoundStatus()
    }
  }
}

