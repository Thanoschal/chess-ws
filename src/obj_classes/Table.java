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

	@Override
	public boolean equals(Object obj) {		
		if (obj == null || getClass() != obj.getClass()) return false;
		Table other = (Table) obj;
		if (this.black.contentEquals(other.getBlack()) && this.white.equals(other.getWhite())) {
			return true;
		}
		else {
			return false;
		}
	}
}