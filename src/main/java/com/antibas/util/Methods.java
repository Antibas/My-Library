/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.util;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 *
 * @author User
 */
public final class Methods {
    public static<T> T[] subArray(T[] array, int beg, int end) {
	return Arrays.copyOfRange(array, beg, end + 1);
    }
    
    public static File downloadFromYoutube(String address, String name) throws IOException, URISyntaxException {
        File f = new File(name);
        URL url = new URI(address).toURL();//new URL(address);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
        BufferedWriter out = new BufferedWriter(new FileWriter(f));
        int c = in.read();
        while (c != -1) {
            out.write(c);
            c = in.read();
        }
        in.close();
        out.close();
        return f;
    }
    
    public static int max(int[] a){
        int max = a[0];
        for(int aa: a){
            if(aa > max) max = aa;
        }
        return max;
    }
    
    public static int min(int[] a){
        int min = a[0];
        for(int aa: a){
            if(aa < min) min = aa;
        }
        return min;
    } 
    
    public static int maxLengthString(String[] s){
        int max = s[0].split(" ").length;
        for(String ss: s){
            if(ss.trim().split(" ").length > max) max = ss.trim().split(" ").length;
        }
        return max;
    }
    
    public static <T extends Comparable<T>> T max(Iterable<T> a) {
    	Iterator<T> it = a.iterator();
    	if(!it.hasNext()) {
    		return null;
    	}
    	T max = it.next();
    	while(it.hasNext()) {
    		T t = it.next();
    		if(t.compareTo(max) > 0) {
    			max = t;
    		}
    	}
    	return max;
    }
    
    public static <T extends Comparable<T>> T min(Iterable<T> a) {
    	Iterator<T> it = a.iterator();
    	if(!it.hasNext()) {
    		return null;
    	}
    	T min = it.next();
    	while(it.hasNext()) {
    		T t = it.next();
    		if(t.compareTo(min) < 0) {
    			min = t;
    		}
    	}
    	return min;
    }
    /**
     * 
     * @param variable
     * @param c
     * @return 
     * @throws IllegalAccessException
     */
    public static String variableName(Object variable, Class<?> c) throws IllegalAccessException{
//        Field[] fields = c.getFields();
//
//        for(Field field: fields){
//            if(field.get(null).equals(variable)) return field.getName();
//        }
//        return null;
        return Arrays.stream(c.getFields()).filter(f -> {
            try {
                return f.get(null).equals(variable);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        })
        .findFirst()
        .orElseThrow(IllegalArgumentException::new)
        .getName();
    }
    
    public static int sum(int a, int b, int[] array){
        if(a >= b || a < 0 || b > array.length-1) throw new IllegalArgumentException();
        int S = 0;
        for(int i = a; i <= b; i++)
            S += array[i];
        return S;
    }
    
    public static int sum(int[] array){
        return sum(0, array.length-1, array);
    }
    
    public static String arrayAsText(String[] a){
        StringBuilder ret = new StringBuilder();
        for(String s: a){
            ret.append(s).append(" ");
        }
        return ret.toString();
    }
    
    public static Vector<Integer> findAllIndexesOf(String string, String substring){
        Vector<Integer> indexes = new Vector<>();
        
        int index = string.indexOf(substring);
        while(index >= 0){
            indexes.add(index);
            index = string.indexOf(substring, index+(substring.length()-1));
        }
        return indexes;
    }
    
    public static <T> Set<T> arrayToSet(T[] array) {
        Set<T> set = new HashSet<>();
        Collections.addAll(set, array);
        return set; 
    }
    
    public static <T extends Serializable> T deepCopy(T object) {
		   try {
		     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		     ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
		     outputStrm.writeObject(object);
		     ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
		     ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
		     return (T) objInputStream.readObject();
		   }
		   catch (Exception e) {
		     e.printStackTrace();
		     return null;
		   }
	 }
    
    public static <T extends Enum<T>> T[] valuesOf(Class<T> cl, String[] values) {
    	T[] enums = (T[]) Array.newInstance(cl, values.length);
    	for(int i = 0; i < enums.length; i++) {
    		enums[i] = Enum.<T>valueOf(cl, values[i]);
        }
    	return enums;
    }
    
    public static boolean and(boolean...bs) {
    	for(boolean b: bs) {
    		if(!b) {
    			return false;
    		}
    	}
    	return true;
    }
    
    public static boolean or(boolean...bs) {
    	for(boolean b: bs) {
    		if(b) {
    			return true;
    		}
    	}
    	return false;
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static boolean isBoolean(String value) {
        return value.equalsIgnoreCase("true")
                || value.equalsIgnoreCase("false");
    }

    public static boolean parseBoolean(String value) {
        if(!isBoolean(value))
            throw new IllegalArgumentException("Invalid boolean value: "+ value);

        return value.equalsIgnoreCase("true");
    }

    public static String toSnakeCase(String input) {
        return input
                .replaceAll("([a-z0-9])([A-Z])", "$1_$2")
                .replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
                .toLowerCase();
    }
}
