package obj_classes;

public class FinishedGame {
	
	private String white;
	private String black;
	private int movesBlack;
	private int movesWhite;
	private String winner;
	private String winnerColor;

	public FinishedGame(String white, String black, int movesBlack, int movesWhite, String winner, String winnerColor) {
		this.white = white;
		this.black = black;
		this.movesBlack = movesBlack;
		this.movesWhite = movesWhite;
		this.winner = winner;
		this.winnerColor = winnerColor;
	}

	public String getWhite() {
		return white;
	}

	public String getBlack() {
		return black;
	}

	public int getMovesBlack() {
		return movesBlack;
	}

	public int getMovesWhite() {
		return movesWhite;
	}

	public String getWinner() {
		return winner;
	}

	public String getWinnerColor() {
		return winnerColor;
	}
}