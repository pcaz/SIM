package org.cazabat.sim.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.cazabat.sim.Constant;
import org.cazabat.sim.engine.DijkstraAlgorithm.DistancedEdge;
import org.cazabat.sim.model.*;


	
public class DijkstraAlgorithm {

	private Map<Vertex,DistancedEdge> settledNodes;
	private Map<Vertex,DistancedEdge> unSettledNodes;
	private Boolean gotit;
	private Vertex actualNode; 
	private float actualDistance;
    private Graph graph;
    private Vertex source;
    private Vertex destination;
    

    public DijkstraAlgorithm(Graph graph) {
        
    	this.graph = graph;
    	
    	this.settledNodes = new HashMap<Vertex,DistancedEdge>();
    	this.unSettledNodes = new HashMap<Vertex,DistancedEdge>();
	    }

    public void execute(Vertex source, Vertex destination) {
    	  
    
    this.source = source;
    this.destination = destination;
    
    // Initialization, distance of all vartex different of source in unSettledNodess with Undefined distance
    	
    	
    	for(int i=0; i<this.graph.size(); i++) {
    		Vertex vertex = this.graph.getVertex(i);
    		if(!vertex.equal(this.source)) {
    				unSettledNodes.put(vertex,new DistancedEdge(new Edge(null, vertex),Constant.UNDEFINED));
    		}
    	}
        
 // Ok, we run it

    
		this.actualDistance = (float)0;
	    this.actualNode = source;

	    while(unSettledNodes.size() > 0) {
			
           onePass(this.actualNode,this.actualDistance);
           DistancedEdge thisNode = getMinimum();
           this.actualDistance = thisNode.getDistance();
           Vertex newNode = thisNode.getDestination();
           this.actualNode = newNode;
           this.unSettledNodes.remove(newNode);
           this.settledNodes.put(newNode,thisNode);
           }
           
    }
    
 /*   private DistancedEdge newEdge(Map <Vertex,DistancedEdge> unSNodes, Vertex from, Float distance) {
    	
    	DistancedEdge unSettledNodes=null;
    	List<Edge> successor = this.graph.successorVertex(from);
    	Float min=(float)Constant.UNDEFINED;
        for(int i=0; i<successor.size();i++) {
        	unSettledNodes = unSettledNodess.get(successor.get(i).getDestination());
        	unSettledNodess.put(unSettledNodes, value)get(unSettledNodes).setDistance(distance + successor.get(i).getWeight();
        	
        }
    	
    }
*/
    private void onePass(Vertex node, float distance) {
    	
    	
    	//first, we get all the successors of node
    	
    	List<Edge> neighbours = this.graph.successorVertex(node);
    	
    	// for all successor, we adjust the unSettledNodes
    	
    	ListIterator<Edge> it = neighbours.listIterator();
    	while(it.hasNext()) {
    		Edge thisEdge = it.next();
    		Vertex thisNode = thisEdge.getDestination();
    		float thisDistance = distance + thisEdge.getWeight();
   			if(this.unSettledNodes.get(thisNode).getDistance() > thisDistance) {
    				this.unSettledNodes.put(thisNode, new DistancedEdge(thisEdge, thisDistance));
    				
    		}
    		
    	}
    	
    }

  

    
    private DistancedEdge getMinimum() {
        
    	Float minimum= (float)Constant.UNDEFINED;
        
        DistancedEdge ret=null;
        
        Set <Map.Entry<Vertex, DistancedEdge>> nodes=this.unSettledNodes.entrySet();
        Iterator<Entry<Vertex, DistancedEdge>> it = nodes.iterator();
        
        while (it.hasNext()){
        	Entry<Vertex, DistancedEdge> val=it.next();
        	if(val.getValue().getDistance() < minimum) {
        		minimum = val.getValue().getDistance();
        		ret= val.getValue();
        	} else {
        		if (val.getValue().getDistance() == minimum) {
        			//TODO et puis quoi, deux chemins possibles équidistans .
        		}
        	}
        	
        }
        return ret;		
    }

    private boolean isSettled(Vertex vertex) {
        return settledNodes.containsKey(vertex);
    }

   

class DistancedEdge {
	private Edge edge;
	private Float distance ;
	
	// Distance is from the source, i.e from the last settledNodes
	
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
		return this.distance;
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
	
	DistancedEdge setEdge(Vertex source, Vertex destination) {
		this.edge= new Edge(source, destination);
		return this;
	}
	
	Edge getEdge() {
		return this.edge;
	}
}
}
