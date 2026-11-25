package cl.uchile.dcc
package exceptions

/**
 * Thrown when attempting to remove a card using an invalid index (nonexistent in hand).
 *
 * @param message a description of the error.
 */
class InvalidCidxException(message: String) extends Exception(message)


