package structures;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import obj_classes.Table;

public class TableHolder {
	private static List<Table> tableList = new LinkedList<Table>();
	private static TableHolder instance = null;
	
	public static synchronized TableHolder getInstance () {
		if (instance == null) instance = new TableHolder();
		return instance;
	}
	
	public void add(Table t) {
		synchronized (tableList) {
			tableList.add(t);
		}
	}
	
	public void remove(Table t) {
		synchronized (tableList) {
			tableList.removeIf(table -> table.equals(t));
		}
	}
	
	public void print() {
		synchronized (tableList) {
			for (Table table : tableList) {
				System.out.println(table.toString());
			}
		}
	}
	
	/* 
	 * https://codingwithcake.com/java/java-8-convert-map-json-array/ 
	 */
	public JsonArray retrieveAllTables() {
		synchronized (tableList) {
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
	
}
