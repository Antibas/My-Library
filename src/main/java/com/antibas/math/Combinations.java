package com.antibas.math;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.antibas.math.Math2.RNG;
import static com.antibas.math.Math2.fac;

public final class Combinations {
    public static <T> List<List<T>> product(List<T> col, int n) {
        List<List<T>> result = new ArrayList<>();
        if (n < 0) {
            return result;
        }
        backtrack(col, n, new ArrayList<>(), result);
        return result;
    }

    public static <T> List<List<T>> permutations(List<T> col, int n) {
        List<List<T>> result = new ArrayList<>();
        if (n < 0 || n > col.size()) {
            return result;
        }
        return backtrack(col, n, new ArrayList<>(), new boolean[col.size()], result);
    }

    public static <T> List<List<T>> combinations(List<T> col, int n) {
        List<List<T>> result = new ArrayList<>();
        if (n < 0 || n > col.size()) {
            return result;
        }
        return backtrack(col, n, 0, new ArrayList<>(), result);
    }

    private static <T> List<List<T>> backtrack(
            List<T> col,
            int n,
            int start,
            List<T> current,
            List<List<T>> result) {

        if (current.size() == n) {
            result.add(new ArrayList<>(current));
            return result;
        }

        for (int i = start; i < col.size(); i++) {
            current.add(col.get(i));
            backtrack(col, n, i + 1, current, result);
            current.removeLast();
        }
        return result;
    }

    private static <T> List<List<T>> backtrack(
            List<T> col,
            int n,
            List<T> current,
            boolean[] used,
            List<List<T>> result) {

        if (current.size() == n) {
            result.add(new ArrayList<>(current));
            return result;
        }

        for (int i = 0; i < col.size(); i++) {
            if (used[i]) continue;

            used[i] = true;
            current.add(col.get(i));

            backtrack(col, n, current, used, result);

            current.removeLast();
            used[i] = false;
        }
        return result;
    }

    private static <T> void backtrack(
            List<T> col,
            int n,
            List<T> current,
            List<List<T>> result) {

        if (current.size() == n) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (T element : col) {
            current.add(element);
            backtrack(col, n, current, result);
            current.removeLast();
        }
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
        if(objects.isEmpty()) {
            return null;//throw new IllegalArgumentException();
        }
        return objects.stream().toList().get(RNG(0, objects.size()-1));
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
}
