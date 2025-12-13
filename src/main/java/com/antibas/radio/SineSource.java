package com.antibas.radio;

import com.antibas.util.ArrayUtils;

public class SineSource extends RadioBlock implements Source {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2804210770677246821L;
	private int sample_rate;
	
	@Override
	public byte[] produce() {
		byte[] ret = ArrayUtils.zerosByte(5);
		return ret;
	}

}
