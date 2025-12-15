package com.antibas.math.function.other;

import com.antibas.math.function.FunctionClass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class D extends FunctionClass{
	protected double t0;

	public D(double t0, double amplitude, double adder) {
		super(amplitude, adder);
		this.t0 = t0;
	}

	public D(double t0, double amplitude) {
		this(t0, amplitude, 0.0d);
	}
	
	public D(double t0) {
		this(t0, 1.0d, 0.0d);
	}
	
	public D() {
		this(0.0d, 1.0d, 0.0d);
	}

    @Override
	public Double apply(Double t) {
		return super.apply((t == t0)? 1.0d : 0.0d);
	}
	
	@Override
	public String toString(String var) {
		return amplitude + "*d(" + var + ") + " + adder;
	}

}
