package cl.uchile.dcc
package rank

import rank.classes.{FigureClass, OddClass, EvenClass}
import rank.RankClass
import cl.uchile.dcc.rank.numbers.figure.{Jack, King, Queen}
import cl.uchile.dcc.rank.numbers.odd.{Ace, Three, Five, Seven, Nine}
import cl.uchile.dcc.rank.numbers.even.{Two, Four, Six, Eight, Ten}

class RankTest extends munit.FunSuite {

  test("Ace has order 1, value 11, and is Odd") {
    val ace = new Ace
    assertEquals(ace.order, 1)
    assertEquals(ace.value, 11)
    assertEquals(ace.rankClass, OddClass)
  }

  test("Two has order 2, value 2, and is Even") {
    val two = new Two
    assertEquals(two.order, 2)
    assertEquals(two.value, 2)
    assertEquals(???)
  }

  test("Jack is a Figure with order 11 and value 10") {
    val jack = new Jack
    assertEquals(jack.order, 11)
    assertEquals(jack.value, 10)
    assertEquals(???)
  }

  test("Queen is a Figure with order 12") {
    val queen = new Queen()
    assertEquals(queen.order, 12)
    assertEquals(???)
  }

  test("King is a Figure with order 13") {
    val king = new King()
    assertEquals(king.order, 13)
    assertEquals(???)
  }

  test("Even ranks have EvenClass") {
    val evenRanks: List[Rank] = List(new Two, new Four, new Six, new Eight, new Ten)
    assert(???)
  }

  test("Odd ranks (excluding Ace) have OddClass") {
    val oddRanks: List[Rank] = List(new Three, new Five, new Seven, new Nine)
    assert(???)
  }

  test("Ranks are ordered correctly across the full deck") {
    val allRanks: List[Rank] = List(
      new Ace, new Two, new Three, new Four, new Five,
      new Six, new Seven, new Eight, new Nine, new Ten,
      new Jack, new Queen, new King
    )
    val orders = allRanks.map(_.order)
    assertEquals(orders, (1 to 13).toList)
  }
}
