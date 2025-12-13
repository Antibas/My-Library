package com.antibas.math.function.other;

import com.antibas.math.function.FunctionClass;

public class Rect extends FunctionClass {
	protected double T;

	public Rect(double T, double amplitude, double adder) {
		super(amplitude, adder);
		this.T = T;
	}

	public Rect(double T, double amplitude) {
		this(T, amplitude, 0.0d);
	}
	
	public Rect(double T) {
		this(T, 1.0d, 0.0d);
	}
	
	public Rect() {
		this(1.0d, 1.0d, 0.0d);
	}
	
	@Override
	public Double apply(Double t) {
		return amplitude*((t > -T/2 && t < T/2)? 1.0d : 0.0d) + adder;
	}

	@Override
	public String toString(String var) {
		return amplitude + "*rect(" + var + "/" + T +") + " + adder;
	}
}
