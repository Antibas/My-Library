/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.math.function.polynomial;

import com.antibas.math.Complex;

/**
 *
 * @author User
 */
public class Quadric extends Polynomial{
    
    public Quadric(double a, double b, double c){
        super(a, b, c);
    }
    
    public double discr(){
        return Math.pow(getMultiple(1), 2) - 4.0 * getMultiple(2) * getMultiple(0);
    }
    
    @Override
    public Complex[] solveForZero(){
    	double D;
        Complex[] s = new Complex[2];
        
        D = discr();
        System.out.println(2.0 * getMultiple(2));
        if(D > 0.0){
            s[0] = new Complex((-getMultiple(1) + Math.sqrt(D)) / (2.0 * getMultiple(2)));
            s[1] = new Complex((-getMultiple(1) - Math.sqrt(D)) / (2.0 * getMultiple(2)));
        } else if(D == 0.0){
            s[0] = s[1] = new Complex(-getMultiple(1) / (2.0 * getMultiple(2)));
        } else {
        	s[0] = new Complex(-getMultiple(1) / 2.0 * getMultiple(2), Math.sqrt(-D) / 2.0 * getMultiple(2));
        	s[1] = new Complex(-getMultiple(1) / 2.0 * getMultiple(2), -Math.sqrt(-D) / 2.0 * getMultiple(2));
        }
        
        return s;
    }

}
