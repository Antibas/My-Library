package com.antibas.math.function.trigonometrical;

public class Tan extends Sin {
	public Tan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tan(double amplitude, double frequency, double phase) {
		super(amplitude, frequency, phase);
		// TODO Auto-generated constructor stub
	}

	public Tan(double amplitude, double frequency) {
		super(amplitude, frequency);
		// TODO Auto-generated constructor stub
	}

	public Tan(double frequency) {
		super(frequency);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Double apply(Double t) {
		return amplitude*Math.tan(frequency*t + this.getPhase())+adder;
	}
	
	@Override
	public String toString(String var) {
		return amplitude + "*tan(" + var + " + " + getPhase() +") + " + adder;
	}
}
