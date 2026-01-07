package com.antibas.math;

import lombok.Getter;

import java.io.Serial;

@Getter
public class Whole extends Number2 implements Comparable<Whole> {

	/**
	 *
	 */
	@Serial
	private static final long serialVersionUID = 5636100019717487640L;
	private long value;

	public Whole(int value) {
		this((long)value);
	}

	public Whole(long value) {
		this.value = value;
	}

	public Whole(Number2 value) {
		this(value.intValue());
	}


    public void setValue(int value) {
		this.value = value;
	}

	@Override
	public Complex complexValue() {
		return new Complex(this.value);
	}

	@Override
	public <T extends Number2> Whole add(T o) {
		return new Whole(this.value+o.intValue());
	}

	@Override
	public <T extends Number2> Number subtract(T o) {
		return new Whole(this.value-o.intValue());
	}

	@Override
	public <T extends Number2> Number multiply(T o) {
		return new Whole(this.value*o.intValue());
	}

	@Override
	public <T extends Number2> Number divide(T o) throws ArithmeticException {
		if (o.intValue() == 0) throw new ArithmeticException();
		return new Whole(this.value/o.intValue());
	}

	@Override
	public Whole invert() {
		return new Whole(1/this.value);
	}

	@Override
	public Whole abs() {
		return new Whole(this);
	}

	@Override
	public <T extends Number2>  boolean greaterThan(T o) {
		return this.value>o.intValue();
	}

	@Override
	public <T extends Number2>  boolean greaterThanOrEqual(T o) {
		return this.value>=o.intValue();
	}

	@Override
	public <T extends Number2> boolean lessThan(T o) {
		return this.value<o.intValue();
	}

	@Override
	public <T extends Number2>  boolean lessThanOrEqual(T o) {
		return this.value<=o.intValue();
	}

	@Override
	public <T extends Number2> boolean notEquals(T o) {
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
	public boolean isPositive() {
		return value > 0;
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
	public Whole pow(int power) {
		return new Whole((int) Math.pow(value, power));
	}

	@Override
	public int compareTo(Whole o) {
		return Long.compare(this.value, o.value);
	}
}
