package com.antibas.er;

import java.util.Vector;

public class Entity{
	public static final int DEFAULT_CAPACITY = 10;
	
	private Vector<Feature> features;
	private Vector<Feature> keyFeatures;
	private final int capacity;
	private int size;
	
	public Entity(Vector<Feature> keyFeatures, Vector<Feature> features, int capacity) {
		this.features = features;
		this.keyFeatures = keyFeatures;
		this.capacity = capacity;
		this.size = 0;
	}
	
	public Entity(Vector<Feature> keyFeatures, int capacity) {
		this(keyFeatures, new Vector<>(), capacity);
	}

	public Entity(Vector<Feature> keyFeatures) {
		this(keyFeatures, DEFAULT_CAPACITY);
	}
	
	public Entity(Vector<Feature> keyFeatures, Vector<Feature> features) {
		this(keyFeatures, features, DEFAULT_CAPACITY);
	}
	
	public Entity(String... names) {
		this(new Vector<>(), DEFAULT_CAPACITY);
		//this.features = new Vector<>();
		for(String n: names) {
			this.addFeature(n);
		}
	}
	
	public int getCapacity() {
		return capacity;
	}

	public int getSize() {
		return size;
	}

	public boolean addFeature(String name) {
		if(this.hasFeature(name)) {
			return false;
		}
		
		return this.features.add(new Feature(name, this.capacity));
	}
	
	public boolean addKeyFeature(String name) {
		if(this.hasKeyFeature(name)) {
			return false;
		}
		
		return this.keyFeatures.add(new Feature(name, this.capacity));
	}
	
	public boolean hasFeature(String name) {
		for(Feature f: features) {
			if(f.getName().equals(name)) {
				return true;
			}
		}
		return this.hasKeyFeature(name);
	}
	
	public boolean hasKeyFeature(String name) {
		for(Feature f: keyFeatures) {
			if(f.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public int featureSize() {
		return this.features.size();
	}
	
	public void setFeature(String name, int index, Object value) {
		this.projection(name).set(index, value);
	}
	
	
	
	public Feature projection(String name) {
		for(Feature f: keyFeatures) {
			if(f.getName().equals(name)) {
				return f;
			}
		}
		for(Feature f: features) {
			if(f.getName().equals(name)) {
				return f;
			}
		}
		return null;
	}
	
	public void addRow(Object...objects) {
		int o = 0;
		for(int i = 0; i < this.features.size(); i++) {
			this.features.elementAt(i).add(objects[o++]);
		}
		this.size++;
	}
	
	public String toString() {
		String s = "";
		for(Feature f: features) {
			s += f.getName() + "\t";
		}
		s += "\n";
		for(int i = 0; i < this.size; i++) {
			for(Feature f: features) {
				if(f.isEmpty()) {
					s += "null\t";
				} else {
					s += f.elementAt(i).toString() + "\t";
				}
				
			}
			s += "\n";
		}
		return s;
	}

}
