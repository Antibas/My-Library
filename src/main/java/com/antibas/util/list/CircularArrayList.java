/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.util.list;

import java.io.Serial;
import java.util.Collection;

/**
 *
 * @author User
 * @param <T>
 */
public class CircularArrayList<T> extends ArrayListIndexConvertor<T> {
    
    /**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 2964387097130036226L;

	public CircularArrayList(){
        super();
    }
    
    public CircularArrayList(int initialCapacity){
        super(initialCapacity);
    }

	public CircularArrayList(Collection<? extends T> c) {
		super(c);
	}

	@Override
	public int indexConvert(int index) {
		if(index < 0) {
			return Math.abs(this.size()+index) % this.size();
		}
		return index % this.size();
	}
	
	/*@Override
	public T get(int index) {
		return super.get(this.indexConvert(index));
	}

	@Override
	public T remove(int index) {
		return super.remove(this.indexConvert(index));
	}

	@Override
	public T set(int index, T element) {
		return super.set(this.indexConvert(index), element);
	}

	@Override
	public void add(int index, T element) {
		super.add(this.indexConvert(index), element);
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		return super.addAll(this.indexConvert(index), c);
	}

	@Override
	protected void removeRange(int fromIndex, int toIndex) {
		super.removeRange(this.indexConvert(fromIndex), this.indexConvert(toIndex));
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return super.subList(this.indexConvert(fromIndex), this.indexConvert(toIndex));
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return super.listIterator(this.indexConvert(index));
	}*/
}
