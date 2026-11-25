package cl.uchile.dcc
package states

import controller.RoundController

/**
 * The edge-case state reached when the deck has no cards left to draw.
 *
 * Reshuffles the discard pile back into the deck, then re-enters
 * ReplenishState to complete the draw.
 *
 * @param controller the RoundController that owns this state.
 */
class DeckEmptyState(controller: RoundController) extends ARoundState(controller) {
  val getName: String = "DeckEmptyState"

  override def onEnter(): Unit = {
    controller.deck.reshuffleFromDiscardPile()
    controller.setMessage("Deck reshuffled from discard pile.")
    controller.transitionTo(new ReplenishState(controller))
  }
}

