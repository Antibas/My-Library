package com.antibas.util.itemset;

import com.antibas.util.ArrayUtils;

import java.io.Serial;
import java.util.*;

public class ItemSet extends HashSet<Item>{

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -3620119176387505062L;
	
	public ItemSet() {
		super();
	}

	public ItemSet(Collection<? extends Item> c) {
		super(c);
	}
	
	public ItemSet(Item... c) {
		super(Arrays.asList(c));
	}

	public ItemSet(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	public ItemSet(int initialCapacity) {
		super(initialCapacity);
	}

	public Item getMaxValueItem() {
//		Item m = new Item();
//		for(Item i: this) {
//			if(m.getValue() < i.getValue()) {
//				m = i;
//			}
//		}
//		return m;
		return this.stream().max(Comparator.comparingDouble(Item::getValue)).orElse(null);
	}
	
	public HashMap<Item, Double> fractionalKnapsack(double weight) {
		double w = 0;
		HashMap<Item, Double> x = new HashMap<>();
		while(w < weight) {
			Item i = getMaxValueItem();
			
			this.remove(i);
			double m = Math.min(i.getWeight(), weight - w);
			x.put(i, m);
			w += m;
		}
		return x;
	}
	
	public double knapsack01(int weight) {
		double[] A, B = ArrayUtils.zerosDouble(weight);
		for(Item i: this) {
			A = B.clone();
			for(int w = (int)i.getWeight(); w < weight; w++) {
				if(A[w - (int)i.getWeight()] > A[w] - (int)i.getBenefit()) {
					B[w] = A[w - (int)i.getWeight()] + (int)i.getBenefit();
				}
			}
		}
		return B[weight-1];
	}
}
