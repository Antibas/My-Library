package com.antibas.util;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CountMap<T> extends HashMap<T, Integer>{

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;

	public CountMap() {
		super();
	}

	public CountMap(Set<T> keySet, int startValue) {
		super();
		for(T key: keySet) {
			super.put(key, startValue); 
		}
	}
	
	public CountMap(Set<T> keySet) {
		this(keySet, 0);
	}

	public CountMap(Map<? extends T, ? extends Integer> m) {
		super(m);
	}

	public CountMap(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	public CountMap(int initialCapacity) {
		super(initialCapacity);
	}

	public Integer increase(T key, int count) {
		return super.put(key, super.get(key)+count);
	}
	
	public Integer decrease(T key, int count) {
		return super.put(key, super.get(key)-count);
	}
	
	public Integer increase(T key) {
		return increase(key, 1);
	}
	
	public Integer decrease(T key) {
		return decrease(key, 1);
	}
	
}
