package com.antibas.util.list;

import java.io.Serial;
import java.util.Collection;
import java.util.Iterator;

public class ReverseArrayList<T> extends ArrayListIndexConvertor<T> {
	
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 6758130848640775718L;

	public ReverseArrayList() {
		super();
	}

	public ReverseArrayList(Collection<? extends T> c) {
		super(c);
	}

	public ReverseArrayList(int initialCapacity) {
		super(initialCapacity);
	}

	@Override
	public int indexConvert(int index) {
		return this.size()-index-1;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<T>() {
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public T next() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
}
