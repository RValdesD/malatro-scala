package cl.uchile.dcc
package hand
import card.Card
import joker.Joker

abstract class Hand {
  def cards: List[Card]
  def jokers: List[Joker]

  def addCard(card: Card): Hand
  def addJoker(joker: Joker): Hand

  def cardCount: Int  = cards.size
  def jokerCount: Int = jokers.size

  override def toString: String =
    s"Hand(cards=[${cards.mkString(", ")}], jokers=[${jokers.mkString(", ")}])"
}

class StandardHand(val cards: List[Card], val jokers: List[Joker]) extends Hand {
  def addCard(card: Card): Hand   = new StandardHand(cards :+ card, jokers)
  def addJoker(joker: Joker): Hand = new StandardHand(cards, jokers :+ joker)
}
