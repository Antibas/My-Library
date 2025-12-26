package com.antibas.math;

import com.antibas.math.function.Domain;

import java.io.Serial;
import java.io.Serializable;

public abstract class Number2 extends Number{

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 3519438330121793034L;
	public static final double DX_DOUBLE = Double.MIN_NORMAL;
	public static final double DX_INT = 1;
    public static final Domain R_DOUBLE = new Domain();
    public static final Domain R_PLUS_DOUBLE = new Domain(0.0, Double.POSITIVE_INFINITY);
    public static final Domain R_MINUS_DOUBLE = new Domain(0.0, true);
    public static final Domain R_INT = new Domain(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 1);
    public static final Domain R_PLUS_INT = new Domain(0.0, Double.POSITIVE_INFINITY, 1);
    public static final Domain R_MINUS_INT = new Domain(Double.NEGATIVE_INFINITY, 0.0, 1);
	
	public abstract Complex complexValue();
	
	public abstract Number add(Number2 o);
	public abstract Number subtract(Number2 o);
	public abstract Number multiply(Number2 o);
	public abstract Number divide(Number2 o) throws ArithmeticException;
	public abstract Number invert();
	public abstract Number abs();
	public abstract Number pow(int power);
	
	public abstract boolean greaterThan(Number2 o);
	public abstract boolean greaterThanOrEqual(Number2 o);
	public abstract boolean lessThan(Number2 o);
	public abstract boolean lessThanOrEqual(Number2 o);
	public abstract boolean notEquals(Number2 o);
	
	public abstract boolean isInfinite();
	public boolean isFinite() {
		return !this.isInfinite();
	}

	public abstract boolean isPositive();
	public boolean isNegative(){
		return !isPositive();
	}
}
