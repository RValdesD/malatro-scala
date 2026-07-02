package cl.uchile.dcc
package states

/**
 * The state in which the player chooses cards to play or discard.
 *
 * This is the only state that accepts player-driven play/discard events
 * directly — every other state in the round either reacts automatically
 * via onEnter or rejects the action.
 *
 * @param controller the RoundController that owns this state.
 */
class SelectingCardsState(controller: RoundController) extends ARoundState(controller) {
  val getName: String = "SelectingCardsState"

  override def playCards(indices: List[Int]): Unit = {
    controller.setSelectedIndices(indices)
    controller.transitionTo(new PlayEvalState(controller))
  }

  override def discardCards(indices: List[Int]): Unit = {
    controller.setSelectedIndices(indices)
    controller.transitionTo(new DiscardState(controller))
  }

  override def onEnter(): Unit = {
    controller.setMessage("Choose 1 to 5 cards to play or discard.")
  }
}

