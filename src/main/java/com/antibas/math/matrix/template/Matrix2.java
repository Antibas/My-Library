/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.math.matrix.template;

import com.antibas.math.matrix.EmptyMatrixException;

import java.util.Collection;
import java.util.function.Consumer;


/**
 *
 * @param <T>
 * @author Antibassis
 */
public class Matrix2<T extends Number> {
    protected final int rows, columns;
    protected T[][] elements;
    private final Class<T> classType;
    
    public Matrix2(int r, int c, Class<T> cc){
        if(r<0 || c<0) throw new IllegalArgumentException(r + " " + c);
        rows = r;
        columns = c;
        classType = cc;
        elements = (T[][]) new Number[r][c];//(T[][]) Array.newInstance(cc.getClass(), r, c);
    }
    
    public Matrix2(int r, Class<T> cc){
        this(r, 1, cc);        
    }
    
    public Matrix2(T a[][], Class<T> cc){
        if(a == null) throw new NullPointerException();
        rows = a.length;
        columns = a[0].length;
        classType = cc;
        elements = a;
    }
    
    public Matrix2(int r, int c, T t){
    	this(r, c, (Class<T>) t.getClass());
    	this.fill(t);
    }
    
    /*public Matrix(T a[]){
        if(a == null) throw new NullPointerException();
        rows = a.length;
        columns = 1;
        T[][] ar = {a};
        elements = ar;
    }*/
    
    public Matrix2(Collection<? super T> a, Class<T> cc){
        this((int)Math.ceil((double)a.size()/2), (int)Math.ceil((double)a.size()/2), cc);
        for(int r = 0; r < rows; r++){ 
            for(int c = 0; c < columns; c++){
                elements[r][c] = (T)a.toArray()[r+c];
            }
        }
    }
    
    public Matrix2(){
        rows = 0;
        columns = 0;
        elements = null;
        classType = null;
    }
    
    public boolean isOrthogonial(){
        return rows == columns;
    }
    
    public T elementAt(int r, int c){
        if(r>rows || c>columns) throw new IndexOutOfBoundsException();
        if(r<0 || c<0) throw new IllegalArgumentException();
        return elements[r][c];
    }
    
    public T elementAt(int r){
        return elementAt(r, 0);
    }
    
    public boolean isNull(){
        return (rows == 0 && columns == 0);
    }
    
    @Override
    public String toString(){
        if(isNull()){
            return "[]";
        } else {
            String ret = "";
            for(int r = 0; r < rows; r++){
                ret += "[";
                for(int c = 0; c < columns; c++){
                    ret += elements[r][c].toString();
                    if(c != columns-1) ret += ", ";
                }
                ret += "]\n";
            }
            return ret;
        }
    }
    
    public void set(int r, int c, T n){
        if(r>rows || c>columns) throw new IndexOutOfBoundsException();
        if(r<0 || c<0) throw new IllegalArgumentException();
        elements[r][c] = n;
    }
    
    /**
     * Sets the element at (r, 0) equal to n
     * @param r for row 
     * @param n an integer
     */
    public void set(int r, T n){
        set(r, 0, n);
    }
    
    /*public void init() throws NullMatrixException{
        if(isNull()) throw new NullMatrixException();
        Scanner in = new Scanner(System.in);
        for(int r = 0; r < rows; r++){ 
            for(int c = 0; c < columns; c++){
                System.out.println("element[" + r + "][" + c + "]: ");
                elements[r][c]= in.nextInt();
            }
        }
    }*/
    
    public void fill(T n) throws EmptyMatrixException{
        if(isNull()) throw new EmptyMatrixException();
        for(int r = 0; r < rows; r++){ 
            for(int c = 0; c < columns; c++){
                elements[r][c] = classType.cast(n);
            }
        }
    }   
    
    public Matrix2<T> submatrix(int exr, int exc){
        Matrix2<T> ret = new Matrix2<>(rows-1, columns-1, classType);
        for(int r = 0; r < ret.rows(); r++){ 
            for(int c = 0; c < ret.columns(); c++){
                if(r != exr || c != exc) ret.set(r, c, elements[r][c]);
            }
        }
        return ret;
    }
    
    public Matrix2<T> T(){
        Matrix2<T> AT = new Matrix2<>(columns, rows, classType);
        for (int r = 0; r < columns; r++){
            for (int c = 0; c < rows; c++) {
                AT.set(r, c, elements[c][r]);
            }
        }
        return AT;
    }

    public int rows() {
        return rows;
    }

    public int columns() {
        return columns;
    }

    public boolean isEmpty() {
        return elements == null;
    }

    public boolean isSingle() {
        return rows == 1 && columns == 1;
    }

    
    
    public void forEachElement(Consumer<? super T> cc) {
        for(int r = 0; r < rows(); r++){ 
            for(int c = 0; c < columns(); c++){
                cc.accept(elements[r][c]);
            }
        }
    }

    public void forEachRow(Consumer<? super T> cc) {
        for(int r = 0; r < rows(); r++){ 
            cc.accept(elements[r][0]);
        }
    }

    public void forEachColumn(Consumer<? super T> cc) {
        for(int c = 0; c < columns(); c++){
            cc.accept(elements[0][c]);
        }
    }
}
