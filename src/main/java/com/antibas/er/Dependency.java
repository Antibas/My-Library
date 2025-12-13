package com.antibas.er;

import java.util.HashSet;
import java.util.Set;

public class Dependency {
	private Set<Feature> rightSide;
	private Set<Feature> leftSide;
	
	public Dependency(Set<Feature> rightSide, Set<Feature> leftSide) {
		this.rightSide = rightSide;
		this.leftSide = leftSide;
	}
	
	public Dependency() {
		this.rightSide = new HashSet<>();
		this.leftSide = new HashSet<>();
	}

	public Set<Feature> getRightSide() {
		return rightSide;
	}

	public void setRightSide(Set<Feature> rightSide) {
		this.rightSide = rightSide;
	}

	public Set<Feature> getLeftSide() {
		return leftSide;
	}

	public void setLeftSide(Set<Feature> leftSide) {
		this.leftSide = leftSide;
	}

	@Override
	public String toString() {
		return rightSide.toString() + " --> " + leftSide.toString();
	}
	
	
}
