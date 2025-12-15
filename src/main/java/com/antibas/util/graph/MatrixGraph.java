package com.antibas.util.graph;

import com.antibas.math.matrix.template.Matrix;
import com.antibas.util.Methods;

import java.io.Serial;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class MatrixGraph<V extends Vertex, E extends Edge> implements Graph<V, E> {

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 2362187127030808446L;
	
	private final Matrix<E> edges;
	private final Vector<V> vertices;
	
	public MatrixGraph(Set<V> vertices) {
		this.vertices = new Vector<>(vertices);
		this.edges = new Matrix<>(vertices.size(), vertices.size(), null);
	}

	@SafeVarargs
    public MatrixGraph(V... vertices) {
		this(Methods.arrayToSet(vertices));
	}
	
	
	
	@Override
	public int compare(V o1, V o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<V> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setExploredVertex(V vertex, boolean explored) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setExploredVertex(String vertex, boolean explored) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isExploredVertex(V vertex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isExploredVertex(String vertex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<V> vertices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<E> edges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<E> putVertex(V vertex, Set<E> edges) {
		
		return null;
	}

	@Override
	public Set<E> putVertex(V vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<E> putVertex(V vertex, E... edges) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V putEdge(V vertex1, V vertex2, E edge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V putEdge(V vertex, E edge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeEdge(V vertex, E edge) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeEdge(E edge) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E getEdge(E edge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E getEdge(String edge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V getVertex(V vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V getVertex(String vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<E> incidentEdges(V vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<E> incidentEdges(String vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<V> incidentVertices(V vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<V> incidentVertices(String vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSelfLoopVertex(V vertex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSelfLoopVertex(String vertex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSelfLoopEdge(E edge) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSelfLoopEdge(String edge) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean areAdjacent(V vertex1, V vertex2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean areAdjacent(String vertex1, String vertex2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int degree(V vertex) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int degree(String vertex) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int connections(E edge) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int connections(String edge) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<V> endVertices(E edge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<V> endVertices(String edge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V oppositeVertex(V vertex, E edge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V oppositeVertex(String vertex, String edge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GraphIterator<V> iterator(V vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GraphIterator<V> dfsIterator(V vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GraphIterator<V> bfsIterator(V vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return this.toString(false);
	}

	@Override
	public String toString(boolean verbose) {
		// TODO Auto-generated method stub
		return edges + "\n" + vertices;
	}

	@Override
	public boolean hasVertex(V vertex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasVertex(String vertex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasEdge(V vertex, E edge) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasEdge(String vertex, String edge) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toConnectionsString(boolean verbose) {
		// TODO Auto-generated method stub
		return null;
	}

}
