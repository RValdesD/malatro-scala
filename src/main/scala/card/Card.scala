package cl.uchile.dcc
package card
import rank.Rank
import cl.uchile.dcc.suit.Suit

/**
 * Standard playing card combining a rank and suit.
 *
 * @param rank the rank of this card.
 * @param suit the suit of this card.
 */
class Card(rank: Rank, suit: Suit) extends ACard(rank, suit)
