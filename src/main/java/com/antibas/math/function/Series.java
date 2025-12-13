/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.math.function;


/**
 *
 * @author User
 */
public interface Series extends java.util.function.Function<Integer, Double>{
    public default double sum(int a, int b){
        if(a >= b) throw new IllegalArgumentException();
        double S = 0d;
        for(int n = a; n <= b; n++){
            S += apply(n);
        }
        return S;
    }
    
    public default double limit(int l){
        return apply(l-1);
    }
    
}
