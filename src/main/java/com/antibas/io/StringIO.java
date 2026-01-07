/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antibas.io;


import com.antibas.math.matrix.Matrix;
import com.antibas.util.CountMap;
import com.antibas.util.Methods;
import com.antibas.util.graph.Edge;
import com.antibas.util.graph.Vertex;
import com.antibas.util.graph.named.AdjacencyGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 *
 * @author User
 */
public abstract class StringIO {
	public static String clearNumerics(String s) {
		return s.replaceAll("[0-9]", "");
	}
	
	public static String clearAlpha(String s) {
		return s.replaceAll("[a-zA-Z]", "");
	}
	
	public static String clearSpecialChars(String s) {
//		String reg = "[=[]()!@#$%^&*;:\\\\'\\\"<>/{}\\\\]";
		return s.replaceAll("[=\\[\\]()!@#$%^&*;:\\\\'\"<>/{}]", "");
	}
	
    public static String LCS(String s1, String s2){
        char[] a1 = s1.toCharArray(), a2 = s2.toCharArray();
        Matrix L = new Matrix(a1.length+1, a2.length+1);
        StringBuilder a = new StringBuilder();
        L.fill(0);
        
        for(int i = 1; i < L.getRows(); i++){
            for(int j = 1; j < L.getColumns(); j++){
                if(a1[i-1] == a2[j-1]) L.set(i, j, L.elementAt(i-1, j-1) + 1);
                else L.set(i, j, Math.max(L.elementAt(i-1, j),L.elementAt(i, j-1)));
            }
        }
        int m = 0;
        for(int i = 1; i < L.getRows(); i++){
            for(int j = 1; j < L.getColumns(); j++){
                if((int)L.elementAt(i, j) > m && a1[i-1] == a2[j-1]){
                    a.append(a1[i - 1]);
                    m = (int) L.elementAt(i, j);
                }
            }
        }
        return a.toString();
    }
    
    public static CountMap<Character> getFrequencies(String string){
    	CountMap<Character> map = new CountMap<>();
    	for(char c: string.toCharArray()) {
    		if(map.containsKey(c)) {
    			map.increase(c);
    		} else {
    			map.put(c, 1);
    		}
    	}
    	return map;
    	
    }
    
    public static AdjacencyGraph huffmanEncoding(String string) {
    	CountMap<Character> map = StringIO.getFrequencies(string);//"This document was typeset using the typographical look-and-feel");

    	List<Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
    	list.sort(Entry.comparingByValue());
    	
    	AdjacencyGraph tree = new AdjacencyGraph();
    	List<Vertex> vertices = new ArrayList<>();
    	
    	for(Entry<Character, Integer> entry: list) {
    		vertices.add(new Vertex(entry.getKey()+"", entry.getValue()));
    	}
    	//System.out.println(vertices);
    	
    	while(vertices.size() > 1) {
    		Vertex min1 = Methods.min(vertices);
    		vertices.remove(min1);
    		Vertex min2 = Methods.min(vertices);
    		vertices.remove(min2);
    		
    		Vertex connector = new Vertex(min1.getName()+"_"+min2.getName(), min1.getCost()+min2.getCost());
    		vertices.add(connector);
    		
    		tree.putVertex(min1);
    		tree.putVertex(min2);
    		tree.putVertex(connector);
    		
    		Edge ez = new Edge(min1.getName().toLowerCase(), 0);
    		Edge eo = new Edge(min2.getName().toLowerCase(), 1);
    		tree.putEdge(min1, connector, ez);
    		tree.putEdge(min2, connector, eo);
    		//System.out.println(tree);
    		//System.out.println(vertices);
    	}
    	return tree;
    }
    
    public static String huffmanDecoding(AdjacencyGraph tree, String binary) {
		for(char bit: binary.toCharArray()) {
			if(bit == '0') {
				
			} else if(bit == '1') {
				
			} else {
				throw new IllegalArgumentException();
			}
		}
    	return null;
    	
    }
}
