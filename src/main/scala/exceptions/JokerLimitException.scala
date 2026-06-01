package cl.uchile.dcc
package exceptions

/**
 * Thrown when attempting to add a joker to a hand that already has
 * a maximum of 2 jokers.
 *
 * @param message error description.
 */

class JokerLimitException(message: String) extends Exception(message)

