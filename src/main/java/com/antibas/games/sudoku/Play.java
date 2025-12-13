package com.antibas.games.sudoku;

public class Play {

	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku(
			"5 3 0 0 7 0 0 0 0",
			"6 0 0 1 9 5 0 0 0",
			"0 9 8 0 0 0 0 6 0",
			"8 0 0 0 6 0 0 0 3",
			"4 0 0 8 0 3 0 0 1",
			"7 0 0 0 2 0 0 0 6",
			"0 6 0 0 0 0 2 8 0",
			"0 0 0 4 1 9 0 0 5",
			"0 0 0 0 8 0 0 7 9"
		);
		
		System.out.println(sudoku);
		System.out.println();
		System.out.println(Sudoku.getSolved(sudoku).isSolved());
		Sudoku sudoku2 = new Sudoku();
		sudoku2.randomize();
		System.out.println(sudoku2);
		System.out.println(Sudoku.getSolved(sudoku2).isSolved());
		System.out.println();
	}

}
