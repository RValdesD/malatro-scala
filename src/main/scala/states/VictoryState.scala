package cl.uchile.dcc
package states

import controller.RoundController

/**
 * The state reached when the target score is met before moves run out.
 *
 * Both player actions become no-ops, consistent with every terminal state.
 * The transition to a Shop phase and the next round lives one level above
 * the round (in a Game-level controller), not here, since Victory only
 * concerns the outcome of a single round.
 *
 * @param controller the RoundController that owns this state.
 */
class VictoryState(controller: RoundController) extends ARoundState(controller) {
  val getName: String = "VictoryState"

  override def onEnter(): Unit = {
    controller.setMessage("Victory: target score reached.")
  }

  override def playCards(indices: List[Int]): Unit = {
    controller.setMessage("Round already won.")
  }

  override def discardCards(indices: List[Int]): Unit = {
    controller.setMessage("Round already won.")
  }
}

