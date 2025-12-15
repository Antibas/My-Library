package com.antibas.math.function;

// Remove Lombok annotations and generate getters/setters manually
import com.antibas.math.Number2;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class FunctionClass implements Function{

    protected double amplitude;
	protected double adder;
	protected Domain domain;
	
	public FunctionClass(double amplitude, double adder, Domain domain) {
		this.amplitude = amplitude;
		this.adder = adder;
		this.domain = domain;
	}
	
	public FunctionClass(double amplitude, double adder) {
		this(amplitude, adder, Number2.R_DOUBLE);
	}
	
	public FunctionClass(double amplitude) {
		this(amplitude, 0.0d);
	}
	
	public FunctionClass(Domain domain) {
		this(1.0d, 0.0d, domain);
	}
	
	public FunctionClass() {
		this(1.0d, 0.0d);
	}

    public FunctionClass add(FunctionClass f) {
		Function r = x -> this.apply(x) + f.apply(x); 
		return (FunctionClass)r;
	}

	@Override
	public String toString() {
		return toString("x");
	}
	
	public abstract String toString(String var);

	@Override
	public Double apply(Double aDouble) {
		return amplitude*aDouble + adder;
	}
}
