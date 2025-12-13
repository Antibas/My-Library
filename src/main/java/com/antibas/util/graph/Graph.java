package com.antibas.util.graph;

import java.io.Serializable;
import java.util.*;

public interface Graph<V extends Vertex, E extends Edge> extends Comparator<V>, Serializable, Iterable<V>{
	void setExploredVertex(V vertex, boolean explored);
	void setExploredVertex(String vertex, boolean explored);
	boolean isExploredVertex(V vertex);
	boolean isExploredVertex(String vertex);
	void reset();
	/**
	 * 
	 * @return all vertices of the graph
	 */
	Set<V> vertices();
	/**
	 * 
	 * @return all distinct edges of the graph
	 */
	Set<E> edges();
	/**
	 * Puts a vertex containing all given edges. If the
	 * vertex already exists, all edges in the set that are not already
	 * connected with two existing vertices are merged 
	 * into the existing edges.
	 * @param vertex
	 * @param edges
	 * @return all incident edges to vertex of the graph
	 */
	Set<E> putVertex(V vertex, Set<E> edges);
	/**
	 * Puts a vertex with no edges. If the
	 * vertex already exists, all edges in the set that are not already
	 * connected with two existing vertices are merged 
	 * into the existing edges.
	 * @param vertex
	 * @return all incident edges to vertex of the graph
	 */
	Set<E> putVertex(V vertex);
	/**
	 * Puts a vertex containing all given edges. If the
	 * vertex already exists, all edges in the set that are not already
	 * connected with two existing vertices are merged 
	 * into the existing edges set.
	 * @param vertex
	 * @param edges
	 * @return all incident edges to vertex of the graph
	 */
	Set<E> putVertex(V vertex, E... edges);
	/**
	 * Puts an edge connecting two vertices.
	 * @param vertex1
	 * @param vertex2
	 * @param edge
	 * @return the first vertex
	 */
	V putEdge(V vertex1, V vertex2, E edge);
	/**
	 * Puts an edge connecting one vertex. If the
	 * vertex already exists and the edge is not already
	 * connected with two existing vertices, it is merged with
	 * the vertex's edge set.
	 * @param vertex
	 * @param edge
	 * @return the vertex
	 */
	V putEdge(V vertex, E edge);
	/**
	 * Removes an edge from a specific vertex
	 * @param vertex
	 * @param edge
	 * @return true if successfully removed
	 */
	boolean removeEdge(V vertex, E edge);
	/**
	 * Removes an edge from all vertices
	 * @param edge
	 * @return true
	 */
	boolean removeEdge(E edge);
	E getEdge(E edge);
	E getEdge(String edge);
	V getVertex(V vertex);
	V getVertex(String vertex);
	boolean hasVertex(V vertex);
	boolean hasVertex(String vertex);
	boolean hasEdge(V vertex, E edge);
	boolean hasEdge(String vertex, String edge);
	Set<E> incidentEdges(V vertex);
	Set<E> incidentEdges(String vertex);
	Set<V> incidentVertices(V vertex);
	Set<V> incidentVertices(String vertex);
	boolean isSelfLoopVertex(V vertex);
	boolean isSelfLoopVertex(String vertex);
	boolean isSelfLoopEdge(E edge);
	boolean isSelfLoopEdge(String edge);
	boolean areAdjacent(V vertex1, V vertex2);
	boolean areAdjacent(String vertex1, String vertex2);
	int degree(V vertex);
	int degree(String vertex);
	int connections(E edge);
	int connections(String edge);
	List<V> endVertices(E edge);
	List<V> endVertices(String edge);
	V oppositeVertex(V vertex, E edge);
	V oppositeVertex(String vertex, String edge);
	GraphIterator<V> iterator(V vertex);
	GraphIterator<V> dfsIterator(V vertex);
	GraphIterator<V> bfsIterator(V vertex);
	String toString(boolean verbose);
	String toConnectionsString(boolean verbose);
	default String toConnectionsString() {
		return this.toConnectionsString(false);
	}
	static <V extends Vertex, E extends Edge> Graph<V, E> depthFirstSearch(Graph<V, E> graph, String vertex) {
		
		if(!graph.hasVertex(vertex)) {
			throw new IllegalArgumentException(vertex);
		}
		//graph.reset();
		//graph.getVertex(vertex).setExplored(true);
		graph.setExploredVertex(vertex, true);
		//System.out.println("vertex: "+vertex);
		for(E e: graph.incidentEdges(vertex)) {
			//System.out.println("e: "+e);
			if(!e.isExplored()) {
				V w = graph.oppositeVertex(vertex, e.getName());
				//System.out.println("w: "+w);
				if(w == null) {
					e.setExplored(true);
					continue;
				}
				if(!w.isExplored()) {
					e.setDiscovery(true);
					graph = Graph.<V, E>depthFirstSearch(graph, w.getName());
				}
				else {
					e.setBackEdge(true);
				}
				e.setExplored(true);
			}
		}
		return graph;
	}
	
