package com.antibas.math;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.lang.constant.Constable;
import java.lang.constant.ConstantDesc;
import java.lang.invoke.MethodHandles;
import java.util.Comparator;
import java.util.Optional;

@Setter
@Getter
public final class Complex extends Number2 implements Comparable<Complex>, Serializable{
	public static final Complex MAX_VALUE = new Complex(Double.MAX_VALUE, Double.MAX_VALUE);
	public static final Complex MIN_VALUE = new Complex(Double.MIN_VALUE, Double.MIN_VALUE);
	public static final Complex DX_COMPLEX = Complex.MIN_VALUE;
	public static final Complex POSITIVE_INFINITY = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
	public static final Complex NEGATIVE_INFINITY = new Complex(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
	public static final Complex NaN = new Complex(Double.NaN, Double.NaN);
	public static final Complex i = new Complex(0, 1);
	public static final int SIZE = 2*Double.SIZE; 
	public static final int BYTES = 2*Double.BYTES;
	
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -99721705343218094L;
	
	private double real, imaginary;

	public Complex(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}

    public Complex(double real) {
		this(real, 0.0d);
	}
	
	public Complex() {
		this(0.0d, 0.0d);
	}
	
	public Complex(Complex c) {
		this(c.real, c.imaginary);
	}
	
	public Complex(String value) {
		this(Complex.parseComplex(value));	
	}

	public double getAmplitude() {
		return Math.sqrt(Math.pow(this.real, 2) + Math.pow(this.imaginary, 2));
	}

	public double getAngle() {
		return Math.atan(this.imaginary / this.real);
	}

	public Complex add(double d) {
		return new Complex(real + d, imaginary);
	}
	
	public Complex add(Complex c) {
		return new Complex(real + c.real, imaginary + c.imaginary);
	}
	
	@Override
	public Complex add(Number2 o) {
		if(o.complexValue() == null) {
			return this.add(o.doubleValue());
		}
		return this.add(o.complexValue());
	}

	public Complex subtract(double d) {
		return new Complex(real - d, imaginary);
	}
	
	public Complex subtract(Complex c) {
		return new Complex(real - c.real, imaginary - c.imaginary);
	}
	
	@Override
	public Complex subtract(Number2 o) {
		if(o.complexValue() == null) {
			return this.subtract(o.doubleValue());
		}
		return this.subtract(o.complexValue());
	}

	public Complex multiply(double d) {
		return new Complex(real * d, imaginary * d);
	}
	
	public Complex multiply(Complex com) {
		double a = real, b = imaginary, c = com.real, d = com.imaginary;
		return new Complex(a*c - b*d, a*d + b*c);
	}
	
	@Override
	public Complex multiply(Number2 o) {
		if(o.complexValue() == null) {
			return this.multiply(o.doubleValue());
		}
		return this.multiply(o.complexValue());
	}

	public Complex divide(double d) {
		if(d == 0) throw new ArithmeticException("Division by zero.");
		return new Complex(real / d, imaginary / d);
	}
	
	public Complex divide(Complex com) {
		double a = real, b = imaginary, c = com.real, d = com.imaginary;
		double e = Math.pow(c, 2) - Math.pow(d, 2);
		Complex ret = new Complex(a*c + b*d, b*c - a*d);
		if(e == 0.0) {
			return ret.multiply(Double.POSITIVE_INFINITY);
		}
		return ret.divide(e);
	}
	
	@Override
	public Complex divide(Number2 o) throws ArithmeticException {
		if(o.complexValue() == null) {
			return this.divide(o.doubleValue());
		}
		return this.divide(o.complexValue());
	}

	@Override
	public Complex invert() {
		double a = real, b = imaginary;
		return (new Complex(a, -b)).divide(Math.pow(a, 2) - Math.pow(b, 2));
	}
	
	public Complex conjugate() {
		return new Complex(real, -imaginary);
	}
	
	public Complex pow(int p) {
		if(p < 0) return invert().pow(Math.abs(p));
		if(p == 0) return new Complex(1.0d);
		if(p == 1) return this;
		return this.multiply(this.pow(p-1));
	}
	
	@Override
	public String toString() {
		return real + (imaginary >= 0.0d?"+":"-") + Math.abs(imaginary) + "j";
	}
	
	public String toPolarString() {
		return this.getAmplitude() + " * exp(j" + this.getAngle() + ")";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
        result = prime * result + Double.hashCode(imaginary);
        result = prime * result + Double.hashCode(real);
		return result;
	}

	@Override
	public int compareTo(Complex o) {
		int c = Double.compare((real), o.real) + Double.compare((imaginary), o.imaginary);
		if(c == 2) return 1;
		if(c == -2) return -1;
		return c;
	}

	@Override
	public int intValue() {
		return (int)this.real;
	}

	@Override
	public long longValue() {
		return (long)this.real;
	}

	@Override
	public float floatValue() {
		return (float)this.real;
	}

	@Override
	public double doubleValue() {
		return this.real;
	}

	@Override
	public Complex complexValue() {
		return new Complex(this);
	}

	@Override
	public boolean greaterThan(Number2 o) {
		return this.real > o.complexValue().real && this.imaginary > o.complexValue().imaginary;
	}

	@Override
	public boolean greaterThanOrEqual(Number2 o) {
		return this.real >= o.complexValue().real && this.imaginary >= o.complexValue().imaginary;
	}

	@Override
	public boolean lessThan(Number2 o) {
		return this.real < o.complexValue().real && this.imaginary < o.complexValue().imaginary;
	}

	@Override
	public boolean lessThanOrEqual(Number2 o) {
		return this.real <= o.complexValue().real && this.imaginary <= o.complexValue().imaginary;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Complex c) {
            return this.real == c.real && this.imaginary == c.imaginary;
		}

		if(obj instanceof Double d) {
            return this.real == d && this.imaginary == 0.0d;
		}
		
		if(obj instanceof Integer d) {
			return this.real == d && this.imaginary == 0.0d;
		}
		
		return false;
	}
	
