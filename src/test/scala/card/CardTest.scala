package cl.uchile.dcc
package card
import cl.uchile.dcc.rank.numbers.figure.{Jack, King, Queen}
import cl.uchile.dcc.rank.numbers.odd.Ace
import cl.uchile.dcc.rank.numbers.even.{Eight, Ten, Two}
import cl.uchile.dcc.suit.{Hearts, Spades, Clovers}
import munit.FunSuite

class CardTest extends FunSuite {

  test("Card getName returns rank and suit combined") {
    val card = new Card(new Ace, new Spades)
    assertEquals(card.getName, "Ace of Spades")
  }

  test("Card getRank returns the correct rank") {
    val rank = new King
    val card = new Card(rank, new Hearts)
    assertEquals(card.getRank, rank)
  }

  test("Card getSuit returns the correct suit") {
    val suit = new Clovers
    val card = new Card(new Ten, suit)
    assertEquals(card.getSuit, suit)
  }

  test("Card compare - sorted alphabetically by name") {
    val cardA = new Card(new Ace,   new Hearts)
    val cardB = new Card(new King,  new Spades)
    val cardC = new Card(new Queen, new Clovers)
    val sorted = List(cardB, cardC, cardA).sorted
    assertEquals(sorted, List(cardA, cardC, cardB))
  }

  test("Card equals - cards with the same rank and suit are equal") {
    val cardA = new Card(new Ace, new Spades)
    val cardB = new Card(new Ace, new Spades)
    assertEquals(cardA, cardB)
  }

  test("Card equals - cards with different rank or suit are not equal") {
    val cardA = new Card(new Ace,  new Spades)
    val cardB = new Card(new King, new Spades)
    assert(!cardA.equals(cardB))
  }

  test("Card equals - card compared to itself is equal") {
    val card = new Card(new Ace, new Hearts)
    assertEquals(card, card)
  }

  test("Card equals - not equal to a non-card object") {
    val card = new Card(new Ace, new Hearts)
    assertEquals(card.equals("not a card"), false)
  }
}
