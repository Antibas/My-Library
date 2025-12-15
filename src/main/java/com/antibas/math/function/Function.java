/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.math.function;

import com.antibas.math.Complex;
import com.antibas.math.Number2;

import java.util.List;
import java.util.Vector;

import static com.antibas.math.function.Indicator.*;

/**
 *
 * @author User
 */
public interface Function extends java.util.function.Function<Double, Double>{
    
    default Function derivative(){
        return derivative(Double.MIN_NORMAL);
    }
    
    default Function derivative(double h){
        return (x) -> (apply(x+h) - apply(x))/h;
    }
    
    default Function derivative(int degree, double h){
        if(degree <= 0) throw new IllegalArgumentException();

        if(degree == 1) return derivative(h);
        return derivative(degree-1, h).derivative(h);
    }
    
    default Function derivative(int degree){
        return derivative(degree, Double.MIN_NORMAL);
    }
    
    default double integral(double a, double b){
        return integral(a, b, Number2.DX_DOUBLE);
    }

    default double innerIntegral(double a, double b, double dx){
        if(a > b){
            double tmp = a;
            a = b;
            b = tmp;
        }
        double S = 0.0;

        for(double i = a; i <= b; i += dx){
            S += apply(i + dx/2.0)*dx;
        }

        return S;
    }

    default double integral(double a, double b, double dx){
        double S = innerIntegral(a, b, dx);
        return (a > b)? -S : S;
    }
    
    default Function integral(double a, double b, double dx, Function f){
        double S = innerIntegral(a, b, dx);
        return x -> 0.0d;
    }
    
    default Function integral(double a, double b, Function f){
        return integral(a, b, Number2.DX_DOUBLE, f);
    }
    
    default Function integral(){
        return (x) -> integral(0, x);
    }
    
    default double limit(double l){
        return limit(l, MINUS);
    }
    
    default double limit(double l, Indicator indicator){
        return switch (indicator) {
            case MINUS -> apply(l - Number2.DX_DOUBLE);
            case PLUS -> apply(l + Number2.DX_DOUBLE);
        };
    }

    default double limit(double l, char indicator){
        return limit(l, Indicator.valueOf(String.valueOf(indicator)));
    }
    
    default List<Complex> solveForZero(){
    	return solveForZero(Number2.R_DOUBLE);
    }
    
    default List<Complex> solveForZero(Domain domain){
        List<Complex> solutions = new Vector<>();
    	
    	double f;
    	for(double x = domain.getStart(); x <= domain.getEnd(); x += domain.getDx()) {
    		f = apply(x);
    		if(f == 0.0d) {
    			solutions.add(new Complex(x));
    		}
    	}
    	
        return solutions;
    }
    
    /*default Series fourierSeries(double f0) {
    	double T0 = 1.0/f0;
    	Function f = t -> Math2.exp(new Complex(0, -2*Math.PI*f0*t)).multiply(f0*apply(t)).getRealPart();
    	return k -> f.integral(0, T0);
    }
    
    default Function fourierTransport() {
    	return f ->
    }*/
}
