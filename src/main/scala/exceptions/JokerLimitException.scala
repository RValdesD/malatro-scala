package cl.uchile.dcc
package exceptions

/**
 * Thrown when attempting to add a joker to a hand that already holds
 * 2 jokers (maximum).
 *
 * @param message error description.
 */

class JokerLimitException(message: String) extends Exception(message)

