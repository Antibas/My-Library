package com.antibas.games.chess;

import com.antibas.math.matrix.Matrix;

import static com.antibas.games.chess.Color.*;
import static com.antibas.games.chess.PieceName.*;

public class Chess {
	public static final int SIZE = 8;
	private final Block[][] blocks;

	public Chess(boolean init) {
		this.blocks = new Block[SIZE][SIZE];
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				this.blocks[i][j] = new Block(Color.fromBool((i % 2 == 0)^(j % 2 == 0)));
			}
		}
		if(init) {
			this.init();
		}
	}
	
	public Chess() {
		this(true);
	}
	
	public Block blockAt(char r, int c) {
		return this.blockAt(Letter.valueOf(""+r), c);
	}
	
	public Block blockAt(Letter r, int c) {
		return blocks[r.index][c-1];
	}
	
	private void placePieceAt(Letter r, int c, Piece piece) throws NotAvailableMovesException {
		if(this.availableMoves(r, c).elementAt(r.index, c-1) == 1.0) {
			throw new NotAvailableMovesException();
		}
		blocks[r.index][c-1].placePiece(piece);
	}
	
	private void placePieceAt(char r, int c, Piece piece) throws NotAvailableMovesException {
		this.placePieceAt(Letter.valueOf(""+r), c, piece);
	}
	
	public void movePiece(char r1, int c1, char r2, int c2) throws NotAvailableMovesException {
		this.movePiece(Letter.valueOf(""+r1), c1, Letter.valueOf(""+r2), c2);
	}
	
	public void movePiece(Letter r1, int c1, Letter r2, int c2) throws NotAvailableMovesException {
		this.placePieceAt(r2, c2, this.blockAt(r1, c1).removePiece());
	}
	
	public Matrix availableMoves(Letter r, int c) {
		Matrix moves = new Matrix(SIZE, SIZE, 0.0);
		Piece piece = this.blockAt(r, c).getPiece();
		if(piece == null) {
			return moves;
		}
		
		switch(piece.getName()) {
		case BISHOP:
			for(int i = c; i < SIZE; i++) {
				moves.set(r.index+i, i, 1.0);
			}
			break;
		case KING:
			moves.set(r.index, c+1, 1.0);
			moves.set(r.index+1, c+1, 1.0);
			moves.set(r.index+1, c, 1.0);
			
			if(r.index-1 >= 0) {
				moves.set(r.index-1, c+1, 1.0);
				moves.set(r.index-1, c, 1.0);
			}
			break;
		case KNIGHT:
			moves.set(r.index+1, c+2, 1.0);
			moves.set(r.index+2, c+1, 1.0);
			if(r.index-1 >= 0) {
				moves.set(r.index-1, c+2, 1.0);
				if(c-2 >= 0) {
					moves.set(r.index-1, c-2, 1.0);
				}
			}
			if(r.index-2 >= 0) {
				moves.set(r.index-2, c+1, 1.0);
				if(c-1 >= 0) {
					moves.set(r.index-2, c-1, 1.0);
				}
			}
			break;
		case PAWN:
			moves.set(r.index, c+1, 1.0);
			if(c == 2) {
				moves.set(r.index, c+2, 1.0);
			}
			break;
		case QUEEN:
			for(int i = c; i < SIZE; i++) {
				moves.set(r.index, i, 1.0);
			}
			for(int i = c; i < SIZE; i++) {
				moves.set(r.index+i, i, 1.0);
			}
			break;
		case ROOK:
			for(int i = c; i < SIZE; i++) {
				moves.set(r.index, i, 1.0);
			}
			break;
		default:
			break;
		
		}
		//TODO check pawns
		
		for(int i = 0; i < moves.getRows(); i++) {
			for(int j = 0; j < moves.getColumns(); j++) {
				if(moves.elementAt(i, j) == 1.0 && this.blocks[i][j].getPiece() != null) {
					if(piece.getColor() == this.blocks[i][j].getPiece().getColor()) {
						moves.set(i, j, 0.0);
					}
				}
			}
		}
		return moves;
	}
	
	public Matrix availableMoves(char r, int c) {
		return this.availableMoves(Letter.valueOf(""+r), c);
	}
	
	public void init() {
		try {
			this.placePieceAt('A', 2, new Piece(PAWN, WHITE));
			this.placePieceAt('B', 2, new Piece(PAWN, WHITE));
			this.placePieceAt('C', 2, new Piece(PAWN, WHITE));
			this.placePieceAt('D', 2, new Piece(PAWN, WHITE));
			this.placePieceAt('E', 2, new Piece(PAWN, WHITE));
			this.placePieceAt('F', 2, new Piece(PAWN, WHITE));
			this.placePieceAt('G', 2, new Piece(PAWN, WHITE));
			this.placePieceAt('H', 2, new Piece(PAWN, WHITE));
			
			this.placePieceAt('A', 7, new Piece(PAWN, BLACK));
			this.placePieceAt('B', 7, new Piece(PAWN, BLACK));
			this.placePieceAt('C', 7, new Piece(PAWN, BLACK));
			this.placePieceAt('D', 7, new Piece(PAWN, BLACK));
			this.placePieceAt('E', 7, new Piece(PAWN, BLACK));
			this.placePieceAt('F', 7, new Piece(PAWN, BLACK));
			this.placePieceAt('G', 7, new Piece(PAWN, BLACK));
			this.placePieceAt('H', 7, new Piece(PAWN, BLACK));
			
			this.placePieceAt('A', 1, new Piece(ROOK, WHITE));
			this.placePieceAt('B', 1, new Piece(KNIGHT, WHITE));
			this.placePieceAt('C', 1, new Piece(BISHOP, WHITE));
			this.placePieceAt('D', 1, new Piece(QUEEN, WHITE));
			this.placePieceAt('E', 1, new Piece(KING, WHITE));
			this.placePieceAt('F', 1, new Piece(BISHOP, WHITE));
			this.placePieceAt('G', 1, new Piece(KNIGHT, WHITE));
			this.placePieceAt('H', 1, new Piece(ROOK, WHITE));
			
			this.placePieceAt('A', 8, new Piece(ROOK, BLACK));
			this.placePieceAt('B', 8, new Piece(KNIGHT, BLACK));
			this.placePieceAt('C', 8, new Piece(BISHOP, BLACK));
			this.placePieceAt('D', 8, new Piece(KING, BLACK));
			this.placePieceAt('E', 8, new Piece(QUEEN, BLACK));
			this.placePieceAt('F', 8, new Piece(BISHOP, BLACK));
			this.placePieceAt('G', 8, new Piece(KNIGHT, BLACK));
			this.placePieceAt('H', 8, new Piece(ROOK, BLACK));
		} catch (NotAvailableMovesException e) {
			// not gonna happen
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		String s = "\tA\tB\tC\tD\tE\tF\tG\tH\n\n";
		for(int i = 0; i < SIZE; i++) {
			s += Integer.toString(i+1) + ".\t";
			for(int j = 0; j < SIZE; j++) {
				s += this.blocks[j][i].toString() + "\t";
			}
			s += "\n\n";
		}
		return s;
	}
	
	
}
