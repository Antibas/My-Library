package com.antibas.nn;

import com.antibas.math.function.Function;

import java.util.Vector;

public class NeuralNetwork {
	private final Layer[] layers;
	private final Function activationFunction;
	
	public NeuralNetwork(Function activationFunction, int... layerSizes) {
		this.activationFunction = activationFunction;
		this.layers = new Layer[layerSizes.length];
		
		for(int i = 0; i < layerSizes.length; i++) {
			if(i == 0) this.layers[i] = new Layer(layerSizes[i]);
			else this.layers[i] = new Layer(layerSizes[i], layerSizes[i-1]);
		}
	}
	
	public void evaluateLayer(Vector<Double> inputs, int layerIndex) {
		Vector<Double> outputs = new Vector<>();
		for(int i = 0; i < this.layers[layerIndex].neuronSize(); i++) {
			for(int j = 0; j < this.layers[layerIndex+1].neuronSize(); j++) {
				outputs.add(this.layers[layerIndex].neuronAt(i).getOutput(inputs, this.activationFunction));
			}
		}
//		double out;
//		for(int layerIndex = 0; layerIndex < layers.length; layerIndex++) {
//			for(int i = 0; i < this.layers[layerIndex].neuronSize(); i++) {
//				for(int j = 0; j < this.layers[layerIndex+1].neuronSize(); j++) {
//					out = this.layers[layerIndex].neuronAt(i).getOutput(inputs, this.activationFunction);
//				}
//			}
//		}
	}
}
