package com.antibas.er;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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

    @Override
	public String toString() {
		return rightSide.toString() + " --> " + leftSide.toString();
	}
	
	
}
