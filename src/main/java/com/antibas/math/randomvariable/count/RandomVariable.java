/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.math.randomvariable.count;

import com.antibas.math.function.Function;

import java.util.Map;

/**
 *
 * @author User
 */
public class RandomVariable extends com.antibas.math.randomvariable.RandomVariable<Double>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1764431765603471418L;
	
	private Function function;
	
	public RandomVariable(Function function) {
		super();
		this.function = function;
	}

	public RandomVariable(Map<? extends Double, ? extends Double> m) {
		super(m);
		this.function = (Function) m;
	}
	
	public double E() {
		return E(1);
	}
	
	public double E(int p) {
		double s = 0.0d;
		for(Double x: super.keySet()) {
			s += super.get(x)*Math.pow(x, p);
		}
		return s;
	}
	
	public Function M() {
		return (s) -> (new Function() {
			@Override
			public Double apply(Double t) {
				return Math.exp(s*t)*function.apply(t);
			}
		}).integral(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
	}
    
	public double var() {
		return E(2) - Math.pow(E(), 2);
	}
}
