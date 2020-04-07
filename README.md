# Terminal Games 
In this Program I designed and implemented the games Tic Tac Toe and Order and Chaos using object oriented programming. These games are designed to be played by two players or teams on a shared terminal window. The Program starts by asking the user if they want to play 1 v 1 or in teams and setting up the teams if need be. Then the teams/users can play either Tic Tac Toe or Order and chaos as many times as they want. When they decide to stop playing the program will print a summary of each teams total wins. 

### Tic Tac Toe 
Tic Tac Toe starts by asking the user what size board they would like to play on, user picks n to create an nXn board. Instructions of how to play and the empty board is printed. Then teams alternate (players also alternate per team if that team choose to take turns every round) with the current board being printed in between moves until either a win occurs or is not possible.

### Order and Chaos
Order and Chaos starts by explaining the instructions and printing an empty board. Teams/players alternate choosing the location and x or o until the board is full. At that point the program checks for an occurrence of exactly five x’s or o’s on the board. If one or more exists order wins otherwise chaos wins. 


## Instructions for Running Program:
1. `cd` into the folder containing src files 
2. run `javac TerminalGames.java` to compile the program
3. Run `java TerminalGames` to run the program 

** Check boiler plates at the beginning of each file for a list of methods and what they do**
#### Classes: 
- Board.java : An instance of a board with a layout for what the current board looks like, and the height and width of the board
- BoardTile.java : Create a cell in a board, holds a value and string representation 
- Name.java : Create a name, allows for firstName, last Name, middleInitial, nickname, prefix, suffix, and username
- OrderAndChaos.java : Create a game of order and chaos. Each game keeps track of boardDim (always 6), two teams (one is order the other chaos, number of Teams (always 2), the board, and if a player won
- Player.java : Creates a player, keeps track of number of wins and their name
- Team.java : Creates a team, keeps track of the roster, how they are alternating turns, the team name, whether its their turn the tokens they can use if playing a game, and number of team wins 
- TerminalsGames.java : Entry point, Welcomes user to program, sets up teams, and asks them which games they want to play 
- TicTacToe.java : Creates a game of tic tac toe. Keeps track of board dimensions, teams, number of teams, the board, and if winner has happened or is not possible


#### Abstract Classes:
- TurnTakingBoardGame.java : An abstract class fro turn taking board games. Keeps track of number of teams, the teams themselves the board, the max number of players allowed on a team, if a winner occurred, and if a winner is possible

#### Interfaces:
- BoardGame.java:  An interface for BoardGames 
- TurnTakingGame.java: An interface for Turn Taking Games
- Winable.java : an interface for winnable objects

