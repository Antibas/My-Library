package com.antibas.games.sudoku;

import com.antibas.math.Math2;
import com.antibas.math.matrix.Matrix;
import com.antibas.util.Methods;
import com.antibas.util.pair.Pair;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Stack;

public class Sudoku {
	private final Matrix grid;
	
	public Sudoku(Matrix grid) {
		if(!grid.isOrthogonial()) {
			throw new IllegalArgumentException();
		}
		this.grid = grid;
		if(!this.isValid()) {
			throw new IllegalArgumentException();
		}
	}
	
	public Sudoku(int size) {
		this(new Matrix(size, size, 0.0));
	}
	
	public Sudoku(String... e) {
		this(new Matrix(e));
	}
	
	public Sudoku() {
		this(9);
	}
	
	public Sudoku(Sudoku sudoku) {
		this.grid = Methods.<Matrix>deepCopy(sudoku.grid);
	}

	public Matrix getGrid() {
		return grid;
	}

	public int size() {
		return grid.rows();
	}
	
	public void randomize(int zeros) {
		this.grid.fill(-1);
		int x, y;
		for(int z = 0; z < zeros; z++) {
			do {
				x = Math2.RNG(this.size()-1);
				y = Math2.RNG(this.size()-1);
			} while(this.get(x, y) == 0);
			this.put(x, y, 0);
		}
		for(int i = 0; i < this.size(); i++) {
        	for(int j = 0; j < this.size(); j++) {
        		int n = this.get(i, j);
        		if(n == -1) {
        			Stack<Integer> possibles = this.getPossibilities(i, j);
        			System.out.println(possibles);
        			if(possibles.isEmpty()) {
        				this.put(i, j, 0);
        				continue;
        			}
        			//assert !possibles.isEmpty();
        			int index = Math2.RNG(possibles.size()-1);
        			int number = possibles.get(index);
        			this.put(i, j, number);
        		}
        	}
		}
		
	}
	
	public void randomize() {
		int number;
		for(int i = 0; i < this.size(); i++) {
        	for(int j = 0; j < this.size(); j++) {
//        		do {
//        			number = Math2.RNG(0, this.size());
//        		} while(!this.putIfPossible(i, j, number));
				this.randomizeBlock(i, j);
        	}
		}
		
	}
	
	public void randomizeBlock(int x, int y) {
		int number;
		do {
			number = Math2.RNG(this.size());
		} while(this.putIfPossible(x, y, number));
	}
	
	public boolean put(int x, int y, int n) {
		try {
			this.grid.set(x, y, n);
			return true;
		} catch(Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	public boolean putIfPossible(int x, int y, int n) {
		if(!this.isPossible(x, y, n)) {
			return false;
		}
		return this.put(x, y, n);
	}
	
	public int get(int x, int y) {
		return (int) this.grid.elementAt(x, y);
	}

    public HashMap<Pair<Integer, Integer>, Stack<Integer>> getPossibilities() {
		HashMap<Pair<Integer, Integer>, Stack<Integer>> possibilities = new HashMap<>();
		for(int i = 0; i < this.size(); i++) {
        	for(int j = 0; j < this.size(); j++) {
        		int n = (int) this.get(i, j);
        		if(n <= 0) {
        			Pair<Integer, Integer> pair = new Pair<>(i, j);
        			possibilities.put(pair, this.getPossibilities(i, j));
        			/*
        			possibilities.put(pair, new Stack<>());
        			for(int k = 1; k <= 9; k++) {
        				if(this.isPossible(i, j, k)) {
        					possibilities.get(pair).add(k);
        				}
        			}*/
        		}
        	}
		}
		return possibilities;
	}
	
	public Stack<Integer> getPossibilities(int x, int y){
		int n = this.get(x, y);
		Stack<Integer> possibles = new Stack<>();
		if(n > 0) {
			return possibles;
		}
		for(int k = 1; k <= 9; k++) {
			if(this.isPossible(x, y, k)) {
				possibles.push(k);
			}
		}
		return possibles;
	}

	@Override
	public String toString() {
		return this.grid.toString();
	}
	
	public boolean isPossible(int x, int y, int n) {
		if(n == 0) { 
			return true;
		}
		for(int i = 0; i < this.size(); i++) {
			if(i == y) {
				continue;
			}
            if(this.get(x, i) == n) {
                return false;
            }
		}
		
		for(int i = 0; i < this.size(); i++) {
			if(i == x) {
				continue;
			}
            if(this.get(i, y) == n) {
                return false;
            }
		}
            
        int x0 = (x/3)*3;
        int y0 = (y/3)*3;
        for(int i = 0; i < Math.sqrt(this.size()); i++) {
        	for(int j = 0; j < Math.sqrt(this.size()); j++) {
        		if(x0+i == x && y0+j == y) {
    				continue;
    			}
                if(this.get(x0+i, y0+j) == n) {
                    return false;
                }
        	}
        }
        return true;
	}
	
	public static Sudoku getSolved(Sudoku sudoku) {
		//First set single choices
		Sudoku solved = new Sudoku(sudoku);
		if(solved.isSolved()) {
			return solved;
		}
		Pair<Integer, Integer> position;
		Stack<Integer> possibles;
		for(Entry<Pair<Integer, Integer>, Stack<Integer>> entry: solved.getPossibilities().entrySet()) {
			position = entry.getKey();
			possibles = entry.getValue();
			if(possibles.size() == 1) {
				solved.putIfPossible(position.getFirst(), position.getSecond(), possibles.peek());
			}
		}
		for(Entry<Pair<Integer, Integer>, Stack<Integer>> entry: solved.getPossibilities().entrySet()) {
			position = entry.getKey();
			possibles = entry.getValue();
			if(possibles.isEmpty()) {
				continue;
			}
			for(int i = 0; i < possibles.size(); i++) {
				if(solved.putIfPossible(position.getFirst(), position.getSecond(), possibles.peek())) {
					solved = getSolved(solved);
				}
			}
		}
		
		return solved;
	}
	
	public int emptyBlocks() {
//		int S = 0;
//		for(int i = 0; i < this.size(); i++) {
//        	for(int j = 0; j < this.size(); j++) {
//        		int n = this.get(i, j);
//        		if(n == 0) {
//        			S++;
//        		}
//        	}
//		}
//		return S;
		return this.grid.stream().map(s -> s==0?1:0).reduce(0, Integer::sum);
	}
	
	public boolean isFull() {
//		for(int i = 0; i < this.size(); i++) {
//        	for(int j = 0; j < this.size(); j++) {
//        		int n = this.get(i, j);
//        		if(n == 0) {
//        			return false;
//        		}
//        	}
//		}
//		return true;
		return this.grid.stream().allMatch(s -> s!=0);
	}
	
	public boolean isValid() {
		for(int i = 0; i < this.size(); i++) {
        	for(int j = 0; j < this.size(); j++) {
        		int n = this.get(i, j);
        		if(n == 0) {
        			continue;
        		}
        		if(!this.isPossible(i, j, n)) {
        			return false;
        		}
        	}
		}
		return true;
	}
	
	public boolean isSolved() {
		return isValid() && isFull();
	}
}
