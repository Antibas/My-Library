package com.antibas.util.pair;

import java.io.Serial;
import java.util.Iterator;

public class NamedPair<V> extends Pair<V, V> implements Iterable<V>{

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -4487931904493274585L;

	public NamedPair(V first, V second) {
		super(first, second);
	}

	@Override
	public Iterator<V> iterator() {
		return new Iterator<>() {
			int i = 0;
			@Override
			public boolean hasNext() {
				return i < 2;
			}

			@Override
			public V next() {
				return (i++ == 0)? NamedPair.this.first: NamedPair.this.second;
			}
		};
	}

}
