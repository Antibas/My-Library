package com.antibas.math;


import lombok.Getter;

import java.io.Serial;

@Getter
public final class Rational extends Number2 implements Comparable<Rational>{

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -4238905875594162213L;
	private int numerator;
	private int denominator;


	public Rational(int numerator, int denominator) {
		if(denominator <= 0 || numerator < 0) {
			throw new IllegalArgumentException();
		}
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public Rational(int numerator) {
		this(numerator, 1);
	}

    public void setNumerator(int numerator) {
		if(numerator < 0) {
			throw new IllegalArgumentException();
		}
		this.numerator = numerator;
	}

    public void setDenominator(int denominator) {
		if(denominator <= 0) {
			throw new IllegalArgumentException();
		}
		this.denominator = denominator;
	}

	public double getValue() {
		return this.doubleValue();
	}

	@Override
	public Complex complexValue() {
		return new Complex(this.doubleValue());
	}

	@Override
	public <T extends Number2>Rational add(T o) {
		if(o instanceof Rational oRational) {
			if(this.denominator == oRational.denominator)
				return new Rational(numerator+oRational.numerator, denominator);
			
			return new Rational(numerator*oRational.denominator+oRational.numerator*denominator, denominator*oRational.denominator);
		}
		return new Rational((int) (numerator+denominator*o.doubleValue()), denominator);
	}

	@Override
	public <T extends Number2>Rational subtract(T o) {
		if(o instanceof Rational oRational) {
			if(this.denominator == oRational.denominator)
				return new Rational(numerator-oRational.numerator, denominator);
			
			return new Rational((int) (numerator*oRational.denominator-oRational.numerator*denominator), denominator*oRational.denominator);
		}
		return new Rational((int) (numerator-denominator*o.doubleValue()), denominator);
	}

	@Override
	public <T extends Number2>Rational multiply(T o) {
		if(o instanceof Rational oRational) {
			return new Rational(numerator*oRational.numerator, denominator*oRational.denominator);
		}
		return new Rational((int) (numerator*o.doubleValue()), this.denominator);
	}

	@Override
	public <T extends Number2>Rational divide(T o) throws ArithmeticException {
		if(o instanceof Rational oRational) {
			return this.multiply(oRational.invert());
		}
		return this.multiply((Number2)(o.invert()));
	}

	@Override
	public Rational invert() {
		return new Rational(this.denominator, this.numerator);
	}

	@Override
	public Rational abs() {
		return new Rational(Math.abs(this.numerator), Math.abs(this.denominator));
	}

	@Override
	public boolean isPositive() {
		return this.doubleValue() > 0;
	}

	@Override
	public int intValue() {
		return numerator/denominator;
	}

	@Override
	public long longValue() {
		return numerator/denominator;
	}

	@Override
	public float floatValue() {
		return ((float)numerator)/((float)denominator);
	}

	@Override
	public double doubleValue() {
		return ((double)numerator)/((double)denominator);
	}

	@Override
	public Number pow(int power) {
		return new Rational(
                (int) Math.pow(numerator, power),
                (int) Math.pow(denominator, power)
        );
	}

	@Override
	public <T extends Number2> boolean greaterThan(T o) {
		return this.doubleValue() > o.doubleValue();
	}

	@Override
	public <T extends Number2> boolean greaterThanOrEqual(T o) {
		return this.doubleValue() >= o.doubleValue();
	}

	@Override
	public <T extends Number2> boolean lessThan(T o) {
		return this.doubleValue() < o.doubleValue();
	}

	@Override
	public <T extends Number2> boolean lessThanOrEqual(T o) {
		return this.doubleValue() <= o.doubleValue();
	}

	@Override
	public <T extends Number2> boolean notEquals(T o) {
		return this.doubleValue() != o.doubleValue();
	}

	@Override
	public boolean isInfinite() {
		return false;
	}

	@Override
	public int compareTo(Rational o) {
		return Double.compare(this.doubleValue(), o.doubleValue());
	}
}
