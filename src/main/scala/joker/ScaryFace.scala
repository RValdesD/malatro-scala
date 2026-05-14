package cl.uchile.dcc
package joker

/**
 * Represents the "Scary Face" joker card.
 *
 * Grants additional chips for each played face card.
 */
class ScaryFace extends AJoker("Scary Face"):

  /** Bonus effect granted by this joker. */
  val description: String =
    "Played face cards each give +30 chips"