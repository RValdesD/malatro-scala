package cl.uchile.dcc
package observer

import munit.FunSuite

/**
 * A minimal TObserver used only for testing TSubject registration and notification.
 */
class CountingObserver extends TObserver {
  private var count: Int = 0
  def updateCount: Int = count
  override def update(): Unit = { count = count + 1 }
}

/**
 * A minimal TSubject used only for testing notification behavior.
 */
class TestSubject extends TSubject {
  def triggerNotify(): Unit = notifyObservers()
}

/**
 * Test suite for TObserver and TSubject.
 *
 * Tests observer registration, removal, and notification dispatch.
 */
class ObserverTest extends FunSuite {

  /**
   * Tests that a registered observer is notified when notifyObservers is called.
   *
   * Verifies the basic registration and notification flow.
   */
  test("registered observer receives a notification") {
    val subject  = new TestSubject
    val observer = new CountingObserver
    subject.addObserver(observer)
    subject.triggerNotify()
    assertEquals(observer.updateCount, 1)
  }

  /**
   * Tests that multiple registered observers all receive a notification.
   *
   * Verifies the loop over all observers dispatches to each one.
   */
  test("multiple registered observers all receive a notification") {
    val subject    = new TestSubject
    val observerA  = new CountingObserver
    val observerB  = new CountingObserver
    subject.addObserver(observerA)
    subject.addObserver(observerB)
    subject.triggerNotify()
    assertEquals(observerA.updateCount, 1)
    assertEquals(observerB.updateCount, 1)
  }

  /**
   * Tests that a removed observer no longer receives notifications.
   *
   * Verifies removeObserver correctly excludes the observer from future calls.
   */
  test("removed observer does not receive a notification") {
    val subject  = new TestSubject
    val observer = new CountingObserver
    subject.addObserver(observer)
    subject.removeObserver(observer)
    subject.triggerNotify()
    assertEquals(observer.updateCount, 0)
  }

  /**
   * Tests that notifyObservers can be called multiple times, incrementing
   * each observer's count each time.
   *
   * Verifies repeated notification dispatch.
   */
  test("repeated notifications increment observer count each time") {
    val subject  = new TestSubject
    val observer = new CountingObserver
    subject.addObserver(observer)
    subject.triggerNotify()
    subject.triggerNotify()
    subject.triggerNotify()
    assertEquals(observer.updateCount, 3)
  }

  /**
   * Tests that a subject with no observers does not throw when notified.
   *
   * Verifies the empty-list case of the notification loop.
   */
  test("notifying with no registered observers does not throw") {
    val subject = new TestSubject
    subject.triggerNotify()
  }
}

