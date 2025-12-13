package com.antibas.games._2048;

import com.antibas.math.Math2;
import com.antibas.math.matrix.Matrix;
import com.antibas.math.randomvariable.RandomVariable;

public class _2048 {
	private static final RandomVariable<Integer> GENERATED = getGenerated();//[] = {2, 4};
	private Matrix matrix;
	private int size;
	
	public _2048(int size) {
		this.size = size;
		this.matrix = new Matrix(size, size, 0);
		this.generate();
		this.generate();
	}
	
	public _2048() {
		this(4);
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public void moveLeft() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size-1; j++) {
				fuseLeft(i, j);
				pushRowLeft(i);
			}
		}
		this.generate();
	}
	
	private void fuseLeft(int i, int j) {
		int first = (int) matrix.elementAt(i, j), second = (int) matrix.elementAt(i, j+1);
		if(first == second) {
			matrix.set(i, j, 2*second);
			matrix.set(i, j+1, 0);
		} 
		
	}
	
	private void pushRowLeft(int row) {
		for(int j = 0; j < size-1; j++) {
			int first = (int) matrix.elementAt(row, j), second = (int) matrix.elementAt(row, j+1);
			if(first == 0 && second != 0) {
				matrix.set(row, j, second);
				matrix.set(row, j+1, 0);
			}
			
		}
	}
	
	public void moveRight() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size-1; j++) {
				fuseRight(i, j);
				pushRowRight(i);
			}
		}
		this.generate();
	}
	
	private void fuseRight(int i, int j) {
		int first = (int) matrix.elementAt(i, j+1), second = (int) matrix.elementAt(i, j);
		if(first == second) {
			matrix.set(i, j+1, 2*second);
			matrix.set(i, j, 0);
		} 
		
	}
	
	private void pushRowRight(int row) {
		for(int j = 0; j < size-1; j++) {
			int first = (int) matrix.elementAt(row, j+1), second = (int) matrix.elementAt(row, j);
			if(first == 0 && second != 0) {
				matrix.set(row, j+1, second);
				matrix.set(row, j, 0);
			}
			
		}
	}
	
	public void moveUp() {
		for(int i = 0; i < size-1; i++) {
			for(int j = 0; j < size; j++) {
				fuseUp(i, j);
				pushColumnUp(j);
			}
		}
		this.generate();
	}
	
	private void fuseUp(int i, int j) {
		int first = (int) matrix.elementAt(i, j), second = (int) matrix.elementAt(i+1, j);
		if(first == second) {
			matrix.set(i, j, 2*second);
			matrix.set(i+1, j, 0);
		} 
		
	}
	
	private void pushColumnUp(int column) {
		for(int i = 0; i < size-1; i++) {
			int first = (int) matrix.elementAt(i, column), second = (int) matrix.elementAt(i+1, column);
			if(first == 0 && second != 0) {
				matrix.set(i, column, second);
				matrix.set(i+1, column, 0);
			}
			
		}
	}
	
	public void moveDown() {
		for(int i = 0; i < size-1; i++) {
			for(int j = 0; j < size; j++) {
				fuseDown(i, j);
				pushColumnDown(j);
			}
		}
		this.generate();
	}
	
	private boolean fuseDown(int i, int j) {
		int first = (int) matrix.elementAt(i+1, j), second = (int) matrix.elementAt(i, j);
		if(first == second) {
			matrix.set(i+1, j, 2*second);
			matrix.set(i, j, 0);
			return true;
		} 
		return false;
	}
	
	private void pushColumnDown(int column) {
		for(int i = 0; i < size-1; i++) {
			int first = (int) matrix.elementAt(i+1, column), second = (int) matrix.elementAt(i, column);
			if(first == 0 && second != 0) {
				matrix.set(i+1, column, second);
				matrix.set(i, column, 0);
			}
			
		}
	}
	
	public boolean ends() {
		return wins() || loses();
	}
	
	private boolean loses() {
		for(int i = 0; i < size-1; i++) {
			for(int j = 0; j < size-1; j++) {
				if(canMove(i, j)) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean canMove(int i, int j) {
		return matrix.elementAt(i, j) == 0 || 
			   matrix.elementAt(i, j) == matrix.elementAt(i, j+1) ||
			   matrix.elementAt(i, j) == matrix.elementAt(i+1, j);
	}

	private boolean wins() {
		for(int i = 0; i < size-1; i++) {
			for(int j = 0; j < size-1; j++) {
				if(matrix.elementAt(i, j) == 2048) {
					return true;
				}
			}
		}
		return false;
	}

	public void generate() {
		int i, j;
		do {
			i = Math2.RNG(size-1);
			j = Math2.RNG(size-1);
		} while(matrix.elementAt(i, j) != 0);
		
		this.matrix.set(i, j, GENERATED.getRand());
	}
	
	@Override
	public String toString() {
		return matrix.toString();
	}
	
	private static RandomVariable<Integer> getGenerated(){
		RandomVariable<Integer> variable = new RandomVariable<>();
		variable.put(2, .8D);
		variable.put(4, .2D);
		return variable;
	}
}
