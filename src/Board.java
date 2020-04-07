/* Mckenzie Joyce 
 * Feb 2020 
 * Board.java : An instance of a board with a layout for what the current board looks like, and the height and width of the board
 * 		Board() : Creates a board with layout set to null, height and width set to 0 (defaults)
 * 		Board(int size) : Creates an empty square board of size x size 
 * 		Board(int height, int width) : Creates an empty height x width sized board 
 * 		checkFull() : Checks if the board is full returns true if there are no empty cells 
 * 		printBoard() : Prints a string representation of the current board 
 */
public class Board {
	BoardTile[][] layout;
	int height;
	int width;
	
	
	
	//Create an empty board, 0 represents empty, 1 represents player x, 2 represents player o
	
	public Board() {
		layout = null;
		height=0;
		width=0;
	}
	
	
	
	public Board(int size){
		this();
		height = size;
		width = size;
		layout = new BoardTile[size][size];
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				layout[i][j] = new BoardTile();
			}
		}
	}
	
	public Board(int h, int w){
		this();
		height = h;
		width = w;
		layout = new BoardTile[h][w];
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				layout[i][j] = new BoardTile();
			}
		}
	}
	
	
	//Checks if there is a full board 
	public boolean checkFull() {
		boolean full = true;
		for(int i =0; i<height; i++) {
			for(int j =0; j<width; j++) {
				if(layout[i][j].token == null) {
					full = false;
				}
			}	
		}
		return full;
	}
	public String toString() {
		String ret = "";
		String bar = "+";
		String space = "|";
		for(int i =0; i<width; i++) {
			bar = bar +"--+";
		}
		for(int i=0; i<height; i++) {
			ret += bar+"\n";
			space = "|";
			for(int j=0; j<width; j++) {
				if(layout[i][j] != null && layout[i][j].strRep != null) {
					space = space + layout[i][j].strRep + " |";
				}
				else {
					space = space + "  |"; }
			}
			ret += space+"\n";
		}
		ret += bar+"\n";
		return ret;
	
	}
	public void printBoard() {
		System.out.println(this.toString());
	}
}
