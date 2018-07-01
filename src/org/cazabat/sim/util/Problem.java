package org.cazabat.sim.util;

import org.cazabat.sim.model.Graph;
import org.cazabat.sim.model.Vertex;

public class Problem {
	Graph graph;
	Vertex source;
	Vertex destination;
	
	public Problem(Graph g, Vertex s, Vertex d) {
		this.graph = g;
		this.source = s;
		this.destination = d;
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

	public Vertex getSource() {
		return source;
	}

	public void setSource(Vertex source) {
		this.source = source;
	}

	public Vertex getDestination() {
		return destination;
	}

	public void setDestination(Vertex destination) {
		this.destination = destination;
	}
	

}
