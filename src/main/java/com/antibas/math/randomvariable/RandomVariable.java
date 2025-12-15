/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.math.randomvariable;

import com.antibas.util.pair.Pair;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author User
 * @param <T>
 */
public class RandomVariable<T> extends HashMap<T, Double>{
    /**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -1579801726158896925L;
	
	public static final double MAX_POSSIBILITY = 1.0d;
    
    public RandomVariable() {
		super();
	}

	public RandomVariable(Map<? extends T, ? extends Double> m) {
		super(m);
	}
	
	public RandomVariable(Pair<? extends T, ? extends Double>... pairs) {
		super();
		for(Pair<? extends T, ? extends Double> pair: pairs) {
			this.put(pair.getFirst(), pair.getSecond());
		}
	}

    protected double totalOdds(){
        double t = 0.0;
        for(double d: super.values()){
            t += d;
        }
        return t;
    }
    
	@Override
	public void putAll(Map<? extends T, ? extends Double> m) {
		super.putAll(m);
		if(totalOdds() > MAX_POSSIBILITY) throw new RuntimeException();
	}

	@Override
	public Double putIfAbsent(T key, Double value) {
		if(totalOdds() == MAX_POSSIBILITY) throw new RuntimeException();
        double s = super.putIfAbsent(key, value);
        if(totalOdds() > MAX_POSSIBILITY) return super.remove(key);
        return s;
	}

	@Override
    public Double put(T key, Double value){
        if(totalOdds() == MAX_POSSIBILITY) throw new RuntimeException();
        Double s = super.put(key, value);
        if(totalOdds() > MAX_POSSIBILITY) return super.remove(key);
        return s;
    }
	
    public T getRand(){
        double select = Math.random(), prev = 0.0, next;
        for(T ks: super.keySet()){
            next = super.get(ks);
            if(select <= next || select >= prev) return ks;
            prev = super.get(ks);
        }
        throw new Error();
    }

	@Override
	public Double get(Object key) {
		// TODO Auto-generated method stub
		return super.get(key);
	}

	@Override
	public Double getOrDefault(Object key, Double defaultValue) {
		// TODO Auto-generated method stub
		return super.getOrDefault(key, defaultValue);
	}
}
