package cl.uchile.dcc
package hand
import card.TCard
import joker.TJoker
import exceptions.{HandFullException, InvalidCidxException, InvalidJidxException, JokerLimitException}
import exceptions.discard.{CardInvalidListidxDiscardException, CardMaxDiscardException, CardMaxTimesDiscardException, CardMinDiscardException}
import exceptions.play.{CardInvalidListidxPlayedException, CardMaxPlayedException, CardMaxSetPlayedException, CardMinPlayedException}
import scala.collection.mutable.ArrayBuffer

/**
 * Abstract base for a hand, holding a mutable collection of cards and jokers.
 *
 * A hand may hold at most 8 cards and at most 2 jokers.
 * Cards may be played at most 3 times and discarded at most 3 times per hand.
 * Direct removal is protected — external callers interact through
 * `playCards` and `discardCards` only.
 * To this end, we build the Aclass with a few values that will be used to show
 * what the min and max values ought to be.
 */
abstract class AHand extends TCardCollection {
  private val MaxCards = 8
  private val MaxJokers = 2
  private val MaxPlays = 3
  private val MaxDiscards = 3
  private val MinPlay = 1
  private val MaxPlay = 5
  private val MinDiscard = 1
  private val MaxDiscard = 5
  private val cards: ArrayBuffer[TCard]  = ArrayBuffer()
  private val jokers: ArrayBuffer[TJoker] = ArrayBuffer()
  private var playCount: Int = 0
  private var discardCount: Int = 0

  /** @return how many times playCards has been called. */
  def timesPlayed: Int = playCount

  /** @return how many times discardCards has been called. */
  def timesDiscarded: Int = discardCount

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
   * Validates a list of indices against the current card collection.
   *
   * Checks every index regardless of order.
   *
   * @param indices the indices to validate.
   * @return true if all indices are within bounds, false otherwise.
   */
  private def allIndicesValid(indices: List[Int]): Boolean =
    indices.forall(i => i >= 0 && i < cards.length)

  /**
   * Plays the cards at the given indices.
   *
   * Collects the cards at each index, then removes them in descending
   * index order so earlier removals do not shift the positions of later ones.
   *
   * @param indices a list of 1 to 5 valid card indices to play.
   * @return the list of played cards in the order the indices were given.
   * @throws CardMinPlayedException            if fewer than 1 index is given.
   * @throws CardMaxPlayedException            if more than 5 indices are given.
   * @throws CardInvalidListidxPlayedException if any index is out of range.
   * @throws CardMaxSetPlayedException         if the hand has already been played 3 times.
   */
  def playCards(indices: List[Int]): List[TCard] = {
    if (indices.size < MinPlay)
      throw new CardMinPlayedException(
        s"Cannot play: must select at least $MinPlay card."
      )
    if (indices.size > MaxPlay)
      throw new CardMaxPlayedException(
        s"Cannot play: cannot select more than $MaxPlay cards (got ${indices.size})."
      )
    if (!allIndicesValid(indices))
      throw new CardInvalidListidxPlayedException(
        s"Cannot play: one or more indices are out of range."
      )
    if (playCount >= MaxPlays)
      throw new CardMaxSetPlayedException(
        s"Cannot play: hand has already been played $MaxPlays times."
      )
    val played = indices.map(i => cards(i))
    indices.sorted.reverse.foreach(i => cards.remove(i))
    playCount += 1
    played
  }

  /**
   * Discards the cards at the given indices.
   *
   * Removes the corresponding cards from the hand without returning them.
   * Removals are performed in descending index order so earlier removals
   * do not shift the positions of later ones.
   *
   * @param indices a list of 1 to 5 valid card indices to discard.
   * @throws CardMinDiscardException              if fewer than 1 index is given.
   * @throws CardMaxDiscardException              if more than 5 indices are given.
   * @throws CardInvalidListidxDiscardException   if any index is out of range.
   * @throws CardMaxTimesDiscardException         if discard has already been used 3 times.
   */
  def discardCards(indices: List[Int]): Unit = {
    if (indices.size < MinDiscard)
      throw new CardMinDiscardException(
        s"Cannot discard: must select at least $MinDiscard card."
      )
    if (indices.size > MaxDiscard)
      throw new CardMaxDiscardException(
        s"Cannot discard: cannot select more than $MaxDiscard cards (got ${indices.size})."
      )
    if (!allIndicesValid(indices))
      throw new CardInvalidListidxDiscardException(
        s"Cannot discard: one or more indices are out of range."
      )
    if (discardCount >= MaxDiscards)
      throw new CardMaxTimesDiscardException(
        s"Cannot discard: already discarded $MaxDiscards times."
      )
    indices.sorted.reverse.foreach(i => cards.remove(i))
    discardCount += 1
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
   * Returns joker at the given index.
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




