package com.antibas.util.cluster;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class Cluster implements Collection<Object>{
	protected Vector<Object> items;

	public Cluster() {
		this.items = new Vector<>();
	}
	
	public <T> boolean addItem(T item){
		return this.items.add(item);
	}
	
	public Object getItem(int index){
		return this.items.elementAt(index);
	}
	
	public <T> T getItem(T value, int index){
		int in = this.items.indexOf(value, index);
		if(in == -1) {
			return null;
		}
		return (T) this.items.elementAt(in);
	}
	
	public <T> T getItem(T value){
		return this.<T>getItem(value, 0);
	}
	
	public <T> T removeItem(T value){
		int in = this.items.indexOf(value);
		if(in == -1) {
			return null;
		}
		return (T) this.items.remove(in);
	}

	@Override
	public String toString() {
		return items.toString();
	}

	@Override
	public int size() {
		return items.size();
	}

	@Override
	public boolean isEmpty() {
		return items.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return items.contains(o);
	}

	@Override
	public Iterator<Object> iterator() {
		return items.iterator();
	}

	@Override
	public Object[] toArray() {
		return items.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return items.toArray(a);
	}

	@Override
	public boolean add(Object e) {
		return this.addItem(e);
	}

	@Override
	public boolean remove(Object o) {
		return (this.removeItem(o) != null);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.items.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<?> c) {
		return this.items.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return this.items.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return this.items.retainAll(c);
	}

	@Override
	public void clear() {
		this.items.clear();
	}
	
	
}
