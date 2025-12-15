/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.math.matrix;


import com.antibas.io.FileIO;
import com.antibas.math.CannotOperateException;
import com.antibas.math.Math2;
import com.antibas.util.ArrayUtils;
import com.antibas.util.Methods;
import com.antibas.util.pair.Pair;

import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;


/**
 * A class that represents a math matrix
 * with integers
 * @author Antibassis
 */
public class Matrix implements Serializable, Collection<Double>, Comparable<Matrix>, Comparator<Matrix>{
	/**
	 * 
	 */
	@Serial
    private static final long serialVersionUID = -5908238935857730098L;
	/**
	 * The default value of the matrix.
	 */
	private static final double defaultValue = Double.NaN;
	
    protected double[][] elements;
    
    /**
     * Gets two integers and creates an rXc matrix 
     * @param r for rows
     * @param c for columns
     */
    public Matrix(int r, int c){       
        this(r, c, defaultValue);
    }
    
    /**
     * Gets an integer and creates an rX1 matrix
     * Equivalent with Matrix(r, 1).
     * @param r for rows
     */
    public Matrix(int r){
        this(r, 1);        
    }
    
    /**
     * Creates an rXc matrix and fills it with an initial value. 
     * @param r for rows
     * @param c for columns
     * @param n the fill value
     */
    public Matrix(int r, int c, double n){
//    	if(r<=0 && c<=0)   throw new IllegalArgumentException("Invalid Input Size: " + r + "X" + c);
//    	if(r<=0) throw new IllegalArgumentException("Invalid Row Size: " + r);
//    	if(c<=0) throw new IllegalArgumentException("Invalid Column Size: " + c);
        this.validate(r, c, false);
        
    	//rows = r;
        //columns = c;
        elements = new double[r][c];
        
        this.fill(n);
    }
    
    /**
     * Creates an rX1 matrix and fills it with an initial value.
     * @param r for rows
     * @param n the fill value
     */
    public Matrix(int r, double n){
    	this(r, 1, n);
    }
    
    /**
     * Gets a 2D array of integers and creates a matrix with it
     * @param e an array of integers
     */
    public Matrix(double[][] e){
    	elements = e;
    }

    /**
     * 
     * @param rows the number of rows
     * @param columns the number of columns
     * @param a an array of doubles
     */
    public Matrix(int rows, int columns, double... a){
    	this(rows, columns);
        int i = 0;
    	for(int r = 0; r < this.getRows(); r++){
            for(int c = 0; c < this.getColumns(); c++){
            	if(i >= a.length) {
            		elements[r][c] = defaultValue;
            	} else {
            		elements[r][c] = a[i++];
            	}
                
            }
        }
    }
    
    /**
     * 
     * @param rows the number of rows
     * @param a an array of doubles
     */
    public Matrix(int rows, double... a){
    	this(rows, (int)Math.ceil((double)a.length/(double)rows), a);
    }
    
    /**
     * 
     * @param a an array of doubles
     */
    public Matrix(double... a){
    	this(1, a);
    }
    
    /**
     * Generates a matrix via a file
     * @param f a txt file
     */
    public Matrix(File f) throws IOException, EmptyMatrixException{
        this(FileIO.fileLines(f), FileIO.fileColumns(f));
        init(f);
    }
    
    /**
     * 
     * @param e an array of strings
     */
    public Matrix(String... e){
        this(e.length, Methods.maxLengthString(e));
        String[] e2;
        int r = 0, c = 0;
        for (String e1 : e) {
        	if(e1.isEmpty()) {
        		throw new IllegalArgumentException("Empty string as input.");
        	}
            e2 = e1.trim().split(" ");
            for (String e21 : e2) {
                elements[r][c] = Double.parseDouble(e21.trim());
                c++;
            }
            r++;
            c = 0;
        }
    }
    
    /**
     * 
     * @param e s
     */
    public Matrix(String e){
    	this(e.split(";"));
    }
    
    /**
     * Generates an empty matrix.
     */
    public Matrix(){
        elements = null;
    }
    
    /**
     * 
     * @param m s
     */
    public Matrix(Matrix m) {
        elements = m.elements;
    }
    
    /**
     * 
     * @return s
     */
    public int getRows() {
    	if(elements == null) {
    		return 0;
    	}
        return elements.length;
    }

