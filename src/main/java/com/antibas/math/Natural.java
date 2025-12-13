package com.antibas.math;

public final class Natural extends Number2 {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5636100019717487640L;
	private long value;
	
	public Natural(int value) {
		this((long)value);
	}
	
	public Natural(long value) {
		if(value < 0) throw new IllegalArgumentException();
		this.value = value;
	}
	
	public Natural(Number2 value) {
		this(value.intValue());
	}

	public long getValue() {
		return value;
	}


	public void setValue(int value) {
		if(value < 0) throw new IllegalArgumentException();
		this.value = value;
	}

	@Override
	public Complex complexValue() {
		return new Complex(this.value);
	}

	@Override
	public Natural add(Number2 o) {
		return new Natural(this.value+o.intValue());
	}

	@Override
	public Natural subtract(Number2 o) {
		return new Natural(this.value-o.intValue());
	}

	@Override
	public Natural multiply(Number2 o) {
		return new Natural(this.value*o.intValue());
	}

	@Override
	public Natural divide(Number2 o) throws ArithmeticException {
		return new Natural(this.value/o.intValue());
	}

	@Override
	public Natural invert() {
		return new Natural(1/this.value);
	}

	@Override
	public Natural abs() {
		return new Natural(this);
	}

	@Override
	public boolean greaterThan(Number2 o) {
		return this.value>o.intValue();
	}

	@Override
	public boolean greaterThanOrEqual(Number2 o) {
		return this.value>=o.intValue();
	}

	@Override
	public boolean lessThan(Number2 o) {
		return this.value<o.intValue();
	}

	@Override
	public boolean lessThanOrEqual(Number2 o) {
		return this.value<=o.intValue();
	}

	@Override
	public boolean notEquals(Number2 o) {
		return this.value!=o.intValue();
	}

	@Override
	public boolean isInfinite() {
		return this.value == Integer.MAX_VALUE;
	}

	@Override
	public boolean isFinite() {
		return !isInfinite();
	}

	@Override
	public int intValue() {
		return (int) this.value;
	}

	@Override
	public long longValue() {
		return this.value;
	}

	@Override
	public float floatValue() {
		return this.value;
	}

	@Override
	public double doubleValue() {
		return this.value;
	}

	@Override
	public Natural pow(int power) {
		return new Natural((int) Math.pow(value, power));
	}

}
