package com.antibas.games.chess;

public enum Color {
	WHITE(true), BLACK(false);
	
	public final boolean color;

	Color(boolean color) {
		this.color = color;
	}
	
	public static Color fromBool(boolean b) {
		return b? WHITE: BLACK;
	}
}
