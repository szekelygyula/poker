package szgy.poker.game;

import java.util.Collections;
import java.util.Random;
import java.util.Stack;

import szgy.poker.exception.PlayerException;

public class Game {
	protected Stack<Card> deck;
	protected Player player;
	
	public Game() {
		player = new Player();
		newDeck();
	}
	
	public void newDeck() {
		deck = new Stack<Card>();
		CardSuit[] suits = CardSuit.values();
		CardRank[] ranks = CardRank.values();
		for(int i = 0; i < suits.length; i++) {
			for(int j = 0; j < ranks.length; j++) {
				deck.push(new Card(suits[i], ranks[j]));
			}
		}
		shuffle();
	}
	
	public void shuffle() {
		long seed = System.currentTimeMillis();
		Collections.shuffle(deck, new Random(seed));
	}
	
	public void deal() throws PlayerException {
		Card[] newCards = new Card[5];
		for(int i = 0; i < 5; i++) {
			newCards[i] = deck.pop();
		}
		player.giveDeal(newCards);
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return "Dealer [deck=" + deck.toString() + "]";
	}
}
