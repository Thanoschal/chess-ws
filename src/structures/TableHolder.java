package structures;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import obj_classes.Table;

public class TableHolder {
	private static List<Table> tableList = new LinkedList<>();
	private static TableHolder instance = null;
	
	public static synchronized TableHolder getInstance () {
		if (instance == null) instance = new TableHolder();
		return instance;
	}
	
	public synchronized void add(Table t) {
		tableList.add(t);
	}
	
	public synchronized void remove(Table t) {
		tableList.removeIf(table -> table.equals(t));
	}
	
	/* 
	 * https://codingwithcake.com/java/java-8-convert-map-json-array/ 
	 */
	public synchronized JsonArray retrieveAllTables() {
		JsonArray array = new JsonArray();
		for (Table table : tableList) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("white", table.getWhite());
			jsonObject.addProperty("black", table.getBlack());
			array.add(jsonObject);
		}
		return array;
	}
}