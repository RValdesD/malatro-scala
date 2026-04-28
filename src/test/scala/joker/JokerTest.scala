package cl.uchile.dcc
package joker
import munit.FunSuite

class JokerTest extends FunSuite {

  test("GreedyJoker has correct name and description") {
    val j1 = new GreedyJoker
    assertEquals(j1.getName,    "Greedy Joker")
    assertEquals(j1.description, "Played cards each give +3 chips")
  }

  test("DeviousJoker has correct name and description") {
    val j2 = new DeviousJoker
    assertEquals(j2.getName,    "Devious Joker")
    assertEquals(j2.description, "+100 chips if hand contains a Straight")
  }

  test("EvenSteven has correct name and description") {
    val j3 = new EvenSteven
    assertEquals(j3.getName,    "Even Steven")
    assertEquals(j3.description, "Played even-ranked cards each give +4 chips")
  }

  test("ScaryFace has correct name and description") {
    val j3 = new ScaryFace
    assertEquals(j3.getName,    "Scary Face")
    assertEquals(j3.description, "Played face cards each give +30 chips")
  }

  test("All jokers are instances of TJoker") {
    val jokers: List[TJoker] = List(new GreedyJoker, new DeviousJoker, new EvenSteven, new ScaryFace)
    assert(jokers.forall(_.getName.nonEmpty))
  }

  test("Joker equals - same name means equal") {
    val a = new GreedyJoker
    val b = new GreedyJoker
    assertEquals(a, b)
  }

  test("Joker equals - different jokers are not equal") {
    assert(!new GreedyJoker().equals(new ScaryFace()))
  }

  test("Jokers sort alphabetically by name") {
    val sorted = List(new ScaryFace, new GreedyJoker, new EvenSteven, new DeviousJoker).sorted
    assertEquals(sorted.map(_.getName), List("Devious Joker", "Even Steven", "Greedy Joker", "Scary Face"))
  }

  test("Joker toString includes name and description") {
    val j = new ScaryFace
    assert(j.toString.contains("Scary Face"))
    assert(j.toString.contains("Played face cards each give +30 chips"))
  }
}