package com.antibas.util.graph;

import com.antibas.util.Nameable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
public class Edge implements Nameable, Comparable<Edge>{
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 8240888198950683767L;
	
	private String name;
	private boolean isExplored;
	private boolean isDiscovery;
	private boolean isBackEdge;
    private final boolean isSelfLoop;
	private double cost;
	
	public Edge(String name, double cost, boolean isSelfLoop) {
		this.name = name;
		this.isExplored = false;
		this.isDiscovery = false;
		this.isBackEdge = false;
		this.isSelfLoop = isSelfLoop;
		this.cost = cost;
	}

	public Edge(String name, double cost) {
		this(name, cost, false);
	}
	
	public Edge(String name, boolean isSelfLoop) {
		this.isExplored = false;
		this.isDiscovery = false;
		this.isBackEdge = false;
		this.isSelfLoop = isSelfLoop;
		if(name.indexOf(':') != -1) {
			String[] tmp = name.split(":");
            this.name = tmp[0];
			this.cost = Double.parseDouble(tmp[1]);
		} else {
			this.name = name;
			this.cost = 0;
		}
	}

	public Edge(String name) {
		this(name, false);
	}

	public void reset() {
		this.isExplored = false;
		this.isDiscovery = false;
		this.isBackEdge = false;
	}

	@Override
	public String toString() {
		return this.toString(false);
	}
	
	public String toString(boolean verbose) {
		if(verbose) {
			return name + ":" + cost + "-> (" + isExplored + ", " + isDiscovery + ", " + isBackEdge + ")";
		}
		return name + ((cost == 0)?"":":"+cost);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Edge) {
			return ((Edge)obj).name.equals(name);
		}
		
		if(obj instanceof String) {
			return ((String)obj).equals(name);
		}
		
		return false;
	}

	@Override
	public int compareTo(Edge o) {
		return Double.compare(cost, o.cost);
	}
	
}
