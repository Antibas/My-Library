package com.antibas.math;


import lombok.Getter;

@Getter
public class Rational extends Number2 {

	/**
	 * 
	 */
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
	public Rational add(Number2 o) {
		if(o instanceof Rational) {
			Rational oRational = (Rational)o;
			if(this.denominator == oRational.denominator)
				return new Rational(numerator+oRational.numerator, denominator);
			
			return new Rational((int) (numerator*oRational.denominator+oRational.numerator*denominator), denominator*oRational.denominator);
		}
		return new Rational((int) (numerator+denominator*o.doubleValue()), denominator);
	}

	@Override
	public Rational subtract(Number2 o) {
		if(o instanceof Rational) {
			Rational oRational = (Rational)o;
			if(this.denominator == oRational.denominator)
				return new Rational(numerator-oRational.numerator, denominator);
			
			return new Rational((int) (numerator*oRational.denominator-oRational.numerator*denominator), denominator*oRational.denominator);
		}
		return new Rational((int) (numerator-denominator*o.doubleValue()), denominator);
	}

	@Override
	public Rational multiply(Number2 o) {
		if(o instanceof Rational) {
			Rational oRational = (Rational)o;
			return new Rational(numerator*oRational.numerator, denominator*oRational.denominator);
		}
		return new Rational((int) (numerator*o.doubleValue()), this.denominator);
	}

	@Override
	public Rational divide(Number2 o) throws ArithmeticException {
		if(o instanceof Rational) {
			Rational oRational = (Rational)o;
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
	public boolean greaterThan(Number2 o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean greaterThanOrEqual(Number2 o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean lessThan(Number2 o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean lessThanOrEqual(Number2 o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean notEquals(Number2 o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInfinite() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFinite() {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return null;
	}

}
