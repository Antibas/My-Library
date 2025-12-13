package com.antibas.util.graph;

import com.antibas.util.Methods;
import com.antibas.util.pair.Pair;

import java.io.Serial;
import java.util.Map;
import java.util.Set;

public class AdjacencyTree<V extends Vertex, E extends Edge> extends AdjacencyGraph<V, E> {

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;
	
	

	public AdjacencyTree() {
		super();
	}

	public AdjacencyTree(Pair<? extends V, ? extends E>... p) {
		super(p);
	}
	
	public AdjacencyTree(AdjacencyGraph<V, E> g) {
		super();
		AdjacencyGraph<V,E> gg = Methods.<AdjacencyGraph<V,E>>deepCopy(g);
		gg.forEach((v, e) -> {
			this.putVertex(v, e);
		});
	}
	
	public AdjacencyTree(AdjacencyTree<V, E> g) {
		super(g);
	}
	
	private boolean validate() {
		AdjacencyGraph<V, E> T = AdjacencyGraph.<V, E>depthFirstSearch(this);
		System.out.println(T);
		//T.reset();
		for(E e: T.edges()) {
			if(e.isBackEdge()) {
				return false;
			}
		}
		return true;
	}
	
	public static <V extends Vertex, E extends Edge> AdjacencyTree<V, E> minimumSpanningTree(AdjacencyGraph<V, E> G) {
		AdjacencyGraph<V, E> T = AdjacencyGraph.<V, E>depthFirstSearch(G);
		for(E e: T.edges()) {
			if(!e.isDiscovery()) {
				T.removeEdge(e);
			}
		}
		return new AdjacencyTree<>(T);
	}

	@Override
	public Set<E> put(V vertex, Set<E> edges) {
		return this.putVertex(vertex, edges);
	}

	@Override
	public Set<E> putVertex(V vertex, Set<E> edges) {
		Set<E> ret = super.putVertex(vertex, edges);
		if(!validate()) {
			throw new InvalidTreeException(vertex.toString());
		}
		return ret;
	}

	@Override
	public Set<E> putVertex(V vertex) {
		Set<E> ret = super.putVertex(vertex);
		if(!validate()) {
			throw new InvalidTreeException(vertex.toString());
		}
		return ret;
	}

	@Override
	public Set<E> putVertex(V vertex, E... edges) {
		if(!validate()) {
			throw new InvalidTreeException(vertex.toString());
		}
		return super.putVertex(vertex, edges);
	}

	@Override
	public void putAll(Map<? extends V, ? extends Set<E>> m) {
		super.putAll(m);
		if(!validate()) {
			throw new InvalidTreeException();
		}
	}

	@Override
	public V putEdge(V vertex1, V vertex2, E edge) {
		V ret = super.putEdge(vertex1, vertex2, edge);
		if(!validate()) {
			throw new InvalidTreeException(vertex1.toString());
		}
		return ret;
	}

	@Override
	public V putEdge(V vertex, E edge) {
		V ret = super.putEdge(vertex, edge);
		if(!validate()) {
			throw new InvalidTreeException(vertex.toString());
		}
		return ret;
	}
	
	public boolean isLeaf(String vertex) {
		return this.incidentEdges(vertex).size() <= 1;
	}
	
	public boolean isLeaf(V vertex) {
		return this.isLeaf(vertex.getName());
	}
	
	public int level(String vertex) {
		return 0;
	}
}
