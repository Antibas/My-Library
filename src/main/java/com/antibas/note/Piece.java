package com.antibas.note;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Piece extends ArrayList<Note>{

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -470419647627169985L;

	public Piece() {
		super();
	}

	public Piece(Note...notes) {
		super();
        Collections.addAll(this, notes);
	}
	
	public Piece(Collection<? extends Note> c) {
		super(c);
	}
	
	

}
