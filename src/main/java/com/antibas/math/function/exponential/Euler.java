package com.antibas.math.function.exponential;

import com.antibas.math.Math2;

public class Euler extends Exp {
	public Euler() {
		super(Math2.E);
	}

	public Euler(double amplitude, double adder) {
		super(Math2.E, amplitude, adder);
	}

	public Euler(double amplitude) {
		super(Math2.E, amplitude);
	}

	@Override
	@Deprecated
	public void setBase(double base) {
		throw new UnsupportedOperationException("Cannot set base of Euler function");
	}
	
}
