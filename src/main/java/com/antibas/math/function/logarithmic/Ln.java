package com.antibas.math.function.logarithmic;

import com.antibas.math.Math2;

public class Ln extends Log{
	public Ln() {
		super(Math2.E);
	}
	
	public Ln(double amplitude, double adder) {
		super(Math2.E, amplitude, adder);
	}

	public Ln(double amplitude) {
		super(Math2.E, amplitude);
	}
	
	@Override
	@Deprecated
	public void setBase(double base) {
		throw new UnsupportedOperationException("Cannot set base of Ln function");
	}

	@Override
	public Double apply(Double t) {
		return amplitude*Math.log(t) + adder;
	}
	
	@Override
	public String toString(String var) {
		return amplitude + "*ln(" + var + ") + " + adder;
	}
}
