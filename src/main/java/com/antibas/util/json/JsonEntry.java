package com.antibas.util.json;

import com.antibas.util.Methods;
import com.antibas.util.pair.Pair;

import java.util.Arrays;

public class JsonEntry extends Pair<String, Object> {
    public JsonEntry(String first, Object second) {
        super(first, second);
    }

    public JsonEntry(JsonEntry jsonEntry){
        super(jsonEntry.first, jsonEntry.second);
    }

    public JsonEntry(String entry){
        this(parseEntry(entry));
    }

    private static JsonEntry parseEntry(String entry){
        String[] split = entry.split(":", 2);
//		if (split.length == 1){
//			return new JsonEntry(null, parseValue(split[0].trim()));
//		}
        if (split.length == 2){
            if(split[0].isEmpty())
//				return new JsonEntry(null, parseValue(split[1]));
                throw new IllegalArgumentException("Empty key");
            return new JsonEntry(split[0].trim(), parseValue(split[1]));
        }
        throw new IllegalArgumentException("Invalid entry: " + Arrays.toString(split));
    }

    public static Object parseValue(String value){
        if(value == null || (value = value.trim()).equals("null")) {
            return null;
        }
        if(Methods.isInteger(value)){
            return Integer.parseInt(value);
        }

        if (Methods.isDouble(value)){
            return Double.parseDouble(value);
        }

        if(Methods.isBoolean(value)){
            return Methods.parseBoolean(value);
        }

        if(value.startsWith("\"")){
            if(value.endsWith("\"")){
                return value.substring(1, value.length()-1);
            }
            throw new IllegalArgumentException();
        }

        if(value.startsWith("[")){
            if(value.endsWith("]")){
//				return value.substring(1, value.length()-1);
                String[] split = value.substring(1, value.length()-1).split(",");
                return Arrays.stream(split).map(JsonEntry::parseValue).toList();
            }
            throw new IllegalArgumentException();
        }

        if(value.startsWith("{")){
            if(value.endsWith("}")){
//				return value.substring(1, value.length()-1);
                return Json.parse(value.substring(1, value.length()-1), ";");
            }
            throw new IllegalArgumentException();
        }

        throw new IllegalArgumentException();

    }
}
