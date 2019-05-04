package obj_classes;

public class FinishedGame {
	
	private String white;
	private String black;
	private int moves;
	private String winner;
	private String winnerColor;

	public FinishedGame(String white, String black, int moves, String winner, String winnerColor) {
		this.white = white;
		this.black = black;
		this.moves = moves;
		this.winner = winner;
		this.winnerColor = winnerColor;
	}

	public String getWhite() {
		return white;
	}

	public String getBlack() {
		return black;
	}

	public int getMoves() {
		return moves;
	}

	public String getWinner() {
		return winner;
	}

	public String getWinnerColor() {
		return winnerColor;
	}
}