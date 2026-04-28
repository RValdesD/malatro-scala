package cl.uchile.dcc
package rank
import cl.uchile.dcc.rank.classes.{OddClass, EvenClass, FigureClass}
import cl.uchile.dcc.rank.numbers.figure.{Jack, King, Queen}
import cl.uchile.dcc.rank.numbers.odd.{Ace, Five, Nine, Seven, Three}
import cl.uchile.dcc.rank.numbers.even.{Eight, Four, Six, Ten, Two}
import cl.uchile.dcc.suit.{Clovers, Hearts, Spades}

import munit.FunSuite

class RankTest extends FunSuite {

  test("Ace has order 1, value 11, and is Odd") {
    val ace = new Ace
    assertEquals(ace.order, 1)
    assertEquals(ace.value, 11)
    assertEquals(ace.rankClass.toString, "Odd")
  }

  test("Two has order 2, value 2, and is Even") {
    val two = new Two
    assertEquals(two.order, 2)
    assertEquals(two.value, 2)
    assertEquals(two.rankClass.toString, "Even")
  }

  test("Jack is a Figure with order 11 and value 10") {
    val jack = new Jack
    assertEquals(jack.order, 11)
    assertEquals(jack.value, 10)
    assertEquals(jack.rankClass.toString, "Figure")
  }

  test("Queen is a Figure with order 12") {
    assertEquals(new Queen().order, 12)
    assertEquals(new Queen().rankClass.toString, "Figure")
  }

  test("King is a Figure with order 13") {
    assertEquals(new King().order, 13)
    assertEquals(new King().rankClass.toString, "Figure")
  }

  test("All even ranks carry EvenClass") {
    val evens: List[Rank] = List(new Two, new Four, new Six, new Eight, new Ten)
    assert(evens.forall(_.rankClass.toString == "Even"))
  }

  test("All odd ranks carry OddClass") {
    val odds: List[Rank] = List(new Ace, new Three, new Five, new Seven, new Nine)
    assert(evens.forall(_.rankClass.toString == "Odd"))
  }

  test("All 13 ranks are ordered correctly from Ace to King") {
    val all: List[Rank] = List(
      new Ace, new Two, new Three, new Four, new Five, new Six, new Seven,
      new Eight, new Nine, new Ten, new Jack, new Queen, new King
    )
    assertEquals(all.map(_.order), (1 to 13).toList)
  }

  test("Rank name matches its identity") {
    assertEquals(new Ace().name,   "Ace")
    assertEquals(new Jack().name,  "Jack")
    assertEquals(new Queen().name, "Queen")
    assertEquals(new King().name,  "King")
    assertEquals(new Ten().name,   "Ten")
  }

  test("Rank toString includes name, order, and value") {
    val king = new King
    assert(king.toString.contains("King"))
    assert(king.toString.contains("13"))
    assert(king.toString.contains("10"))
  }
}
