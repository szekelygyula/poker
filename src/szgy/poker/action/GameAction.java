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
	 * Létrehoz egy új játékot
	 * @param playerName A játékot játszó játékos neve
	 * @return A létrehozás eredményét tartalmazó JSONObject
	 */
	@SuppressWarnings("unchecked")
	public JSONObject newGame(String playerName) {
		JSONObject object = new JSONObject();
		game = new Game();
		Player player = new Player(playerName);
		game.setPlayer(player);
		object.put("status", "success");
		object.put("msg", "Játék sikeresen létrehoza");
		object.put("playerName", playerName);
		return object;
	}

	/**
	 * Új lapokat oszt a játékosnak
	 * @return Az osztás eredményét tartalmazó JSONObject
	 * @throws PlayerException Abban az esetben, ha a játékos nem a szabályoknak megfelelõen kap lapot
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
		object.put("msg", "Új leosztás sikeres");
		object.put("cards", cardsArray);
		return object;
	}
	
	/**
	 * Kiértékeli a játékos lapjait
	 * @return A kiértékelés eredményét tartalmazó JSONObject
	 * @throws EvaluatorException Abban az esetben, ha a kiértékelõnek adott lapok nem felelnek meg a szabályoknak.
	 */
	@SuppressWarnings("unchecked")
	public JSONObject evaluate() throws EvaluatorException {
		JSONObject object = new JSONObject();
		Player player = game.getPlayer();
		Evaluator evaluator = new FiveCardEvaluator();
		Hand hand = evaluator.evaluate(player.getCards());
		object.put("status", "success");
		object.put("msg", "Kiértékelés sikeres");
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
