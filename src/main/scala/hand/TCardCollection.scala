package cl.uchile.dcc
package hand
import card.TCard
import joker.TJoker

import scala.collection.mutable.ArrayBuffer

/**
 * Represents a collection of cards and jokers with play and discard functionality.
 */
trait TCardCollection {
  /**
   * Adds a card to the collection.
   * @param card the card to add.
   */
  def addCard(card: TCard): Unit

  /**
   * Adds a joker to the collection.
   * @param joker the joker to add.
   */
  def addJoker(joker: TJoker): Unit

  /**
   * Plays the cards at the given indices.
   *
   * Returns the corresponding cards and removes them from the hand.
   * The index list must contain between 1 and 5 valid indices.
   * The hand may be played at most 3 times.
   *
   * @param indices the positions of the cards to play.
   * @return the list of played cards in the order the indices were given.
   * @throws CardMinPlayedException               if fewer than 1 index is given.
   * @throws CardMaxPlayedException               if more than 5 indices are given.
   * @throws CardInvalidListidxPlayedException    if any index is out of range.
   * @throws CardMaxSetPlayedException            if the hand has already been played 3 times.
   */
  def playCards(indices: List[Int]): List[TCard]

  /**
   * Discards the cards at the given indices.
   *
   * Removes the corresponding cards from the hand without returning them.
   * The index list must contain between 1 and 5 valid indices.
   * Discard may be performed at most 3 times.
   *
   * @param indices the positions of the cards to discard.
   * @throws CardMinDiscardException              if fewer than 1 index is given.
   * @throws CardMaxDiscardException              if more than 5 indices are given.
   * @throws CardInvalidListidxDiscardException   if any index is out of range.
   * @throws CardMaxTimesDiscardException         if discard has already been used 3 times.
   */
  def discardCards(indices: List[Int]): Unit

  /** @return all cards currently held, as an immutable list. */
  def getCards: List[TCard]

  /** @return all jokers currently held, as an immutable list. */
  def getJokers: List[TJoker]

  /**
   * Returns the card at the given index.
   * @param index the position to retrieve.
   * @throws IndexOutOfBoundsException if the index is out of range.
   */
  def getCard(index: Int): TCard

  /**
   * Returns the joker at the given index.
   * @param index the position to retrieve.
   * @throws IndexOutOfBoundsException if the index is out of range.
   */
  def getJoker(index: Int): TJoker

  /** @return the number of cards held. */
  def cardCount: Int

  /** @return the number of jokers held. */
  def jokerCount: Int

  /** @return how many times playCards has been called. */
  def timesPlayed: Int

  /** @return how many times discardCards has been called. */
  def timesDiscarded: Int
}


