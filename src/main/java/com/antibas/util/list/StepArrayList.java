package com.antibas.util.list;

import java.io.Serial;
import java.util.Collection;

public class StepArrayList<T> extends ArrayListIndexConvertor<T> {

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -8224538278152981821L;
	private int step;

	public StepArrayList() {
		this(1);
	}

	public StepArrayList(Collection<? extends T> c) {
		this(1, c);
	}

	public StepArrayList(int step) {
		super();
		this.step = step;
	}

	public StepArrayList(int step, Collection<? extends T> c) {
		super(c);
		this.step = step;
	}

	public StepArrayList(int step, int initialCapacity) {
		super(initialCapacity);
		this.step = step;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	@Override
	public int indexConvert(int index) {
		return index+this.step;
	}
	
}
