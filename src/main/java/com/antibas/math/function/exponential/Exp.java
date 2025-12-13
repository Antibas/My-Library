package com.antibas.math.function.exponential;

import com.antibas.math.function.FunctionClass;

public class Exp extends FunctionClass {
	protected double base;
	
	public Exp(double base, double amplitude, double adder) {
		super(amplitude, adder);
		this.base = base;
	}
	
	public Exp(double base, double amplitude) {
		this(base, amplitude, 0.0d);
	}
	
	public Exp(double base) {
		this(base, 1.0d, 0.0d);
	}

	public double getBase() {
		return base;
	}

	public void setBase(double base) {
		this.base = base;
	}

	@Override
	public Double apply(Double t) {
		return amplitude*Math.pow(base, t) + adder;
	}

	@Override
	public String toString(String var) {
		return amplitude + "*" + base + "^" + var + " + " + adder;
	}
	
}
