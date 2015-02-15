package szgy.poker.action;

import org.json.JSONArray;
import org.json.JSONObject;

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

	public JSONObject newDeal() throws PlayerException {
		JSONObject object = new JSONObject();
		game.newDeck();
		game.deal();
		Player player = game.getPlayer();
		JSONArray cardsArray = new JSONArray();
		for(Card actCard : player.getCards()) {
			cardsArray.put(actCard.toJSON());
		}
		object.put("status", "success");
		object.put("msg", "Új leosztás sikeres");
		object.put("cards", cardsArray);
		return object;
	}

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
