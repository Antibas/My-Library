package com.antibas.util.json;

import java.io.Serial;
import java.util.*;

public class Json extends HashMap<String, Object> implements Iterable<Object>{

	public Json(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	public Json(int initialCapacity) {
		super(initialCapacity);
	}

	public Json() {
		super();
	}

	public Json(Map<? extends String, ?> m) {
		super(m);
	}

	public Json(Json json){
		super(json);
	}

	public Json(String json){
		this(Json.parse(json));
	}

	public Json(String json, String delimiter){
		this(Json.parse(json, delimiter));
	}


	public Object putParse(String key, String value) {
		return super.put(key, JsonEntry.parseValue(value));
	}

	public Object put(JsonEntry jsonEntry) {
		return super.put(jsonEntry.getFirst(), jsonEntry.getSecond());
	}

	public void putAll(Collection<? extends JsonEntry> m) {
//		for(JsonEntry jsonEntry: m)
//			this.put(jsonEntry);
		m.forEach(this::put);
	}


	@Serial
	private static final long serialVersionUID = 1856733155231772823L;

	@Override
	public String toString() {
		return toString(1);
	}

	public String toString(int depth) {
		String depthStr = "\t".repeat(depth), bracketDepth = "\t".repeat(depth-1);
		StringBuilder str = new StringBuilder(bracketDepth+"{\n");
		for(Entry<String, Object> entry: this.entrySet()){
			String v;
            switch (entry.getValue()) {
                case null -> v = "null";
                case String strValue -> v = "\"" + strValue + "\"";
                case Json json -> v = json.toString(depth + 1);
                default -> v = entry.getValue().toString();
            }
			str.append(depthStr).append("\"").append(entry.getKey()).append("\": ").append(v).append(",\n");
		}
		str.delete(str.length()-2, str.length()-1);
		str.append(bracketDepth).append("}");
		return str.toString();
	}

	public static Json parse(String jsonStr){
		return Json.parse(jsonStr, "\n");
	}

	public static Json parse(String jsonStr, String delimiter){
		if(jsonStr == null || jsonStr.isEmpty())
			return null;
		Json json = new Json();

		String[] entries = jsonStr.split(delimiter);
		for(JsonEntry jsonEntry : Arrays.stream(entries).map(JsonEntry::new).toList()){
			json.put(jsonEntry);
		}
		return json;
	}

	@Override
	public Iterator<Object> iterator() {
		return new JsonIterator();
	}

	private class JsonIterator implements Iterator<Object> {
		private final List<String> stack;

		public JsonIterator() {
			this.stack = new ArrayList<>(Json.this.keySet());
		}
		@Override
		public boolean hasNext() {
			return !this.stack.isEmpty();
		}

		@Override
		public Object next() {
			return Json.this.get(this.stack.removeFirst());
		}
	}
}
