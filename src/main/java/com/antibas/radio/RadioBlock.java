package com.antibas.radio;

import com.antibas.util.Nameable;

import java.io.Serial;

public abstract class RadioBlock implements Nameable{
	/**
	 * 
	 */
	public static final long[] BASIC_SAMPLE_RATES = {22000, 44100, 48000, 88200, 96000};
	@Serial
    private static final long serialVersionUID = 1L;
	private String id;

	public RadioBlock(String id) {
		this.id = id;
	}

	public RadioBlock() {
		this("");
	}
	
	@Override
	public void setName(String name) {
		this.id = name;
	}

	@Override
	public String getName() {
		return id;
	}
	
	
}
