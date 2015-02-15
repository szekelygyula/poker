package szgy.poker.game;

import org.json.JSONObject;

public class Hand implements JSONAble {
	protected HandRank handRank;
	protected int value;
	protected CardRank highCard;
	
	public Hand() {
		this.handRank = null;
		this.value = 0;
		this.highCard = null;
	}
	
	public Hand(HandRank handRank, int value, CardRank highCard) {
		this.handRank = handRank;
		this.value = value;
		this.highCard = highCard;
	}

	public HandRank getHandRank() {
		return handRank;
	}

	public void setHandRank(HandRank handRank) {
		this.handRank = handRank;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public CardRank getHighCard() {
		return highCard;
	}

	public void setHighCard(CardRank highCard) {
		this.highCard = highCard;
	}

	@Override
	public String toString() {
		return "Hand [handRank=" + handRank + ", value=" + value
				+ ", highCard=" + highCard + "]";
	}
	
	@Override
	public JSONObject toJSON() {
		JSONObject cardObject = new JSONObject();
		cardObject.append("handRank", handRank);
		cardObject.append("value", value);
		cardObject.append("highCard", highCard);
		cardObject.append("name", handRank.getName());
		return cardObject;
	}
}
