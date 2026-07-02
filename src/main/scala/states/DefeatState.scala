package cl.uchile.dcc
package states

import controller.RoundController
/**
 * The state reached when plays and discards are both exhausted and the
 * target score was not met.
 *
 * Both player actions become no-ops. Equivalent in role to the uploaded
 * reference's GameOverState, renamed to distinguish a lost round from
 * a won one.
 *
 * @param controller the RoundController that owns this state.
 */
class DefeatState(controller: RoundController) extends ARoundState(controller) {
  val getName: String = "DefeatState"

  override def onEnter(): Unit = {
    controller.setMessage("Defeat: no moves remaining and target score not met.")
  }

  override def playCards(indices: List[Int]): Unit = {
    controller.setMessage("Round over: no plays remaining.")
  }

  override def discardCards(indices: List[Int]): Unit = {
    controller.setMessage("Round over: no plays remaining.")
  }
}

