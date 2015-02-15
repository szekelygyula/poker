package szgy.poker.game;

import szgy.poker.exception.EvaluatorException;

public interface Evaluator {
	public Hand evaluate(Card[] cards) throws EvaluatorException;
}
