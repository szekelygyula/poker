package szgy.poker.game;

import szgy.poker.exception.EvaluatorException;

public interface Evaluator {
	/**
	 * Egy kártyákat tartalmazó tömbbõl megállapítja, hogy milyen kombináció van benne.
	 * Azért készítettem hozzá interface-t, hogy a különbözõ poker játékokhoz különbözõ kiértékelõt lehessen készíteni.
	 * @param cards A lapok tömbje
	 * @return Egy Hand objektummal tér vissza, ami tartalmazza a kombinációt, a kombináció értékét és a legmagasabb lap értékét.
	 * @throws EvaluatorException Abban az esetben, ha a kártyák nem felelnek meg a poker szabályainak  (pl.: túl sok vagy túl kevés)
	 */
	public Hand evaluate(Card[] cards) throws EvaluatorException;
}
