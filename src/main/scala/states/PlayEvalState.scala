package cl.uchile.dcc
package states

import controller.RoundController

/**
 * The state in which the played cards' combination and score are resolved.
 *
 * Plays the selected cards from the hand, computes the score via the
 * controller's ScoreCalculator, applies it to the round total, and
 * transitions to ShowScoreState. Does not accept further input.
 *
 * @param controller the RoundController that owns this state.
 */
class PlayEvalState(controller: RoundController) extends ARoundState(controller) {
  val getName: String = "PlayEvalState"

  override def onEnter(): Unit = {
    val playedCards = controller.hand.playCards(controller.selectedIndices)
    val roundScore  = controller.scoreCalculator.calculate(playedCards, controller.hand.getJokers)
    controller.addToTotalScore(roundScore.total)
    controller.setMessage(s"Played for ${roundScore.total} points.")
    controller.transitionTo(new ShowScoreState(controller))
  }
}

