package com.antibas.graphics;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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

}
