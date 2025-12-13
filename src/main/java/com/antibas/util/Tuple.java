package com.antibas.util;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

public class Tuple<T> implements Collection<T>{
	private final Vector<T> items;
	
	public Tuple() {
		items = new Vector<>();
	}
	
	public Tuple(T...ts) {
		items = new Vector<>();
        Collections.addAll(items, ts);
	}
	
	public Tuple(Collection<? extends T> c) {
		items = new Vector<>(c);
	}
	
	public T elementAt(int index) {
		return items.elementAt(index);
	}
	
	@Override
	public String toString() {
		return "(" + items.toString().substring(1, items.toString().length()-1) + ")";
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return items.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return items.contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return items.iterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return items.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return items.toArray(a);
	}

	@Override
	@Deprecated
	public boolean add(T e) {
		throw new UnsupportedOperationException("Tuple is immutable. Use addAll() instead." );
		//return items.add(e);
	}

	@Override
	@Deprecated
	public boolean remove(Object o) {
		throw new UnsupportedOperationException("Tuple is immutable. Use addAll() instead.");
		//return items.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return items.contains(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return items.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return items.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return items.removeAll(c);
	}

	@Override
	public void clear() {
		items.clear();
	}

}
