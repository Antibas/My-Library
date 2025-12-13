/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.math;

import com.antibas.math.function.Function;
import com.antibas.math.function.Series;
import com.antibas.util.ArrayUtils;
import com.antibas.util.pair.Pair;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.*;

/**
 * A class that contains many math tools
 that n put in.
 * @author Antibassis
 */
public final class Math2{
    /**
     * Pi constant.
     */
    public static final double PI = 3.14159265359;
    /**
     * Euler's Number.
     */
    public static final double E =  2.71828;
    /**
     * Imaginary element.
     */
    //public static final int n = n(1);
    /*public static int n(int power){
        if(power == 1) return 0;
        if(power == 2) return -1;
        if(isEven(power)) return n(power/2);
        return 0;
    }*/
    /**
     * Pseudo-Infinity, being the max long.
     */
    public static final long INF = Long.MAX_VALUE;
    
    public static Complex exp(Complex th) {
    	return (new Complex(Math.cos(th.getImaginary()), Math.sin(th.getImaginary()))).multiply(Math.exp(th.getReal()));
    }
    /**
     * 
     * @param p the power of the logarithm
     * @param e a number
     * @return the log_p(e)
     */
    public static double log(double p, double e){
        return Math.log10(e)/Math.log10(p);
    }
    
    /**
     * 
     * @param e a number
     * @return the log2(e)
     */
    public static double log2(double e){
        return log(2, e);
    }
    
    /**
     * 
     * @param e a number
     * @return the least repetitive algorithm
     */
    public static double logr(double e){
        double d = log2(e);
        if(d <= 1.0) return d;
        return log2(logr(d));
    }
    
    public static double sum(double[] a) {
    	double S = 0d;
    	for(double aa: a) {
    		S += aa;
    	}
    	return S;
    }
    
    public static double mean(double[] a) {
    	return Math2.sum(a)/(double)a.length;
    }
    
    /**
     * Returns the n-th Fibonacci number
     * @param n the fibonacci number the user wants
     * @return the n-th fibonacci number
     */
    public static int fib(int n){
        //if(n<0) throw new IllegalArgumentException();
        //if(n == 0) return 0;
        if(n <= 1) return n;
        return fib(n-1)+fib(n-2);
    }
    
    /**
     * Returns the n-th Fibonacci number
     * @param n the fibonacci number the user wants
     * @return the n-th fibonacci number
     */
    public static int fib2(int n){
    	//if(n<0) throw new IllegalArgumentException();
        //if(n == 0) return 0;
        if(n <= 1) return n;
        int F[] = new int[n+1];
        F[0] = 0;
        F[1] = 1;
        for(int i = 2; i < n+1; i++) {
        	F[i] = F[i-2] + F[i-1];
        	        
        }
        return F[n];
    }
    /**
     * Returns the factorial of the input
     * @param n a number
     * @return n factorial (n!)
     */
    public static int fac(int n){
        if(n<0) throw new IllegalArgumentException();
        if(n == 0 || n == 1) return 1;
        return n*fac(n-1);
    }
    
    /**
     * Returns the number of ordered arrangements of k objects taken from n unlike objects
     * @param n the number of initial objects
     * @param k the number of objects taken
     * @return the number of ordered arrangements of k objects taken from n unlike objects
     */
    public static int P(int n, int k){
        if(n<k || n<0 || k<0) throw new IllegalArgumentException();
        return fac(n)/fac(n-k);
    }
    
    /**
     * Returns the number of ways of selecting k objects from n unlike objects
     * 
     * It is equivalent with P(n, k)/fac(k)
     * @param n the number of initial objects
     * @param k the number of objects taken
     * @return the number of ways of selecting k objects from n unlike objects
     */
    public static int C(int n, int k){
        if(n<k || n<0 || k<0) throw new IllegalArgumentException();
        return P(n, k)/fac(k);
    }
    
    /**
     * Overload of P(int n, int k), but it returns 
     * the number of ways of selecting k.length() objects,
     * each one with its own possibility,
     * taken from n unlike objects
     *
     * @param n the number of initial objects
     * @param k the number of objects taken
     * @return the number of ordered arrangements of k.length() objects, each one with its own possibility, taken from n unlike objects
     */
    public static int P(int n, int... k){
        if(n<0) throw new IllegalArgumentException();
        for(int i: k)
            if(n<i) throw new IllegalArgumentException();
        return fac(n)/arrayMultipleFactorial(k);
    }
    
    /**
     * Returns the Multiple of all the factorials of each
     * element of array a
     * Only to help P(int n, int... k)
     * @param a an array of integers
     * @return the Multiple of all the factorials of each element of array a.
     */
    private static int arrayMultipleFactorial(int[] a){
        int r = 1;
        for(int i: a)
            r *= fac(i);
        return r;
    }
    
