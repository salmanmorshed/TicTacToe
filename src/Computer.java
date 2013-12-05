public class Computer extends Player {

	public char opponentMarker;

	public Computer(Game myGame, char marker) {
		super(myGame, marker);
		opponentMarker = (marker == 'X') ? 'O' : 'X';
	}

	public MoveInfo minimizeMove() {
		Integer bestMove = null;
		Integer bestScore = null;
		for (Integer currentMove: game.getFreePositions()) {
			game.putMark(opponentMarker, currentMove);
			Integer currentScore;
			if (game.isGameOver()) {
				currentScore = getScore();
			} else {
				MoveInfo move = maximizeMove();
				currentScore = move.moveScore;
			}
			game.undoPutMark();
			if (bestScore == null || currentScore < bestScore) {
				bestScore = currentScore;
				bestMove = currentMove;
			}
		}
		return new MoveInfo(bestMove, bestScore);
	}

	public MoveInfo maximizeMove() {
		Integer bestMove = null;
		Integer bestScore = null;
		for (Integer currentMove: game.getFreePositions()) {
			game.putMark(marker, currentMove);
			Integer currentScore;
			if (game.isGameOver()) {
				currentScore = getScore();
			} else {
				MoveInfo move = minimizeMove();
				currentScore = move.moveScore;
			}
			game.undoPutMark();
			if (bestScore == null || currentScore > bestScore) {
				bestScore = currentScore;
				bestMove = currentMove;
			}
		}
		return new MoveInfo(bestMove, bestScore);
	}

	public int getScore() {
		if (game.winner == this.marker) {
			return 1;
		} else if (game.winner == this.opponentMarker) {
			return -1;
		} else {
			return 0;
		}
	}

	public void makeMove() {
		MoveInfo myMove = maximizeMove();
		game.putMark(marker, myMove.movePosition);
	}

	public class MoveInfo {
		public final Integer movePosition;
		public final Integer moveScore;

		public MoveInfo(Integer p, Integer s) {
			movePosition = p;
			moveScore = s;
		}
	}

}
