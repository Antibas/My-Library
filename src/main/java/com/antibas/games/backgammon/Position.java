package com.antibas.games.backgammon;

import lombok.Getter;

@Getter
public class Position {
	private int place;
	private boolean up;
	
	public Position(int place, boolean up) {
		this.place = place;
		this.up = up;
	}

    public void move(int by) {
		if(place+by > Table.SIZE) {
			place = (place+by) % Table.SIZE;
			changeSide();
		} else {
			place += by;
		}
	}

    public void changeSide() {
		this.up = !this.up;
	}
	
	
}
