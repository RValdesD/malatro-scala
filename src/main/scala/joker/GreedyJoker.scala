package cl.uchile.dcc
package joker

/**
 * Represents the "Greedy Joker" card.
 *
 * Grants additional chips for each played card.
 */
class GreedyJoker extends AJoker("Greedy Joker"):

  /** Bonus effect granted by this joker. */
  val description: String =
    "Played cards each give +3 chips