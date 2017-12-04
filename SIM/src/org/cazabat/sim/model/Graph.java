package org.cazabat.sim.model;


import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Graph {
    private final List<Vertex> vertices;
    private final List<Edge> edges;
    private final int size; 

    public Graph(List<Vertex> vertices, List<Edge> edges) {
    	this.vertices=new ArrayList<Vertex>(vertices);
    	this.edges=new ArrayList<Edge>(edges);
        this.size = vertices.size();
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }
    
    public int size() {
    	return this.size;
    }
    
    public Vertex getVertex(int indice) {
    	return this.vertices.get(indice);
    }

    public void addVertex(Vertex vertex) {
    	vertices.add(vertex);
    }

    public void addEdge(Edge edge) {
    	edges.add(edge);
    }

    public boolean addPath(Vertex from, Vertex to, float weight) {
    	// on compare ici les objets from et to, il faudrait comparer
    	// le numero si on clonait les Node
	if (!edges.contains(from) || !edges.contains(to))
		return false;
	
	addEdge(new Edge(from, to, weight));
	return true;
	
    }
    
    public List<Edge> successorVertex(Vertex node) {
    	List<Edge> result=new ArrayList<Edge>();
    	Iterator<Edge> itr=edges.iterator();
    	Edge val;
    	
    	while(itr.hasNext())
    	{
    		val=itr.next();
    		if(val.source.equals(node)) {
    			result.add(val);
    			
    		}
    	}
  
    	return result;
    }
    public List<Edge> predecessorVertex(Vertex node) {
    	List<Edge> result=new ArrayList<Edge>();
    	Iterator<Edge> itr=edges.iterator();
    	Edge val;
    	
    	while(itr.hasNext())
    	{
    		val=itr.next();
    		if(val.destination.equals(node)) {
    			result.add(val);
    			
    		}
    	}
  
    	return result;
    }
}

