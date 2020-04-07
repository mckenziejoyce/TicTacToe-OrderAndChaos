/* McKenzie Joyce 
 * Feb 2020 
 * BoardTile.java : Create a cell in a board, holds a value, string representation and if there is a token on it (allows expansion to game like chess or checkers)
 * 	BoardTile() : Creates a boardTile with a empty string as a rep and value = 0 (default)
 * 	BoardTile(String name) : Creates a boardTille with string rep = name and value = 0
 *  BoardTile(String name, int num) : Creates a boardTille with string rep = name and value = num
 *  BoardTile(int num) : Creates a boardTille with string rep = " " and value = num
 */
public class BoardTile {
	String strRep;
	int value;
	GamePiece token;
	
	public BoardTile() {
		strRep = " ";
		value = 0;
		token = null;
	}
	public BoardTile(GamePiece tok) {
		this();
		token = tok;
		value = tok.val;
		strRep = tok.stringRep;
	}
	public BoardTile(String name) {
		this();
		strRep = name;
	}
	public BoardTile(String name, int num) {
		this();
		strRep = name;
		value = num;
	}
	public BoardTile(int num) {
		this();
		value = num;
	}
	public void addToken(GamePiece tok) {
		token = tok;
		value = tok.val;
		strRep = tok.stringRep;
	}
}
