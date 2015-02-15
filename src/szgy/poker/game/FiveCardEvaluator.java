package szgy.poker.game;

import java.util.Arrays;

import szgy.poker.exception.FiveCardEvaluatorException;

public class FiveCardEvaluator implements Evaluator {	
	@Override
	public Hand evaluate(Card[] cards) throws FiveCardEvaluatorException {
		if(cards == null) throw new FiveCardEvaluatorException("Cards not given");
		if(cards.length > 5) throw new FiveCardEvaluatorException("Too much cards given");
		if(cards.length < 5) throw new FiveCardEvaluatorException("Too less cards given");
		Hand hand = null;
		hand = checkRoyalFlush(cards);
		if(hand != null) return hand;
		hand = checkStraightFlush(cards);
		if(hand != null) return hand;
		hand = checkFourOfAKind(cards);
		if(hand != null) return hand;
		hand = checkFullHouse(cards);
		if(hand != null) return hand;
		hand = checkFlush(cards);
		if(hand != null) return hand;
		hand = checkStraight(cards);
		if(hand != null) return hand;
		hand = checkThreeOfAKind(cards);
		if(hand != null) return hand;
		hand = checkTwoPair(cards);
		if(hand != null) return hand;
		hand = checkPair(cards);
		if(hand != null) return hand;
		hand = checkHighCard(cards);
		if(hand != null) return hand;
		return null;
	}
	
	private Hand checkHighCard(Card[] cards) {
		Hand hand = new Hand();
		CardRank highCard = searchHighCard(cards);
		hand.setHandRank(HandRank.HIGH_CARD);
		hand.setValue(highCard.getValue());
		hand.setHighCard(highCard);
		return hand;
	}
	
	private Hand checkPair(Card[] cards) {
		int pairCounter = 0;
		CardRank pairRank = null;
		for(int i = 0; i < cards.length; i++) {
			Card actCard = cards[i];
			int sameCardCounter = 0;
			for(int j = 0; j < cards.length; j++) {
				Card compareToCard = cards[j];
				if(i != j && !compareToCard.getRank().equals(pairRank)) {
					if(actCard.getRank().equals(compareToCard.getRank())) {
						sameCardCounter++;
					}
				}
			}
			if(sameCardCounter == 1) {
				pairCounter++;
				pairRank = actCard.getRank();
			}
		}
		if(pairCounter == 1) {
			Hand hand = new Hand();
			CardRank highCard = searchHighCard(cards);
			hand.setHandRank(HandRank.PAIR);
			hand.setValue(pairRank.getValue());
			hand.setHighCard(highCard);
			return hand;
		} else {
			return null;
		}
	}
	
	private Hand checkTwoPair(Card[] cards) {
		int pairCounter = 0;
		CardRank twoPairRank = null;
		CardRank pair1Rank = null;
		CardRank pair2Rank = null;
		for(int i = 0; i < cards.length; i++) {
			Card actCard = cards[i];
			int sameCardCounter = 0;
			for(int j = 0; j < cards.length; j++) {
				Card compareToCard = cards[j];
				if(i != j) {
					if(pair1Rank == null || !compareToCard.getRank().equals(pair1Rank)) {
						if(pair2Rank == null || !compareToCard.getRank().equals(pair2Rank)) {
							if(actCard.getRank().equals(compareToCard.getRank())) {
								sameCardCounter++;
							}
						}
					}
				}
			}
			if(sameCardCounter == 1) {
				pairCounter++;
				if(pairCounter == 1) {
					pair1Rank = actCard.getRank();
				} else if (pairCounter == 2) {
					pair2Rank = actCard.getRank();
				}
				
			}
		}
		if(pairCounter == 2) {
			if(pair1Rank.getValue() > pair2Rank.getValue()) {
				twoPairRank = pair1Rank;
			} else {
				twoPairRank = pair2Rank;
			}
			Hand hand = new Hand();
			CardRank highCard = searchHighCard(cards);
			hand.setHandRank(HandRank.TWO_PAIR);
			hand.setValue(twoPairRank.getValue());
			hand.setHighCard(highCard);
			return hand;
		} else {
			return null;
		}
	}
	
	private Hand checkThreeOfAKind(Card[] cards) {
		int threeOfAKindCounter = 0;
		CardRank threeOfAKindRank = null;
		for(int i = 0; i < cards.length; i++) {
			Card actCard = cards[i];
			int sameCardCounter = 0;
			for(int j = 0; j < cards.length; j++) {
				Card compareToCard = cards[j];if(i != j && !compareToCard.getRank().equals(threeOfAKindRank)) {
					if(actCard.getRank().equals(compareToCard.getRank())) {
						sameCardCounter++;
					}
				}
			}
			if(sameCardCounter == 2) {
				threeOfAKindCounter++;
				threeOfAKindRank = actCard.getRank();
			}
		}
		if(threeOfAKindCounter == 1) {
			Hand hand = new Hand();
			CardRank highCard = searchHighCard(cards);
			hand.setHandRank(HandRank.THREE_OF_A_KIND);
			hand.setValue(threeOfAKindRank.getValue());
			hand.setHighCard(highCard);
			return hand;
		} else {
			return null;
		}
	}
	
