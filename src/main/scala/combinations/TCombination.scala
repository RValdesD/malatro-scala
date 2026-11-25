package cl.uchile.dcc
package combinations
import card.TCard
import joker.{DeviousJoker, EvenSteven, GreedyJoker, ScaryFace, TJoker}
import score.Score

/**
 * Represents a poker combination with a base score and validation logic.
 *
 * Declares applyScore for double dispatch with a Joker, and the four
 * concrete back-call methods — one per joker type.
 */
trait TCombination {
  def chips: Int
  def multiplier: Double
  def isValid(cards: List[TCard]): Boolean

  /**
   * Applies a joker's effect to the given score for this combination.
   * Dispatches to the joker, which calls back with the concrete type.
   *
   * @param score the current score.
   * @param joker the joker whose effect is to be applied.
   * @return the updated Score.
   */
  def applyScore(score: Score, joker: TJoker): Score

  /** Called back by GreedyJoker during double dispatch. */
  def applyScoreFromGreedyJoker(joker: GreedyJoker, score: Score): Score

  /** Called back by DeviousJoker during double dispatch. */
  def applyScoreFromDeviousJoker(joker: DeviousJoker, score: Score): Score

  /** Called back by EvenSteven during double dispatch. */
  def applyScoreFromEvenSteven(joker: EvenSteven, score: Score): Score

  /** Called back by ScaryFace during double dispatch. */
  def applyScoreFromScaryFace(joker: ScaryFace, score: Score): Score
}

