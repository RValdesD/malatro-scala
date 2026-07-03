package cl.uchile.dcc
package exceptions.discard

/**
 * Thrown when attempting to discard less than one card (minimum).
 *
 * @param message a description of the error.
 */
class CardMinDiscardException(message: String) extends Exception(message)