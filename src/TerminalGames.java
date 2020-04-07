/* McKenzie Joyce 
 * Feb 2020
 * TerminalGames.java : Welcomes user to program, sets up teams, and asks them which games they want to play 
 * 	main : Entry point to whole program 
 * 	terminalBoardGames() : calls function to create teams, then asks the user which game they want to play. When user is done playing prints wins 
 * 	createTeams(int numberOfTeams) : Creates the numberOfTeams desired and returns in an array
 * 	getNumberOfPlayers(): Gets the number of players per team from user 
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class TerminalGames {
	public static int MAX_PLAYERS_PER_TEAM = 10;
	public static Scanner scan = new Scanner( System.in );
	
	
	public static void main(String[] args) {
		terminalBoardGames();
	}
	public static void terminalBoardGames() {
		//Welcomes users to the game, explains how it works, and prints an empty board
		boolean playAgain = false;
		System.out.println("Welcome to terminal Board Games! ");
		Team[] teamz = createTeams(2);
		do {
			System.out.println("Would you like to play Tic Tac Toe or Order and Chaos? Type TTT or OC respectivly");
			String game = scan.next();
			if(game.compareToIgnoreCase("TTT")==0) {
				TicTacToe ttt = new TicTacToe(teamz);
				ttt.startGame();
				
			}
			else if(game.compareToIgnoreCase("OC")==0) {
				OrderAndChaos oc = new OrderAndChaos(teamz);
				oc.startGame();
			}
			else {
				System.out.println("Sorry that was not a valid input must be \"TTT\" or \"OC\"");
				playAgain = true;
				continue;
			}
			System.out.println("Would you like to play another game? Type y for yes and anything else for no");
			String resp = scan.next();
			if(resp.compareToIgnoreCase("y") ==0) {
				playAgain = true;
			}
			else {
				playAgain = false;
			}
			System.out.println("");
				
		}while(playAgain);
		System.out.println("Thanks for Playing!");
		System.out.println("Team "+ teamz[0].teamName.username+" you had "+teamz[0].teamWins+" wins!");
		System.out.println("Team "+ teamz[1].teamName.username+" you had "+teamz[1].teamWins+" wins!");
	}
	public static Team[] createTeams(int numberOfTeams) {
		Team[] teams = new Team[numberOfTeams];
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
	public static int getNumberOfPlayers() {
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
}
