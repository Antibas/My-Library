package com.antibas.util;

import com.antibas.math.Complex;
import com.antibas.util.pair.Pair;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public final class ArrayUtils {
	public static double[] toPrimitive(Double[] arr) {
		double[] ret = new double[arr.length];
		
		for(int i = 0; i < arr.length; i++) {
			ret[i] = arr[i];
		}
		
		return ret;
	}
	
	public static int[] toPrimitive(Integer[] arr) {
		int[] ret = new int[arr.length];
		
		for(int i = 0; i < arr.length; i++) {
			ret[i] = arr[i];
		}
		
		return ret;
	}
	
	public static float[] toPrimitive(Float[] arr) {
		float[] ret = new float[arr.length];
		
		for(int i = 0; i < arr.length; i++) {
			ret[i] = arr[i];
		}
		
		return ret;
	}
	
	public static byte[] toPrimitive(Byte[] arr) {
		byte[] ret = new byte[arr.length];
		
		for(int i = 0; i < arr.length; i++) {
			ret[i] = arr[i];
		}
		
		return ret;
	}
	
	public static short[] toPrimitive(Short[] arr) {
		short[] ret = new short[arr.length];
		
		for(int i = 0; i < arr.length; i++) {
			ret[i] = arr[i];
		}
		
		return ret;
	}
	
	public static long[] toPrimitive(Long[] arr) {
		long[] ret = new long[arr.length];
		
		for(int i = 0; i < arr.length; i++) {
			ret[i] = arr[i];
		}
		
		return ret;
	}
	
	
	
	public static Double[] toWrapper(double[] arr) {
		Double[] ret = new Double[arr.length];
		
		for(int i = 0; i < arr.length; i++) {
			ret[i] = arr[i];
		}
		
		return ret;
	}
	
	public static Double[][] toWrapper(double[][] arr) {
		Double[][] ret = new Double[arr.length][arr[0].length];
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				ret[i][j] = arr[i][j];
			}
		}
		
		return ret;
	}
	
	public static double[] resizeArray(double[] oldArray, int newSize) {
        int oldSize = oldArray.length;
        double[] newArray = new double[newSize];//java.lang.reflect.Array.newInstance(elementType, newSize);
        int preserveLength = Math.min(oldSize, newSize);
        if (preserveLength <= 0) {
        	throw new IllegalArgumentException();
        }
        System.arraycopy(oldArray, 0, newArray, 0, preserveLength);
        return newArray;
  }
	
	public static int[] zerosInt(int n) {
        return new int[n];
	}
	
	public static int[][] zerosInt(int n, int m) {
		int[][] z = new int[n][m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				z[i][j] = 0;
			}
		}
		return z;
	}
	
	public static byte[] zerosByte(int n) {
        return new byte[n];
	}
	
	public static byte[][] zerosByte(int n, int m) {
		byte[][] z = new byte[n][m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				z[i][j] = 0;
			}
		}
		return z;
	}
	
	public static double[] zerosDouble(int n) {
		double[] z = new double[n];
		for(int i = 0; i < n; i++) {
			z[i] = 0;
		}
		return z;
	}
	
	public static double[][] zerosDouble(int n, int m) {
		double[][] z = new double[n][m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				z[i][j] = 0;
			}
		}
		return z;
	}
	
	public static int[] onesInt(int n) {
		int[] z = new int[n];
		for(int i = 0; i < n; i++) {
			z[i] = 1;
		}
		return z;
	}
	
	public static int[][] onesInt(int n, int m) {
		int[][] z = new int[n][m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				z[i][j] = 1;
			}
		}
		return z;
	}
	
	public static boolean[] falses(int n) {
		boolean[] z = new boolean[n];
		for(int i = 0; i < n; i++) {
			z[i] = false;
		}
		return z;
	}
	
	public static double max(double[] arr) {
		double m = arr[0];
		for(int i = 1; i < arr.length; i++) {
			if(m < arr[i]) {
				m = arr[i];
			}
		}
		return m;
	}
	
	public static double min(double[] arr) {
		double m = arr[0];
		for(int i = 1; i < arr.length; i++) {
			if(m > arr[i]) {
				m = arr[i];
			}
		}
		return m;
	}
	
	public static double max(byte[] arr) {
		byte m = arr[0];
		for(int i = 1; i < arr.length; i++) {
			if(m < arr[i]) {
				m = arr[i];
			}
		}
		return m;
	} 
	
	public static double sum(double[] a) {
		double S = 0;
		for(double aa: a) {
			S += aa;
		}
		return S;
	}
	
	public static double[] add(double[] a, double[] b) {
		if(a.length != b.length) {
    		throw new IllegalArgumentException();
    	}
		
		double[] ret = new double[a.length];
		for(int i = 0; i < ret.length; i++) {
			ret[i] = a[i]+b[i];
		}
		return ret;
	}
	
	public static double[] sub(double[] a, double[] b) {
		if(a.length != b.length) {
    		throw new IllegalArgumentException();
    	}
		
		double[] ret = new double[a.length];
		for(int i = 0; i < ret.length; i++) {
			ret[i] = a[i]-b[i];
		}
		return ret;
	}
	
	public static double[] mul(double[] a, double[] b) {
		if(a.length != b.length) {
    		throw new IllegalArgumentException();
    	}
		
		double[] ret = new double[a.length];
		for(int i = 0; i < ret.length; i++) {
			ret[i] = a[i]*b[i];
		}
		return ret;
	}
	
	public static double[] pow(double[] a, double b) {

		double[] ret = new double[a.length];
		for(int i = 0; i < ret.length; i++) {
			ret[i] = Math.pow(a[i], b);
		}
		return ret;
	}
	
	public static Vector<Integer> find(double[] a, Function<Double, Boolean> cond) {
		Vector<Integer> indices = new Vector<>();
		Vector<Boolean> where = ArrayUtils.where(a, cond);
		
		for(int i = 0; i < a.length; i++) {
			if(where.elementAt(i)) {
				indices.add(i);
			}
		}
		return indices;
	}
	
	public static Vector<Integer> find(double[] a) {
		return ArrayUtils.find(a, aa -> aa == 0);
	}
	
	public static Vector<Integer> find(int[] a, Function<Integer, Boolean> cond) {
		Vector<Integer> indices = new Vector<>();
		Vector<Boolean> where = ArrayUtils.where(a, cond);
		
		for(int i = 0; i < a.length; i++) {
			if(where.elementAt(i)) {
				indices.add(i);
			}
		}
		return indices;
	}
	
	public static Vector<Integer> find(int[] a) {
		return ArrayUtils.find(a, aa -> aa == 0);
	}
	
	public static <T> Vector<Integer> find(T[] a, Function<T, Boolean> cond) {
		Vector<Integer> indices = new Vector<>();
		Vector<Boolean> where = ArrayUtils.<T>where(a, cond);
		
		for(int i = 0; i < a.length; i++) {
			if(where.elementAt(i)) {
				indices.add(i);
			}
		}
		return indices;
	}
	
	public static <T> Vector<Integer> find(T[] a) {
		return ArrayUtils.find(a, Objects::isNull);
	}
	
	public static <T> Vector<Integer> find(Iterable<? extends T> a, Function<T, Boolean> cond) {
		Vector<Integer> indices = new Vector<>();
		
		Iterator<? extends T> iter = a.iterator();
		int i = 0;
		while(iter.hasNext()) {
			if(cond.apply(iter.next())) {
				indices.add(i);
			}
			i++;
		}
		
		return indices;
	}
	
	public static <T> Vector<Integer> find(Iterable<? extends T> a) {
		return ArrayUtils.find(a, Objects::isNull);
	}
	
	public static Vector<Boolean> where(double[] a, Function<Double, Boolean> cond) {
		Vector<Boolean> indices = new Vector<>();
        for (double v : a) {
            indices.add(cond.apply(v));
        }
		return indices;
	}
	
	public static Vector<Boolean> where(double[] a) {
		return ArrayUtils.where(a, aa -> aa == 0);
	}
	
	public static Vector<Boolean> where(int[] a, Function<Integer, Boolean> cond) {
		Vector<Boolean> indices = new Vector<>();
        for (int j : a) {
            indices.add(cond.apply(j));
        }
		return indices;
	}
	
	public static Vector<Boolean> where(int[] a) {
		return ArrayUtils.where(a, aa -> aa == 0);
	}
	
	public static <T> Vector<Boolean> where(T[] a, Function<T, Boolean> cond) {
		Vector<Boolean> indices = new Vector<>();
        for (T t : a) {
            indices.add(cond.apply(t));
        }
		return indices;
	}
	
	public static <T> Vector<Boolean> where(T[] a) {
		return ArrayUtils.where(a, Objects::isNull);
	}
	
	public static <T> Vector<Boolean> where(Iterable<? extends T> a, Function<T, Boolean> cond) {
		Vector<Boolean> indices = new Vector<>();
        for (T t : a) {
            indices.add(cond.apply(t));
        }
		
		return indices;
	}
	
	public static <T> Vector<Boolean> where(Iterable<? extends T> a) {
		return ArrayUtils.where(a, Objects::isNull);
	}
	
	public static <T> T[] reverse(T[] arr, Class<T> cl) {
		T[] ret = (T[]) Array.newInstance(cl, arr.length);
		
		for(int i = 0; i < arr.length; i++) {
			ret[i] = arr[arr.length - i - 1];
		}
		
		return ret;
	}
	
	public static int[] reverse(int[] arr) {
		int[] ret = new int[arr.length];
		
		for(int i = 0; i < arr.length; i++) {
			ret[i] = arr[arr.length - i - 1];
		}
		
		return ret;
	}

	public static double[] reverse(double[] arr) {
		double[] ret = new double[arr.length];
		
		for(int i = 0; i < arr.length; i++) {
			ret[i] = arr[arr.length - i - 1];
		}
		
		return ret;
	}
	
	public static Complex[] reverse(Complex[] arr) {
		Complex[] ret = new Complex[arr.length];
		
		for(int i = 0; i < arr.length; i++) {
			ret[i] = arr[arr.length - i - 1];
		}
		
		return ret;
	}
	
	public static Pair<Vector<Integer>, Vector<Integer>> arraySplit(int[] arr, int index){
		//int[][] arrays = zerosInt(2, (int)Math.max(arr.length-index-1, index+1));//new int[2][(int)Math.max(arr.length-index+1, index+1)];
		Vector<Integer> array1 = new Vector<>();
		Vector<Integer> array2 = new Vector<>();
		for(int i = 0; i < arr.length; i++) {
			if(i < index) {
				array1.add(arr[i]);
			} else {
				array2.add(arr[i]);
			}
		}
		
		return new Pair<>(array1, array2);
	}
	
	public static Pair<Vector<Integer>, Vector<Integer>> arraySplit(int[] arr){
		return arraySplit(arr, (int)(Math.ceil((double) arr.length /2)));
	}
	
	public static int[] byIndex(int[] a, Collection<? extends Integer> indices) {
		int[] a2 = new int[indices.size()];
		int i = 0;
		for(Integer index: indices) {
			a2[i++] = a[index];
		}
		return a2;
	}
	
	public static double[] byIndex(double[] a, Collection<? extends Integer> indices) {
		double[] a2 = new double[indices.size()];
		int i = 0;
		for(Integer index: indices) {
			a2[i++] = a[index];
		}
		return a2;
	}
	
	public static <T> Vector<T> byIndex(Collection<? extends T> a, Collection<? extends Integer> indices) {
		Vector<T> a2 = new Vector<>();
		int i = 0;
		//Iterator<? extends T> iter = a.iterator();
		Iterator<? extends Integer> iter = indices.iterator();
		while(iter.hasNext()) {
			a2.add(null);
		}
		return a2;
	}
	
	public static double[] normalize(double[] a) {
		double[] aa = new double[a.length];
		double max = max(a), min = min(a);
		double l = max - min;
		for(int i = 0; i < aa.length; i++) {
			aa[i] = (a[i] - min)/l;
		}
		return aa;
	}

	public static <T> List<T> merge(Collection<? extends Collection<? extends T>> collections) {
		return collections.stream().flatMap(Collection::stream).collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] merge(T[][] arrays) {
		List<T> ret = new ArrayList<>();
		for(T[] a: arrays) {
            ret.addAll(Arrays.asList(a));
		}
		return ret.toArray((T[]) Array.newInstance(arrays[0].getClass().getComponentType(), ret.size()));
	}

