/* McKenzie Joyce
 * GamePiece.java : An instance of a game piece, mostly implemented for expandability 
 * 	GamePiece() : Creates an game piece that essentially doesnt exist 
 * 	GamePiece(int value) : Creates a game piece with a value
 * 	GamePiece(String rep) : Creates a game piece with a string representation 
 * 	GamePiece(String rep, int value) : Creates a game piece with a string representation and value
 */
public class GamePiece {
	int val;
	String stringRep;
	Name name;
	String color;
	
	public GamePiece(){
		val = 0;
		stringRep = "";
		name = new Name();
		color = "";
	}
	public GamePiece(int value) {
		this();
		val = value;
	}
	public GamePiece(String rep) {
		this();
		stringRep = rep;
	}
	public GamePiece(String rep, int value) {
		this();
		val = value;
		stringRep = rep;
	}
	public String toString() {
		return stringRep;
	}
	
}
