package com.antibas.util.graph;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Setter
@Getter
public class DirectedEdge extends Edge {
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -2568313332093812420L;
	
	private Direction direction;
	
	public DirectedEdge(String name, double cost, Direction direction) {
		super(name, cost);
		this.direction = direction;
	}
	
	public DirectedEdge(String name, double cost) {
		this(name, cost, Direction.TO);
	}

	public DirectedEdge(String name, Direction direction) {
		this(name, 0, direction);
	}

	public DirectedEdge(String name) {
		this(name, 0, Direction.TO);
	}

    @Override
	public String toString() {
		return super.toString() + ": " + direction.toString().toLowerCase();
	}

}
