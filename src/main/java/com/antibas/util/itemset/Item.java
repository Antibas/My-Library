package com.antibas.util.itemset;

public class Item {
	private double weight;
	private double benefit;
	
	public Item(double weight, double benefit) {
		this.weight = weight;
		this.benefit = benefit;
	}
	
	public Item() {
		this(0, 0);
	}

    public double getValue() {
		if(weight == 0) return 0;
		return benefit / weight;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getBenefit() {
		return benefit;
	}

	public void setBenefit(double benefit) {
		this.benefit = benefit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
        result = prime * result + Double.hashCode(benefit);
        result = prime * result + Double.hashCode(weight);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Item itm) {
            return itm.weight == this.weight && itm.benefit == this.benefit;
		}
		return false;
	}

	@Override
	public String toString() {
		return "[" + weight + ", " + benefit + "]";
	}
}
