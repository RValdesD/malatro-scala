package cl.uchile.dcc
package joker

/**
 * Represents the "Even Steven" joker card.
 *
 * Grants additional chips for each played card
 * with an even rank.
 */
class EvenSteven extends AJoker("Even Steven"):

  /** Bonus effect granted by this joker. */
  val description: String =
    "Played even-ranked cards each give +4 chips"
