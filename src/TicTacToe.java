/* McKenzie Joyce 
 * Feb 2020 
 * TicTacToe.java : Creates a game of tic tac toe. Keeps track of board dimensions, teams, number of teams, the board, and if 
 * 					winner has happened or is not possible
 * 	TicTacToe() : Creates a game of tic tac toe where values are all null or 0 (default)
 * 	TicTacToe(Team[] teamz) : Creates a game of tic tac toe with the inputed teams
 * 	startGame() : Calls functions to set up the board, set first player, explains the rules, then calls playGame
 * 	setUpBoard() : Creates the boars using user input for size then sets up x and o values 
 * 	checkWin() : Checks for winner by calculating the value of possible win combos and compares 
 * 	noWinnerPossible() : returns true if neither player has a chance to win the game
 * 	playGame(): while there is no winner but it is possible for a player to win will keep asking user which move they want to make
 * 	setFirstPlayer(): Sets the first player 
 */
public class TicTacToe extends TurnTakingBoardGame {
	int boardDim;
	int MAX_BOARD_SIZE = 100;
	Team teamOne;
	Team teamTwo;

	
	public int playerXValue; 
	public int playerOValue; 
	public GamePiece x;
	public GamePiece o;
	
	public TicTacToe() {
		boardDim = 0;
		teamOne = null;
		teamTwo = null;
		numOfTeams = 0;
		teams = null;
		board = null;
		winner = false;
		noWinnerPossible = false;
	}
	
	public TicTacToe(Team[] teamz) {
		this();
		teamOne = teamz[0];
		teamTwo = teamz[1];
		numOfTeams = 2;
		teams = teamz;
		board = null;
		
	}
	
	
	public void startGame() {
		if(teams == null) {
			teams = createTeams(2);
			numOfTeams = 2;
			teamOne = teams[0];
			teamTwo = teams[1];
		}
		setFirstPlayer();
		setUpBoard();
		System.out.println("To make your move enter the coordinate value of your desired move with a space, for example:");
		System.out.println(" +----+----+----+ \n |0 0 |0 1 |0 2 | ... \n +----+----+----+ \n |1 0 |1 1 |1 2 | ... \n +----+----+----+ \n |2 0 |2 1 |2 2 | ... \n +----+----+----+ \n . \n . \n .");
		board.printBoard();
		playGame();
	}
	
	public void setUpBoard() {
		boardDim = getBoardSize(MAX_BOARD_SIZE);
		board = createBoard(boardDim, boardDim);
		playerXValue = boardDim +1;
		playerOValue = 1;
		x = new GamePiece("X", playerXValue);
		o = new GamePiece("O", playerOValue);
		teamOne.addToken(x);
		teamTwo.addToken(o);
	}
	
	
	public boolean checkWin() {
		boolean winner = false;
		int horizontalSum =0;
		int verticalSum =0;
		int diagonalLRSum =0;
		int diagonalRLSum =0;
		
		
		
		for(int row =0; row<boardDim; row++) {
			for(int col =0; col <boardDim; col++) {
				if(board.layout[row][col] != null) {
					verticalSum += board.layout[row][col].value;
					if(row==col){
			            diagonalLRSum += board.layout[row][col].value;
			        }
				}
				if(board.layout[col][row] != null) {
					horizontalSum += board.layout[col][row].value;
				}
				if(row+col==(boardDim-1) && board.layout[row][col] != null){
			          diagonalRLSum+= board.layout[row][col].value;
			    }
				
			}
			if(horizontalSum == (teamOne.gamePieces[0].val*boardDim)|| verticalSum == (teamOne.gamePieces[0].val*boardDim)){
				winner = true;
				break;
			}
			if(horizontalSum == (teamTwo.gamePieces[0].val*boardDim) || verticalSum == (teamTwo.gamePieces[0].val*boardDim)){
				winner = true;
				break;
			}
			horizontalSum = 0;
			verticalSum =0;
			
		}
		if(diagonalLRSum == (teamOne.gamePieces[0].val*boardDim)|| diagonalRLSum == (teamOne.gamePieces[0].val*boardDim)
				|| diagonalLRSum == (teamTwo.gamePieces[0].val*boardDim) || diagonalRLSum == (teamTwo.gamePieces[0].val*boardDim)) {
			winner = true;
			
		}
		if(winner) {
			switchGameTurns(teamOne);
			switchGameTurns(teamTwo);
			teamOne.gamePieces = null;
			teamTwo.gamePieces = null;
		}
		return winner;
	}
	
	public boolean noWinnerPossible() {
		boolean winner = checkWin();
		boolean full = board.checkFull();
		if(full && !winner) {
			System.out.println("A stalemate has occured no winner is possible :(");
			return true;
		}
		else {
			return false;
		}
	}
	
	public void playGame() {
		while(!winner && !noWinnerPossible) {
			for(Team team : teams) {
				if(team.isTurn) {
					Player upNext = team.determineTurn();
					System.out.println(upNext.name.username + " enter your move: ");
					int[] move = getUserMove();
					int row = move[0];
					int col=move[1];
					makeMove(team.gamePieces[0], row, col);
					switchRoundTurns(team);
					noWinnerPossible = board.checkFull();
					winner = checkWin(); 
					if(winner) {
						team.teamWins += 1;
						upNext.numberOfWins +=1;
						System.out.println("Congratulations " + upNext.name.username + " and Team "+team.teamName.toString());
					}
					board.printBoard();
					break;
				}
			}
		}
	}
	
	
	public void setFirstPlayer() {
		teams[0].isTurn = true;
	}
	
	
}