    /**
     * 
     * @return s
     */
    public int getColumns() {
    	if(elements == null) {
    		return 0;
    	}
        return elements[0].length;
    }
    
    /**
     * 
     * @param r the row number
     * @param c the column number
     * @return the element at the position (r, c)
     */
    public double elementAt(int r, int c){
        validate(r, c);
        return elements[r][c];
    }

    private void validate(int r, int c) {
        this.validate(r, c, true);
    }

    private void validate(int r, int c, boolean checkEmpty) {
        if(isEmpty() && checkEmpty) throw new EmptyMatrixException();
        if((r> getRows() || r<0) && (c> getColumns() || c<0)) throw new IndexOutOfBoundsException("Indices " + r + " and " + c + " are invalid for size " + getRows() + "X" + getColumns());
        if(r> getRows() || r<0) throw new IndexOutOfBoundsException("Index " + r + " is invalid for row size " + getRows());
        if(c> getColumns() || c<0) throw new IndexOutOfBoundsException("Index " + c + " is invalid for column size " + getColumns());
    }

    /**
     * 
     * @return s
     */
    public boolean isOrthogonial(){
        return getRows() == getColumns();
    }
    
    /**
     * 
     * @return s
     */
    public boolean isEmpty() {
        return elements == null || elements.length == 0;
    }

    /**
     * 
     * @return s
     */
    public boolean isSingle() {
        return getRows() == 1 && getColumns() == 1;
    }
    
    /**
     * 
     * @return s
     * @throws EmptyMatrixException s
     */
    public boolean isMirrored() throws EmptyMatrixException{
        if(isEmpty()) throw new EmptyMatrixException();
        for(int r = 0; r < getRows(); r++){
            for(int c = 0; c < getColumns(); c++){
                if(elements[r][c] != elements[getRows()-r-1][getColumns()-c-1]) return false;
            }
        } 
        return true;
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Matrix O) {
	        if(getRows() != O.getRows() || getColumns() != O.getColumns()) return false;
	        for(int r = 0; r < getRows(); r++){
	            for(int c = 0; c < getColumns(); c++){
	                if(!Objects.equals(elements[r][c], O.elements[r][c])) return false;
	            }
	        }
	        return true;
        }
        
