



/**
 * Test suite for Suit functionality.
 *
 * Tests name retrieval and toString formatting for all four concrete suit types.
 */
class SuitTest extends FunSuite {

  /**
   * Tests that all four suits have distinct names.
   *
   * Verifies that no two suits share the same name string.
   */
  test("All four suits have distinct names") {
    val suits: List[Suit] = List(new Hearts, new Spades, new Clover, new Diamonds)
    assertEquals(suits.map(_.name).distinct.size, 4)
  }

  /**
   * Tests that each suit's toString returns its name.
   *
   * Verifies the toString implementation for all four suit types.
   */
  test("Each suit toString returns its name") {
    assertEquals(new Hearts().toString,   "Hearts")
    assertEquals(new Spades().toString,   "Spades")
    assertEquals(new Clover().toString,   "Clover")
    assertEquals(new Diamonds().toString, "Diamonds")
  }
}
