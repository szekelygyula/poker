package szgy.poker.game;

import org.json.JSONObject;

public class Card implements Comparable<Card>, JSONAble {
	protected CardSuit suit;
	protected CardRank rank;
	
	public Card(CardSuit suit, CardRank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public CardSuit getSuit() {
		return suit;
	}

	public void setSuit(CardSuit suit) {
		this.suit = suit;
	}

	public CardRank getRank() {
		return rank;
	}

	public void setRank(CardRank rank) {
		this.rank = rank;
	}
	
	public boolean equals(Card card) {
		return suit.equals(card.suit) && rank.equals(card.rank);
	}
	
	@Override
	public String toString() {
		return rank + " of " + suit;
	}

	@Override
	public int compareTo(Card campareCard) {
		int compareRank = ((Card) campareCard).rank.getValue(); 
		return this.rank.getValue() - compareRank;
	}

	@Override
	public JSONObject toJSON() {
		JSONObject cardObject = new JSONObject();
		cardObject.append("suit", suit);
		cardObject.append("rank", rank);
		return cardObject;
	}
}
