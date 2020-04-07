/* McKenzie Joyce 
 * Feb 2020 
 * OrderAndChaos.java : Create a game of order and chaos. Each game keeps track of boardDim (always 6), two teams (one is order the
 *                      other chaos, number of Teams (always 2), the board, and if a player won
 * 	OrderAndChaos() : Creates a game and sets everything = to null and winner booleans = false (default)
 *  OrderAndChaos(Team[] teamz) : Creates a game with the teams = inputed teamz 
 *  startGame() : Explains how the game works, sets up the board, picks the first player, and calls playGame
 *  playGame() : While the board is not full asks player to make their moves
 *  setUpBoard() : Creates a 6 x 6 board and assigns the x and o tokens to each team 
 *  pickXorO() : Once a user choose where to make a move this function is called for them to place an x or o there 
 *  setFirstPlayer() : The team who is playing as order this game always goes first according to the rules 
 *  checkWin() : Checks for a win. Board must be full and have a string of exactly five x's or o's for order to win else chaos wins
 *  fiveAlike() : Checks if there are exactly five alike tokens in a row 
 *  testStrings(String[] strings): Tests if the strings match a order winning string 
 *  noWinnerPossible() : Always returns false because there is always a winner 
 */
import java.util.Arrays; 

public class OrderAndChaos extends TurnTakingBoardGame{
	private int boardDim = 6;
	Team order;
	Team chaos;
	boolean orderWin;
	boolean chaosWin;
	int gameCount = 1;
	
	public int xValue = boardDim +1; 
	public int oValue = 1; 
	public GamePiece x = new GamePiece("X", xValue);
	public GamePiece o = new GamePiece("O", oValue);
	public OrderAndChaos() {
		order = null;
		chaos = null;
		numOfTeams = 0;
		teams = null;
		board = null;
		winner = false;
		noWinnerPossible = false;
		orderWin = false;
		chaosWin = false;
	}
	
	public OrderAndChaos(Team[] teamz) {
		this();
		numOfTeams = 2;
		teams = teamz;
		board = null;
		if(gameCount % 2 == 0) {
			order = teams[0];
			chaos = teams[1];
			gameCount +=1;
		}
		else {
			order = teams[1];
			chaos = teams[0];
			gameCount +=1;
		}
	}
	
	public void startGame(){
		if(teams == null) {
			teams = createTeams(2);
			numOfTeams = 2;
			order = teams[0];
			chaos = teams[1];
		}
		setFirstPlayer();
		setUpBoard();
		System.out.println("To make your move enter the coordinate value of your desired move with a space, for example:");
		System.out.println(" +----+----+----+ \n |0 0 |0 1 |0 2 | ... \n +----+----+----+ \n |1 0 |1 1 |1 2 | ... \n +----+----+----+ \n |2 0 |2 1 |2 2 | ... \n +----+----+----+ \n . \n . \n .");
		System.out.println("Then you will be asked wheter you want to place an x or o in that spot:");
		board.printBoard();
		playGame();
		resetValues();
		
	}
	public void resetValues() {
		orderWin = false;
		chaosWin = false;
		
	}
	
	public void setUpBoard() {
		board = createBoard(boardDim, boardDim);
		order.addToken(x);
		order.addToken(o);
		chaos.addToken(x);
		chaos.addToken(o);
	}
	
	public void playGame() {
		while(!orderWin && !chaosWin) {
			for(Team team: teams) {
				if(team.isTurn) {
					Player upNext = team.determineTurn();
					System.out.println(upNext.name.username + " enter your move: ");
					int[] move = getUserMove();
					int row = move[0];
					int col=move[1];
					GamePiece token = pickXorO();
					makeMove(token, row, col);
					switchRoundTurns(team);
					winner = checkWin();
					if(winner) {
						if(orderWin) {
							order.teamWins += 1;
							System.out.println("Congratulations "+order.teamName.username);
						}
						if(chaosWin) {
							chaos.teamWins += 1;
							System.out.println("Congratulations "+chaos.teamName.username);
						}
					}
					board.printBoard();
					break;
				}
			}
		}
		
	}
	
	public GamePiece pickXorO() {
		System.out.println("Would you like to place a x (type x) or an o (type o)");
		boolean picked = false;
		GamePiece token = new GamePiece();
		while(!picked) {
			String resp = scan.next();
			if(resp.compareToIgnoreCase("x") == 0) {
				token = x;
				picked = true;
			}
			else if(resp.compareToIgnoreCase("o") == 0) {
				token = o;
				picked = true;
			}
			else {
				System.out.println("Sorry you must choose either x or o,  try again");
			}	
		}
		return token;
	}
	
	public void setFirstPlayer() {
		order.isTurn = true;
	}

	
	public boolean checkWin() {
		boolean boardFull = board.checkFull();
		if(!boardFull) {
			return false;
		}
		orderWin = fiveAlike();
		chaosWin = (!orderWin);
		if(orderWin) {
			System.out.println("Order wins!");
		}
		if(chaosWin) {
			System.out.println("Chaos wins!");
		}
		return (orderWin || chaosWin);
	}
	
	public boolean fiveAlike() {
		boolean winner = false;
		String[] diagonals = {"", "", ""};
		String[] horZVert = {"", ""};
		
		// Horizontal and Vertical 
		for(int i = 0; i <boardDim; i++) {
			for(int j = 0; j <boardDim; j++) {
				horZVert[0] += board.layout[i][j].strRep;
				horZVert[1] += board.layout[j][i].strRep;
			}
			winner = testStrings(horZVert);
			if(winner) {
				break;
			}
		}
		// Left Right Diagonal 
		if(!winner) {
			for(int i = 0, j= 1; i <boardDim; i++, j++) {
					diagonals[0] += board.layout[i][i].strRep;
					if(j < boardDim) {
						diagonals[1] += board.layout[i][j].strRep;
						diagonals[2] += board.layout[j][i].strRep;
					}
			}
			winner = testStrings(diagonals);
		}
		
		// Right Left Diagonal
		if(!winner) {
			Arrays.setAll(diagonals, i -> "");
			for(int i = boardDim-1, j= 0; i > 0; i--, j++) {
				diagonals[0] += board.layout[i][j+1].strRep;
				diagonals[1] += board.layout[i-1][j].strRep;
				diagonals[2] += board.layout[i][j].strRep;
			}
			diagonals[2] += board.layout[0][boardDim-1].strRep;
			winner = testStrings(diagonals);
		}
		return winner;
	}
	
	
	public boolean testStrings(String[] strings) {
		boolean isWin = false;
		for(String s : strings) {
			if(s.length() == boardDim -1) {
				if(s.compareTo("XXXXX") == 0 || s.compareTo("OOOOO") == 0 ) {
					isWin = true;
				}
			}
			if(s.length() == boardDim) {
				if(s.compareTo("XXXXXO") == 0 || s.compareTo("OOOOOX") == 0) {
					isWin = true;
				}
				if(s.compareTo("OXXXXX") == 0 || s.compareTo("XOOOOO") == 0) {
					isWin = true;
				}
			}
		}
		
		return isWin;
	}
	
	public boolean noWinnerPossible() {
		return false;
	}
}
