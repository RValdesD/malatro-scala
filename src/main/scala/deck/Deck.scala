package cl.uchile.dcc
package deck

import card.TCard
import cl.uchile.dcc.hand.Hand
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
 * Represents a deck of cards that can be drawn from and replenished.
 *
 * Maintains a separate discard pile internally so that, once the deck
 * is exhausted, it can be reshuffled back in rather than leaving the
 * round unable to replenish the player's hand.
 */
class Deck {
  private val cards: ArrayBuffer[TCard] = ArrayBuffer()
  private val discardPile: ArrayBuffer[TCard] = ArrayBuffer()

  /**
   * Loads the deck with the given cards, replacing any current contents.
   * @param initialCards the cards to load into the deck.
   */
  def load(initialCards: List[TCard]): Unit = {
    cards.clear()
    cards.appendAll(initialCards)
  }

  /** @return true if the deck has no cards left to draw. */
  def isEmpty: Boolean = cards.isEmpty

  /** @return the number of cards remaining in the deck. */
  def remaining: Int = cards.length

  /**
   * Draws a single card from the top of the deck.
   * @return the drawn card.
   * @throws NoSuchElementException if the deck is empty.
   */
  def draw(): TCard = {
    val card = cards.remove(0)
    card
  }

  /**
   * Draws cards from the deck into the given hand until the hand reaches
   * the target size, or the deck runs out, whichever happens first.
   *
   * @param hand       the hand to draw into.
   * @param targetSize the hand size to draw up to.
   */
  def drawUpTo(hand: Hand, targetSize: Int): Unit = {
    while (hand.cardCount < targetSize && !isEmpty) {
      hand.addCard(draw())
    }
  }

  /**
   * Moves the given cards into the discard pile.
   * @param discarded the cards to move to the discard pile.
   */
  def sendToDiscardPile(discarded: List[TCard]): Unit = {
    discardPile.appendAll(discarded)
  }

  /**
   * Reshuffles the discard pile back into the deck and clears it.
   */
  def reshuffleFromDiscardPile(): Unit = {
    cards.appendAll(Random.shuffle(discardPile.toList))
    discardPile.clear()
  }
}

