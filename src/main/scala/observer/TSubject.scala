package cl.uchile.dcc
package observer

/**
 * Represents a subject in the Observer design pattern.
 *
 * A subject maintains a list of TObserver instances and notifies them
 * when a relevant state change occurs.
 */
trait TSubject {
  private var observers: List[TObserver] = List()

  /**
   * Registers an observer to receive future notifications.
   *
   * @param observer the observer to add.
   */
  def addObserver(observer: TObserver): Unit = {
    observers = observer :: observers
  }

  /**
   * Removes an observer so it no longer receives notifications.
   *
   * @param observer the observer to remove.
   */
  def removeObserver(observer: TObserver): Unit = {
    observers = observers.filterNot(_.equals(observer))
  }

  /**
   * Notifies all registered observers of a state change.
   */
  protected def notifyObservers(): Unit = {
    var i = 0
    while (i < observers.size) {
      observers(i).update()
      i += 1
    }
  }
}

