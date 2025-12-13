package com.antibas.games.backgammon;

public class Position {
	private int place;
	private boolean up;
	
	public Position(int place, boolean up) {
		this.place = place;
		this.up = up;
	}

	public int getPlace() {
		return place;
	}

	public void move(int by) {
		if(place+by > Table.SIZE) {
			place = (place+by) % Table.SIZE;
			changeSide();
		} else {
			place += by;
		}
	}

	public boolean isUp() {
		return up;
	}

	public void changeSide() {
		this.up = !this.up;
	}
	
	
}
