package com.antibas.math.function.other;

import com.antibas.math.function.FunctionClass;

public class Tri extends FunctionClass {
	protected double T;

	public Tri(double T, double amplitude, double adder) {
		super(amplitude, adder);
		this.T = T;
	}

	public Tri(double T, double amplitude) {
		this(T, amplitude, 0.0d);
	}
	
	public Tri(double T) {
		this(T, 1.0d, 0.0d);
	}
	
	public Tri() {
		this(1.0d, 1.0d, 0.0d);
	}
	
	@Override
	public Double apply(Double t) {
		return amplitude*((t > -T && t < T)? (1.0d - Math.abs(t)/T) : 0.0d) + adder;
	}
	
	@Override
	public String toString(String var) {
		return amplitude + "*tri(" + var + "/" + T +") + " + adder;
	}
}
