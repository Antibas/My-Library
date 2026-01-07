package com.antibas.util.graph;

import com.antibas.util.Methods;
import com.antibas.util.pair.Pair;

import java.io.Serial;
import java.util.*;

public class AdjacencyGraph<V extends Vertex, E extends Edge> extends HashMap<V, Set<E>> implements Graph<V, E>{

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 7418938933885661991L;

	public AdjacencyGraph() {
		super();
	}

	public AdjacencyGraph(Map<? extends V, ? extends HashSet<E>> m) {
		super(m);
	}
	
	@SuppressWarnings("unchecked")
	public AdjacencyGraph(Pair<? extends V, ? extends E>... p) {
		super();
        for (Pair<? extends V, ? extends E> pair : p) {
            this.putVertex(pair.getFirst(), pair.getSecond());
        }
	}
	
	public AdjacencyGraph(AdjacencyGraph<V, E> g) {
		super();
		//AdjacencyGraph<V,E> gg = Methods.<AdjacencyGraph<V,E>>deepCopy(g);
		g.forEach((v, e) -> {
			this.putVertex(v, e);
		});
	}
	
	@Override
	public void setExploredVertex(V vertex, boolean explored) {
		this.getVertex(vertex).setExplored(explored);
	}
	
	@Override
	public void setExploredVertex(String vertex, boolean explored) {
		this.getVertex(vertex).setExplored(explored);
	}
	
	@Override
	public boolean isExploredVertex(V vertex) {
		return this.getVertex(vertex).isExplored();
	}
	
	@Override
	public boolean isExploredVertex(String vertex) {
		return this.getVertex(vertex).isExplored();
	}
	
	@Override
	public void reset() {
		this.forEach((v, e)->{
			v.reset();
			e.forEach(Edge::reset);
		});
	}
	
	@Override
	public Set<V> vertices(){
		return this.keySet();
	}
	
	@Override
	public Set<E> edges(){
		HashSet<E> set = new HashSet<>();
		for(V v: vertices()) {
			set.addAll(this.incidentEdges(v.getName()));
		}
		return set;
	}
	
	@Override
	@Deprecated
	public Set<E> put(V vertex, Set<E> edges) {
		return this.putVertex(vertex, edges);
	}
	
