/* McKenzie Joyce
 * Feb 2020
 * Winable.java : An interface for things that are winable 
 * 	checkWin(): Must have a function that checks if a win occurs
 * 	noWinnerPossible(): Must have a function that checks if a win is possible
 */
public interface Winable {
	public abstract boolean checkWin();
	public abstract boolean noWinnerPossible();
}
