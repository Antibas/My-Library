package com.antibas.util.itemset.item;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Item<T> extends com.antibas.util.itemset.Item{
	private T item;

	public Item() {
		this(null);
	}

	public Item(T item) {
		super();
		this.item = item;
	}

	public Item(T item, double weight, double benefit) {
		super(weight, benefit);
		this.item = item;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return item.equals(obj);
	}
	
	
}
