package com.antibas.math.function.trigonometrical;

public class Cos extends Sin {
	public Cos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cos(double amplitude, double frequency, double phase) {
		super(amplitude, frequency, phase);
		// TODO Auto-generated constructor stub
	}

	public Cos(double amplitude, double frequency) {
		super(amplitude, frequency);
		// TODO Auto-generated constructor stub
	}

	public Cos(double frequency) {
		super(frequency);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Double apply(Double t) {
		return super.apply(Math.cos(frequency*t + this.getPhase()));
	}
	
	@Override
	public String toString(String var) {
		return amplitude + "*cos(" + var + " + " + getPhase() +") + " + adder;
	}
}
