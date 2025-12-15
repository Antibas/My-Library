package com.antibas.nn;

import java.util.Collection;
import java.util.Vector;

public class Layer {
	private final Vector<Neuron> neurons;
	public Layer(Collection<? extends Neuron> neurons){
		this.neurons = new Vector<>(neurons);
    }
	
	public Layer(int neuronSize, int prevWeightSize){
		this(new Vector<>());
		for(int i = 0; i < neuronSize; i++) {
        	this.neurons.add(new Neuron(prevWeightSize));
        }
    }
	
	public Layer(int neuronSize){
		this(neuronSize, 1);
    }
	
	public Layer(){
		this(1);
    }
	
	public boolean addNeuron(Neuron neuron) {
		return this.neurons.add(neuron);
	}
	
	public Neuron neuronAt(int index) {
		return this.neurons.elementAt(index);
	}
	
	public Neuron removeNeuron(int index) {
		return this.neurons.remove(index);
	}
	
	public int neuronSize() {
		return this.neurons.size();
	}

//	public Vector<Double> getOutputs(Vector<Double> inputs){
//		return null;
//	}

	@Override
	public String toString() {
		return "Layer[" + neurons + "]";
	}
	
	
}
