/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.math.function.polynomial;

import com.antibas.math.function.FunctionClass;
import com.antibas.util.ArrayUtils;
import lombok.Getter;

import java.util.Arrays;
import java.util.Vector;

/**
 *
 * @author User
 */
public class Polynomial extends FunctionClass{
    protected double[] multiples;
    @Getter
    protected int power;
    
    public Polynomial(double... m){
    	super();
    	if(m.length == 0) {
    		power = 0;
    		multiples = null;
    	} else if(m.length != 1 && m[0] == 0.0) {
    		throw new IllegalArgumentException();
    	} else {
    		power = m.length-1;
    		multiples = m;
    	}
        
    }
    
    public Polynomial(Vector<Double> m){
    	super();
        if(m.size() != 1 && m.elementAt(0) == 0.0) throw new IllegalArgumentException();
        power = m.size()-1;
        multiples = ArrayUtils.toPrimitive((Double[]) m.toArray());
    }
    
    public Polynomial(Polynomial p) {
    	super();
		this.multiples = p.multiples;
		this.power = p.power;
	}
    
	public void setPower(int power) {
		int oldPower = this.power;
		this.power = power;
		if(this.power > oldPower) {
			this.multiples = ArrayUtils.resizeArray(multiples, power+1);//System.arraycopy(ArrayUtils.zeros(n), 0, multiples, destPos, length);
		}
		else {
			this.multiples = Arrays.copyOf(multiples, this.power+1);
		}
	}

    public void setMultiples(double... m) {
		this.multiples = m;
		this.power = m.length - 1;
	}
    
    public void setMultiple(int p, double m){
        if(p == power && m == 0) throw new IllegalArgumentException();
        multiples[power-p] = m;
        //multiples.set(multiples.size()-p-1, m);
    }

    public final double getMultiple(int p){
        return multiples[power-p];
    }
    
    public void addMultiple(int p, double m){
        setMultiple(p, m + getMultiple(p));
    }
    
    public Polynomial[] horner(double x) {
    	double y = this.getMultiple(power);
    	Vector<Double> pl = new Vector<>(power-1);
    	Polynomial[] ret = new Polynomial[3];
    	
    	pl.add(y);
    	
    	for(int p = power-1; p >= 0; p--) {
    		y *= x;
    		y += this.getMultiple(p);
    		if(p != 0) {
    			pl.add(y);
    		}
    	}
    	//Double[] rev = ArrayUtils.<Double>reverseArray((Double[])pl.toArray(new Double[pl.size()]), Double.class);
    	Double[] rev = (Double[])pl.toArray(new Double[pl.size()]);

    	//Polynomial pl1 = new Polynomial(ArrayUtils.toPrimitive(rev));
    	ret[0] = new Polynomial(ArrayUtils.toPrimitive(rev));
    	ret[1] = new Polynomial(1, -x);
    	
    	if(y != 0.0) {
    		ret[2] = new Polynomial(y);
    	} 
    	else {
    		ret[2] = null;
    	}
    	return ret;
    }
    
    @Override
    public Double apply(Double x){
        double S = 0.0d;
        for(int i = 0; i <= power; i++) S += (double)getMultiple(i)*Math.pow(x, i);
        return S;
    }
    
    /*public Matrix asMatrix(){
        Matrix m = new Matrix(1, power+1);
        for(int p = 0; p <= power; p++){
            m.set(0, m.columns()-p-1, getMultiple(p));
        }
        return m;
    }
    
    @Override
    public String toString(){
    	return toString("x");
    }*/
    
    @Override
    public String toString(String var) {
    	StringBuilder ret = new StringBuilder();
        double m;
        for(int p = power; p >= 0; p--){
            m = getMultiple(p);
            if(m == 0) continue;
            if(m > 0 && p != power) ret.append("+");
            
            if(p != 0){
                if(m == 1.0) {
                    if(p != 1) ret.append(var).append("^").append(p);
                    else ret.append(var);
                } else if(m == -1.0) {
                    if(p != 1) ret.append("-").append(var).append("^").append(p);
                    else ret.append("-").append(var);
                } else {
                    if(p != 1) ret.append(m).append(var).append("^").append(p);
                    else ret.append(m).append(var);
                }
            } else {
                ret.append(m);
            }
        }
        return ret.toString();
    }
    
    @Override
    public Polynomial add(FunctionClass f) {
    	Polynomial p = (Polynomial)f;
    	Polynomial pmax = new Polynomial(power >= p.power? this: p);
    	Polynomial pmin = new Polynomial(power < p.power? this: p);
    	for(int i = 0; i <= pmin.power; i++) {
    		pmax.setMultiple(i, pmax.getMultiple(i)+pmin.getMultiple(i)); 
    	}
    	return pmax;
    }
    
    public static Polynomial sub(Polynomial p1, Polynomial p2) {
    	Polynomial pmax = new Polynomial(p1.power >= p2.power? p1: p2);
    	Polynomial pmin = new Polynomial(p1.power < p2.power? p1: p2);
    	for(int i = 0; i <= pmin.power; i++) {
    		pmax.setMultiple(i, pmax.getMultiple(i)-pmin.getMultiple(i)); 
    	}
    	return pmax;
    }
    
    public static Polynomial mul(Polynomial p1, Polynomial p2) {
    	Polynomial pmax = new Polynomial(p1.power >= p2.power? p1: p2);
    	Polynomial pmin = new Polynomial(p1.power < p2.power? p1: p2);
    	/*for(int i = 0; i <= pmin.power; i++) {
    		for(int j = 0; j <= pmax.power; j++) {
    			
    		}
    	}*/
    	return pmax;
    }
}