package cl.uchile.dcc
package hand
import cl.uchile.dcc.card.TCard
import cl.uchile.dcc.joker.TJoker

import scala.collection.mutable.ArrayBuffer

trait TCardCollection {
  def placeCard(card: TCard): Unit
  def placeJoker(joker: TJoker): Unit
  def getCards: ArrayBuffer[TCard]
  def getJokers: ArrayBuffer[TJoker]
  def getCard(index: Int): TCard
  def getJoker(index: Int): TJoker
  def cardCount: Int
  def jokerCount: Int
}
