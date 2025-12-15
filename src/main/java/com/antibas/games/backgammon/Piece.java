package com.antibas.games.backgammon;

import com.antibas.games.chess.Color;
import lombok.Getter;

public class Piece {
	@Getter
	private final Color color;
	private final Position position;

	public Piece(Color color, int place, boolean up) {
		this.color = color;
		this.position = new Position(place, up);
	}

	@Override
	public String toString() {
		return "Piece [color=" + color + ", position=" + position + "]";
	}

	
}
