package com.antibas.util;

import java.io.Serial;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Json extends HashMap<String, Object> implements Iterable<Object>{

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1856733155231772823L;

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("{\n");
		for(Entry<String, Object> entry: this.entrySet()){
			String v;
			if(entry.getValue() instanceof String) {
				v = "\""+entry.getValue()+"\"";
			} else {
				v = entry.getValue().toString();
			}
			str.append("\t\"").append(entry.getKey()).append("\": ").append(v).append(",\n");
		}
		str.append("}");
		return str.toString();
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
