package cl.uchile.dcc
package exceptions.discard

/**
 * Thrown when attempting to discard an invalid list of indices (out of bounds).
 *
 * @param message a description of the error.
 */
class CardInvalidListidxDiscardException(message: String) extends Exception(message) 
