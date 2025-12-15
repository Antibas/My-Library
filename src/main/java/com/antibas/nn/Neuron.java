package com.antibas.nn;

import com.antibas.math.function.Function;

import java.util.Collection;
import java.util.Vector;

public class Neuron {
	private double bias;
	private final Vector<Double> prevWeights;
	
	public Neuron(double bias, Collection<? extends Double> prevWeights) {
        this.bias = bias;
        this.prevWeights = new Vector<>(prevWeights);
        
    }
	
    public Neuron(double bias, int prevWeightsSize) {
        this(bias, new Vector<>());
        for(int i = 0; i < prevWeightsSize; i++) {
        	this.prevWeights.add(Math.random());
        }
    }

    public Neuron(int prevWeightsSize) {
        this(Math.random(), prevWeightsSize);
    }
    
    public Neuron() {
        this(Math.random(), 1);
    }

	public double getOutput(Vector<Double> inputs, Function activationFunction) {
		if(inputs.size() != this.prevWeights.size())
			throw new IllegalArgumentException();
		
		double out = this.bias;
		for(int i = 0; i < inputs.size(); i++) {
			out += inputs.elementAt(i)*this.prevWeights.elementAt(i);
		}
		
		return activationFunction.apply(out);
	}

	public double getBias() {
		return bias;
	}

	public void setBias(double bias) {
		this.bias = bias;
	}
	
	public boolean addWeight(double weight) {
		return this.prevWeights.add(weight);
	}
	
	public double weightAt(int index) {
		return this.prevWeights.elementAt(index);
	}
	
	public void setWeightAt(int index, double weight) {
		this.prevWeights.setElementAt(weight, index);
	}
	
	public double removeWeight(int index) {
		return this.prevWeights.remove(index);
	}
	
	public int weightSize() {
		return this.prevWeights.size();
	}

	@Override
	public String toString() {
		return "Neuron[" + bias + "]";
	}
}
