package com.antibas.games.backgammon;

import com.antibas.games.chess.Color;

public class Piece {
	private final Color color;
	private final Position position;

	public Piece(Color color, int place, boolean up) {
		this.color = color;
		this.position = new Position(place, up);
	}

	public Color getColor() {
		return color;
	}

	@Override
	public String toString() {
		return "Piece [color=" + color + ", position=" + position + "]";
	}

	
}
