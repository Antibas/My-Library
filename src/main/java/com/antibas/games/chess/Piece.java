package com.antibas.games.chess;

import com.antibas.io.ConsoleColors;
import lombok.Getter;

@Getter
public class Piece {
	private final PieceName name;
	private final Color color;
	
	public Piece(PieceName name, Color color) {
		this.name = name;
		this.color = color;
	}

    @Override
	public String toString() {
		return ConsoleColors.colorizeAnsiString((color == Color.WHITE)? ConsoleColors.ANSI_WHITE: ConsoleColors.ANSI_BLACK, name.toString().substring(0, 3) + "_" + color.toString().charAt(0));
	}
	
	
	
}
