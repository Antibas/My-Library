package com.antibas.radio;

import lombok.Getter;

import java.io.Serial;

@Getter
public class VariableBlock<T> extends RadioBlock {
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -3441021488681458981L;
	private T value;
	
	public VariableBlock(String id, T value) {
		super(id);
		this.value = value;
	}
	
	public VariableBlock(String id) {
		this(id, null);
	}
}
