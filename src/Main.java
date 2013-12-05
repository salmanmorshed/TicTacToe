
public class Main {
	public static Game game;
	public static Player a, b;
	
	public static void main(String[] args) {
		game = new Game();
		a = new Player(game, 'X');
		b = new Computer(game, 'O');
		game.playGame(a, b);
	}

}