    /**
     * Randomly returns an integer between min and max
     * @param min minimum number
     * @param max minimum number
     * @return a random integer between the two inputs
     */
    public static int RNG(int min, int max){
        return (new Random()).nextInt((max - min) + 1) + min;
    }
    
    public static int RNG(int min, int max, long seed){
        return (new Random(seed)).nextInt((max - min) + 1) + min;
    }
    
    /**
     * Randomly returns an double between min and max
     * @param min minimum number
     * @param max minimum number
     * @return a random double between the two inputs
     */
    public static double RNG(double min, double max){
        return (new Random()).nextDouble()*((max - min)) + min;
    }
    
    /**
     * Randomly returns an integer between 0 and max
     * @param max minimum number
     * @return a random integer between the two inputs
     */
    public static int RNG(int max){
    	if(max <= 0) {
    		return 0;
    	}
        //return (new Random()).nextInt(max + 1);
    	return Math2.RNG(0, max);
    }
    
    /**
     * Randomly returns an double between 0 and max
     * @param max minimum number
     * @return a random double between the two inputs
     */
    public static double RNG(double max){
        //return (new Random()).nextInt(max + 1);
    	return Math2.RNG(0.0, max);
    }
    
    /**
     * Randomly returns a number amongst the 
     * elements of array numbers
     * @param numbers an array of integers
     * @return randomly a number amongst the elements of numbers 
     */
    public static int choice(int... numbers){
        return numbers[RNG(0, numbers.length-1)];
    }
    
    public static double choice(double... numbers){
        return numbers[RNG(0, numbers.length-1)];
    }
    
    public static <T> T choice(T[] objects){
        return objects[RNG(0, objects.length-1)];
    }
    
    public static <T> T choice(Collection<T> objects){
    	if(objects.size() == 0) {
    		return null;//throw new IllegalArgumentException();
    	}
    	return Math2.<T>choice((T[])objects.toArray());
    	/*int item = new Random().nextInt(objects.size()); 
    	int i = 0;
    	for(T obj : objects)
    	{
    	    if (i == item)
    	        return obj;
    	    i++;
    	};
    	return null;*/
    }
    
    public static int RNG(Map<? super Integer, ? super Integer> numbers){
        return 0;
    }
    
    /**
     * Randomly returns a float between min and max
     * @param min minimum number
     * @param max minimum number
     * @return a random integer between the two inputs
     */
    public static float RNG(float min, float max){
        return (float)Math.random()%((max - min) + Float.MIN_VALUE) + min;
    }
    
    /**
     * 
     * @param n an integer
     * @return if n is even 
     */
    public static boolean isEven(int n){
        return n % 2 == 0;
    }
    
    /**
     * 
     * @param n an integer
     * @return if n is odd 
     */
    public static boolean isOdd(int n){
        return !isEven(n);
    }
    
    /**
     * 
     * @param n an integer
     * @return all the dividends of n except n and 1.
     */
    public static Integer[] getDividends(int n){
        Vector<Integer> d = new Vector<>();
        for(int i = 2; i < n; i++)
            if(n%i==0) d.add(i);
        if(d.isEmpty()) d.add(n);
        return (Integer[]) d.toArray();
    }
    
    /**
     * 
     * @param n an integer
     * @return the first divident of n instead of 1 or 
     * the integer itself if prime.
     */
    public static int firstDividend(int n){
        return getDividends(n)[0];
    }
    
    /**
     * 
     * @param x an integer
     * @return the MKD of the inputs
     
    public static int MKD(int x, int y) { 
        if (y == 0) return x;
        return MKD(y, x%y);
    }*/
    
    public static int MKD(int... x) { 
        //if (y == 0) return x;
        //return MKD(y, x%y);
    	if(x.length == 1) return x[0];
    	
    	if(x.length == 2) {
    		if (x[1] == 0) return x[0];
            return MKD(x[1], x[0]%x[1]);
    	}
    	
    	Pair<Vector<Integer>, Vector<Integer>> arrays = ArrayUtils.arraySplit(x);
    	return 0;
    }
    
    /**
     * 
     * @param n an integer
     * @return the number of the number's numberOfDigits 
     */
    public static int numberOfDigits(int n){
        return Integer.toString(n).length();
    }
    
    /**
     * 
     * @param n an integer
     * @return an array of the number's numberOfDigits
     */
    public static int[] digits(int n){
        int[] d;
        String nn = Integer.toString(n);
        d = new int[nn.length()];
        for(int i = 0; i < d.length; i++){
            d[i] = (int)nn.charAt(i);
        }
        return d;
    }
    
