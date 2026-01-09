/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.util.list;

import com.antibas.math.combinations.Combination;
import com.antibas.math.Math2;
import com.antibas.util.ArrayUtils;
import com.antibas.util.Methods;

import java.io.Serial;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author User
 */
public class RandomArrayList<E> extends ArrayListIndexConvertor<E> {
	@Serial
	private static final long serialVersionUID = 1239168756052538810L;

	private long seed;

	public RandomArrayList(){
        super();
    }

	public RandomArrayList(long seed) {
		super();
		this.seed = seed;
	}

	public RandomArrayList(long seed, Collection<? extends E> c) {
		super(c);
		this.seed = seed;
	}


	public RandomArrayList(Collection<? extends E> c) {
		super(c);
	}

	public RandomArrayList(long seed, int initialCapacity) {
		super(initialCapacity);
		this.seed = seed;
	}

	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

	@Override
	public int indexConvert(int index) {
		return Math2.RNG(0, this.size()-1, this.seed);
	}

	/*@Override
	public int indexOf(Object o) {
		return this.indexConvert(0);
	}

	@Override
	public int lastIndexOf(Object o) {
		return this.indexConvert(0);
	}

	@Override
	public E get(int index) {
		return super.get(this.indexConvert(index));
	}

	@Override
	public E set(int index, E element) {
		return super.set(this.indexConvert(index), element);
	}

	@Override
	public E remove(int index) {
		return super.remove(this.indexConvert(index));
	}

	@Override
	public boolean remove(Object o) {
		return super.remove(o);
	}

	@Override
	protected void removeRange(int fromIndex, int toIndex) {
		super.removeRange(this.indexConvert(fromIndex), this.indexConvert(toIndex));
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return super.listIterator(this.indexConvert(index));
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return super.subList(this.indexConvert(fromIndex), this.indexConvert(toIndex));
	}*/
	
	
	
	@Override
	public Iterator<E> iterator() {
        return new Iterator<E>() {
			private final int[] indexSet = Math2.range(0, size());
			private final boolean[] indices = ArrayUtils.falses(indexSet.length);

			@Override
			public boolean hasNext() {
				return Methods.and(indices);//!(currentIndexR == rows-1 && currentIndexC == columns); //);//currentIndexR < rows;
			}

			@Override
			public E next() {
				int index;
				do{
					index = Combination.choice(indexSet);
				} while(indices[index]);
				indices[index] = true;
				return RandomArrayList.this.get(index);

			}

			@Override
			public void remove() {
				RandomArrayList.this.remove(this.next());
				//throw new UnsupportedOperationException();
			}
		};
	}

	/*@Override
	public String toString() {
		String s = "";
		Iterator<E> it = this.iterator();
		while(it.hasNext()) {
			
		}
		return s;
	}*/
	
	
}
