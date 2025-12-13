package com.antibas.graphics;

public class Color {
	private short r, g, b;

	public Color(short r, short g, short b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public Color(short r, short g) {
		this(r, g, (short) 0);
	}
	
	public Color(short r) {
		this(r, (short) 0, (short) 0);
	}
	
	public Color() {
		this((short) 0, (short) 0, (short) 0);
	}

	public short getR() {
		return r;
	}

	public void setR(short r) {
		this.r = r;
	}

	public short getG() {
		return g;
	}

	public void setG(short g) {
		this.g = g;
	}

	public short getB() {
		return b;
	}

	public void setB(short b) {
		this.b = b;
	}
}
