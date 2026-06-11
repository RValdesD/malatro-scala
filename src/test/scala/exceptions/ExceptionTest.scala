package cl.uchile.dcc
package exceptions
import exceptions.discard.{CardInvalidListidxDiscardException, CardMaxDiscardException, CardMaxTimesDiscardException, CardMinDiscardException}
import exceptions.play.{CardInvalidListidxPlayedException, CardMaxPlayedException, CardMaxSetPlayedException, CardMinPlayedException}
import munit.FunSuite


/**
 * Test suite for custom Hand exception classes.
 *
 * Verifies that each exception carries the correct message and extends
 * the expected type.
 */
class ExceptionTest extends FunSuite {
  
  /**
   * Tests that HandFullException stores and exposes the given message.
   *
   * Verifies that the exception message is accessible via getMessage.
   */
  test("HandFullException carries the correct message") {
    val ex = new HandFullException("hand is full")
    assertEquals(ex.getMessage, "hand is full")
  }

  /**
   * Tests that HandFullException is an instance of Exception.
   *
   * Verifies the inheritance chain.
   */
  test("HandFullException is an Exception") {
    val ex = new HandFullException("hand is full")
    assert(ex.isInstanceOf[Exception])
  }
  
  /**
   * Tests that JokerLimitException stores and exposes the given message.
   *
   * Verifies that the exception message is accessible via getMessage.
   */
  test("JokerLimitException carries the correct message") {
    val ex = new JokerLimitException("joker limit reached")
    assertEquals(ex.getMessage, "joker limit reached")
  }

  /**
   * Tests that JokerLimitException is an instance of Exception.
   *
   * Verifies the inheritance chain.
   */
  test("JokerLimitException is an Exception") {
    val ex = new JokerLimitException("joker limit reached")
    assert(ex.isInstanceOf[Exception])
  }
  
  /**
   * Tests that InvalidCardIndexException stores and exposes the given message.
   *
   * Verifies that the exception message is accessible via getMessage.
   */
  test("InvalidCardIndexException carries the correct message") {
    val ex = new InvalidCidxException("invalid card index")
    assertEquals(ex.getMessage, "invalid card index")
  }

  /**
   * Tests that InvalidCidxException is an instance of Exception.
   *
   * Verifies the inheritance chain.
   */
  test("InvalidCidxException is an Exception") {
    val ex = new InvalidCidxException("invalid card index")
    assert(ex.isInstanceOf[Exception])
  }
  
  /**
   * Tests that InvalidJokerIndexException stores and exposes the given message.
   *
   * Verifies that the exception message is accessible via getMessage.
   */
  test("InvalidJokerIndexException carries the correct message") {
    val ex = new InvalidJidxException("invalid joker index")
    assertEquals(ex.getMessage, "invalid joker index")
  }

  /**
   * Tests that InvalidJidxException is an instance of Exception.
   *
   * Verifies the inheritance chain.
   */
  test("InvalidJidxException is an Exception") {
    val ex = new InvalidJidxException("invalid joker index")
    assert(ex.isInstanceOf[Exception])
  }

  // ── CardMaxSetPlayedException ─────────────────────────────────────────────

  /**
   * Tests that CardMaxSetPlayedException stores and exposes the given message.
   *
   * Verifies that the exception message is accessible via getMessage.
   */
  test("CardMaxSetPlayedException carries the correct message") {
    val ex = new CardMaxSetPlayedException("played too many times")
    assertEquals(ex.getMessage, "played too many times")
  }

  /**
   * Tests that CardMaxSetPlayedException is an instance of Exception.
   *
   * Verifies the inheritance chain.
   */
  test("CardMaxSetPlayedException is an Exception") {
    val ex = new CardMaxSetPlayedException("played too many times")
    assert(ex.isInstanceOf[Exception])
  }

  // ── CardMaxTimesDiscardException ──────────────────────────────────────────

  /**
   * Tests that CardMaxTimesDiscardException stores and exposes the given message.
   *
   * Verifies that the exception message is accessible via getMessage.
   */
  test("CardMaxTimesDiscardException carries the correct message") {
    val ex = new CardMaxTimesDiscardException("discarded too many times")
    assertEquals(ex.getMessage, "discarded too many times")
  }

  /**
   * Tests that CardMaxTimesDiscardException is an instance of Exception.
   *
   * Verifies the inheritance chain.
   */
  test("CardMaxTimesDiscardException is an Exception") {
    val ex = new CardMaxTimesDiscardException("discarded too many times")
    assert(ex.isInstanceOf[Exception])
  }

