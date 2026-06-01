package cl.uchile.dcc
package hand
import card.TCard
import joker.TJoker
import exceptions.{HandFullException, InvalidCidxException, InvalidJidxException, JokerLimitException}
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ArrayBuffer

/**
 * Abstract base for a hand, holding a mutable collection of cards and jokers.
 *
 * A hand may hold at most 8 cards and at most 2 jokers.
 * Cards and jokers are stored separately and accessed by index or as an
 * immutable list. Direct removal is protected — external callers interact
 * through `playCards` only.
 */
abstract class AHand extends TCardCollection {
  private val MaxCards  = 8
  private val MaxJokers = 2

  private val cards:  ArrayBuffer[TCard]  = ArrayBuffer()
  private val jokers: ArrayBuffer[TJoker] = ArrayBuffer()

  /**
   * Adds a card to the hand.
   *
   * @param card the card to add.
   * @throws HandFullException if the hand already holds 8 cards.
   */
  def addCard(card: TCard): Unit = {
    if (cards.length >= MaxCards)
      throw new HandFullException(
        s"Cannot add card: hand is full (maximum $MaxCards cards)."
      )
    cards.append(card)
  }

  /**
   * Removes the card at the given index from the hand.
   *
   * @param index the zero-based position of the card to remove.
   * @throws InvalidCardIndexException if the index is out of range.
   */
  protected def removeCard(index: Int): Unit = {
    if (index < 0 || index >= cards.length)
      throw new InvalidCidxException(
        s"Cannot remove card: index $index does not exist (hand has ${cards.length} cards)."
      )
    cards.remove(index)
  }

  /**
   * Adds a joker to the hand.
   *
   * @param joker the joker to add.
   * @throws JokerLimitException if the hand already holds 2 jokers.
   */
  def addJoker(joker: TJoker): Unit = {
    if (jokers.length >= MaxJokers)
      throw new JokerLimitException(
        s"Cannot add joker: hand already holds the maximum of $MaxJokers jokers."
      )
    jokers.append(joker)
  }

  /**
   * Removes the joker at the given index from the hand.
   *
   * @param index the zero-based position of the joker to remove.
   * @throws InvalidJokerIndexException if the index is out of range.
   */
  protected def removeJoker(index: Int): Unit = {
    if (index < 0 || index >= jokers.length)
      throw new InvalidJidxException(
        s"Cannot remove joker: index $index does not exist (hand has ${jokers.length} jokers)."
      )
    jokers.remove(index)
  }

  /**
   * Plays the cards at the given indices.
   *
   * Collects the cards at each index, then removes them from the hand
   * in descending index order so earlier removals do not shift
   * the positions of later ones.
   *
   * @param indices a list of 1 to 5 valid card indices to play.
   * @return the list of played cards in the order the indices were given.
   */
  def playCards(indices: List[Int]): List[TCard] = {
    val played = indices.map(i => cards(i))
    indices.sorted.reverse.foreach(i => cards.remove(i))
    played
  }

  /** @return all cards currently in the hand, as an immutable list. */
  def getCards: List[TCard] = cards.toList

  /** @return all jokers currently in the hand, as an immutable list. */
  def getJokers: List[TJoker] = jokers.toList

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



