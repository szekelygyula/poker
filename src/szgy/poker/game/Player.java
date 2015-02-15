package szgy.poker.game;

import szgy.poker.exception.PlayerException;

public class Player {
	protected String name;
	protected Card[] cards;
	
	public Player() {
		this.name = "";
		cards = new Card[5];
	}
	
	public Player(String name) {
		this.name = name;
		cards = new Card[5];
	}
	
	/**
	 * Megkapja a leosztást
	 * @param cards A kapott lapok
	 * @throws PlayerException Abban az esetben ha a kapott lapok száma nem megfelelõ
	 */
	public void giveDeal(Card[] cards) throws PlayerException {
		if(cards == null) throw new PlayerException("No cards given!");
		if(cards.length > 5) throw new PlayerException("Too much card given!");
		if(cards.length < 5) throw new PlayerException("Too less card given!");
		this.cards = cards;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Card[] getCards() {
		return cards;
	}
}
