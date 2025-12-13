package com.antibas.util.list;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;

public class NumberList<T extends Number> extends ArrayList<T> {

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -2918887156876294962L;

	public NumberList() {
		super();
	}

	public NumberList(Collection<? extends T> c) {
		super(c);
	}

	public NumberList(int initialCapacity) {
		super(initialCapacity);
	}
	
	public void plus(T number) {
		for(int i = 0; i < size(); i++) {
			this.set(i, this.get(i));
		}
	}
}
