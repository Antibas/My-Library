package com.antibas.games.backgammon;

import com.antibas.games.chess.Color;
import com.antibas.math.Math2;
import com.antibas.util.pair.Pair;

import java.util.Vector;

public abstract class Table {
	public static final int SIZE = 12;
	//private final Vector<Piece>[] uppieces, downpieces;
	private Vector<Piece> pieces;
	public Table() {
		/*this.uppieces = new Vector[SIZE];
		for(int i = 0; i < SIZE; i++) {
			this.uppieces[i] = new Vector<>();
		}
		
		this.downpieces = new Vector[SIZE];
		for(int i = 0; i < SIZE; i++) {
			this.downpieces[i] = new Vector<>();
		}*/
		this.pieces = new Vector<>();
	}
	
	public Pair<Integer, Integer> throwDice() {
		return new Pair<>(Math2.RNG(1, 6), Math2.RNG(1, 6));
	}
	
	public abstract boolean canGo(Color color, Position from, Position to);
}
