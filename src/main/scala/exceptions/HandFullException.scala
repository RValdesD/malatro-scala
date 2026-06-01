package cl.uchile.dcc
package exceptions

/**
 * Thrown when attempting to add cards to a hand that has reached
 * maximum capacity (8 cards).
 *
 * @param message error description.
 */
class HandFullException(message: String) extends Exception(message)

