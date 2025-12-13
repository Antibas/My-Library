/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.math.matrix;

/**
 *
 * @author User
 */
public class EmptyMatrixException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 328611355730645968L;

	public EmptyMatrixException() {
		this("Matrix is empty.");
    }

	public EmptyMatrixException(String string) {
		super(string);
	}
    
}
