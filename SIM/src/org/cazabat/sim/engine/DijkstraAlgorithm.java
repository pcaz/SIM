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
import java.util.Set;

import org.cazabat.sim.Constant;
import org.cazabat.sim.model.*;


	
public class DijkstraAlgorithm {

	private Map<Vertex,DistancedEdge> settleNodes;
	private Map<Vertex,DistancedEdge> unSettledNodes;
	private Boolean gotit;
	private Vertex actualVertex; 
	private float actualDistance;
    private Graph graph;
    private Vertex source;
    private Vertex destination;
    

    public DijkstraAlgorithm(Graph graph) {
        
    	this.graph = graph;
    	
    	this.settleNodes = new HashMap<Vertex,DistancedEdge>();
    	this.unSettledNodes = new HashMap<Vertex,DistancedEdge>();
	    }

    public void execute(Vertex source, Vertex destination) {
    	  
    
    this.source = source;
    this.destination = destination;
    
    // Initialization, distance of source is null, all others is undefined
    	
    	
    	for(int i=0; i<this.graph.size(); i++) {
    		Vertex vertex = this.graph.getVertex(i);
    		if(vertex.equal(this.source)) {
    			DistancedEdge actualDistancedEdge = new DistancedEdge(new Edge(null,vertex), (float)0);
    			settleNodes.put(vertex,actualDistancedEdge);
    		
    		}
    		else
    		{
    			unSettledNodes.put(vertex,new DistancedEdge(null,Constant.UNDEFINED));
    		}
    	}
        
 // Ok, we run it

    
		this.actualDistance = (float)0;
	    

	    while(unSettledNodes.size() > 0) {
			
           Vertex newNode= onePass(this.actualDistance);
           if (this.actualDistance == (float)0) {
        	   this.settleNodes.remove(this.source);
           }
           this.actualDistance = this.settleNodes.get(newNode).getDistance();
            
                }
    }
    
 /*   private DistancedEdge newEdge(Map <Vertex,DistancedEdge> unSNodes, Vertex from, Float distance) {
    	
    	DistancedEdge unSettledNode=null;
    	List<Edge> successor = this.graph.successorVertex(from);
    	Float min=(float)Constant.UNDEFINED;
        for(int i=0; i<successor.size();i++) {
        	unSettledNode = unSettledNodes.get(successor.get(i).getDestination());
        	unSettledNodes.put(unSettledNode, value)get(unsettledNode).setDistance(distance + successor.get(i).getWeight();
        	
        }
    	
    }
*/
    private Vertex onePass(float actualDistance) {
    	
    	
    	Map <Vertex,DistancedEdge> newOnes=new Hashtable<Vertex, DistancedEdge>();
    	
    	
    
    	
        List<DistancedEdge> result = new ArrayList<DistancedEdge>();    	
    	Set<Map.Entry<Vertex, DistancedEdge>> settled = this.settleNodes.entrySet();

    	Iterator<Map.Entry<Vertex, DistancedEdge>> it = settled.iterator();
        while(it.hasNext()) {
        	DistancedEdge thisDistancedEdge = it.next().getValue();
            Vertex node=thisDistancedEdge.getDestination();
            newOnes.putAll(this.getNexts(node,actualDistance));
          }
        
        Set <Map.Entry<Vertex, DistancedEdge>> modifs=newOnes.entrySet();
        Iterator <Map.Entry<Vertex, DistancedEdge>> it1 = modifs.iterator();
        
        while(it1.hasNext()) {
        	DistancedEdge thisOne = it1.next().getValue();
        	this.unSettledNodes.remove(thisOne.getDestination());
        	this.settleNodes.put(thisOne.getDestination(), thisOne);
        }
        return getMinimum(this.settleNodes);
        
    }

  

    private Map<Vertex, DistancedEdge> getNexts(Vertex node, float actualDistance) {
    	
    	float thisDistance = actualDistance;
    	Map<Vertex, DistancedEdge> result=new Hashtable<Vertex, DistancedEdge>();
    	
    	// List<DistancedEdge> result=new ArrayList<DistancedEdge>();
    
        List<Edge> neighbours = this.graph.successorVertex(node);
        ListIterator<Edge> it=neighbours.listIterator();
        while(it.hasNext())
        {
        	Edge thisOne = it.next();
        	if(this.settleNodes.containsKey(thisOne.getDestination()))
        	{
        			DistancedEdge de = this.settleNodes.get(thisOne.getDestination()) ;
        			if(de.distance > de.edge.getWeight()+actualDistance)
        			{
        				de.distance = de.edge.getWeight()+actualDistance;
        				de.setEdge(de.getSource(), node);
        				if(de.distance < thisDistance) {
        					thisDistance = de.distance;
        				}
        				
        			}
        			
        	}
        	else
        	{
        		Vertex destination = thisOne.getDestination();
        		float newDistance = thisOne.getWeight() +actualDistance;
        		
        		result.put(destination, new DistancedEdge(new Edge(node, destination), newDistance));
        		
        	    if(thisDistance==(float)0 || newDistance < thisDistance) {
        	    	thisDistance = newDistance;
        	    }
        	    	
        	    
        	}
        }       
        return result;
   }

    private Vertex getMinimum(Map<Vertex, DistancedEdge> search) {
        
    	Float minimum= (float)Constant.UNDEFINED;
        
        Vertex ret=null;
        
        Set<Vertex> vertexes = search.keySet();
        Iterator<Vertex> it = vertexes.iterator();
        
        while (it.hasNext()){
        	DistancedEdge val=search.get((it.next()));
        	float newDistance= val.getDistance();
        	if((newDistance != 0) && (newDistance < minimum)) { 
        		ret=val.getDestination();
        		minimum = newDistance; 
        		}
           	}
        	
        
        return ret;
        		
    }

    private boolean isSettled(Vertex vertex) {
        return settleNodes.containsKey(vertex);
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
