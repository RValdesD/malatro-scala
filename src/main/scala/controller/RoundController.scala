package cl.uchile.dcc
package controller

/**
 * The main controller of a round.
 *
 * Manages the round's state machine (State pattern) and reacts to
 * notifications from the hand (Observer pattern). All player-facing
 * events (playCards, discardCards) are delegated to the current
 * TRoundState, so the controller never inspects state identity directly.
 */
class RoundController extends TObserver {
  private var currentState: TRoundState = new SelectingCardsState(this)
  private var roundHand: Hand = new Hand
  private var roundDeck: Deck = new Deck
  private var message: String = "Round not started."
  private var selected: List[Int] = List()
  private var totalScore: Double = 0.0
  private var targetScore: Double = 0.0
  private var handSize: Int = 8
  private val calculator: ScoreCalculator = new ScoreCalculator()

  /** @return the current round state. */
  def state: TRoundState = currentState

  /** @return the player's current hand. */
  def hand: Hand = roundHand

  /** @return the round's deck. */
  def deck: Deck = roundDeck

  /** @return the latest status message. */
  def getMessage: String = message

  /** @return the indices most recently selected by the player. */
  def selectedIndices: List[Int] = selected

  /** @return the running total score for this round. */
  def getTotalScore: Double = totalScore

  /** @return the score required to win this round. */
  def getTargetScore: Double = targetScore

  /** @return the hand size the round replenishes up to. */
  def targetHandSize: Int = handSize

  /** @return the score calculator used to evaluate plays. */
  def scoreCalculator: ScoreCalculator = calculator

  /**
   * Sets the indices the player has currently selected.
   * @param indices the selected card indices.
   */
  def setSelectedIndices(indices: List[Int]): Unit = {
    selected = indices
  }

  /**
   * Updates the controller's status message.
   * @param msg the new message.
   */
  def setMessage(msg: String): Unit = {
    message = msg
  }

  /**
   * Adds the given amount to the round's running total score.
   * @param amount the score to add.
   */
  def addToTotalScore(amount: Double): Unit = {
    totalScore = totalScore + amount
  }

  /**
   * Initialises the round with the given hand, deck, and target score.
   *
   * @param initialHand  the starting hand for the round.
   * @param initialDeck  the deck to draw from during replenishment.
   * @param target       the score required to win the round.
   */
  def initRound(initialHand: Hand, initialDeck: Deck, target: Double): Unit = {
    roundHand = initialHand
    roundDeck = initialDeck
    targetScore = target
    totalScore = 0.0
    transitionTo(new SelectingCardsState(this))
  }

  /**
   * Transitions the round to a new state, invoking the new state's
   * onEnter hook immediately. States that perform automatic work
   * (e.g. PlayEvalState) may transition onward again from within
   * their own onEnter, forming a chain that ends at a state which
   * waits on player input or is terminal.
   *
   * @param newState the state to transition into.
   */
  def transitionTo(newState: TRoundState): Unit = {
    currentState = newState
    currentState.onEnter()
  }

  /**
   * Checks whether the round has been won, lost, or should continue.
   *
   * Called after Replenish completes. Transitions to VictoryState if the
   * target score has been met, to DefeatState if the hand has no plays
   * or discards remaining, or back to SelectingCardsState otherwise.
   */
  def checkRoundStatus(): Unit = {
    if (totalScore >= targetScore) {
      transitionTo(new VictoryState(this))
    } else if (roundHand.timesPlayed >= 3 && roundHand.timesDiscarded >= 3) {
      transitionTo(new DefeatState(this))
    } else {
      transitionTo(new SelectingCardsState(this))
    }
  }

  /**
   * Plays the cards at the given indices.
   * Delegates to the current state.
   *
   * @param indices 1 to 5 valid indices into the hand's card list.
   */
  def playCards(indices: List[Int]): Unit = {
    currentState.playCards(indices)
  }

  /**
   * Discards the cards at the given indices.
   * Delegates to the current state.
   *
   * @param indices 1 to 5 valid indices into the hand's card list.
   */
  def discardCards(indices: List[Int]): Unit = {
    currentState.discardCards(indices)
  }

  /**
   * Called by the hand when an observed state change occurs
   * (e.g. play or discard limits reached).
   */
  override def update(): Unit = {
    checkRoundStatus()
  }
}

