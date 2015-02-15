package szgy.poker.action;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import szgy.poker.exception.EvaluatorException;
import szgy.poker.exception.PlayerException;
import szgy.poker.game.Card;
import szgy.poker.game.Evaluator;
import szgy.poker.game.FiveCardEvaluator;
import szgy.poker.game.Game;
import szgy.poker.game.Hand;
import szgy.poker.game.Player;

public class GameAction {
	protected Game game;
	
	public GameAction(Game game) {
		this.game = game;
	}
	
	/**
	 * L�trehoz egy �j j�t�kot
	 * @param playerName A j�t�kot j�tsz� j�t�kos neve
	 * @return A l�trehoz�s eredm�ny�t tartalmaz� JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject newGame(String playerName) {
		JSONObject object = new JSONObject();
		game = new Game();
		Player player = new Player(playerName);
		game.setPlayer(player);
		object.put("status", "success");
		object.put("msg", "J�t�k sikeresen l�trehoza");
		object.put("playerName", playerName);
		return object;
	}

	/**
	 * �j lapokat oszt a j�t�kosnak
	 * @return Az oszt�s eredm�ny�t tartalmaz� JSONObject
	 * @throws PlayerException Abban az esetben, ha a j�t�kos nem a szab�lyoknak megfelel�en kap lapot
	 */
	@SuppressWarnings("unchecked")
	public JSONObject newDeal() throws PlayerException {
		JSONObject object = new JSONObject();
		game.newDeck();
		game.deal();
		Player player = game.getPlayer();
		JSONArray cardsArray = new JSONArray();
		for(Card actCard : player.getCards()) {
			cardsArray.add(actCard.toJSON());
		}
		object.put("status", "success");
		object.put("msg", "�j leoszt�s sikeres");
		object.put("cards", cardsArray);
		return object;
	}
	
	/**
	 * Ki�rt�keli a j�t�kos lapjait
	 * @return A ki�rt�kel�s eredm�ny�t tartalmaz� JSONObject
	 * @throws EvaluatorException Abban az esetben, ha a ki�rt�kel�nek adott lapok nem felelnek meg a szab�lyoknak.
	 */
	@SuppressWarnings("unchecked")
	public JSONObject evaluate() throws EvaluatorException {
		JSONObject object = new JSONObject();
		Player player = game.getPlayer();
		Evaluator evaluator = new FiveCardEvaluator();
		Hand hand = evaluator.evaluate(player.getCards());
		object.put("status", "success");
		object.put("msg", "Ki�rt�kel�s sikeres");
		object.put("hand", hand.toJSON());
		return object;
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	
}
