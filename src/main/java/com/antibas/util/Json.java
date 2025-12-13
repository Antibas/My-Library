package com.antibas.util;

import java.io.Serial;
import java.util.HashMap;

public class Json extends HashMap<String, Object> {

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

}
