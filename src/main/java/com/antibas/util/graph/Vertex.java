package com.antibas.util.graph;


import com.antibas.util.Nameable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Setter
@Getter
public class Vertex implements Nameable, Comparable<Vertex>{
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;
	
	private String name;
    private boolean isExplored;
	private double cost;
	
	public Vertex(String name, double cost) {
		this.name = name;
		this.isExplored = false;
		this.cost = cost;
	}
	
	public Vertex(String name) {
		this.isExplored = false;
		if(name.indexOf(':') != -1) {
			String[] tmp = name.split(":");
			this.name = tmp[0];
			this.cost = Double.parseDouble(tmp[1]);
		} else {
			this.name = name;
			this.cost = 0;
		}
	}

	public void reset() {
		this.isExplored = false;
	}

	@Override
	public String toString() {
		return this.toString(false);
	}
	
	public String toString(boolean verbose) {
		if(verbose) {
			return name + ":"+cost+ "-> (" + isExplored + ")";
		}
		return name + ((cost == 0)?"":":"+cost);
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Vertex) {
			return ((Vertex)obj).name.equals(name);
		}
		
		if(obj instanceof String) {
			return ((String)obj).equals(name);
		}
		
		return false;
	}

	@Override
	public int compareTo(Vertex o) {
		return Double.compare(cost, o.cost);
	}
}
