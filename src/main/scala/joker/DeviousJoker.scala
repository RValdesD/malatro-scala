package cl.uchile.dcc
package joker

/**
 * Represents the "Devious Joker" card.
 *
 * Grants an additional 100 chips when the played hand
 * contains a Straight.
 */
class DeviousJoker extends AJoker("Devious Joker"):

  /** Bonus effect granted by this joker. */
  val description: String = "+100 chips if hand contains a Straight"
