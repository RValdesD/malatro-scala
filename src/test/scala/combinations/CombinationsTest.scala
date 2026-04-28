package cl.uchile.dcc
package combinations

class CombinationTest extends FunSuite {
  
  val evaluator = new CombinationEvaluator

  //everything hearts
  val aH  = new Card(new Ace,   new Hearts)
  val twoH  = new Card(new Two,   new Hearts)
  val threeH = new Card(new Three, new Hearts)
  val fourH  = new Card(new Four,  new Hearts)
  val fiveH  = new Card(new Five,  new Hearts)
  val sixH   = new Card(new Six,   new Hearts)
  val tenH   = new Card(new Ten,   new Hearts)
  val jH     = new Card(new Jack,  new Hearts)
  val qH     = new Card(new Queen, new Hearts)
  val kH     = new Card(new King,  new Hearts)

  //mixed: suits
  val aS   = new Card(new Ace,   new Spades)
  val twoS = new Card(new Two,   new Spades)
  val twoC = new Card(new Two,   new Clover)
  val twoD = new Card(new Two,   new Diamonds)
  val threeS = new Card(new Three, new Spades)
  val fourS  = new Card(new Four,  new Spades)
  val fiveS  = new Card(new Five,  new Spades)
  val tenS   = new Card(new Ten,   new Spades)
  val jS     = new Card(new Jack,  new Spades)
  val qS     = new Card(new Queen, new Spades)
  val kS     = new Card(new King,  new Spades)
  val kC     = new Card(new King,  new Clover)

  //straight flush

  test("StraightFlush - valid: A-2-3-4-5 same suit (low ace)") {
    val combo = new StraightFlush
    assert(combo.isValid(List(aH, twoH, threeH, fourH, fiveH)))
  }

  test("StraightFlush - valid: 10-J-Q-K-A same suit (high ace)") {
    val combo = new StraightFlush
    assert(combo.isValid(List(tenH, jH, qH, kH, aH)))
  }

  test("StraightFlush - invalid: consecutive but mixed suits") {
    val combo = new StraightFlush
    assert(!combo.isValid(List(aH, twoS, threeH, fourH, fiveH)))
  }

  test("StraightFlush - invalid: same suit but not consecutive") {
    val combo = new StraightFlush
    assert(!combo.isValid(List(aH, twoH, threeH, fourH, sixH)))
  }

  test("StraightFlush - base score: 100 chips, x8 multiplier") {
    val combo = new StraightFlush
    assertEquals(combo.chips,      100)
    assertEquals(combo.multiplier, 8.0)
  }

  //flush

  test("Flush - valid: five hearts, non-consecutive") {
    val combo = new Flush
    assert(combo.isValid(List(aH, twoH, fourH, sixH, kH)))
  }

  test("Flush - invalid: four cards only") {
    val combo = new Flush
    assert(!combo.isValid(List(aH, twoH, fourH, sixH)))
  }

  test("Flush - invalid: mixed suits") {
    val combo = new Flush
    assert(!combo.isValid(List(aH, twoH, threeH, fourH, fiveS)))
  }

  test("Flush - base score: 35 chips, x4 multiplier") {
    val combo = new Flush
    assertEquals(combo.chips,      35)
    assertEquals(combo.multiplier, 4.0)
  }

  //straight

  test("Straight - valid: A-2-3-4-5 mixed suits (low ace)") {
    val combo = new Straight
    assert(combo.isValid(List(aH, twoS, threeH, fourH, fiveS)))
  }

  test("Straight - valid: 10-J-Q-K-A mixed suits (high ace)") {
    val combo = new Straight
    assert(combo.isValid(List(tenH, jS, qH, kS, aH)))
  }

  test("Straight - invalid: gap in sequence") {
    val combo = new Straight
    assert(!combo.isValid(List(aH, twoS, threeH, fourH, sixS)))
  }

  test("Straight - invalid: fewer than 5 cards") {
    val combo = new Straight
    assert(!combo.isValid(List(aH, twoS, threeH, fourH)))
  }

