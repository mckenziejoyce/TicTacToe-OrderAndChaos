/* McKenzie Joyce
 * Feb 2020
 * Team.java : Creates a team, keeps track of the roster, how they are alternating turns, the team name, wheter its their turn
 * 			   the tokens they can use if playing a game, and number of team wins 
 * 	Team() : Creates a team with everything set to null or 0 (default)
 * 	Team(Player[] lineup, boolean rounds) : Creates a team with inputed players as the roster, and how they want to take turns
 * 	Team(Player[] lineup, boolean rounds, Name team) : Creates a team with inputed players as the roster, team name, and how they want to take turns
 *	Team(boolean rounds): Creates a team and sets how they want to take turns
 *	Team(boolean rounds, boolean first): Creates a team and sets how they want to take turns also sets them to go first
 *	addPlayer(Player playa) : Add a player to the team 
 * 	addToken(BoardTile tok) : Add a game piece to the options of game pieces 
 * 	determineTurn() : returns player whose turn it is next on the team 
 */
public class Team {
	Player[] roster;
	boolean alternateRounds;
	boolean alternateGames;
	Name teamName;
	boolean isTurn;
	int rosterCount;
	GamePiece[] gamePieces;
	int teamWins;
	
	public Team() {
		roster = null;
		alternateRounds = true;
		alternateGames = false;
		teamName = null;
		isTurn = false;
		rosterCount = 0;
		gamePieces = null;
		teamWins = 0;
	}
	
	public Team(Player[] lineup, boolean rounds) {
		this();
		roster = lineup;
		alternateRounds = rounds;
		alternateGames = !rounds;
	}
	public Team(Player[] lineup, boolean rounds, Name team) {
		this();
		roster = lineup;
		teamName = team;
		alternateRounds = rounds;
		alternateGames = !rounds;
	}
	public Team(boolean rounds) {
		this();
		alternateRounds = rounds;
		alternateGames = !rounds;
	}
	public Team(boolean rounds, boolean first) {
		this();
		alternateRounds = rounds;
		alternateGames = !rounds;
		isTurn = first;
	}
	public void addPlayer(Player playa) {
		if(roster == null) {
			roster = new Player[1];
			roster[0] = playa;
		}
		else {
			int len = roster.length;
			Player newroster[] = new Player[len + 1];
			for (int i = 0; i < len; i++) 
				newroster[i] = roster[i];
			newroster[len] = playa;
			roster = newroster;
		}
	}
	public void addToken(GamePiece tok) {
		if(gamePieces == null) {
			gamePieces = new GamePiece[1];
			gamePieces[0] = tok;
		}
		else {
			int len = gamePieces.length;
			GamePiece[] temp = new GamePiece[len+1];
			for(int i =0; i < len; i++) {
				temp[i] = gamePieces[i];
			}
			temp[len] = tok;
			gamePieces = temp;
		}
	}
	public Player determineTurn() {
		Player upNext;
		if(rosterCount < roster.length) {
			upNext = roster[rosterCount];
		}
		else {
			int mod = rosterCount % roster.length;
			upNext = roster[mod];
		}
		return upNext;
		
	}
	
}
