package com.antibas.util.list;


import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

public class ConditionArrayList<T> extends ArrayList<T> {
	@Serial
	private static final long serialVersionUID = -5303022406052170453L;

	private Function<T ,Boolean> condition;

	public ConditionArrayList() {
		this(x -> true);
	}
	
	public ConditionArrayList(Collection<? extends T> c) {
		this(x -> true, c);
	}

	public ConditionArrayList(int initialCapacity) {
		this(x -> true, initialCapacity);
	}

	public ConditionArrayList(Function<T ,Boolean> condition) {
		super();
		this.condition = condition;
	}
	
	public ConditionArrayList(Function<T ,Boolean> condition, Collection<? extends T> c) {
		super(c);
		this.condition = condition;
		removeIfNotCondition();
	}

	public ConditionArrayList(Function<T ,Boolean> condition, int initialCapacity) {
		super(initialCapacity);
		this.condition = condition;
	}
	
	private boolean removeIfNotCondition() {
		boolean removed = false;
		for(int i = 0; i < this.size(); i++) {
			if(!this.condition.apply(this.get(i))) {
				this.remove(i);
				removed = true;
			}
		}
		return removed;
	}

	public Function<T, Boolean> getCondition() {
		return condition;
	}

	@Override
	public T set(int index, T element) {
		if(!condition.apply(element)) {
			return null;
		}
		return super.set(index, element);
	}

	@Override
	public boolean add(T e) {
		if(!condition.apply(e)) {
			return false;
		}
		return super.add(e);
	}

	@Override
	public void add(int index, T element) {
		if(!condition.apply(element)) {
			return;
		}
		super.add(index, element);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		return super.addAll(c) && !removeIfNotCondition();
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		return super.addAll(index, c) && !removeIfNotCondition();
	}

    public void setCondition(Function<T, Boolean> condition) {
		this.condition = condition;
		removeIfNotCondition();
	}
	
	
}
