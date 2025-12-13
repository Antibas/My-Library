package com.antibas.math.function;

import com.antibas.math.Number2;

public abstract class SeriesClass implements Series{
	protected double amplitude;
	protected double adder;
	protected Domain domain;
	
	public SeriesClass(double amplitude, double adder, Domain domain) {
		this.amplitude = amplitude;
		this.adder = adder;
		this.domain = domain;
	}
	
	public SeriesClass(double amplitude, double adder) {
		this(amplitude, adder, Number2.R_INT);
	}
	
	public SeriesClass(double amplitude) {
		this(amplitude, 0.0d);
	}
	
	public SeriesClass(Domain domain) {
		this(1.0d, 0.0d, domain);
	}
	
	public SeriesClass() {
		this(1.0d, 0.0d);
	}

    public SeriesClass add(SeriesClass f) {
		Series r = x -> this.apply(x) + f.apply(x); 
		return (SeriesClass)r;
	}

	public double getAmplitude() {
		return amplitude;
	}

	public void setAmplitude(double amplitude) {
		this.amplitude = amplitude;
	}

	public double getAdder() {
		return adder;
	}

	public void setAdder(double adder) {
		this.adder = adder;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	@Override
	public String toString() {
		return toString("n");
	}
	
	public abstract String toString(String var);
}
