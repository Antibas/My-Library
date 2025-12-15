package com.antibas.util.graph;

import com.antibas.util.pair.Pair;

import java.io.Serial;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DirectedAdjacencyGraph<V extends Vertex, E extends DirectedEdge> extends AdjacencyGraph<V, E> {
	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = -5503984591246792910L;

	public DirectedAdjacencyGraph() {
		super();
	}

	public DirectedAdjacencyGraph(AdjacencyGraph<V, E> g) {
		super(g);
	}

	public DirectedAdjacencyGraph(Map<? extends V, ? extends HashSet<E>> m) {
		super(m);
	}

	@SafeVarargs
    public DirectedAdjacencyGraph(Pair<? extends V, ? extends E>... p) {
		super(p);
	}

	@Override
	public Set<E> incidentEdges(String vertex) {
		Set<E> inc = super.incidentEdges(vertex);
        inc.removeIf(e -> e.getDirection() == Direction.FROM);
		return inc;
	}
}