	@Override
	public Set<E> putVertex(V vertex, Set<E> edges) {
		if(!this.containsKey(vertex)) {
			return super.put(vertex, edges);
		}
		for(E e: edges) {
			incidentEdges(vertex).add(e);
			if(this.connections(e) > 2 || (this.connections(e) > 1 && e.isSelfLoop())) {
				incidentEdges(vertex).remove(e);
			}
		}
		
		return incidentEdges(vertex);
	}
	
	
	@Override
	public Set<E> putVertex(V vertex) {
		return this.putVertex(vertex, new HashSet<>());
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Set<E> putVertex(V vertex, E... edges) {
		return this.putVertex(vertex, Methods.arrayToSet(edges));
	}
	
	@Override
	public void putAll(Map<? extends V, ? extends Set<E>> m) {
		m.forEach(this::putVertex);
	}
	
	@Override
	public V putEdge(V vertex1, V vertex2, E edge) {
		putEdge(vertex1, edge);
		putEdge(vertex2, edge);
		return this.getVertex(vertex1);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public V putEdge(V vertex, E edge) {
		this.putVertex(vertex, edge);
		return this.getVertex(vertex);
	}
	
	@Override
	public boolean removeEdge(V vertex, E edge) {
		return incidentEdges(vertex).remove(edge);
	}
	
	@Override
	public boolean removeEdge(E edge) {
		this.forEach((v, e) -> {
			this.removeEdge(v, edge);
		});
		return true;
	}

	@Override
	public V putEdge(String vertex1, String vertex2, E edge) {
		return this.putEdge((V) new Vertex(vertex1), (V) new Vertex(vertex2), edge);
	}

	@Override
	public V putEdge(String vertex, E edge) {
		return this.putEdge((V) new Vertex(vertex), edge);
	}

	@Override
	public boolean removeEdge(String vertex, E edge) {
		return this.removeEdge((V) new Vertex(vertex), edge);
	}

	@Override
	public boolean removeEdge(String edge) {
		return this.removeEdge((E) new Edge(edge));
	}

	@Override
	public boolean hasVertex(V vertex) {
		return this.hasVertex(vertex.getName());
	}

	@Override
	public boolean hasVertex(String vertex) {
		return this.containsKey(vertex);
	}

	@Override
	public boolean hasEdge(V vertex, E edge) {
		return this.hasEdge(vertex.getName(), edge.getName());
	}

	@Override
	public boolean hasEdge(String vertex, String edge) {
		for(E e: this.incidentEdges(vertex)) {
			if(e.getName().equals(edge)) {
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public Set<E> get(Object key) {
//		if(key instanceof String str) {
//			for(V v: this.keySet()) {
//				if(v.getName().equals(str)) {
//					return super.get(v);
//				}
//			}
//		}
//		return super.get(key);
		return (key instanceof String str)?
				super.get(
					this.keySet()
					.stream()
					.filter(e -> e.getName().equals(str))
					.findFirst()
					.orElse(null)
				)
				: super.get(key);
	}
	
	@Override
	public E getEdge(E edge) {
		return this.getEdge(edge.getName());
	}
	
	public E getEdge(String edge) {
//		for(E e: this.edges()) {
//			if(e.getName().equals(edge)) {
//				return e;
//			}
//		}
//		return null;
		return this.edges()
				.stream()
				.filter(e -> e.getName().equals(edge))
				.findFirst()
				.orElse(null);
	}
	
	@Override
	public V getVertex(V vertex) {
		return this.getVertex(vertex.getName());
	}
	
	public V getVertex(String vertex) {
		for(V v: this.keySet()) {
			if(v.getName().equals(vertex)) {
				return v;
			}
		}
		return null;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public Set<E> incidentEdges(String vertex) {
		return this.get(vertex);
	}
	
	@Override
	public Set<E> incidentEdges(V vertex) {
		return incidentEdges(vertex.getName());
	}
	
	public Set<V> incidentVertices(String vertex){
		return this.incidentVertices(this.getVertex(vertex));
	}
	
	@Override
	public Set<V> incidentVertices(V vertex) {
		Set<V> vertices = new HashSet<>();
		for(E e: incidentEdges(vertex)) {
			List<V> end = this.endVertices(e);
			if(end.size() != 1) {
				end.remove(vertex);
			}
			vertices.addAll(end);
		}
		return vertices;
	}
	
	@Override
	public boolean areAdjacent(V vertex1, V vertex2) {
		return this.areAdjacent(vertex1.getName(), vertex2.getName());
	}
	
	public boolean areAdjacent(String vertex1, String vertex2) {
		for(E v1: incidentEdges(vertex1)) {
			for(E v2: incidentEdges(vertex2)) {
				if(v1.equals(v2)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean areParallel(String edge1, String edge2) {
		return this.endVertices(edge1).equals(this.endVertices(edge2));
	}
	
	@Override
	public boolean isSelfLoopVertex(V vertex) {
		return this.isSelfLoopVertex(vertex.getName());
	}
	
	public boolean isSelfLoopVertex(String vertex) {
		for(E e: incidentEdges(vertex)) {
			if(isSelfLoopEdge(e.getName())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean isSelfLoopEdge(E edge) {
		return this.isSelfLoopEdge(edge.getName());
	}
	
	public boolean isSelfLoopEdge(String edge) {
		return this.getEdge(edge).isSelfLoop();
	}
	
	public boolean existsPath(V from, V to) {
		return this.existsPath(from.getName(), to.getName());
	}
	
	public boolean existsPath(String from, String to) {
		if(this.areAdjacent(from, to)) {
			this.setExploredVertex(from, true);
			this.setExploredVertex(to, true);
			return true;
		}
		
		System.out.println(this.incidentVertices(from));
		for(V v: this.incidentVertices(from)) {
			if(!this.isExploredVertex(v) && this.existsPath(v.getName(), to)){
			//if(existsPath(v.getName(), to)){
				this.setExploredVertex(v, true);
				return true;
			}
			//this.setExploredVertex(v, true);
		}
		return false;
	}
	
	public List<V> path(V from, V to){
		return path(from.getName(), to.getName());
	}
	
	public List<V> path(String from, String to){
		List<V> ret = new ArrayList<>();
		if(this.areAdjacent(from, to)) {
			ret.add(this.getVertex(from));
			ret.add(this.getVertex(to));
			return ret;
		}
		for(E e: this.incidentEdges(from)) {
			
		}
		return ret;
	}
	
	@Override
	public boolean containsKey(Object key) {
		if(key instanceof String str) {
			for(V v: vertices()) {
				if(v.getName().equals(str)) {
					return true;
				}
			}
			return false;
		}
		return super.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		if(value instanceof String str) {
			for(V v: vertices()) {
				for(E e: incidentEdges(v)) {
					if(e.getName().equals(str)) {
						return true;
					}
				}
			}
			return false;
		}
		return super.containsValue(value);
	}

	@Override
	public int degree(String vertex) {
		return incidentEdges(vertex).size();
	}
	
	@Override
	public int degree(V vertex) {
		return incidentEdges(vertex).size();
	}
	
	@Override
	public int connections(E edge) {
		return connections(edge.getName());
	}
	
	public int connections(String edge) {
		int c = 0;
		for(V v: vertices()) {
			for(E e: incidentEdges(v.getName())) {
				if(e.getName().equals(edge)) {
					c++;
				}
			}
		}
		return c;
	}
	
	@Override
	public List<V> endVertices(E edge){
		return this.endVertices(edge.getName());
	}
	
	public List<V> endVertices(String edge) {
		List<V> vertices = new ArrayList<>();
		for(V v: vertices()) {
			for(E e: incidentEdges(v.getName())) {
				if(e.getName().equals(edge)) {
					vertices.add(v);
				}
			}
		}
		
		/*if(connections(edge) == 2) {
			return new Pair<>(vertices.elementAt(0), vertices.elementAt(1));
		}
		
		if(connections(edge) == 1) {
			return new Pair<>(vertices.elementAt(0), vertices.elementAt(0));
		}
		
		return null;*/
		return vertices;
	}
	
	@Override
	public V oppositeVertex(V vertex, E edge) {
		return this.oppositeVertex(vertex.getName(), edge.getName());
	}
	
	public V oppositeVertex(String vertex, String edge) {
		List<V> p = this.endVertices(edge);
		if(p.size() == 1) {
			if(this.isSelfLoopEdge(edge)) {
				return this.getVertex(vertex);
			}
			return null;
		}
		
		if(this.getVertex(vertex).equals(p.get(0))) {
			return p.get(1);
		}
		
		if(this.getVertex(vertex).equals(p.get(1))) {
			return p.getFirst();
		}
		return null;
	}
	
	@Override
	public String toString() {
		return this.toString(false);
	}
	
	public String toString(boolean verbose) {
		StringBuilder str = new StringBuilder();
		
		for(V v: vertices()) {
			str.append(v.toString(verbose)).append(" => [");
			for(E e: this.incidentEdges(v.getName())) {
				str.append(e.toString(verbose)).append(", ");
			}
			str.delete(str.length()-2, str.length());
			str.append("]\n");
			   //.append(t).append("\n");
		}
		
		return str.toString();
	}

	@Override
	public String toConnectionsString(boolean verbose) {
		StringBuilder str = new StringBuilder();
		
		for(V v: vertices()) {
			for(V w: this.incidentVertices(v)) {
				str.append(v.toString(verbose)).append(" <=> ")
				.append(w.toString(verbose)).append("\n");
			}
		}
		return str.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof AdjacencyGraph<?, ?> objnew) {
			return this.vertices().equals(objnew.vertices()) &&
				   this.edges().equals(objnew.edges());
		}
		return false;
		
	}

	@Override
	public int compare(V o1, V o2) {
		return o1.compareTo(o2);
	}
	

	@Override
	public GraphIterator<V> iterator() {
		return new GraphDFSIterator<>(this, this.vertices().iterator().next());
	}
	
	@Override
	public GraphIterator<V> iterator(V vertex) {
		return new GraphDFSIterator<>(this, vertex);
	}
	
	@Override
	public GraphIterator<V> dfsIterator(V vertex) {
		return new GraphDFSIterator<>(this, vertex);
	}
	
	@Override
	public GraphIterator<V> bfsIterator(V vertex) {
		return new GraphBFSIterator<>(this, vertex);
	}
	
	
	public static <V extends Vertex, E extends Edge> AdjacencyGraph<V, E> mergeGraphs(AdjacencyGraph<V, E> graph1, AdjacencyGraph<V, E> graph2){
		AdjacencyGraph<V, E> graph = new AdjacencyGraph<>(graph1);
		graph2.forEach((v, e) -> {
			//System.out.println(v + ": "+graph1.containsKey(v));
			graph.putVertex(v, e);
		});
		return graph;
	}
	
	public static <V extends Vertex, E extends Edge> AdjacencyGraph<V, E> depthFirstSearch(AdjacencyGraph<V, E> graph, V vertex){
		graph.reset();
		return (AdjacencyGraph<V, E>) Graph.depthFirstSearch(new AdjacencyGraph<>(graph), vertex.getName());
	}
	
	public static <V extends Vertex, E extends Edge> AdjacencyGraph<V, E> depthFirstSearch(AdjacencyGraph<V, E> graph) {
		//graph.reset();
		return AdjacencyGraph.<V,E>depthFirstSearch(new AdjacencyGraph<>(graph), graph.vertices().iterator().next());
		
	}
	
	public static <V extends Vertex, E extends Edge> Queue<V> depthFirstSearchPath(AdjacencyGraph<V, E> graph){
		return Graph.depthFirstSearchPath(new AdjacencyGraph<>(graph), graph.vertices().iterator().next());
	}
	
	public static <V extends Vertex, E extends Edge> AdjacencyGraph<V, E> breadthFirstSearch(AdjacencyGraph<V, E> graph, V vertex){
		return (AdjacencyGraph<V, E>) Graph.breadthFirstSearch(new AdjacencyGraph<>(graph), vertex.getName());
	}
	
	public static <V extends Vertex, E extends Edge> Queue<V> breadthFirstSearchPath(AdjacencyGraph<V, E> graph){
		return Graph.breadthFirstSearchPath(new AdjacencyGraph<>(graph), graph.vertices().iterator().next());
	}
}