        if(o instanceof Double O) {
	        for(int r = 0; r < getRows(); r++){
	            for(int c = 0; c < getColumns(); c++){
	                if(elements[r][c] != O) return false;
	            }
	        }
	        return true;
        }
        
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.getRows();
        hash = 67 * hash + this.getColumns();
        return hash;
    }
    
    @Override
    public String toString(){
        if(isEmpty()) return "[]";
        StringBuilder ret = new StringBuilder();
        for(int r = 0; r < getRows(); r++){
            if(r == 0) ret.append("[[");
            else ret.append(" [");
            for(int c = 0; c < getColumns(); c++){
                ret.append(elements[r][c]);
                if(c != getColumns()-1) ret.append("  ");
            }
            if(r == getRows()-1) ret.append("]]");
            else ret.append("],\n");
        }
        return ret.toString();
    }
    
    /**
     * Sets the element at (r, c) equal to n
     * @param r for row 
     * @param c for column
     * @param n an integer
     */
    public void set(int r, int c, double n){
        validate(r, c);
        elements[r][c] = n;
    }
    
    /**
     * Sets the element at (r, 0) equal to n
     * @param r for row 
     * @param n an integer
     */
    public void set(int r, double n){
        this.set(r, 0, n);
    }
    
    /**
     * 
     * @param row s
     */
    public void addRow(double... row) {
    	if(isEmpty()) {
    		int rows = 1;
            int columns = row.length;
    		this.elements = new double[rows][columns];
    		System.arraycopy(row, 0, this.elements[0], 0, row.length);
    		return;
    	}
    	
    	if(row.length > this.getColumns()) {
    		throw new ArrayIndexOutOfBoundsException("Array of size " + row.length + " cannot be added to a matrix with column size " + getColumns());
    	}
    	double[][] tmp = this.elements;
    	this.elements = new double[this.getRows()+1][this.getColumns()];
    	
    	System.arraycopy(tmp, 0, this.elements, 0, tmp.length);
    	System.arraycopy(row, 0, this.elements[getRows()-1], 0, row.length);
    }
    
    /**
     * 
     * @param row s
     * @return s
     */
    public Matrix excludeRow(int row) {
    	if(row>= getRows() || row<0) throw new IndexOutOfBoundsException("Index " + row + " is invalid for row size " + getRows());
    	
    	
    	Matrix m = new Matrix(this.getRows()-1, this.getColumns());
    	
    	int j = 0;
    	for(int i = 0; i < this.getRows(); i++) {
    		if(i == row) {
    			continue;
    		}
    		System.arraycopy(this.elements[i], 0, m.elements[j++], 0, this.elements[i].length);
    	}
    	
    	return m;
    }
    
    /**
     * 
     * @param column s
     */
    public void addColumn(double... column) {
    	if(isEmpty()) {
    		int rows = column.length;
            int columns = 1;
    		this.elements = new double[rows][columns];
    		for(int i = 0; i < column.length; i++) {
    			this.elements[i][0] = column[i];
    		}
    		return;
    	}
    	
    	if(column.length > this.getRows()) {
    		throw new ArrayIndexOutOfBoundsException("Array of size " + column.length + " cannot be added to a matrix with row size " + getRows());
    	}
    	
    	double[][] tmp = this.elements;
    	this.elements = new double[this.getRows()][this.getColumns()+1];
    	
    	int a = 0;
    	for(int i = 0; i < tmp.length; i++) {
    		System.arraycopy(tmp[i], 0, this.elements[i], 0, tmp[i].length);
    		if(a < column.length) {
    			this.elements[i][getColumns()-1] = column[a++];
    		} else {
    			this.elements[i][getColumns()-1] = defaultValue;
    		}
    	}
    	
    }
    
    /**
     * 
     * @param column s
     * @return s
     */
    public Matrix excludeColumn(int column) {
    	if(column>= getColumns() || column<0) throw new IndexOutOfBoundsException("Index " + column + " is invalid for column size " + getColumns());
    	
    	Matrix m = this.T();
    	return m.excludeRow(column).T();
    }
    
    /**
     * 
     * @return d
     */
    public Matrix T(){
        Matrix AT = new Matrix(getColumns(), getRows());
        for (int r = 0; r < getColumns(); r++){
            for (int c = 0; c < getRows(); c++) {
                AT.set(r, c, elements[c][r]);
            }
        }
        return AT;
    }
    
    /**
     * 
     * @return s
     */
    public Matrix invert() {
    	if(isEmpty()) throw new EmptyMatrixException();
    	Matrix Ainv = new Matrix(this);
    	Ainv.forEach(x -> 1.0/x);
    	return Ainv;
    }
    
    /**
     * 
     * @return s
     */
    public Matrix flipHorizontally() {
    	if(isEmpty()) throw new EmptyMatrixException();
    	Matrix A = new Matrix(this);
    	for (int r = 0; r < getColumns(); r++){
    		A.elements[r] = ArrayUtils.reverse(A.elements[r]);
    	}
    	return A;
    }
    
    /**
     * 
     * @return s
     */
    public Matrix flipVertically() {
    	return this.T().flipHorizontally().T();
    }
    
    /**
     * 
     * @return s
     */
    public Matrix flipDiagonally() {
    	return this.flipVertically().flipHorizontally();
    }
    
    /**
     * Initializes the matrix by receiving input from a file.
     * @param f s
     * @throws EmptyMatrixException s
     * @throws IOException s
     */
    public final void init(File f) throws EmptyMatrixException, IOException {
        if(isEmpty()) throw new EmptyMatrixException();
        //this.fromRepresentation(FileIO.fileToString(f));
    }
    
    /**
     * Fills the matrix with random numbers.
     */
    public void randomize(){
        if(isEmpty()) throw new EmptyMatrixException();
//        for(int r = 0; r < rows(); r++){
//            for(int c = 0; c < columns(); c++){
//                elements[r][c] = new Random().nextDouble();
//            }
//        }
        this.forEach((Function<Double, Double>) e -> new Random().nextDouble());
    }
    
    /**
     * Fills the matrix with random numbers between min and max.
     * @param min minimum number
     * @param max maximum number
     */
    public void randomize(double min, double max){
        if(isEmpty()) throw new EmptyMatrixException();
//        for(int r = 0; r < rows(); r++){
//            for(int c = 0; c < columns(); c++){
//                elements[r][c]= Math2.RNG(min, max);
//            }
//        }
        this.forEach((Function<Double, Double>) e -> Math2.RNG(min, max));
    }
    
    /**
     * Fills the matrix with random numbers between 0 and max.
     * @param max maximum number
     */
    public void randomize(double max){
        this.randomize(0, max);
    }
    
    /**
     * Sets all matrix elements equal to n
     * @param n an integer 
     * @throws EmptyMatrixException s
     */
    public void fill(double n) throws EmptyMatrixException{
    	if(isEmpty()) throw new EmptyMatrixException();
    	this.forEach(x -> n);
    }
    
    /**
     * 
     * @param cc s
     */
    public void forEach(Function<Double, Double> cc){
        for(int r = 0; r < getRows(); r++){
            for(int c = 0; c < getColumns(); c++){
                elements[r][c] = cc.apply(elements[r][c]);
            }
        }
    }

    public void forEach(TriConsumer<Integer, Integer, Double> action) {
        if (isEmpty()) return;
        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getColumns(); c++) {
                double value = elementAt(r, c);
                action.accept(r, c, value);
            }
        }
    }

    @FunctionalInterface
    public interface TriConsumer<T, U, V> {
        double accept(T t, U u, V v);
    }

    /**
     * 
     * @return the elements of the matrix as an array 
     * @throws EmptyMatrixException s
     */
    public double[][] toPrimitiveArray() throws EmptyMatrixException{
        if(isEmpty()) return null;
        double[][] ret = new double[getRows()][getColumns()];
        for(int r = 0; r < getRows(); r++){
            System.arraycopy(elements[r], 0, ret[r], 0, getColumns());
        }
        return ret;
    }
    
    /**
     * Assigns as many elements from a to the matrix as possible
     * @param a a 2D array of integers
     */
    public void fromArray(double[][] a){
        //if(a.length > rows || a[0].length > columns) throw new IndexOutOfBoundsException();
    	int rows = a.length;
    	int columns = a[0].length;
        int minr = a.length, minc = a[0].length;
        for(int r = 0; r < minr; r++){ 
            System.arraycopy(a[r], 0, elements[r], 0, minc);
        }
    }
    
    /**
     * 
     * @param a s
     */
    public void fromArray(Double[][] a){
        //if(a.length > rows || a[0].length > columns) throw new IndexOutOfBoundsException();
    	int rows = a.length;
    	int columns = a[0].length;
        int minr = a.length, minc = a[0].length;
        for(int r = 0; r < minr; r++){ 
            System.arraycopy(a[r], 0, elements[r], 0, minc);
        }
    }
    
    /**
     * Makes the matrix as the I matrix.
     * @throws EmptyMatrixException s
     * @throws NoOrthogonialMatrixException s
     */
    public void toI() throws EmptyMatrixException, NoOrthogonialMatrixException{
        if(isEmpty()) throw new EmptyMatrixException();
        if(!isOrthogonial()) throw new NoOrthogonialMatrixException();
//        for(int r = 0; r < rows(); r++){
//            for(int c = 0; c < columns(); c++){
//                elements[r][c] = (r==c)? 1.0 : 0.0;
//            }
//        }
        this.forEach((r, c, e) -> (Objects.equals(r, c))? 1.0 : 0.0);
    }

    /**
     * 
     * @param r for row
     * @return the sum of all elements in row r
     */
    public double rowSum(int r){
    	if(isEmpty()) throw new EmptyMatrixException();
        if(r> getRows()) throw new IndexOutOfBoundsException();
        if(r<0) throw new IllegalArgumentException();
        double S = 0;
        for(int c = 0; c < getColumns(); c++)
            S += elements[r][c];
        return S;
    }
    
    /**
     * 
     * @param c for column
     * @return the sum of all elements in column c
     */
    public double columnSum(int c){
    	if(isEmpty()) throw new EmptyMatrixException();
        if(c> getColumns()) throw new IndexOutOfBoundsException();
        if(c<0) throw new IllegalArgumentException();
        double S = 0;
        for(int r = 0; r < getRows(); r++)
            S += elements[r][c];
        return S;
    }
    
    /**
     * 
     * @param r for row
     * @return if all elements in row r are 0
     */
    public boolean isZeroRow(int r){
    	if(isEmpty()) throw new EmptyMatrixException();
        if(r>= getRows()) throw new IndexOutOfBoundsException();
        if(r<0) throw new IllegalArgumentException();
        for(int c = 0; c < getColumns(); c++)
            if(elements[r][c] != 0.0) return false;
        return true;
    }
    
    /**
     * 
     * @param c for column
     * @return if all elements in column c are 0
     */
    public boolean isZeroColumn(int c){
    	if(isEmpty()) throw new EmptyMatrixException();
        if(c>= getColumns()) throw new IndexOutOfBoundsException();
        if(c<0) throw new IllegalArgumentException();
        for(int r = 0; r < getRows(); r++)
            if(elements[r][c] != 0.0) return false;
        return true;
    }
    
    /**
     * 
     * @return the trace of the matrix 
     * @throws NoOrthogonialMatrixException s
     */
    public double trace() throws NoOrthogonialMatrixException{
    	if(isEmpty()) throw new EmptyMatrixException();
        if(!isOrthogonial()) throw new NoOrthogonialMatrixException();
        double S = 0;
        for(int rc = 0; rc < getRows(); rc++){
            S += elements[rc][rc];
        }
        /*for(int r = 0; r < rows; r++){ 
            for(int c = 0; c < columns; c++){
                if(r == c) S += elements[r][c];
            }
        }*/
        return S;
    }

    /**
     * 
     * @return the sum of all elements of the matrix
     */
    public double sum(){
//        double S = 0;
//        for(int r = 0; r < rows(); r++){
//            for(int c = 0; c < columns(); c++){
//                S += elements[r][c];
//            }
//        }
//        return S;
        return Arrays.stream(elements).mapToDouble(x -> Arrays.stream(x).sum()).sum();
    }
    
    /**
     * 
     * @return the sum of all absolute values of the elements of the matrix
     */
    public double absSum(){
//    	double S = 0;
//        for(int r = 0; r < rows(); r++){
//            for(int c = 0; c < columns(); c++){
//                S += Math.abs(elements[r][c]);
//            }
//        }
//        return S;
        return Arrays.stream(elements).mapToDouble(x -> Arrays.stream(x).map(Math::abs).sum()).sum();
    }
    
    /**
     * 
     * @return the determinate of the matrix 
     * @throws NoOrthogonialMatrixException s
     * @throws CannotOperateException s
     */
    public double det() throws NoOrthogonialMatrixException, CannotOperateException{
    	if(isEmpty()) throw new EmptyMatrixException();
        if (!isOrthogonial()) throw new NoOrthogonialMatrixException();
        if (isSingle()) throw new CannotOperateException(); 
        if (getRows() == 2 && getColumns() == 2) return elements[0][0]*elements[1][1] - elements[0][1]*elements[1][0];
        //return submatrix(Math2.RNG(1, rows), Math2.RNG(1, columns)).det();
        double S = 0;
        Matrix sub;
        for(int i = 0; i < getRows(); i++){
            sub = submatrix(i, 0);
            //S += (-1)^(i-1)*elements[i][0]*sub.det();
            S += Math.pow(-1.0d, (double)i-1.0d)*elements[i][0]*sub.det();
        }
        return S;
    }
    
    /**
     * 
     * @param exr for exclusive row
     * @param exc for exclusive column
     * @return a submatrix of the matrix, excluding row exr and column exc
     */
    public Matrix submatrix(int exr, int exc){
    	return this.excludeRow(exr).excludeColumn(exc);
    }
    
    /**
     * Multiplies all elements of the matrix by a
     * @param a a number 
     */
    public Matrix multiply(double a) {
    	if(isEmpty()) throw new EmptyMatrixException();
    	Matrix A = new Matrix(this);
    	A.forEach(x -> a*x);
    	return A;
    }
    
    public void multiply(int r, int c, double a) {
        validate(r, c);
        elements[r][c] *= a;
    }
    
    public Matrix multiply(Matrix a) {
    	return Matrix.multiplyMatrices(this, a);
    }
    
    /**
     * Adds a to all elements of the matrix
     * @param a a number 
     */
    public Matrix add(double a) {
    	if(isEmpty()) throw new EmptyMatrixException();
    	Matrix A = new Matrix(this);
    	A.forEach(x -> a+x);
    	return A;
    }
    
    public Matrix add(Matrix a) {
    	return Matrix.addMatrices(this, a);
    }
    
    public void add(int r, int c, double a) {
//    	if(isEmpty()) throw new EmptyMatrixException();
//    	if((r>=rows() || r<0) && (c>=columns() || c<0)) throw new IndexOutOfBoundsException("Indices " + r + " and " + c + " are invalid for size " + rows() + "X" + columns());
//        if(r>=rows() || r<0) throw new IndexOutOfBoundsException("Index " + r + " is invalid for row size " + rows());
//        if(c>=columns() || c<0) throw new IndexOutOfBoundsException("Index " + c + " is invalid for column size " + columns());
        this.validate(r, c);
    	elements[r][c] += a;
    }
    
    /**
     * Subtracts a to all elements of the matrix
     * Equivalent to add(-a).
     * @param a a number 
     */
    public Matrix subtract(double a) {
    	if(isEmpty()) throw new EmptyMatrixException();
    	Matrix A = new Matrix(this);
    	this.forEach(x -> x-a);
    	return A;
    }
    
    public void subtract(int r, int c, double a) {
//    	if(isEmpty()) throw new EmptyMatrixException();
//    	if((r>=rows() || r<0) && (c>=columns() || c<0)) throw new IndexOutOfBoundsException("Indices " + r + " and " + c + " are invalid for size " + rows() + "X" + columns());
//        if(r>=rows() || r<0) throw new IndexOutOfBoundsException("Index " + r + " is invalid for row size " + rows());
//        if(c>=columns() || c<0) throw new IndexOutOfBoundsException("Index " + c + " is invalid for column size " + columns());
        this.validate(r, c);
    	elements[r][c] -= a;
    }
    
    public Matrix subtract(Matrix a) {
    	return Matrix.subtractMatrices(this, a);
    }
    
    public double max(){
    	if(isEmpty()) throw new EmptyMatrixException();
        double m = 0;
        for(int r = 0; r < getRows(); r++){
            for(int c = 0; c < getColumns(); c++){
                if(elements[r][c] > m) m = elements[r][c];
            }
        }
        return m;
    }
    
    public double min(){
    	if(isEmpty()) throw new EmptyMatrixException();
    	double m = 0;
        for(int r = 0; r < getRows(); r++){
            for(int c = 0; c < getColumns(); c++){
                if(elements[r][c] < m) m = elements[r][c];
            }
        }
        return m;
    }
    
    /**
     * Divides all the elements of the matrix by a
     * @param a a number 
     * @return A
     */
    public Matrix divide(double a) {
    	if(isEmpty()) throw new EmptyMatrixException();
        if(a==0) throw new IllegalArgumentException();
        Matrix A = new Matrix(this);
        A.forEach(x -> x/a);
        return A;
    }
    
    public void divide(int r, int c, double a) {
//    	if(isEmpty()) throw new EmptyMatrixException();
//    	if((r>=rows() || r<0) && (c>=columns() || c<0)) throw new IndexOutOfBoundsException("Indices " + r + " and " + c + " are invalid for size " + rows() + "X" + columns());
//        if(r>=rows() || r<0) throw new IndexOutOfBoundsException("Index " + r + " is invalid for row size " + rows());
//        if(c>=columns() || c<0) throw new IndexOutOfBoundsException("Index " + c + " is invalid for column size " + columns());
        this.validate(r, c);
    	if(a==0) throw new ArithmeticException("Division by zero.");
    	elements[r][c] /= a;
    }
    
    public Matrix divide(Matrix a) {
    	return Matrix.divideMatrices(this, a);
    }
    
    public Matrix dot(Matrix a) {
    	return Matrix.multiplyDotMatrices(this, a);
    }
    
    
    
    /**
     * Generates and returns an rcXrc I matrix
     * @param rc for rows and columns
     * @return an rcXrc I matrix
     * @throws EmptyMatrixException A
     * @throws NoOrthogonialMatrixException A
     */
    public static Matrix I(int rc) throws EmptyMatrixException, NoOrthogonialMatrixException{
        if(rc<0) throw new IllegalArgumentException();
        Matrix ret = new Matrix(rc, rc);
        ret.toI();
        return ret;
    }
    
    /**
     * Adds two matrixes and returns the result
     * @param A first matrix
     * @param B second matrix
     * @return the result of A+B 
     * @throws CannotOperateException A
     */
    public static Matrix addMatrices(Matrix A, Matrix B) throws CannotOperateException{
    	if(A.isEmpty() || B.isEmpty()) throw new EmptyMatrixException();
    	if(A.getRows() != B.getRows() || A.getColumns() != B.getColumns()) throw new CannotOperateException();
        Matrix R = new Matrix(A.getRows(), A.getColumns());
        for(int r = 0; r < R.getRows(); r++){
            for(int c = 0; c < R.getColumns(); c++){
                R.elements[r][c] = A.elementAt(r, c) + B.elementAt(r, c);
            }
        }
        return R;
    }
    
    /**
     * Subtracts the first matrix from the second and returns the result
     * @param A first matrix
     * @param B second matrix
     * @return the result of A-B 
     * @throws CannotOperateException s
     */
    public static Matrix subtractMatrices(Matrix A, Matrix B) throws CannotOperateException{
    	if(A.isEmpty() || B.isEmpty()) throw new EmptyMatrixException();
    	if(A.getRows() != B.getRows() || A.getColumns() != B.getColumns()) throw new CannotOperateException();
        Matrix R = new Matrix(A.getRows(), A.getColumns());
        for(int r = 0; r < R.getRows(); r++){
            for(int c = 0; c < R.getColumns(); c++){
                R.elements[r][c] = A.elementAt(r, c) - B.elementAt(r, c);
            }
        }
        return R;
    }
    
    public static Matrix multiplyDotMatrices(Matrix A, Matrix B) throws CannotOperateException{
    	if(A.isEmpty() || B.isEmpty()) throw new EmptyMatrixException();
    	if(A.getRows() != B.getRows() || A.getColumns() != B.getColumns()) throw new CannotOperateException();
        Matrix R = new Matrix(A.getRows(), A.getColumns());
        for(int r = 0; r < R.getRows(); r++){
            for(int c = 0; c < R.getColumns(); c++){
                R.elements[r][c] = A.elementAt(r, c) * B.elementAt(r, c);
            }
        }
        return R;
    }
    
    /**
     * Multiplies the first matrix with the second and returns the result
     * @param A first matrix
     * @param B second matrix
     * @return the result of AxB 
     * @throws CannotOperateException s
     */
    public static Matrix multiplyMatrices(Matrix A, Matrix B) throws CannotOperateException{
    	if(A.isEmpty() || B.isEmpty()) throw new EmptyMatrixException();
    	if(A.getColumns() != B.getRows()) throw new CannotOperateException();
        Matrix BT = (Matrix) B.T();
        Matrix R = new Matrix(A.getRows(), B.getColumns());
        //R.fill(0);
        for(int r = 0; r < R.getRows(); r++){
            for(int c = 0; c < R.getColumns(); c++){
            	double S = 0;
                for(double a: A.elements[r]) 
                    for(double b: BT.elements[c]) 
                        S += a*b;
                R.elements[r][c] = S;
            }
        }
        return R;
    }
    /**
     * Multiplies the A matrix with its transposition
     * @param A a matrix
     * @return the result of AxAT
     * @throws CannotOperateException s
     */
    public static Matrix multiplyMatrices(Matrix A) throws CannotOperateException{
        return multiplyMatrices(A, A.T());
    }
    
    public static Matrix divideMatrices(Matrix A, Matrix B) throws CannotOperateException{
    	if(A.isEmpty() || B.isEmpty()) throw new EmptyMatrixException();
    	if(A.getRows() != B.getRows() || A.getColumns() != B.getColumns()) throw new CannotOperateException();
        Matrix R = new Matrix(A.getRows(), A.getColumns());
        for(int r = 0; r < R.getRows(); r++){
            for(int c = 0; c < R.getColumns(); c++){
                R.elements[r][c] = A.elementAt(r, c) / B.elementAt(r, c);
            }
        }
        return R;
    }

	/*@Override
	public void fromRepresentation(String representation) throws InvalidSaveException {
		String[] s = representation.trim().split(" ");
		int i = 0;
        for(int r = 0; r < rows(); r++){ 
            for(int c = 0; c < columns(); c++){
                if(i >= s.length) elements[r][c] = 0;
                else elements[r][c] = Double.parseDouble(s[i++]);
            }
        }
	}

	@Override
	public byte[] getRepresentation() {
		return this.toString().getBytes();
	}*/

	@Override
	public int compareTo(Matrix o) {
		return (int)Math.floor(this.min() - o.min());
	}

	@Override
	public int compare(Matrix o1, Matrix o2) {
		return o1.compareTo(o2);
	}

	@Override
	public int size() {
		return getRows()* getColumns();
	}

	@Override
	public boolean contains(Object o) {
//		for(int r = 0; r < rows(); r++){
//            for(int c = 0; c < columns(); c++){
//            	if(o.equals(this.elements[r][c])) {
//            		return true;
//            	}
//            }
//		}
//		return false;
        return Arrays.stream(elements).anyMatch(x -> Arrays.stream(x).anyMatch(o::equals));
	}

	@Override
	public MatrixIterator iterator() {
        return new MatrixIterator();
	}

	@Override
	public Double[][] toArray() {
		if(isEmpty()) throw new EmptyMatrixException();
        double[][] ret = new double[getRows()][getColumns()];
        for(int r = 0; r < getRows(); r++){
            System.arraycopy(elements[r], 0, ret[r], 0, getColumns());
        }
        return ArrayUtils.toWrapper(ret);
	}

	@Override
	public <T> T[] toArray(T[] a) {
		if(isEmpty()) throw new EmptyMatrixException();
        for(int r = 0; r < a.length; r++){ 
            System.arraycopy(elements[r], 0, a[r], 0, a.length);
        }
        return a;
	}

	@Override
	public boolean add(Double e) {
		try{
			this.addRow(e);
		} catch(Throwable ee) {
			return false;
		}
		return true;
	}

	@Override
	public boolean remove(Object o) {
		for(int r = 0; r < getRows(); r++){
            for(int c = 0; c < getColumns(); c++){
            	if(o.equals(this.elements[r][c])) {
            		this.clear(r, c);
            		return true;
            	}
            }
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!this.contains(o)) {
                return false;
            }
        }
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends Double> a) {
		int i = 0;
//        Double[] aa = (Double[]) a.toArray();
        List<Double> aa = new ArrayList<>(a);
    	for(int r = 0; r < getRows(); r++){
            for(int c = 0; c < getColumns(); c++){
            	if(i >= a.size()) {
            		elements[r][c] = defaultValue;
            	} else {
            		elements[r][c] = aa.remove(i++);
            	}
                
            }
        }
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            if (!this.remove(o)) {
                return false;
            }
        }
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
        for (Object o : c) {
            if (!c.contains(o) && !this.remove(o)) {
                return false;
            }
        }
		return true;
	}

	@Override
	public void clear() {
		this.fill(defaultValue);
	}
	
	/**
	 * 
	 * @param r s
	 * @param c s
	 */
	public void clear(int r, int c) {
		this.elements[r][c] = defaultValue;
	}
	
	/**
	 * 
	 */
	public void nullify() {
		//rows = 0;
        //columns = 0;
        elements = null;
	}
	
	
	public class MatrixIterator implements Iterator<Double>{
        private int currentIndexR;
        private int currentIndexC;
        
        public MatrixIterator() {
        	currentIndexR = 0;
            currentIndexC = 0;
        }
        
        @Override
        public boolean hasNext() {
        	return !(currentIndexR == getRows()-1 && currentIndexC == getColumns());
        }

        /**
         * 
         * @return s
         */
        public Pair<Integer, Integer> nextIndex(){
        	if(!this.hasNext()) {
        		throw new RuntimeException();
        	}
        	int r;
        	int c;
        	if(currentIndexC < getColumns()) {
        		r = currentIndexR;
        		c = currentIndexC++;
        	} else {
        		r = ++currentIndexR;
        		c = currentIndexC = 0;
        		currentIndexC++;
        	}
        	return new Pair<Integer, Integer>(r, c);
        }
        
        @Override
        public Double next() {
        	Pair<Integer, Integer> i = this.nextIndex();
        	return elements[i.getFirst()][i.getSecond()];
        }
        
        @Override
        public void remove() {
        	Pair<Integer, Integer> i = this.nextIndex();
            Matrix.this.clear(i.getFirst(), i.getSecond());
        }
	}
	
}