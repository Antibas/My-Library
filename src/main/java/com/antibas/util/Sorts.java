/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.util;

/**
 *
 * @author User
 */
public final class Sorts {
    public static <T extends Comparable<T>> int partition(T[] arr, int left, int right){
        int i = left, j = right;
        T tmp;
        T pivot = arr[(left + right) / 2];

        while (i <= j) {
            while (arr[i].compareTo(pivot) < 0) i++;
            while (arr[j].compareTo(pivot) > 0) j--;

            if (i <= j) {
                  tmp = arr[i];
                  arr[i] = arr[j];
                  arr[j] = tmp;
                  i++;
                  j--;
            }
        }
        return i;
    }
    
    public static int[][] partition(int[] arr){
        int[] a = new int[(arr.length + 1)/2];
        int[] b = new int[arr.length - a.length];
        System.arraycopy(arr, 0, a, 0, a.length);
        System.arraycopy(arr, a.length, b, 0, b.length);
        return new int[][]{a, b};
    }
    
    public static int[] merge(int[] a, int[] b){
        int[] arr = new int[a.length + b.length];
        System.arraycopy(a, 0, arr, 0, arr.length-b.length);
        System.arraycopy(b, 0, arr, a.length, arr.length-a.length);
        return arr;
    }

    public static <T extends Comparable<T>> void quickSort(T[] arr, int left, int right) {
          int index = partition(arr, left, right);

          if (left < index - 1)
                quickSort(arr, left, index - 1);
          if (index < right)
                quickSort(arr, index, right);
    }
    
    public static <T extends Comparable<T>> void quickSort(T[] arr) {
          quickSort(arr, 0, arr.length-1);
    }
    
    public static <T extends Comparable<T>> void selectionSort(T[] arr){
        for (int i = 0; i < arr.length - 1; i++)  
        {  
            int index = i;  
            for (int j = i + 1; j < arr.length; j++){  
                if (arr[j].compareTo(arr[index]) < 0){  
                    index = j;//searching for lowest index  
                }  
            }  
            T smallerNumber = arr[index];   
            arr[index] = arr[i];  
            arr[i] = smallerNumber;  
        }  
    }
    
    public static void merge(int[] arr, int l, int m, int r) {
	    int i, j, k; 
	    int n1 = m - l + 1; 
	    int n2 =  r - m; 
	  
	    /* create temp arrays */
	    int[] L = new int[n1], R = new int[n2];
	  
	    /* Copy data to temp arrays L[] and R[] */
	    for (i = 0; i < n1; i++) 
	        L[i] = arr[l + i]; 
	    for (j = 0; j < n2; j++) 
	        R[j] = arr[m + 1 + j]; 
	  
	    /* Merge the temp arrays back into arr[l..r]*/
	    i = 0; // Initial index of first subarray 
	    j = 0; // Initial index of second subarray 
	    k = l; // Initial index of merged subarray 
	    while (i < n1 && j < n2) 
	    { 
	        if (L[i] <= R[j]) 
	        { 
	            arr[k] = L[i]; 
	            i++; 
	        } 
	        else
	        { 
	            arr[k] = R[j]; 
	            j++; 
	        } 
	        k++; 
	    } 
	  
	    /* Copy the remaining elements of L[], if there 
	       are any */
	    while (i < n1) 
	    { 
	        arr[k] = L[i]; 
	        i++; 
	        k++; 
	    } 
	  
	    /* Copy the remaining elements of R[], if there 
	       are any */
	    while (j < n2) 
	    { 
	        arr[k] = R[j]; 
	        j++; 
	        k++; 
	    } 
	} 
  
	/* l is for left index and r is right index of the 
	   sub-array of arr to be sorted */
	public static void mergeSort(int[] arr, int l, int r)
	{ 
	    if (l < r) 
	    { 
	        // Same as (l+r)/2, but avoids overflow for 
	        // large l and h 
	        int m = l+(r-l)/2; 
	  
	        // Sort first and second halves 
	        mergeSort(arr, l, m); 
	        mergeSort(arr, m+1, r); 
	  
	        merge(arr, l, m, r); 
	    } 
	} 
}
