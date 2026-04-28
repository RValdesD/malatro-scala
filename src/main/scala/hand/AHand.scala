package cl.uchile.dcc
package hand

import cl.uchile.dcc.card.TCard
import cl.uchile.dcc.joker.TJoker

import scala.collection.mutable.ArrayBuffer

abstract class AHand extends TCardCollection {
  protected val cards:  ArrayBuffer[TCard]  = ArrayBuffer()
  protected val jokers: ArrayBuffer[TJoker] = ArrayBuffer()

  def placeCard(card: TCard): Unit   = cards.append(card)
  def placeJoker(joker: TJoker): Unit = jokers.append(joker)

  def getCards:  ArrayBuffer[TCard]  = cards
  def getJokers: ArrayBuffer[TJoker] = jokers

  def getCard(index: Int): TCard = {
    if (index < 0 || index >= cards.length)
      throw new IndexOutOfBoundsException(
        s"Index $index is out of bounds for cards of size ${cards.length}"
      )
    cards(index)
  }

  def getJoker(index: Int): TJoker = {
    if (index < 0 || index >= jokers.length)
      throw new IndexOutOfBoundsException(
        s"Index $index is out of bounds for jokers of size ${jokers.length}"
      )
    jokers(index)
  }

  def cardCount:  Int = cards.length
  def jokerCount: Int = jokers.length
}
