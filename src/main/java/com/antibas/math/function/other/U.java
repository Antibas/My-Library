package com.antibas.math.function.other;

import com.antibas.math.function.FunctionClass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class U extends FunctionClass{
	protected double t0;

	public U(double t0, double amplitude, double adder) {
		super(amplitude, adder);
		this.t0 = t0;
	}

	public U(double t0, double amplitude) {
		this(t0, amplitude, 0.0d);
	}
	
	public U(double t0) {
		this(t0, 1.0d, 0.0d);
	}
	
	public U() {
		this(0.0d, 1.0d, 0.0d);
	}

    @Override
	public Double apply(Double t) {
		return amplitude*((t >= t0)? 1.0d : 0.0d) + adder;
	}

	@Override
	public String toString(String var) {
		return amplitude + "*u(" + var + ") + " + adder;
	}
}
