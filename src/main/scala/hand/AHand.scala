package cl.uchile.dcc
package hand
import cl.uchile.dcc.card.TCard
import cl.uchile.dcc.joker.TJoker

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ArrayBuffer

/**
 * Represents an abstract base for a hand, holding a mutable collection of cards and jokers.
 *
 * Cards and jokers are stored separately and accessed by index or as a full list.
 * Both collections start empty.
 */
abstract class AHand extends TCardCollection {
  private val cards:  ArrayBuffer[TCard]  = ArrayBuffer()
  private val jokers: ArrayBuffer[TJoker] = ArrayBuffer()

  /**
   * Adds a card to the hand.
   * @param card the card to add.
   */
  def placeCard(card: TCard): Unit = cards.append(card)

  /**
   * Adds a joker to the hand.
   * @param joker the joker to add.
   */
  def placeJoker(joker: TJoker): Unit = jokers.append(joker)

  /** @return all cards currently in the hand. */
  def getCards: ArrayBuffer[TCard] = cards

  /** @return all jokers currently in the hand. */
  def getJokers: ArrayBuffer[TJoker] = jokers

  /**
   * Returns the card at the given index.
   * @param index the zero-based position to retrieve.
   * @throws IndexOutOfBoundsException if the index is out of range.
   */
  def getCard(index: Int): TCard = {
    if (index < 0 || index >= cards.length)
      throw new IndexOutOfBoundsException(
        s"Index $index is out of bounds for cards of size ${cards.length}"
      )
    cards(index)
  }

  /**
   * Returns the joker at the given index.
   * @param index the zero-based position to retrieve.
   * @throws IndexOutOfBoundsException if the index is out of range.
   */
  def getJoker(index: Int): TJoker = {
    if (index < 0 || index >= jokers.length)
      throw new IndexOutOfBoundsException(
        s"Index $index is out of bounds for jokers of size ${jokers.length}"
      )
    jokers(index)
  }

  /** @return the number of cards in the hand. */
  def cardCount: Int = cards.length

  /** @return the number of jokers in the hand. */
  def jokerCount: Int = jokers.length
}
