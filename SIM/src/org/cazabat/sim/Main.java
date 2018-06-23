package org.cazabat.sim;

import java.util.ArrayList;
import java.util.List;

import org.cazabat.sim.engine.DijkstraAlgorithm;
import org.cazabat.sim.model.Edge;
import org.cazabat.sim.model.Graph;
import org.cazabat.sim.model.Vertex;
import org.junit.Test;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Vertex> nodes;
	    List<Edge> edges;

	        nodes = new ArrayList<Vertex>();
	        edges = new ArrayList<Edge>();
	    
	        nodes.add(new Vertex("A",0,1,0));
	        nodes.add(new Vertex("B",0,0,1));
	        nodes.add(new Vertex("C",0,0,2));
	        nodes.add(new Vertex("D",0,2,1));
	        nodes.add(new Vertex("E",0,2,2));
	        nodes.add(new Vertex("F",0,1,3));
	        
	        edges.add (new Edge("AB",0,nodes.get(0), nodes.get(1), 2));
	        edges.add (new Edge("BC",0,nodes.get(1), nodes.get(2), 2));
	        edges.add (new Edge("AD",0,nodes.get(0), nodes.get(3), 3));
	        edges.add (new Edge("DE",0,nodes.get(3), nodes.get(4), 1));
	        edges.add (new Edge("CF",0,nodes.get(2), nodes.get(5), 3));
	        edges.add (new Edge("EF",0,nodes.get(4), nodes.get(5), 1));
	        
	        

	        // Lets check from location Loc_1 to Loc_10
	        Graph graph = new Graph(nodes, edges);
	        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
	        dijkstra.execute(nodes.get(0),nodes.get(5));


	}

}