  test("Straight - base score: 30 chips, x4 multiplier") {
    val combo = new Straight
    assertEquals(combo.chips,      30)
    assertEquals(combo.multiplier, 4.0)
  }

  //3 of a kind

  test("ThreeOfAKind - valid: three twos") {
    val combo = new ThreeOfAKind
    assert(combo.isValid(List(twoH, twoS, twoC)))
  }

  test("ThreeOfAKind - invalid: only a pair") {
    val combo = new ThreeOfAKind
    assert(!combo.isValid(List(twoH, twoS, threeH)))
  }

  test("ThreeOfAKind - base score: 30 chips, x3 multiplier") {
    val combo = new ThreeOfAKind
    assertEquals(combo.chips,      30)
    assertEquals(combo.multiplier, 3.0)
  }

  //teto pair
  
  test("Pair - valid: two kings") {
    val combo = new Pair
    assert(combo.isValid(List(kH, kS)))
  }

  test("Pair - invalid: no matching ranks") {
    val combo = new Pair
    assert(!combo.isValid(List(aH, twoS)))
  }

  test("Pair - base score: 10 chips, x2 multiplier") {
    val combo = new Pair
    assertEquals(combo.chips,      10)
    assertEquals(combo.multiplier, 2.0)
  }

  //highcard

  test("HighCard - valid: any non-empty hand") {
    val combo = new HighCard
    assert(combo.isValid(List(aH)))
  }

  test("HighCard - base score: 5 chips, x1 multiplier") {
    val combo = new HighCard
    assertEquals(combo.chips,      5)
    assertEquals(combo.multiplier, 1.0)
  }

  //evaluation: priority

  test("Evaluator picks StraightFlush over Flush and Straight") {
    val result = evaluator.evaluate(List(aH, twoH, threeH, fourH, fiveH))
    assertEquals(result.chips,      100)
    assertEquals(result.multiplier, 8.0)
  }

  test("Evaluator picks Flush over Straight when same suit but non-consecutive") {
    val result = evaluator.evaluate(List(aH, twoH, fourH, sixH, kH))
    assertEquals(result.chips,      35)
    assertEquals(result.multiplier, 4.0)
  }

  test("Evaluator picks Straight over ThreeOfAKind") {
    val result = evaluator.evaluate(List(aH, twoS, threeH, fourH, fiveS))
    assertEquals(result.chips,      30)
    assertEquals(result.multiplier, 4.0)
  }

  test("Evaluator picks ThreeOfAKind over Pair") {
    val result = evaluator.evaluate(List(twoH, twoS, twoC, kH, aS))
    assertEquals(result.chips,      30)
    assertEquals(result.multiplier, 3.0)
  }

  test("Evaluator picks Pair over HighCard") {
    val result = evaluator.evaluate(List(kH, kS, aH))
    assertEquals(result.chips,      10)
    assertEquals(result.multiplier, 2.0)
  }

  test("Evaluator falls back to HighCard for unmatched hand") {
    val result = evaluator.evaluate(List(aH, threeS, fiveH, kC))
    assertEquals(result.chips,      5)
    assertEquals(result.multiplier, 1.0)
  }


  //edge cases for Ace (since it can take values 1 or 14 within a stair yet has single value 1)

  test("Straight - A-2-3-4-5 is valid (Ace as 1)") {
    val combo = new Straight
    assert(combo.isValid(List(aS, twoH, threeS, fourH, fiveS)))
  }

  test("Straight - 10-J-Q-K-A is valid (Ace as 14)") {
    val combo = new Straight
    assert(combo.isValid(List(tenS, jH, qS, kH, aS)))
  }

  test("StraightFlush - A-2-3-4-5 same suit is valid (Ace as 1)") {
    val combo = new StraightFlush
    assert(combo.isValid(List(aH, twoH, threeH, fourH, fiveH)))
  }

  test("StraightFlush - 10-J-Q-K-A same suit is valid (Ace as 14)") {
    val combo = new StraightFlush
    assert(combo.isValid(List(tenH, jH, qH, kH, aH)))
  }
}

