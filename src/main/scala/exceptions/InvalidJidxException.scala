package cl.uchile.dcc
package exceptions

/**
 * Thrown when attempting to remove a joker using an index that does not
 * exist in the hand.
 *
 * @param message a description of the error.
 */
class InvalidJidxException(message: String) extends Exception(message)

