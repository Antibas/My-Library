package com.antibas.math;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public final class Natural extends Whole {
	
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 5636100019717487640L;

	public Natural(int value) {
		this((long)value);
	}

	public Natural(long value) {
		super(value);
		if(value < 0) throw new IllegalArgumentException();
	}
	
	public Natural(Number2 value) {
		this(value.intValue());
	}

	@Override
    public void setValue(int value) {
		if(value < 0) throw new IllegalArgumentException();
		super.setValue(value);
	}
}
