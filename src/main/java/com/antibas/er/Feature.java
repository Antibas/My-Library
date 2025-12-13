package com.antibas.er;

import com.antibas.util.Nameable;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import java.util.function.Function;

public class Feature implements Nameable, Collection<Object>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7289897119009164996L;
	
	private String name;
	private Vector<Object> values;
	
	public Feature(String name) {
		this.name = name;
		this.values = new Vector<>();
	}
	
	public Feature(String name, int capacity, Object initValue) {
		this.name = name;
		this.values = new Vector<>(capacity);
		/*for(int i = 0; i < this.values.capacity(); i++) {
			this.values.add(initValue);
		}*/
	}
	
	public Feature(String name, int capacity) {
		this(name, capacity, null);
	}
	
	public Feature(String name, Vector<Object> values) {
		this.name = name;
		this.values = values;
	}
	
	public Object elementAt(int index) {
		return this.values.elementAt(index);
	}
	
	public Vector<Object> selection(Function<Object, Boolean> cond){
		Vector<Object> selected = new Vector<>();
		for(Object t: this.values) {
			if(cond.apply(t)) {
				selected.add(t);
			}
		}
		return selected;
	}
	
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.values.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.values.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return this.values.contains(o);
	}

	@Override
	public Iterator<Object> iterator() {
		// TODO Auto-generated method stub
		return this.values.iterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return this.values.toArray();
	}

	@Override
	public <U> U[] toArray(U[] a) {
		// TODO Auto-generated method stub
		return this.values.toArray(a);
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return this.values.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return this.values.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends Object> c) {
		// TODO Auto-generated method stub
		return this.values.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return this.values.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return this.retainAll(c);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.values.clear();
	}

	@Override
	public boolean add(Object objects) {
		// TODO Auto-generated method stub
		return this.values.add(objects);
	}
	
	public Object set2(int index, Object value) {
		return this.values.set(index, value);
	}

	public Object set(int index, Object value) {
		return this.values.set(index, value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feature other = (Feature) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