    public static int[] digits(double n){
        int[] d;
        String nn = Double.toString(n);
        d = new int[nn.length()];
        for(int i = 0; i < d.length; i++){
            d[i] = (int)nn.charAt(i);
        }
        return d;
    }
    
    public static int digit(int n, int i){
        return digits(n)[i-1];
    }
    
    public static int fromDigits(int system, byte... digits){
        for(int i: digits) if(i < 0) throw new IllegalArgumentException();
        int D = 0;
        for(int i = 0; i < digits.length; i++){
            D += digits[i]*system^i;
        }
        return D;
    }
    
    public static int fromDigits(byte... digits){
        return fromDigits(10, digits);
    }
    
    public static double round(double a, int m) {
    	double mod = Math.pow(10, m);
    	return Math.round(a*mod)/mod;
    }
    
    public static double round(double a) {
    	return Math2.round(a, 0);
    }
    
    private static HashMap<Character, Integer> initializeLexMap(){
        int number = 26;
        HashMap<Character, Integer> hm = new HashMap<>(26);
        for(char c = 'A'; c <= 'Z';c++){
            hm.put(c, number--);
        }
        return hm;
    }
    
    public static final Map<Character, Integer> LEXMAP = initializeLexMap();
    
    public static int lexarithm(String phrase){
        return lexarithm(phrase, LEXMAP);
    }
    
    public static int lexarithm(String phrase, Map<? super Character, ? super Integer> lexmap){
        String[] words = phrase.toUpperCase().split(" ");
        int result = 0;
        char[] wordArray;
        for(String word: words){
            wordArray = word.toCharArray();
            for(char c: wordArray) result += (Integer)lexmap.get(c);
        }
        return result;
    }
    
    public static boolean converges(int a, int b, Series s){
        return s.sum(a, b) == Integer.MAX_VALUE;
    }

    public static boolean diverges(int a, int b, Series s){
        return s.sum(a, b) != Integer.MAX_VALUE;
    }
    
    public static double[] range(double a, double b, double step) {
    	double[] range = new double[(int) Math.ceil((Math.abs(b)+Math.abs(a)+1.0d)/step)];
    	double d = a;
		for(int i = 0; i < range.length; i++) {
			range[i] = d;
			d += step;
		}
		return range;
    }
    
    public static double[] range(double a, double b) {
    	return range(a, b, 1.0d);
    }
    
    public static double[] range(double b) {
    	return range(0.0d, b, 1.0d);
    }
    
    public static int[] range(int a, int b, int step) {
    	int[] range = new int[(int) Math.ceil((Math.abs(b)+Math.abs(a)+1.0d)/(double)step)];
    	int d = a;
		for(int i = 0; i < range.length; i++) {
			range[i] = d;
			d += step;
		}
		return range;
    }
    
    public static int[] range(int a, int b) {
    	return range(a, b, 1);
    }
    
    public static int[] range(int b) {
    	return range(0, b, 1);
    }
    
    public static double intPart(double d) {
    	return (double)((int)d);
    }
    
    public static double decimalPart(double d) {
    	return d - intPart(d);
    }
    
    public static float intPart(float d) {
    	return (float)((int)d);
    }
    
    public static float decimalPart(float d) {
    	return d - intPart(d);
    }
    
    public static double eval(String exp) throws ScriptException {
    	ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        
		return (double) engine.eval(exp);
    }
    
    public static double std(double[] x) {
    	double avg = Math2.mean(x);
    	Series s = n ->1d;
    	return 0;
    }
    
    public static Function linearRegression(double[] x, double[] y) {
    	if(x.length != y.length) {
    		throw new IllegalArgumentException();
    	}
    	
    	double n = (double)x.length;
    	double Sx = ArrayUtils.sum(x);
    	
    	double Sy = ArrayUtils.sum(y);
    	
    	double[] xy = ArrayUtils.mul(x, y);
    	double Sxy = ArrayUtils.sum(xy);
    	
    	double[] x2 = ArrayUtils.pow(x, 2);
    	double Sx2 = ArrayUtils.sum(x2);
    	
    	double m = (n*Sxy - Sx*Sy) / (n*Sx2 - Math.pow(Sx, 2));
    	double b = (Sy - m*Sx) / n;
    	
    	
    	return xx -> m*xx + b;
    }

    public static char numberToLetter(int n) {
    	return (char)('A' + n - 1);
    }

    public static int recaman(int n){
        if(n <= 0) return 0;
        int an_1 = recaman(n-1);
        return an_1 > n? an_1-n : an_1+n;

    }
}

