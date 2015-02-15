package szgy.poker.game;

import szgy.poker.exception.EvaluatorException;

public interface Evaluator {
	/**
	 * Egy k�rty�kat tartalmaz� t�mbb�l meg�llap�tja, hogy milyen kombin�ci� van benne.
	 * Az�rt k�sz�tettem hozz� interface-t, hogy a k�l�nb�z� poker j�t�kokhoz k�l�nb�z� ki�rt�kel�t lehessen k�sz�teni.
	 * @param cards A lapok t�mbje
	 * @return Egy Hand objektummal t�r vissza, ami tartalmazza a kombin�ci�t, a kombin�ci� �rt�k�t �s a legmagasabb lap �rt�k�t.
	 * @throws EvaluatorException Abban az esetben, ha a k�rty�k nem felelnek meg a poker szab�lyainak  (pl.: t�l sok vagy t�l kev�s)
	 */
	public Hand evaluate(Card[] cards) throws EvaluatorException;
}