	static <V extends Vertex, E extends Edge> Queue<V> depthFirstSearchPath(Graph<V, E> graph, V vertex){
		Queue<V> vertices = new ArrayDeque<>();
		graph.setExploredVertex(vertex, true);
		vertices.add(vertex);
		//System.out.println(vertex);
		for(E e: graph.incidentEdges(vertex)) {
			//System.out.println(e);
			if(!e.isExplored()) {
				V w = graph.oppositeVertex(vertex, e);
				//System.out.println(w);
				if(w == null) {
					continue;
				}
				if(!w.isExplored()) {
					//graph = Graph.<V, E>depthFirstSearch(graph, w.getName());
					vertices.addAll(depthFirstSearchPath(graph, w));
				}
				e.setExplored(true);
			}
		}
		return vertices;
	}
	static <V extends Vertex, E extends Edge> Graph<V, E> breadthFirstSearch(Graph<V, E> graph, String vertex) {
		Vector<ArrayList<V>> lists = new Vector<>();
		
		graph.setExploredVertex(vertex, true);
		lists.add(new ArrayList<>());
		lists.getFirst().add(graph.getVertex(vertex));
		
		int list_p = 0;
		while(!lists.get(list_p).isEmpty()) {
			lists.add(new ArrayList<>());
			for(V v: lists.get(list_p)) {
				for(E e: graph.incidentEdges(v)) {
					V w = graph.oppositeVertex(v, e);
					if(!e.isExplored()) {
						if(!w.isExplored()) {
							e.setDiscovery(true);
							graph.setExploredVertex(w, true);
							lists.get(list_p+1).add(graph.getVertex(w));
						} else {
							e.setBackEdge(true);
						}
					}
				}
			}
			list_p++;
		}
		return graph;
	}
	static <V extends Vertex, E extends Edge> Queue<V> breadthFirstSearchPath(Graph<V, E> graph, V vertex) {
		Vector<ArrayList<V>> lists = new Vector<>();
		Queue<V> vertices = new ArrayDeque<>();
		
		graph.setExploredVertex(vertex, true);
		vertices.add(vertex);
		lists.add(new ArrayList<>());
		lists.getFirst().add(graph.getVertex(vertex));

		int list_p = 0;
		while(!lists.get(list_p).isEmpty()) {
			lists.add(new ArrayList<>());
			for(V v: lists.get(list_p)) {
				for(E e: graph.incidentEdges(v)) {
					V w = graph.oppositeVertex(v, e);
					if(!e.isExplored()) {
						if(!w.isExplored()) {
							e.setDiscovery(true);
							graph.setExploredVertex(w, true);
							vertices.add(w);
							lists.get(list_p+1).add(graph.getVertex(w));
						}
					}
				}
			}
			list_p++;
		}
		return vertices;
	}
	abstract class GraphIterator<V extends Vertex> implements Iterator<V>{
		protected Queue<V> vertices;
		
		@Override
		public boolean hasNext() {
			return !vertices.isEmpty();
		}

		@Override
		public V next() {
			return vertices.poll();
		}

	}
	class GraphDFSIterator<V extends Vertex, E extends Edge> extends GraphIterator<V>{
		public GraphDFSIterator(Graph<V, E> graph, V vertex) {
			vertices = Graph.depthFirstSearchPath(graph, vertex);
		}
	}

	class GraphBFSIterator<V extends Vertex, E extends Edge> extends GraphIterator<V>{
		public GraphBFSIterator(Graph<V, E> graph, V vertex) {
			vertices = Graph.breadthFirstSearchPath(graph, vertex);
		}
	}
}
