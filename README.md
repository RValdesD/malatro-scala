# Tarea 2 | Entrega Final 2

EP4: excepciones, no alcancé a terminar pero dejé un blueprint
Project structure:

Elements
--------------------------------

score: Score trait (chips, multiplier, total)

rank:  Rank hierarchy - Ace to King, classified as Even, Odd, or Figure

suit:  Four standard suits

card:  TCard / ACard / Card - a playing card combining Rank and Suit

joker:  TJoker / AJoker and four concrete Jokers

hand:  TCardCollection / AHand / Hand - holds cards and jokers

combination:  TCombination / ACombination and six poker combinations 

test: mirrors the src layout, one test suite per package

A Score has a chip count and a multiplier. total is derived as chips × multiplier.

Visual Overview
-------------------------------------
src/main/scala
├── card/        
├── combinations/        
├── hand/         
├── joker/        
├── rank/
├──── classes/
├──── numbers/
├─────── even/
├─────── figure/
├─────── odd/
├── score/
├── suit/

src/test/scala
├── card/
├── combinations/
├── hand/
├── joker/
├── rank/
├── score/
├── suit/

Overview
--------------------------------
Rank
Thirteen ranks from Ace to King. Each rank has

- an order (1–13) and a value (pip or scoring value)
- a RankClass: EvenClass, OddClass, or FigureClass (J, Q, K)
- Ace is Odd. Its order is 1 but its value is 11.

Suit
Four suits: Hearts, Spades, Clover, Diamonds.

Card
A Card takes a Rank and a Suit. Its name is derived as "<Rank> of <Suit>". Cards are ordered and comparable by name.

Joker
Four jokers, each with a name and a description of its effect:

Greedy Joker — played cards each give +3 chips
Devious Joker — +100 chips if hand contains a Straight
Even Steven — played even-ranked cards each give +4 chips
Scary Face — played face cards each give +30 chips

Hand
A Hand holds a collection of Cards and Jokers. Cards and jokers are placed individually and retrieved by index or as a full list.

Combinations
Six poker combinations evaluated in priority order.


Combinations (priority order)

│ Priority        │ Chips │ Multiplier   │ Requirement                          │

│ 1. StraightFlush│  100  │     ×8       │ 5 consecutive cards, same suit       │
│ 2. Flush        │   35  │     ×4       │ 5 cards, same suit, any order        │
│ 3. Straight     │   30  │     ×4       │ 5 consecutive cards, any suit        │
│ 4. ThreeOfAKind │   30  │     ×3       │ 3+ cards sharing the same rank       │
│ 5. Pair         │   10  │     ×2       │ 2+ cards sharing the same rank       │
│ 6. HighCard     │    5  │     ×1       │ Any non-empty hand (fallback)        │

