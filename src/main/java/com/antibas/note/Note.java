package com.antibas.note;

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

	public double getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(double startPoint) {
		this.startPoint = startPoint;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public NoteKey getKey() {
		return key;
	}

	public void setKey(NoteKey key) {
		this.key = key;
	}

	public int getOctave() {
		return octave;
	}

	public void setOctave(int octave) {
		this.octave = octave;
	}
}