//	@SuppressWarnings("unchecked")
	public static int[] merge(int[][] arrays) {
		int size = 0;
		for(int[] a: arrays) {
			size += a.length;
		}
		int[] ret = new int[size];
		for(int i = 0, j = 0; i < arrays.length; i++) {
			for(int a: arrays[i]) {
				ret[j++] = a;
			}
		}
		return ret;
	}

	public static float[] merge(float[][] arrays) {
		int size = 0;
		for(float[] a: arrays) {
			size += a.length;
		}
		float[] ret = new float[size];
		for(int i = 0, j = 0; i < arrays.length; i++) {
			for(float a: arrays[i]) {
				ret[j++] = a;
			}
		}
		return ret;
	}

	public static double[] merge(double[][] arrays) {
		int size = 0;
		for(double[] a: arrays) {
			size += a.length;
		}
		double[] ret = new double[size];
		for(int i = 0, j = 0; i < arrays.length; i++) {
			for(double a: arrays[i]) {
				ret[j++] = a;
			}
		}
		return ret;
	}

	public static long[] merge(long[][] arrays) {
		int size = 0;
		for(long[] a: arrays) {
			size += a.length;
		}
		long[] ret = new long[size];
		for(int i = 0, j = 0; i < arrays.length; i++) {
			for(long a: arrays[i]) {
				ret[j++] = a;
			}
		}
		return ret;
	}

	public static char[] merge(char[][] arrays) {
		int size = 0;
		for(char[] a: arrays) {
			size += a.length;
		}
		char[] ret = new char[size];
		for(int i = 0, j = 0; i < arrays.length; i++) {
			for(char a: arrays[i]) {
				ret[j++] = a;
			}
		}
		return ret;
	}

	public static byte[] merge(byte[][] arrays) {
		int size = 0;
		for(byte[] a: arrays) {
			size += a.length;
		}
		byte[] ret = new byte[size];
		for(int i = 0, j = 0; i < arrays.length; i++) {
			for(byte a: arrays[i]) {
				ret[j++] = a;
			}
		}
		return ret;
	}

	public static boolean[] merge(boolean[][] arrays) {
		int size = 0;
		for(boolean[] a: arrays) {
			size += a.length;
		}
		boolean[] ret = new boolean[size];
		for(int i = 0, j = 0; i < arrays.length; i++) {
			for(boolean a: arrays[i]) {
				ret[j++] = a;
			}
		}
		return ret;
	}
}
