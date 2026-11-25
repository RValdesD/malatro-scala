package cl.uchile.dcc
package exceptions

/**
 * Thrown when attempting to add cards to a hand that already holds 8 (maximum).
 *
 * @param message error description.
 */
class HandFullException(message: String) extends Exception(message)

