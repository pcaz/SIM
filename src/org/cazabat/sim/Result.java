package org.cazabat.sim;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.cazabat.sim.model.Edge;

public class Result {

	private float distance;
	private List<Edge> path;
	
	public Result() {
		distance = (float)0;
		path = new ArrayList<Edge>();		
	}
	
	public Result(float f, List<Edge>edges) {
		distance = f;
		path = edges;		
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}
	
	public void AddEdge(Edge newEdge) {
		path.add(newEdge);
		
	}
	
	public List<Edge> getPath() {
		return path;
	}

	public String toString() {
		
		if(distance == Constant.UNDEFINED || path == null) return "Destination unreachable";
		
		StringBuffer str = new StringBuffer();
		
		str.append( "Dist: "+distance+"\n\n");
		
		Iterator<Edge> it=path.iterator();
		while(it.hasNext()){
			Edge next=it.next();
			str.append(next.getName()+":"+next.getWeight()+"\n");
		}
		
		return str.toString();
		
		
		
	}
}
