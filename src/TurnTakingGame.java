/* McKenzie Joyce
 * Feb 2020 
 * TurnTakingGame.java : A TurnTakingGame interface 
 * 	createTeams(int numberOfTeams): Must have a function that creates the numberOfTeams desired and returns in an array
 * 	switchGameTurns(Team team): Must have a function that switches which player on the inputed teams is up next if they switch per game
 *  switchRoundTurns(Team team): Must have a function that switches which player on the inputed teams is up next if they switch per round
 *  setFirstPlayer(): Must have a function that sets the first player
 *  switchTeams(): Must have a function that switches which teams turn it is 
 */
public interface TurnTakingGame {
	// Function to create teams
	public abstract Team[] createTeams(int numberOfTeams);
	// Function to switch Turns  
	public abstract void switchGameTurns(Team team);
	public void switchRoundTurns(Team team);
	public abstract void setFirstPlayer();
	public abstract void switchTeams();
	
}
