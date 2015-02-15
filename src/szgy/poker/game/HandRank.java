package szgy.poker.game;

public enum HandRank {
	HIGH_CARD(0, "Magas lap"), PAIR(1, "Egy p�r"), TWO_PAIR(2, "K�t p�r"), THREE_OF_A_KIND(3, "Drill"), 
	STRAIGHT(4, "Sor"), FLUSH(5, "Fl�ss"), FULL_HOUSE(6, "Full"), FOUR_OF_A_KIND(7, "P�ker"),
	STRAIGHT_FLUSH(8, "Sz�nsor"), ROYAL_FLUSH(9, "Royal fl�ss");
	
	private int value;
	private String name;
	
	private HandRank(int value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public int getValue() {
		return value;
	}
	
	public String getName() {
		return name;
	}
}
