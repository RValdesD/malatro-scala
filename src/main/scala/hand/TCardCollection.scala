package cl.uchile.dcc
package hand
import card.TCard
import joker.TJoker

import scala.collection.mutable.ArrayBuffer

/**
 * Represents a collection of cards and jokers with play functionality.
 */
trait TCardCollection {
  /**
   * Adds a card to the collection.
   * @param card the card to add.
   */
  def addCard(card: TCard): Unit

  /**
   * Removes the card at the given index from the collection.
   * @param index the zero-based position of the card to remove.
   */
  def removeCard(index: Int): Unit

  /**
   * Adds a joker to the collection.
   * @param joker the joker to add.
   */
  def addJoker(joker: TJoker): Unit

  /**
   * Removes the joker at the given index from the collection.
   * @param index the zero-based position of the joker to remove.
   */
  def removeJoker(index: Int): Unit

  /**
   * Plays the cards at the given indices.
   *
   * Returns the corresponding cards and removes them from the hand.
   * Indices must be valid and the list must contain between 1 and 5 entries.
   *
   * @param indices the positions of the cards to play.
   * @return the list of played cards, in the order the indices were given.
   */
  def playCards(indices: List[Int]): List[TCard]

  /** @return all cards currently held. */
  def getCards: ArrayBuffer[TCard]

  /** @return all jokers currently held. */
  def getJokers: ArrayBuffer[TJoker]

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
}
