package com.antibas.util.list;

import com.antibas.math.Number2;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;

public class NumberList<T extends Number2> extends ArrayList<T> {

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
        this.replaceAll(t -> (T) t.add(number));
	}

	public void minus(T number) {
		this.replaceAll(t -> (T) t.subtract(number));
	}
}
