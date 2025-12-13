package com.antibas.note;

public enum NoteKey {
	C(16.35),
	C_SHARP(17.32),
	D(18.35),
	D_SHARP(19.45),
	E(20.6),
	F(21.83),
	FSHARP(23.12),
	G(24.5),
	G_SHARP(25.96),
	A(27.5),
	A_SHARP(29.14),
	B(30.87);

	private double frequency;

	private NoteKey(double frequency) {
		this.frequency = frequency;
	}

	public double getFrequency(int octave) {
		return frequency*(octave+1);
	}

	public double getFrequency() {
		return getFrequency(0);
	}
}
