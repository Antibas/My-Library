package com.antibas;
import com.antibas.math.Math2;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//		  Complex c = new Complex(1,2);
//		  System.out.println(c);
        for(int i = 0; i < 100; i++) {
            System.out.println(Math2.recaman(i));
        }
		  /*
		  Graph<Vertex, Edge> G = new Graph<>();
		  Vertex A = new Vertex("A");
		  Vertex B = new Vertex("B");
		  Vertex C = new Vertex("C");
		  Vertex D = new Vertex("D");

		  Edge a = new Edge("a", 7);
		  Edge b = new Edge("b", 12);
		  Edge c = new Edge("c");
		  Edge d = new Edge("d", 2);
		  Edge e = new Edge("e", 1);
		  G.putVertex(A, a, b);
		  G.putVertex(B, b);
		  G.putVertex(C, a, c, d);
		  G.putVertex(D, c, d);
		  G.putVertex(D, e);
		  System.out.println(G);
		  System.out.println(G.vertices());
		  System.out.println(G.edges());
		  System.out.println(G.isSelfLoopVertex(D));
		  System.out.println(G.incidentEdges(A));
		  System.out.println(G.endVertices(e));
		  System.out.println("A: "+G.incidentVertices(A));
		  System.out.println("B: "+G.incidentVertices(B));
		  System.out.println("C: "+G.incidentVertices(C));
		  System.out.println("D: "+G.incidentVertices(D));
		  //System.out.println(G.existsPath(A, D));
		  System.out.println(G);
		  //Graph.<Vertex, Edge>depthFirstSearch(G, A);
		  System.out.println(Tree.<Vertex, Edge>minimumSpanningTree(G));
		  System.out.println(G);*/
        //RadioByte byte1 = new RadioByte(16, 'c');
        //System.out.println(byte1);
        //StringIO.huffmanCoding("This document was typeset using the typographical look-and-feel");

		  /*AdjacencyGraph<Vertex, Edge> G1 = new AdjacencyGraph<>();
		  AdjacencyGraph<Vertex, Edge> G2 = new AdjacencyGraph<>();
		  Vertex A = new Vertex("A", 1);
		  Vertex B = new Vertex("B", 4);
		  Vertex C = new Vertex("C", 2);
		  Vertex D = new Vertex("D",7);
		  Vertex E = new Vertex("E",8);
		  Vertex F = new Vertex("F",3);
		  Vertex G = new Vertex("G",12);


		  Edge a = new Edge("a");
		  Edge b = new Edge("b");
		  Edge c = new Edge("c");
		  Edge d = new Edge("d");
		  Edge e = new Edge("e");
		  Edge f = new Edge("f");
		  Edge g = new Edge("g", true);

		  G1.putVertex(A, a, b);

		  G1.putVertex(B, a, c);
		  G1.putVertex(D, c);
		  G1.putVertex(E, d);

		  G2.putVertex(B, d);
		  G2.putVertex(C, b, e, f);
		  G2.putVertex(F, e);
		  G2.putVertex(G, f, g);

		  System.out.println(G1);
		  System.out.println(G2);
		  System.out.println();
		  AdjacencyGraph<Vertex, Edge> G3 = AdjacencyGraph.mergeGraphs(G1, G2);
		  System.out.println(G3.toConnectionsString());
		  //Graph<Vertex, Edge> G3 = new MatrixGraph<>(A, B, C, C, D);
		  //System.out.println(G3);
		  G1.putVertex(A, b);
		  G1.putVertex(B, b);

		  G2.putVertex(A, a);
		  G2.putVertex(C, a, c, d);
		  G2.putVertex(D, c, d);
		  G2.putVertex(D, e);
		  System.out.println(G1);
		  System.out.println(G2);
		  System.out.println(Graph.mergeGraphs(G1, G2));
		  Json json1 = new Json();
		  json1.put("int", 1);
		  json1.put("bool", true);
		  json1.put("str", "smothosaf");
		  json1.put("com", new Complex(6, 7));
		  Json json = new Json();

		  json.put("int", 1);
		  json.put("bool", true);
		  json.put("str", "smothosaf");
		  json.put("com", new Complex(6, 7));
		  json.put("copy", json1);

		  System.out.println(json);*/

		  /*Vector<Integer> ints = new Vector<>();
		  ints.add(0);
		  ints.add(1);
		  ints.add(7);
		  ints.add(100);

		  System.out.println(Methods.min(ints));*/
        //String str = "BCCABBDDAECCBBAEDDCC";
        //System.out.println(StringIO.huffmanEncoding(str));

    }
}