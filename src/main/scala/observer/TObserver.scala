package cl.uchile.dcc
package observer

/**
 * Represents an observer in the Observer design pattern.
 *
 * An observer registers itself with a TSubject and is notified
 * whenever the subject's state changes.
 */
trait TObserver {
  /**
   * Called by the subject when a relevant state change occurs.
   */
  def update(): Unit
}
