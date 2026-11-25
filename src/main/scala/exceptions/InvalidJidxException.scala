package cl.uchile.dcc
package exceptions

/**
 * Thrown when attempting to remove a joker using an invalid index (nonexistent in hand).
 *
 * @param message a description of the error.
 */
class InvalidJidxException(message: String) extends Exception(message)

