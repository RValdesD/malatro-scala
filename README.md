# Tarea 1 | Entrega Parcial 2

Project structure:

score/        Score trait (chips, multiplier, total)

rank/         Rank hierarchy — Ace to King, classified as Even, Odd, or Figure

suit/         Four standard suits

card/         TCard / ACard / Card — a playing card combining Rank and Suit

joker/        TJoker / AJoker and four concrete Jokers

hand/         TCardCollection / AHand / Hand — holds cards and jokers

combination/  TCombination / ACombination and six poker combinations (To be done based on test suite)

test/
mirrors the src layout, one test suite per package


----Score---

A Score has a chip count and a multiplier. total is derived as chips × multiplier.


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
Note: The project has yet to be documented. This will be done pretty soon hopefully (aaaaa)
