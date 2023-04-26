### Advanced Software Engineering Project

# Dart Score Counter

## Supported functionalities

- [x] track the score of a single dart game
- [x] allow input of settings of the number of players, legs, the number of legs to win a set, the number of sets to win a match and which player begins a match
- [ ] multiple dart game variants, like 501, 301, Cricket etc.
- [x] display statistics at the end of a leg
- [ ] allow to create players and select them for a game
- [ ] store game history and statistics, also for each player
- [ ] beautify the app, with a menu, statistics, history and create player page

<br>

## Ubiquitous Language

There isn't a lot of domain knowledge about darts, but when someone never played darts before, this might help
<br>

- **Player**: A player is a person who plays darts
- **Leg**: A Leg is a single round of darts (e.g. in 501, when a player managed to get to 0 points, the **Leg** is over and this player won the **Leg**)
- **Set**: A set is a single set of a match. A set can have multiple legs. To win a set, a player has to win a certain number of legs
- **Match**: A match is an entire dart game. A match can have multiple sets. To win a match, a player has to win a certain number of sets
- **Score**: The score is the number of points a player still needs to win (e.g. A player needs 170 more points before he wins)
- **Dart**: A dart is a single dart of a player in a throw. Holds information like: triple 20 or single 5
- **Throw**: A throw is a single throw of a player in a leg. A throw can have multiple darts (might depends on game mode)
- **Player/Leg Statistics**: The Statistics are the statistics of a player in a leg or overall (e.g. checkout-quote: 20%, average throw: 180)
- **Game History**: It consist of every action taken by every player
- **Busted**: Occurs, when a player fails to get the score below zero or if he didn't get to zero with a double number (e.g. double 20 or double 16)