	private Hand checkStraight(Card[] cards) {
		Arrays.sort(cards);
		if(cards[0].getRank().getValue()+1 == cards[1].getRank().getValue()) {
			if(cards[1].getRank().getValue()+1 == cards[2].getRank().getValue()) {
				if(cards[2].getRank().getValue()+1 == cards[3].getRank().getValue()) {
					if(cards[3].getRank().getValue()+1 == cards[4].getRank().getValue()) {
						Hand hand = new Hand();
						CardRank highCard = searchHighCard(cards);
						hand.setHandRank(HandRank.STRAIGHT);
						hand.setValue(highCard.getValue());
						hand.setHighCard(highCard);
						return hand;
					} else {
						return null;
					}
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	private Hand checkFlush(Card[] cards) {
		if(cards[0].getSuit().equals(cards[1].getSuit())) {
			if(cards[0].getSuit().equals(cards[2].getSuit())) {
				if(cards[0].getSuit().equals(cards[3].getSuit())) {
					if(cards[0].getSuit().equals(cards[4].getSuit())) {
						Hand hand = new Hand();
						CardRank highCard = searchHighCard(cards);
						hand.setHandRank(HandRank.FLUSH);
						hand.setValue(highCard.getValue());
						hand.setHighCard(highCard);
						return hand;
					} else {
						return null;
					}
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	private Hand checkFullHouse(Card[] cards) {
		Hand pairHand = null;
		Hand threeOfAKindHand = null;
		pairHand = checkPair(cards);
		if(pairHand != null) {
			threeOfAKindHand = checkThreeOfAKind(cards);
			if(threeOfAKindHand != null) {
				Hand hand = new Hand();
				CardRank highCard = searchHighCard(cards);
				hand.setHandRank(HandRank.FULL_HOUSE);
				hand.setValue(threeOfAKindHand.getValue());
				hand.setHighCard(highCard);
				return hand;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	private Hand checkFourOfAKind(Card[] cards) {
		int fourOfAKindCounter = 0;
		CardRank fourOfAKindRank = null;
		for(int i = 0; i < cards.length; i++) {
			Card actCard = cards[i];
			int sameCardCounter = 0;
			for(int j = 0; j < cards.length; j++) {
				Card compareToCard = cards[j];
				if(i != j && !compareToCard.getRank().equals(fourOfAKindRank)) {
					if(actCard.getRank().equals(compareToCard.getRank())) {
						sameCardCounter++;
					}
				}
			}
			if(sameCardCounter == 3) {
				fourOfAKindCounter++;
				fourOfAKindRank = actCard.getRank();
			}
		}
		if(fourOfAKindCounter == 1) {
			Hand hand = new Hand();
			CardRank highCard = searchHighCard(cards);
			hand.setHandRank(HandRank.FOUR_OF_A_KIND);
			hand.setValue(fourOfAKindRank.getValue());
			hand.setHighCard(highCard);
			return hand;
		} else {
			return null;
		}
	}
	
	private Hand checkStraightFlush(Card[] cards) {
		Hand straightHand = null;
		Hand flushHand = null;
		straightHand = checkStraight(cards);
		if(straightHand != null) {
			flushHand = checkFlush(cards);
			if(flushHand != null) {
				Hand hand = new Hand();
				CardRank highCard = searchHighCard(cards);
				hand.setHandRank(HandRank.STRAIGHT_FLUSH);
				hand.setValue(highCard.getValue());
				hand.setHighCard(highCard);
				return hand;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	private Hand checkRoyalFlush(Card[] cards) {
		Hand straightFlushHand = null;
		straightFlushHand = checkStraightFlush(cards);
		if(straightFlushHand != null) {
			CardRank highCard = searchHighCard(cards);
			if(highCard.equals(CardRank.ACE)) {
				Hand hand = new Hand();
				hand.setHandRank(HandRank.ROYAL_FLUSH);
				hand.setValue(highCard.getValue());
				hand.setHighCard(highCard);
				return hand;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	private CardRank searchHighCard(Card[] cards) {
		CardRank highCard = CardRank.TWO;
		for(Card actCard : cards) {
			if(actCard.rank.getValue() > highCard.getValue()) {
				highCard = actCard.rank;
			}
		}
		return highCard;
	}

}