  // ── CardMaxPlayedException ────────────────────────────────────────────────

  /**
   * Tests that CardMaxPlayedException stores and exposes the given message.
   *
   * Verifies that the exception message is accessible via getMessage.
   */
  test("CardMaxPlayedException carries the correct message") {
    val ex = new CardMaxPlayedException("too many cards played")
    assertEquals(ex.getMessage, "too many cards played")
  }

  /**
   * Tests that CardMaxPlayedException is an instance of Exception.
   *
   * Verifies the inheritance chain.
   */
  test("CardMaxPlayedException is an Exception") {
    val ex = new CardMaxPlayedException("too many cards played")
    assert(ex.isInstanceOf[Exception])
  }

  // ── CardMinPlayedException ────────────────────────────────────────────────

  /**
   * Tests that CardMinPlayedException stores and exposes the given message.
   *
   * Verifies that the exception message is accessible via getMessage.
   */
  test("CardMinPlayedException carries the correct message") {
    val ex = new CardMinPlayedException("too few cards played")
    assertEquals(ex.getMessage, "too few cards played")
  }

  /**
   * Tests that CardMinPlayedException is an instance of Exception.
   *
   * Verifies the inheritance chain.
   */
  test("CardMinPlayedException is an Exception") {
    val ex = new CardMinPlayedException("too few cards played")
    assert(ex.isInstanceOf[Exception])
  }

  // ── CardInvalidListidxPlayedException ─────────────────────────────────────

  /**
   * Tests that CardInvalidListidxPlayedException stores and exposes the given message.
   *
   * Verifies that the exception message is accessible via getMessage.
   */
  test("CardInvalidListidxPlayedException carries the correct message") {
    val ex = new CardInvalidListidxPlayedException("invalid play index")
    assertEquals(ex.getMessage, "invalid play index")
  }

  /**
   * Tests that CardInvalidListidxPlayedException is an instance of Exception.
   *
   * Verifies the inheritance chain.
   */
  test("CardInvalidListidxPlayedException is an Exception") {
    val ex = new CardInvalidListidxPlayedException("invalid play index")
    assert(ex.isInstanceOf[Exception])
  }

  // ── CardMaxDiscardException ───────────────────────────────────────────────

  /**
   * Tests that CardMaxDiscardException stores and exposes the given message.
   *
   * Verifies that the exception message is accessible via getMessage.
   */
  test("CardMaxDiscardException carries the correct message") {
    val ex = new CardMaxDiscardException("too many cards discarded")
    assertEquals(ex.getMessage, "too many cards discarded")
  }

  /**
   * Tests that CardMaxDiscardException is an instance of Exception.
   *
   * Verifies the inheritance chain.
   */
  test("CardMaxDiscardException is an Exception") {
    val ex = new CardMaxDiscardException("too many cards discarded")
    assert(ex.isInstanceOf[Exception])
  }

  // ── CardMinDiscardException ───────────────────────────────────────────────

  /**
   * Tests that CardMinDiscardException stores and exposes the given message.
   *
   * Verifies that the exception message is accessible via getMessage.
   */
  test("CardMinDiscardException carries the correct message") {
    val ex = new CardMinDiscardException("too few cards discarded")
    assertEquals(ex.getMessage, "too few cards discarded")
  }

  /**
   * Tests that CardMinDiscardException is an instance of Exception.
   *
   * Verifies the inheritance chain.
   */
  test("CardMinDiscardException is an Exception") {
    val ex = new CardMinDiscardException("too few cards discarded")
    assert(ex.isInstanceOf[Exception])
  }

  // ── CardInvalidListidxDiscardException ────────────────────────────────────

  /**
   * Tests that CardInvalidListidxDiscardException stores and exposes the given message.
   *
   * Verifies that the exception message is accessible via getMessage.
   */
  test("CardInvalidListidxDiscardException carries the correct message") {
    val ex = new CardInvalidListidxDiscardException("invalid discard index")
    assertEquals(ex.getMessage, "invalid discard index")
  }

  /**
   * Tests that CardInvalidListidxDiscardException is an instance of Exception.
   *
   * Verifies the inheritance chain.
   */
  test("CardInvalidListidxDiscardException is an Exception") {
    val ex = new CardInvalidListidxDiscardException("invalid discard index")
    assert(ex.isInstanceOf[Exception])
  }
}
