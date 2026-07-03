package cl.uchile.dcc
package exceptions.discard

/**
 * Thrown when attempting to discard over than 5 cards (maximum).
 *
 * @param message a description of the error.
 */
class CardMaxDiscardException(message: String) extends Exception(message) 
