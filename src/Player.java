import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Player {
	public Game game;
	public char marker;

	public Player(Game myGame, char myMarker) {
		game = myGame;
		marker = myMarker;
	}

	public void makeMove() {
		int myMove = -1;
		while (true) {
			try {
				myMove = Integer.parseInt(getInput("Input for " + marker + ": "));
			} catch (Exception e) {
			}
			if (game.getFreePositions().contains(myMove)) {
				break;
			} else {
				System.out.println("Invalid choice!");
			}
		}
		game.putMark(marker, myMove);
	}

	public static String getInput(String prompt) {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));
		System.out.print(prompt);
		System.out.flush();
		try {
			return stdin.readLine();
		} catch (Exception e) {
			return "Error: " + e.getMessage();
		}
	}
}
