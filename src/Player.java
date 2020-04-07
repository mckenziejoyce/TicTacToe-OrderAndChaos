/* McKenzie Joyce
 * Feb 2020
 * Player.java : Creates a player, keeps track of number of wins and their name
 * 	Player() : Creates a player with number of wins = 0 and name = null (default)
 * 	Player(Name username) : Creates a player with no wins and sets name
 * 	Player(String username) : Creates a player with no wins and sets name
 */
public class Player {
	int numberOfWins;
	Name name;
	
	public Player() {
		numberOfWins = 0;
		name = null;
	}
		
	public Player(Name username) {
		this();
		name = username;
	}
	public Player(String username) {
		this();
		name = new Name(username);
	}
	
}
