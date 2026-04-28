package cl.uchile.dcc
package hand
import munit.FunSuite
import cl.uchile.dcc.card.Card
import cl.uchile.dcc.joker.{GreedyJoker, EvenSteven, ScaryFace}
import cl.uchile.dcc.rank.numbers.even.Ten
import cl.uchile.dcc.rank.numbers.figure.{Jack, King, Queen}
import cl.uchile.dcc.suit.{Clovers, Hearts, Spades}
import cl.uchile.dcc.rank.numbers.odd.Ace
class HandTest extends FunSuite {

  var hand: Hand = _

  override def beforeEach(context: BeforeEach): Unit = {
    hand = new Hand
  }

  test("Hand starts with no cards or jokers") {
    assertEquals(hand.cardCount,  0)
    assertEquals(hand.jokerCount, 0)
  }

  test("placeCard increases card count") {
    hand.placeCard(new Card(new Ace, new Spades))
    assertEquals(hand.cardCount, 1)
  }

  test("placeJoker increases joker count") {
    hand.placeJoker(new GreedyJoker)
    assertEquals(hand.jokerCount, 1)
  }

  test("getCard retrieves card at correct index") {
    val card = new Card(new Ace, new Hearts)
    hand.placeCard(card)
    assertEquals(hand.getCard(0), card)
  }

  test("getJoker retrieves joker at correct index") {
    val joker = new EvenSteven
    hand.placeJoker(joker)
    assertEquals(hand.getJoker(0), joker)
  }

  test("getCard throws on out-of-bounds index") {
    intercept[IndexOutOfBoundsException] { hand.getCard(0) }
  }

  test("getJoker throws on out-of-bounds index") {
    intercept[IndexOutOfBoundsException] { hand.getJoker(0) }
  }

  test("Hand holds multiple cards and jokers independently") {
    hand.placeCard(new Card(new Ace,  new Spades))
    hand.placeCard(new Card(new King, new Hearts))
    hand.placeCard(new Card(new Ten,  new Clovers))
    hand.placeJoker(new GreedyJoker)
    hand.placeJoker(new ScaryFace)
    assertEquals(hand.cardCount,  3)
    assertEquals(hand.jokerCount, 2)
  }

  test("getCards contains all placed cards") {
    val c1 = new Card(new Ace,  new Spades)
    val c2 = new Card(new King, new Hearts)
    hand.placeCard(c1)
    hand.placeCard(c2)
    assert(hand.getCards.contains(c1))
    assert(hand.getCards.contains(c2))
  }

  test("getJokers contains all placed jokers") {
    val j1 = new GreedyJoker
    val j2 = new EvenSteven
    hand.placeJoker(j1)
    hand.placeJoker(j2)
    assert(hand.getJokers.contains(j1))
    assert(hand.getJokers.contains(j2))
  }
}
