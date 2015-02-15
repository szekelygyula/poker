package szgy.poker.test;

import static org.junit.Assert.*;

import org.junit.Test;

import szgy.poker.exception.FiveCardEvaluatorException;
import szgy.poker.game.Card;
import szgy.poker.game.CardRank;
import szgy.poker.game.CardSuit;
import szgy.poker.game.FiveCardEvaluator;
import szgy.poker.game.HandRank;

public class EvaluatorTest {

	@Test
	public void test() {
		try {
			FiveCardEvaluator evaluator  = new FiveCardEvaluator();
			
			Card[] highCardHand = {new Card(CardSuit.SPADES, CardRank.ACE), 
								new Card(CardSuit.DIAMONDS, CardRank.KING), 
								new Card(CardSuit.HEARTS, CardRank.NINE),
								new Card(CardSuit.CLUBS, CardRank.FOUR),
								new Card(CardSuit.CLUBS, CardRank.QUEEN),};
			HandRank highCardHandExpected = HandRank.HIGH_CARD;
			assertEquals("Magas lap", highCardHandExpected, evaluator.evaluate(highCardHand).getHandRank());
			
			Card[] pairHand = {new Card(CardSuit.SPADES, CardRank.ACE), 
								new Card(CardSuit.DIAMONDS, CardRank.KING), 
								new Card(CardSuit.HEARTS, CardRank.NINE),
								new Card(CardSuit.CLUBS, CardRank.NINE),
								new Card(CardSuit.CLUBS, CardRank.QUEEN),};
			HandRank pairHandExpected = HandRank.PAIR;
			assertEquals("Egy pár", pairHandExpected, evaluator.evaluate(pairHand).getHandRank());
			

			Card[] twoPairHand = {new Card(CardSuit.SPADES, CardRank.QUEEN), 
								new Card(CardSuit.DIAMONDS, CardRank.KING), 
								new Card(CardSuit.HEARTS, CardRank.NINE),
								new Card(CardSuit.CLUBS, CardRank.NINE),
								new Card(CardSuit.CLUBS, CardRank.QUEEN),};
			HandRank twoPairHandExpected = HandRank.TWO_PAIR;
			assertEquals("Két pár", twoPairHandExpected, evaluator.evaluate(twoPairHand).getHandRank());

			Card[] threeOfAKindHand = {new Card(CardSuit.SPADES, CardRank.NINE), 
								new Card(CardSuit.DIAMONDS, CardRank.KING), 
								new Card(CardSuit.HEARTS, CardRank.NINE),
								new Card(CardSuit.CLUBS, CardRank.NINE),
								new Card(CardSuit.CLUBS, CardRank.QUEEN),};
			HandRank threeOfAKindHandExpected = HandRank.THREE_OF_A_KIND;
			assertEquals("Drill", threeOfAKindHandExpected, evaluator.evaluate(threeOfAKindHand).getHandRank());

			Card[] starightHand = {new Card(CardSuit.SPADES, CardRank.ACE), 
								new Card(CardSuit.DIAMONDS, CardRank.KING), 
								new Card(CardSuit.HEARTS, CardRank.JACK),
								new Card(CardSuit.CLUBS, CardRank.TEN),
								new Card(CardSuit.CLUBS, CardRank.QUEEN),};
			HandRank straightHandExpected = HandRank.STRAIGHT;
			assertEquals("Sor", straightHandExpected, evaluator.evaluate(starightHand).getHandRank());

			Card[] flushHand = {new Card(CardSuit.SPADES, CardRank.ACE), 
								new Card(CardSuit.SPADES, CardRank.KING), 
								new Card(CardSuit.SPADES, CardRank.THREE),
								new Card(CardSuit.SPADES, CardRank.NINE),
								new Card(CardSuit.SPADES, CardRank.QUEEN),};
			HandRank flushHandExpected = HandRank.FLUSH;
			assertEquals("Flöss", flushHandExpected, evaluator.evaluate(flushHand).getHandRank());

			Card[] fullHouseHand = {new Card(CardSuit.SPADES, CardRank.ACE), 
								new Card(CardSuit.DIAMONDS, CardRank.ACE), 
								new Card(CardSuit.HEARTS, CardRank.ACE),
								new Card(CardSuit.CLUBS, CardRank.NINE),
								new Card(CardSuit.HEARTS, CardRank.NINE),};
			HandRank fullHouseHandExpected = HandRank.FULL_HOUSE;
			assertEquals("Full", fullHouseHandExpected, evaluator.evaluate(fullHouseHand).getHandRank());

			Card[] fourOfAKindHand = {new Card(CardSuit.SPADES, CardRank.NINE), 
								new Card(CardSuit.DIAMONDS, CardRank.NINE), 
								new Card(CardSuit.HEARTS, CardRank.NINE),
								new Card(CardSuit.CLUBS, CardRank.NINE),
								new Card(CardSuit.CLUBS, CardRank.QUEEN),};
			HandRank fourOfAKindHandExpected = HandRank.FOUR_OF_A_KIND;
			assertEquals("Póker", fourOfAKindHandExpected, evaluator.evaluate(fourOfAKindHand).getHandRank());

			Card[] straightFlushHand = {new Card(CardSuit.SPADES, CardRank.TEN), 
								new Card(CardSuit.SPADES, CardRank.EIGHT), 
								new Card(CardSuit.SPADES, CardRank.SIX),
								new Card(CardSuit.SPADES, CardRank.NINE),
								new Card(CardSuit.SPADES, CardRank.SEVEN),};
			HandRank straightFlushHandExpected = HandRank.STRAIGHT_FLUSH;
			assertEquals("Színsor", straightFlushHandExpected, evaluator.evaluate(straightFlushHand).getHandRank());
			
			Card[] royalFlushhHand = {new Card(CardSuit.SPADES, CardRank.JACK), 
								new Card(CardSuit.SPADES, CardRank.KING), 
								new Card(CardSuit.SPADES, CardRank.TEN),
								new Card(CardSuit.SPADES, CardRank.QUEEN),
								new Card(CardSuit.SPADES, CardRank.ACE),};
			HandRank royalFlushHandExpected = HandRank.ROYAL_FLUSH;
			assertEquals("Royal flöss", royalFlushHandExpected, evaluator.evaluate(royalFlushhHand).getHandRank());
			
		} catch (FiveCardEvaluatorException e) {
			e.printStackTrace();
		}
	}

}
