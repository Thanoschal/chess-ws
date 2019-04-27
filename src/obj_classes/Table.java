package obj_classes;

public class Table {
	private String white;
	private String black;

	public Table(String white, String black) {
		this.white = white;
		this.black = black;
	}
	
	public String getWhite() {
		return white;
	}

	public String getBlack() {
		return black;
	}

	@Override
	public String toString() {
		return "Table: [white=" + white + ", black=" + black + "]";
	}
	
}