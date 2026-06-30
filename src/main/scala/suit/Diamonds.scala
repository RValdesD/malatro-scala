package cl.uchile.dcc
package suit

/**
 * The Diamonds suit.
 *
 * Responds to GreedyJoker by adding +3 to the multiplier for each
 * Diamond card played.
 */
class Diamonds extends Suit {
  val name: String = "Diamonds"

  override def applyScoreFromGreedyJoker(joker: GreedyJoker, score: Score): Score =
    score.addMultiplier(3)
}

