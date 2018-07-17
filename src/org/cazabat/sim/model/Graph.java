package org.cazabat.sim.model;


import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Graph {
    private final List<Vertex> vertices;
    private final List<Edge> calculatedEdges;
    private final List<Edge> trueEdges;
    private final int size; 
    private boolean oriented;

    
    public Graph(List<Vertex> vertices, List<Edge> edges) {
    	 
    	
    	 
    	this.vertices=new ArrayList<Vertex>(vertices);
        this.size = vertices.size();
        this.oriented = false;
        this.trueEdges = new ArrayList<Edge>(edges);
        this.calculatedEdges = getGraphEdges(edges);
    }

    public Graph(List<Vertex> vertices, List<Edge> edges, boolean oriented) {
    	this.vertices=new ArrayList<Vertex>(vertices);
    	this.size = vertices.size();
    	this.oriented = oriented;
    	this.trueEdges = new ArrayList<Edge>(edges);
    	this.calculatedEdges = getGraphEdges(edges);
    }

    public int getSize() {
		return size;
	}

	public boolean isOriented() {
		return oriented;
	}

	private List<Edge> getGraphEdges(List<Edge> edges){
    	
    	List<Edge> graphEdge =new ArrayList<Edge>(edges);
    	if(oriented) return graphEdge;
    	
    	List<Edge> reciprocalEdge = new ArrayList<Edge>(); 
    	Iterator<Edge> it=graphEdge.iterator();
    	while(it.hasNext()){
    		Edge thisEdge = it.next();
    		reciprocalEdge.add(thisEdge.inverseEdge());
    	}
    	graphEdge.addAll(reciprocalEdge);
    	return graphEdge;
    }
    
    public List<Vertex> getVertices() {
        return vertices;
    }

   
    public List<Edge> getCalculatedEdges() {
        return calculatedEdges;
    }
    
    public List<Edge> getTrueEdges() {
        return trueEdges;
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
    	trueEdges.add(edge);
    	calculatedEdges.add(edge);
    	if(!this.oriented){
    		calculatedEdges.add(new Edge(edge.getDestination(), edge.getSource(), edge.getWeight()));
    	}
    }
    
    public boolean removeCalculatedEdge(Edge edge) {
    
    	Iterator<Edge> it=this.calculatedEdges.iterator();
    	while(it.hasNext()){
    		Edge thisEdge = it.next();
    		if(thisEdge.equals(edge)){
    			this.calculatedEdges.remove(thisEdge);
    			return true;
    		}
    	}
    	return false;
    }

  
    
    public List<Edge> successorVertex(Vertex node) {
    	List<Edge> result=new ArrayList<Edge>();
    	Iterator<Edge> itr=calculatedEdges.iterator();
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
    	Iterator<Edge> itr=calculatedEdges.iterator();
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

