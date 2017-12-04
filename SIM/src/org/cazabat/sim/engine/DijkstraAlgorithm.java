package org.cazabat.sim.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.cazabat.sim.Constant;
import org.cazabat.sim.model.*;


	
public class DijkstraAlgorithm {

	private Map<Vertex,DistancedEdge> settledNodes;
	private Map<Vertex,DistancedEdge> unSettledNodes;
	private Boolean gotIt;
	private Vertex actualVertex; 
	private float actualDistance;
   
  
    
    private Graph graph;

    public DijkstraAlgorithm(Graph graph) {
        
    	this.graph = graph;
    	settledNodes = new HashMap<Vertex,DistancedEdge>();
		unSettledNodes = new HashMap<Vertex,DistancedEdge>();
		gotIt = false;
		actualDistance = (float)0;
    }

    public void execute(Vertex source, Vertex destination) {
    	  
        
    // Initialization, distance of source is null, all others is undefined
		
    	for(int i=0; i<this.graph.size(); i++) {
    		Vertex vertex = this.graph.getVertex(i);
    		if(vertex.equal(source)) {
    		
    			settledNodes.put(source, new DistancedEdge(new Edge(null, source),actualDistance));
    		}
    		else {
    			
    		unSettledNodes.put(vertex,new DistancedEdge(null,(float)Constant.UNDEFINED));
    		}
    	}
        
 // Ok, we run it 
       
		while (!gotIt && unSettledNodes.size() > 0) {
			
            DistancedEdge newEdge = (unSettledNodes);
            Vertex target = newEdge.getDestination();
            float newDistance= newEdge.getWeight();
            
            actualDistance = actualDistance + newDistance;
            settledNodes.put(target,newEdge);
            unSettledNodes.remove(target);    
       }
    }

    private float findMinimalDistances(Vertex node) {
        List<Edge> adjacentNodes = getNeighbors(node);
        for (Edge target : adjacentNodes) {
            if (target.getWeight() > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

  

    private List<Edge> getNeighbors(Vertex node) {
    	List<Edge> result=new ArrayList<Edge>();
        List<Edge> neighbors = graph.successorVertex(node);
        for (Edge edge : neighbors) {
                   if( !isSettled(edge.getDestination())) {
                result.add(edge);
            }
        }
        return result;
    }

    private Edge getMinimum(Map<Edge,Float> search) {
        Float minimum= (float)0;
        Edge ret=null;
        Set<Edge> edges = search.keySet();
        Iterator<Edge> it = edges.iterator();
        
        while (it.hasNext()){
        	Edge val=it.next();
        	if (minimum == null) {
                minimum = val.getWeight();
                ret=val;
        	}
        	else
        	{
        		if (val.getWeight()<minimum)
        			{
        					minimum=val.getWeight();
        					ret=val;
        					
        			}
        	}
        	
          
        }
        return ret;
        		
    }

    private boolean isSettled(Vertex vertex) {
        return settledNodes.contains(vertex);
    }

    private Float getShortestDistance(Vertex destination) {
        Float d = distance.get(destination);
        if (d == null) {
            return (float)Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<Vertex> getPath(Vertex target) {
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        Vertex step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

}

class DistancedEdge {
	private Edge edge;
	private Float distance ;
	
	// Distance is from the source, i.e from the last settledNode
	
	DistancedEdge(Edge edge, Float distance) {
	   this.edge = edge;
	   this.distance = distance;
	}
	Vertex getSource(){
		return edge.getSource();
		
	}
	
	Vertex getDestination() {
		return edge.getDestination();
	}
	
	Float getWeight() {
		return edge.getWeight();
	}
	
	Float getDistance() {
		return distance;
	}
	
	DistancedEdge setDistance(Float distance) {
		this.distance=distance;
		return this;
	}
	
	DistancedEdge put(Edge edge, Float distance) {
		this.edge=edge;
		this.distance=distance;
		return this;
	}
}
