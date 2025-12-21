/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.math.matrix.template;


import com.antibas.io.FileIO;
import com.antibas.math.CannotOperateException;
import com.antibas.math.Number2;
import com.antibas.math.matrix.EmptyMatrixException;
import com.antibas.math.matrix.NoOrthogonalMatrixException;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;


/**
 * A class that represents a math matrix
 * with integers
 * @author Antibassis
 */
public class Matrix<T> implements Serializable, Collection<T>, Comparable<Matrix<T>>, Comparator<Matrix<T>>{
	protected int rows, columns;
    protected T[][] elements;
    /**
     * Gets two integers and creates an rXc matrix 
     * @param r for rows
     * @param c for columns
     */
    public Matrix(int r, int c){
    	if(r<=0 || c<=0) throw new IllegalArgumentException(r + " " + c);
        rows = r;
        columns = c;
        elements = (T[][]) new Object[r][c];
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
    public Matrix(int r, int c, T n){
    	this(r, c);
    	this.fill(n);
    }
    
    /*public Matrix(int r, int c, String command) {
    	this(r, c);
    	switch(command) {
    	case "randomize":
    		this.randomize();
    		break;
    	case "init":
    		this.init();
    		break;
    	}
    }*/
    
    /**
     * Gets a 2D array of integers and creates a matrix with it
     * @param e an array of integers
     */
    public Matrix(T e[][]){
    	elements = e;
    	rows = e.length;
        columns = e[0].length;
        
    }
    
    /**
     * Gets a 1D array of integers and creates a matrix with it
     * @param a an array of integers
     */
    /*public Matrix(int e[]){
    	this(e.length, 1);
    	for(int i = 0; i < e.length; i++) {
    		elements[i][0] = e[i];
    	}
    	//elements = e; TODO COPY
    }*/
    
    /**
     * Gets a collection of integers and creates a matrix 
     * with its elements by spliting the collection in half
     * 
     * @param a a collection of Integers 
     */
    public Matrix(Collection<? super T> a){
    	this((int)Math.ceil((double)a.size()/2), (int)Math.ceil((double)a.size()/2));
        for(int r = 0; r < rows; r++){ 
            for(int c = 0; c < columns; c++){
                elements[r][c] = (T)a.toArray()[r+c];
            }
        }
    }
    
    public Matrix(int split, T... a){
    	this(split, (int)Math.ceil((double)a.length/(double)split));
        int i = 0;
    	for(int r = 0; r < rows; r++){ 
            for(int c = 0; c < columns; c++){
            	if(i >= a.length) {
            		elements[r][c] = null;
            	} else {
            		elements[r][c] = a[i++];
            	}
                
            }
        }
    }
    
    public Matrix(T... a){
    	this(2, a);
    }
    
    /**
     * Generates a matrix via a file
     * @param f a txt file
     * @throws IOException 
     * @throws EmptyMatrixException
     */
    public Matrix(File f) throws IOException, EmptyMatrixException{
        this(FileIO.fileLines(f), FileIO.fileColumns(f));
        init(f);
    }
    
    /*public Matrix(char sep, String... e){
        this(e.length, Methods.max(e));
        String[] e2;
        int r = 0, c = 0;
        for (String e1 : e) {
            e2 = e1.trim().split(""+sep);
            for (String e21 : e2) {
                elements[r][c] = Double.parseDouble(e21);
                c++;
            }
            r++;
            c = 0;
        }
    }*/
    
    /**
     * 
     * @param e an array of strings
     */
    /*public Matrix(String... e){
    	//this(' ', e);
        this(e.length, Methods.max(e));
        String[] e2;
        int r = 0, c = 0;
        for (String e1 : e) {
        	if(e1.isEmpty()) {
        		throw new IllegalArgumentException(e1);
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
    
    public Matrix(String e){
    	this(e.split(";"));
    }*/
    
    /**
     * Generates an empty matrix.
     */
    public Matrix(){
    	rows = 0;
        columns = 0;
        elements = null;
    }
    
    public Matrix(Matrix<T> m) {
    	rows = m.rows;
        columns = m.columns;
        elements = m.elements;
    }
    
    public int rows() {
        return rows;
    }

    public int columns() {
        return columns;
    }
    
    /**
     * 
     * @param r the row number
     * @param c the column number
     * @return the element at the position (r, c)
     */
    public T elementAt(int r, int c){
        if(r>rows || c>columns || r<0 || c<0) throw new IndexOutOfBoundsException();
        return elements[r][c];
    }
    
    /**
     * 
     * @param r the row number
     * @return the element at the position (r, 1)
     */
    public T elementAt(int r){
        return this.elementAt(r, 0);
    }
    
    public boolean isOrthogonal(){
        return rows == columns;
    }
    
    public boolean isEmpty() {
        return (rows == 0 && columns == 0) || (elements == null);
    }

    public boolean isSingle() {
        return rows == 1 && columns == 1;
    }
    
    /**
     * 
     * @return
     * @throws EmptyMatrixException 
     */
    public boolean isMirrored() throws EmptyMatrixException{
        if(isEmpty()) throw new EmptyMatrixException();
        if(isSingle()) return true;
        for(int r = 0; r < rows; r++){ 
            for(int c = 0; c < columns; c++){
                if(!Objects.equals(elements[r][c], elements[rows-r-1][columns-c-1])) return false;
            }
        } 
        return true;
    }
    
    @Override
    public boolean equals(Object o){
        if(!(o instanceof Matrix)) throw new IllegalArgumentException();
        Matrix<T> O = (Matrix<T>)o;
        if(rows != O.rows || columns != O.columns) return false;
        for(int r = 0; r < rows; r++){ 
            for(int c = 0; c < columns; c++){
                if(!Objects.equals(elements[r][c], O.elements[r][c])) return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.rows;
        hash = 67 * hash + this.columns;
        return hash;
    }
    
    @Override
    public String toString(){
        if(isEmpty()) return "[]";
        if(isSingle()) return "[" + elements[0][0] + "]";
        String ret = "";
        for(int r = 0; r < rows; r++){
            ret += "[";
            for(int c = 0; c < columns; c++){
                ret += elements[r][c];
                if(c != columns-1) ret += "  ";
            }
            ret += "]\n";
        }
        return ret;
    }
    
    /**
     * Sets the element at (r, c) equal to n
     * @param r for row 
     * @param c for column
     * @param n an integer
     */
    public void set(int r, int c, T n){
        if(r>rows || c>columns || r<0 || c<0) throw new IndexOutOfBoundsException();
        elements[r][c] = n;
    }
    
    /**
     * Sets the element at (r, 0) equal to n
     * @param r for row 
     * @param n an integer
     */
    public void set(int r, T n){
        this.set(r, 0, n);
    }
    
    public Matrix<T> T(){
        Matrix<T> AT = new Matrix<>(columns, rows);
        for (int r = 0; r < columns; r++){
            for (int c = 0; c < rows; c++) {
                AT.set(r, c, elements[c][r]);
            }
        }
        return AT;
    }
    
    public Matrix<T> flipHorizontally() {
    	Matrix<T> A = new Matrix<>(this);
    	for (int r = 0; r < columns; r++){
    		//A.elements[r] = ArrayUtils.<T>reverse(A.elements[r], Number2.class);
    	}
    	return A;
    }
    
    public Matrix<T> flipVertically() {
    	return this.T().flipHorizontally().T();
    }
    
    public Matrix<T> flipDiagonally() {
    	return this.flipVertically().flipHorizontally();
    }
    
    /**
     * Initializes the matrix by receiving inputs from JOptionPane dialogs.
     * @throws NullMatrixException
     */
    /*public void init() throws NullMatrixException {
        if(isEmpty()) throw new NullMatrixException();
        for(int r = 0; r < rows; r++){ 
            for(int c = 0; c < columns; c++){
                elements[r][c]= Double.parseDouble(JOptionPane.showInputDialog(null, "element[" + (r+1) + "][" + (c+1) + "]: ", "Matrix " + super.toString() + " initialization", JOptionPane.PLAIN_MESSAGE));
            }
        }
    }*/
    
    /**
     * Initializes the matrix by receiving input from a file.
     * @param f
     * @throws EmptyMatrixException
     * @throws IOException
     */
    public final void init(File f) throws EmptyMatrixException, IOException {
        if(isEmpty()) throw new EmptyMatrixException();
        //this.fromRepresentation(FileIO.fileToString(f));
    }
    
    /**
     * Fills the matrix with random numbers.
     */
    /*public void randomize(){
        if(isEmpty()) throw new NullMatrixException();
        for(int r = 0; r < rows; r++){ 
            for(int c = 0; c < columns; c++){
                elements[r][c]= new Random().nextInt();
            }
        }
    }*/
    
    /**
     * Fills the matrix with random numbers between min and max.
     * @param min minimum number
     * @param max maximum number
     */
    /*public void randomize(double min, double max){
        if(isEmpty()) throw new NullMatrixException();
        for(int r = 0; r < rows; r++){ 
            for(int c = 0; c < columns; c++){
                elements[r][c]= Math2.RNG(min, max);
            }
        }
    }*/
    
    /**
     * Fills the matrix with random numbers between 0 and max.
     * @param max maximum number
     */
    /*public void randomize(double max){
        this.randomize(0, max);
    }*/
    
    /**
     * Sets all matrix elements equal to n
     * @param n an integer 
     * @throws EmptyMatrixException 
     */
    public void fill(T n) throws EmptyMatrixException{
    	if(isEmpty()) throw new EmptyMatrixException();
        for(int r = 0; r < rows; r++){ 
            for(int c = 0; c < columns; c++){
                elements[r][c] = n;
            }
        }
    }
    
    public void forEach(Function<T, T> cc){
        for(int r = 0; r < rows; r++){ 
            for(int c = 0; c < columns; c++){
                elements[r][c] = cc.apply(elements[r][c]);
            }
        }
    }
    
    /**
     * 
     * @return the elements of the matrix as an array 
     * @throws EmptyMatrixException 
     */
    public T[][] asArray() throws EmptyMatrixException{
        if(isEmpty()) throw new EmptyMatrixException();
        T[][] ret = (T[][]) new Number2[rows][columns];
        for(int r = 0; r < rows; r++){ 
            System.arraycopy(elements[r], 0, ret[r], 0, columns);
        }
        return ret;
    }
    
    /**
     * Assigns as many elements from a to the matrix as possible
     * @param a a 2D array of integers
     */
    public void fromArray(T[][] a){
        //if(a.length > rows || a[0].length > columns) throw new IndexOutOfBoundsException();
        int minr = Math.min(a.length, rows), minc = Math.min(a[0].length, columns);
        for(int r = 0; r < minr; r++){ 
            System.arraycopy(a[r], 0, elements[r], 0, minc);
        }
    }
    
    /**
     * Makes the matrix as the I matrix.
     * @throws NullMatrixException
     * @throws NoOrthogonalMatrixException
     */
    /*public void toI() throws NullMatrixException, NoOrthogonialMatrixException{
        if(isEmpty()) throw new NullMatrixException();
        if(!isOrthogonial()) throw new NoOrthogonialMatrixException();
        for(int r = 0; r < rows; r++){ 
            for(int c = 0; c < columns; c++){
                elements[r][c] = (r==c)? 1.0 : 0.0;
            }
        }
    }*/
    
    /**
     * @throws NullMatrixException
     * @throws NoOrthogonalMatrixException
     * @deprecated use static toI(int rc);
     * @see makeI()
     * @return the matrix as an I
     
    public Matrix toI() throws NullMatrixException, NoOrthogonialMatrixException{
        Matrix ret = new Matrix(rows, columns);
        ret.makeI();
        return ret;
    }*/
    
    /**
     * 
     * @param c for column
     * @return the sum of all elements in column c
     */
    /*public double columnSum(int c){
        if(c>columns) throw new IndexOutOfBoundsException();
        if(c<0) throw new IllegalArgumentException();
        double S = 0;
        for(int r = 0; r < rows; r++)
            S += elements[r][c];
        return S;
    }*/
    
    /**
     * 
     * @param r for row
     * @return if all elements in row r are 0
     */
    public boolean isNullRow(int r){
        if(r>rows) throw new IndexOutOfBoundsException();
        if(r<0) throw new IllegalArgumentException();
        for(int c = 0; c < columns; c++)
            if(elements[r][c] != null) return false;
        return true;
    }
    
    /**
     * 
     * @param c for column
     * @return if all elements in column c are 0
     */
    public boolean isNullColumn(int c){
        if(c>columns) throw new IndexOutOfBoundsException();
        if(c<0) throw new IllegalArgumentException();
        for(int r = 0; r < rows; r++)
            if(elements[r][c] != null) return false;
        return true;
    }
    
    /**
     * 
     * @return the determinate of the matrix 
     * @throws NoOrthogonalMatrixException
     * @throws CannotOperateException 
     */
    /*public double det() throws NoOrthogonialMatrixException, CannotOperateException{
        if (!isOrthogonial()) throw new NoOrthogonialMatrixException();
        if (isSingle()) throw new CannotOperateException(); 
        if (rows == 2 && columns == 2) return elements[0][0]*elements[1][1] - elements[0][1]*elements[1][0];
        //return submatrix(Math2.RNG(1, rows), Math2.RNG(1, columns)).det();
        double S = 0;
        Matrix sub;
        for(int i = 0; i < rows; i++){
            sub = submatrix(i, 0);
            //S += (-1)^(i-1)*elements[i][0]*sub.det();
            S += Math.pow(-1.0d, (double)i-1.0d)*elements[i][0]*sub.det();
        }
        return S;
    }*/
    
    /**
     * 
     * @param exr for exclusive row
     * @param exc for exclusive column
     * @return a submatrix of the matrix, excluding row exr and column exc
     */
    public Matrix<T> submatrix(int exr, int exc){
        Matrix<T> ret;
        if(isSingle()) return this;
        if(rows == 1) ret = new Matrix<>(rows, columns-1);
        else if(columns == 1) ret = new Matrix<>(rows-1, columns);
        else ret = new Matrix<>(rows-1, columns-1);

        int i = 0, j = 0;
        
        for(int r = 0; r < rows; r++){ 
            for(int c = 0; c < columns; c++){
                if(r == exr && c == exc) ret.set(i, ++j, elements[r+1][c+1]);
                else if(r == exr) ret.set(i, ++j, elements[r+1][c]);
                else if(c == exc) ret.set(i, ++j, elements[r][c+1]);
                else ret.set(i, ++j, elements[r][c]);
                /*if(r != exr && c != exc){ 
                    ret.set(i, ++j, elements[r][c]);
                }*/
            }
            j = 0;
            i++;
        }
        return ret;
    }
    
    
    
    

	/*@Override
	public void fromRepresentation(String representation) throws InvalidSaveException {
		String[] s = representation.trim().split(" ");
		int i = 0;
        for(int r = 0; r < rows; r++){ 
            for(int c = 0; c < columns ; c++){
                if(i >= s.length) elements[r][c] = null;
                //else elements[r][c] = Double.parseDouble(s[i++]);
            }
        }
	}

	@Override
	public byte[] getRepresentation() {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public int compareTo(Matrix<T> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compare(Matrix<T> o1, Matrix<T> o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(T e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}