/* McKenzie Joyce
 * Feb 2020
 * TurnTakingBoardGame.java : An abstract class fro turn taking board games. Keeps track of number of teams, the teams themselves
 * 							  the board, the max number of players allowed on a team, if a winner occurred, and if a winner is possible
 * 	TurnTakingBoardGame() : Sets everything to null or 0 (default)
 * 	TurnTakingBoardGame(Board boardInput, Team[] teamsInput) : Creates a T-T BG using the board an inputed teams
 * 	createTeams(int numberOfTeams): Creates the numberOfTeams desired and returns in an array
 * 	getNumberOfPlayers(): Gets the number of players per team from user 
 * 	getBoardSize(int MAX_BOARD_SIZE): Gets the size of the board from user without letting them pick a bigger board than max board size 
 * 	createBoard(int height, int width): Creates the board using height and width 
 * 	makeMove(BoardTile token, int row, int col) : Places the desired game piece to the desired board cell
 * 	checkMoveInput(int row, int col): Checks to see if move is a legal/possible move
 * 	switchTeams(): Switches which teams turn it is 
 * 	switchRoundTurns(Team team): Switches which player on the inputed teams is up next if they switch per round
 * 	switchGameTurns(Team team): Switches which player on the inputed teams is up next if they switch per game
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class TurnTakingBoardGame implements TurnTakingGame, BoardGame, Winable {
	int numOfTeams;
	Team[] teams;
	Board board;
	public static int MAX_PLAYERS_PER_TEAM = 10;
	public static Scanner scan = new Scanner( System.in );
	boolean winner;
	boolean noWinnerPossible;
	
	
	TurnTakingBoardGame(){
		numOfTeams = 0;
		teams = null;
		board = null;
	}
	
	TurnTakingBoardGame(Board boardInput, Team[] teamsInput){
		this();
		numOfTeams = teamsInput.length;
		teams = teamsInput;
		board = boardInput;
	}
	
	public abstract void startGame();
	
	public abstract void setUpBoard();
	
	public abstract void setFirstPlayer();
	
	
	public Team[] createTeams(int numberOfTeams) {
		teams = new Team[numberOfTeams];
		boolean alternateRounds = true;
		boolean needResponse = true;
		System.out.println("Would you like to play in teams? Type y for yes and anything else for no ");
		String response = scan.nextLine();
		if(response.compareToIgnoreCase("y")==0) {
			for(int i =0; i < numberOfTeams; i++) {
				System.out.println("Team "+ Integer.toString(i+1)+", do you want to take turns by alternating every round (type r) or every game (type g)?");
				needResponse = true;
				while(needResponse) {
					String turns = scan.next();
					if(turns.compareToIgnoreCase("r") == 0) {
						alternateRounds = true;
						needResponse = false;
					}
					if(turns.compareToIgnoreCase("g") == 0) {
						alternateRounds = false;
						needResponse = false;
					}
					if(needResponse == true) {
						System.out.print("Invalid key entry, press r for alternating per round and g for alternating per game");;
					}
				}
				teams[i]= new Team(alternateRounds);
				Name teamName = new Name("Team "+ Integer.toString(i+1));
				teams[i].teamName = teamName;
				System.out.println("How many players are there on team "+Integer.toString(i+1)+"?");
				needResponse = true;
				int numOfPlayers = getNumberOfPlayers();
				for(int j =0; j < numOfPlayers; j++) {
					System.out.println("Player " + Integer.toString(j+1)+ " enter your username:");
					String username = scan.next();
					Player newPlayer = new Player(username);
					teams[i].addPlayer(newPlayer);
				}
			}
		}
		else {
			for(int i =0; i < numberOfTeams; i++) {
				System.out.println("Player "+Integer.toString(i+1)+" enter your username: ");
				String username = scan.nextLine();
				Player newPlayer = new Player(username);
				teams[i]= new Team();
				Name teamName = newPlayer.name;
				teams[i].teamName = teamName;
				teams[i].addPlayer(newPlayer);
			}
		}
		return teams;
	}
	
	public int getNumberOfPlayers() {
		boolean approved = false;
		int num = 0;
		while(!approved) {
			try {
				num = scan.nextInt();
			}catch(InputMismatchException e) {
				scan.next();
				System.out.println("Invalid number of players entry, must be an integer");
				continue;
			}
			if(num > 0 && num < MAX_PLAYERS_PER_TEAM) {
				approved = true;
			}
			else {
				System.out.println("Im sorry teams must be between 1-10 players try again");
			}
		}
		return num;
	}
	
	public int getBoardSize(int MAX_BOARD_SIZE) {
		System.out.println("What size board would you like to play on? Enter a value for n to create your nxn board");
		boolean boardSize = false;
		int boardDim = 0;
		while(!boardSize) {
			try {
				boardDim = scan.nextInt();
			}catch(InputMismatchException e) {
				scan.next();
				System.out.println("Invalid board size, input must be an integer");
				continue;
			}
			if(boardDim > 0 && boardDim <= MAX_BOARD_SIZE) {
				boardSize = true;
			}
			else {
				System.out.println("Im sorry thats not a valid board size, n must be between 0 and 50");
			}
		}
		return boardDim;
	}
	
	public Board createBoard(int height, int width) {
		Board board = new Board(height, width);
		return board;
	}
	
	public int[] getUserMove() {
		int[] move = new int[2];
		boolean approvedMove = false;
		int row = 0;
		int col = 0;
		while(!approvedMove) {
			try {
				row = scan.nextInt();
				col = scan.nextInt();
			} catch(InputMismatchException e) {
				scan.next();
				System.out.println("Make sure you are entering your move in the correct format: row col (no commas, space in between)");
				continue;
			}
			approvedMove = checkMoveInput(row,col);
			if(!approvedMove) {
				System.out.println("Sorry not a valid move, be sure to pick an empty space on the board. Try again");
			}
		}
		move[0] = row;
		move[1] = col;
		return move;
	}
	
	public void makeMove(GamePiece token, int row, int col) {
		board.layout[row][col].addToken(token);
		switchTeams();
	}
	
	public boolean checkMoveInput(int row, int col) {
		boolean valid = true;
		if(row < 0 || row >= board.height) {
			valid = false;
		}
		if(col < 0 || col >= board.width) {
			valid = false;
		}
		if(valid == true && board.layout[row][col].value != 0) {
			valid = false;
		}
		return valid;
	}
	
	
	public void switchTeams() {
		for(int i = 0;  i < numOfTeams; i++) {
			if(teams[i].isTurn) {
				if(i == numOfTeams-1) {
					teams[i].isTurn = false;
					teams[0].isTurn = true;
				}
				else {
					teams[i].isTurn = false;
					teams[i+1].isTurn = true;
				}
			break;
			}
		}
	}
	
	public void switchRoundTurns(Team team) {
		if(team.alternateRounds) {
			team.rosterCount += 1;
		}
	}
	
	public void switchGameTurns(Team team){
		if(team.alternateGames) {
			team.rosterCount += 1;
		}
	}
	
}
