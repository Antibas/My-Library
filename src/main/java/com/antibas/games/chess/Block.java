package com.antibas.games.chess;

public class Block {
	private final Color color;
	private Piece piece;
	
	public Block(Color color, Piece piece) {
		this.color = color;
		this.piece = piece;
	}
	
	public Block(Color color) {
		this(color, null);
	}

	public Color getColor() {
		return color;
	}

	public Piece getPiece() {
		return piece;
	}

	public void placePiece(Piece piece) {
		this.piece = piece;
	}
	
	public Piece removePiece() {
		Piece tmp = this.piece;
		this.piece = null;
		return tmp;
	}

	@Override
	public String toString() {
		if(piece != null) {
			return piece.toString();
		}
		return color.toString();
	}
}
