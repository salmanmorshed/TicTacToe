import java.util.Stack;
import java.util.ArrayList;

public class Game {
	public char board[];
	public Stack<Integer> lastMoves;
	public char winner;
	public Player player1, player2;

	public Game() {
		board = new char[9];
		for (int i = 0; i < 9; i++) {
			board[i] = '-';
		}
		lastMoves = new Stack<>();
		winner = '-';
	}

	public ArrayList<Integer> getFreePositions() {
		ArrayList<Integer> freePos = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			if (this.board[i] == '-') {
				freePos.add(i);
			}
		}
		return freePos;
	}

	public void putMark(char marker, int position) {
		this.board[position] = marker;
		this.lastMoves.push(position);
	}

	public void undoPutMark() {
		int position = lastMoves.pop();
		board[position] = '-';
		winner = '-';
	}

	public boolean isGameOver() {
		int winPos[][] = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 },
				{ 1, 4, 7 }, { 2, 5, 8 }, { 0, 4, 8 }, { 2, 4, 6 } };
		for (int i = 0; i < 8; i++) {
			int a = winPos[i][0], b = winPos[i][1], c = winPos[i][2];
			if (this.board[a] == this.board[b] && this.board[b] == this.board[c] && this.board[c] != '-') {
				winner = this.board[a];
				return true;
			}
		}
		if (this.getFreePositions().size() == 0) {
			winner = '-';
			return true;
		}
		return false;
	}

	public void printBoard() {
		for (int i = 0; i < 9; i++) {
			System.out.print(this.board[i]);
			if ((i + 1) % 3 == 0 && i < 8) {
				System.out.print("\n");
			}
		}
		System.out.println("\n");
	}

	public void playGame(Player p1, Player p2) {
		player1 = p1;
		player2 = p2;

		int i = 9;
		while (i-- != 0) {
			this.printBoard();
			if (i % 2 == 0) {
				p1.makeMove();
			} else {
				p2.makeMove();
			}
			if (this.isGameOver()) {
				this.printBoard();
				if (winner == '-') {
					System.out.println("Game over! Result: DRAW.");
				} else {
					System.out.println("Game over! Result: " + this.winner
							+ " wins.");
				}
				break;
			}
		}
	}

}
