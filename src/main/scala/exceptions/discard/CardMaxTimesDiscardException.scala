package cl.uchile.dcc
package exceptions.discard

/**
 * Thrown when attempting to discard more than thrice (maximum).
 *
 * @param message a description of the error.
 */
class CardMaxTimesDiscardException(message: String) extends Exception(message)
