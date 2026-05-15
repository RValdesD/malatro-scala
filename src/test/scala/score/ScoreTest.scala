package cl.uchile.dcc
package score
import munit.FunSuite


class ConcreteScore(val chips: Int, val multiplier: Double) extends Score

class ScoreTest extends FunSuite {

  test("Score total is chips multiplied by multiplier") {
    val s = new ConcreteScore(100, 2.5)
    assertEquals(s.total, 250.0)
  }

  test("Score exposes chips and multiplier correctly") {
    val s = new ConcreteScore(50, 3.0)
    assertEquals(s.chips, 50)
    assertEquals(s.multiplier, 3.0)
  }

  test("Score with zero chips yields zero total") {
    assertEquals(new ConcreteScore(0, 5.0).total, 0.0)
  }

  test("Score with zero multiplier yields zero total") {
    assertEquals(new ConcreteScore(200, 0.0).total, 0.0)
  }
}
