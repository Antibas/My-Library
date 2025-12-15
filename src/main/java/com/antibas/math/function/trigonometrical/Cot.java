package com.antibas.math.function.trigonometrical;

public class Cot extends Sin {
	@Override
	public Double apply(Double t) {
//		if(phaseModifier == RADIANS) {
//			return amplitude/Math.tan(frequency*t + phase)+adder;
//		}
		return super.apply(1/Math.tan(frequency*t + this.getPhase()));
	}
	
	@Override
	public String toString(String var) {
		return amplitude + "*cot(" + var + " + " + getPhase() +") + " + adder;
	}
}
