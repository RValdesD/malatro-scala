package cl.uchile.dcc
package hand
import card.TCard
import joker.TJoker
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ArrayBuffer

/**
 * Abstract base for a hand, holding a mutable collection of cards and jokers.
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
  def addCard(card: TCard): Unit = cards.append(card)

  /**
   * Removes the card at the given index from the hand.
   * @param index the zero-based position of the card to remove.
   */
  protected def removeCard(index: Int): Unit = cards.remove(index)

  /**
   * Adds a joker to the hand.
   * @param joker the joker to add.
   */
  def addJoker(joker: TJoker): Unit = jokers.append(joker)

  /**
   * Removes the joker at the given index from the hand.
   * @param index the zero-based position of the joker to remove.
   */
  protected def removeJoker(index: Int): Unit = jokers.remove(index)

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


