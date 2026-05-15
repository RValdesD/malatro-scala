package cl.uchile.dcc
package hand
import card.TCard
import joker.TJoker

import scala.collection.mutable.ArrayBuffer

/**
 * Represents a collection of playing cards and jokers.
 *
 * Provides operations for adding, retrieving, and counting cards
 * contained in the collection.
 */
trait TCardCollection {
  /**
   * Adds a card to the collection.
   *
   * @param card card to add
   */
  def placeCard(card: TCard): Unit

  /**
   * Adds a joker to the collection.
   *
   * @param joker joker to add
   */
  def placeJoker(joker: TJoker): Unit

  /** @return all cards in the collection */
  def getCards: ArrayBuffer[TCard]

  /** @return all jokers in the collection */
  def getJokers: ArrayBuffer[TJoker]

  /**
   * Retrieves a card at the specified index.
   *
   * @param index index of the card
   * @return card at `index`
   */
  def getCard(index: Int): TCard

  /**
   * Retrieves a joker at the specified index.
   *
   * @param index index of the joker
   * @return joker at `index`
   */
  def getJoker(index: Int): TJoker

  /** @return number of cards in the collection */
  def cardCount: Int

  /** @return number of jokers in the collection */
  def jokerCount: Int
}