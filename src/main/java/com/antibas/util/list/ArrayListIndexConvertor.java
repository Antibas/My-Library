package com.antibas.util.list;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public abstract class ArrayListIndexConvertor<T> extends ArrayList<T> implements IndexConvertor {
	
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -7547462208936406770L;

	public ArrayListIndexConvertor() {
		super();
	}

	public ArrayListIndexConvertor(Collection<? extends T> c) {
		super(c);
	}

	public ArrayListIndexConvertor(int initialCapacity) {
		super(initialCapacity);
	}

//	@Override
//	public int indexOf(Object o) {
//		return this.indexConvert(super.indexOf(o));
//	}
//
//	@Override
//	public int lastIndexOf(Object o) {
//		return this.indexConvert(super.lastIndexOf(o));
//	}

	@Override
	public T get(int index) {
		return super.get(this.indexConvert(index));
	}

//	@Override
//	public T remove(int index) {
//		return super.remove(this.indexConvert(index));
//	}
//
//	@Override
//	public T set(int index, T element) {
//		return super.set(this.indexConvert(index), element);
//	}
//
//	@Override
//	public void add(int index, T element) {
//		super.add(this.indexConvert(index), element);
//	}
//
//	@Override
//	public boolean addAll(int index, Collection<? extends T> c) {
//		return super.addAll(this.indexConvert(index), c);
//	}
//
//	@Override
//	protected void removeRange(int fromIndex, int toIndex) {
//		super.removeRange(this.indexConvert(fromIndex), this.indexConvert(toIndex));
//	}
//
//	@Override
//	public List<T> subList(int fromIndex, int toIndex) {
//		return super.subList(this.indexConvert(fromIndex), this.indexConvert(toIndex));
//	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return super.listIterator(this.indexConvert(index));
	}
}
