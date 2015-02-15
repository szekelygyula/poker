package szgy.poker.game;

import org.json.simple.JSONObject;

public interface JSONAble {
	/**
	 * Kiírja az objektumot egy JSONObject-be.
	 * @return A objektummal JSONObject formájában.
	 */
	public JSONObject toJSON();
}
