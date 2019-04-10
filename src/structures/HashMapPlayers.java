package structures;

import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class HashMapPlayers {

	private static HashMap<String, String> players = new HashMap<String, String>();
	private static HashMapPlayers instance = null;
	
	public static synchronized HashMapPlayers getInstance () {
		if (instance == null) instance = new HashMapPlayers();
		return instance;
	}
	
	public void push (String key) {
		synchronized (players) {
			players.put(key, key);
		}
	}
	
	public void pop (String key) {
		synchronized (players) {
			players.remove(key);
		}
	}
	
	public void print () {
		synchronized (players) {
			players.keySet().forEach(key->System.out.println(key));
		}
	}
	
	/* 
	 * https://codingwithcake.com/java/java-8-convert-map-json-array/ 
	 */
	public JsonArray retrieveAllKeys() {
		synchronized (players) {
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
}