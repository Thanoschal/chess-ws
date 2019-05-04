package structures;

import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class HashMapPlayers {

	private static HashMap<String, String> players = new HashMap<>();
	private static HashMapPlayers instance = null;
	
	public static synchronized HashMapPlayers getInstance () {
		if (instance == null) instance = new HashMapPlayers();
		return instance;
	}
	
	public synchronized void push (String key) {
		players.put(key, key);
	}
	
	public synchronized void pop (String key) {
		players.remove(key);
	}
	
	/* 
	 * https://codingwithcake.com/java/java-8-convert-map-json-array/ 
	 */
	public synchronized JsonArray retrieveAllKeys() {
		return players.entrySet().stream().map(r -> {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("name", r.getKey());
			return jsonObject;
		}).reduce(new JsonArray(), (jsonArray, jsonObject) -> {
			jsonArray.add(jsonObject);
			return jsonArray;
		}, (jsonArray, otherJsonArray) -> {
			jsonArray.addAll(otherJsonArray);
			return jsonArray;
		});
	}
}