	@Override
	public boolean notEquals(Number2 o) {
		return !this.equals(o);
	}

	@Override
	public Complex abs() {
		return new Complex(this.getAmplitude());
	}
	
	public static Complex parseComplex(String value) {
		int i_ind;
		if((i_ind = value.indexOf('-', 1)) == -1) {
			if((i_ind = value.indexOf('+', 1)) == -1) {
				if(!value.endsWith("j") && !value.endsWith("i")) {
					return new Complex(Double.parseDouble(value));
				}
				
				if(value.equals("j") || value.equals("i")) {
					return new Complex(0, 1);
				}
				
				if(value.equals("-j") || value.equals("-i")) {
					return new Complex(0, -1);
				}
				
				return new Complex(0.0, Double.parseDouble(value.substring(0, value.length()-1)));
			}
		}
		
		String r_str = value.substring(0, i_ind);
		String i_str = value.substring(i_ind);

		boolean r_str_check = r_str.endsWith("j") || r_str.endsWith("i"),
				i_str_check = i_str.endsWith("j") || i_str.endsWith("i");

		if(r_str_check && !i_str_check) {
			return parseComplex(r_str, i_str);
		}
		
		if(!r_str_check && i_str_check) {
			return parseComplex(i_str, r_str);
		}
		
		throw new NumberFormatException();
	}

	private static Complex parseComplex(String r_str, String i_str){
		if(r_str.equals("j") || r_str.equals("i")) {
			return new Complex(Double.parseDouble(i_str), 1);
		}

		if(r_str.equals("-j") || r_str.equals("-i")) {
			return new Complex(Double.parseDouble(i_str), -1);
		}

		return new Complex(Double.parseDouble(i_str), Double.parseDouble(r_str.substring(0, r_str.length()-1)));
	}

	@Override
	public boolean isInfinite() {
		return this.real == Double.POSITIVE_INFINITY || this.real == Double.NEGATIVE_INFINITY ||
			   this.imaginary == Double.POSITIVE_INFINITY || this.imaginary == Double.NEGATIVE_INFINITY;
	}

	@Override
	public boolean isPositive() {
		return this.real > 0 && this.imaginary > 0;
	}
}
