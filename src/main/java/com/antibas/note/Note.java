package com.antibas.note;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Note {
	public final static double MAX_VELOCITY = 1.2;
	public final static int MAX_OCTAVE = 10;
	public final static double RIGHT_BALANCE = 1.0;
	public final static double LEFT_BALANCE = -1.0;
	
	private double startPoint, duration, velocity, balance;
	private NoteKey key;
	private int octave;
	
	public Note(double startPoint, double duration, NoteKey key, int octave, double velocity, double balance) {
		this.startPoint = startPoint;
		this.duration = duration;
		this.velocity = velocity;
		this.balance = balance;
		this.key = key;
		this.octave = octave;
	}

	public Note(double startPoint, double duration, NoteKey key, int octave) {
		this(startPoint, duration, key, octave, 1.0, 0.0);
	}

}
