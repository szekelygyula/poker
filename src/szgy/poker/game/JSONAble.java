package szgy.poker.game;

import org.json.simple.JSONObject;

public interface JSONAble {
	/**
	 * Ki�rja az objektumot egy JSONObject-be.
	 * @return A objektummal JSONObject form�j�ban.
	 */
	public JSONObject toJSON();
}
