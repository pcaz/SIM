package org.cazabat.sim.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.cazabat.sim.Result;
import org.cazabat.sim.engine.DijkstraAlgorithm;
import org.cazabat.sim.model.*;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestDijkstraAlgorithm {

    private List<Vertex> nodes;
    private List<Edge> edges;

    @Test
    public void testExcute() {
    	 List<Edge> edges;

	        nodes = new ArrayList<Vertex>();
	        edges = new ArrayList<Edge>();
	    
	        nodes.add(new Vertex("A",0,0,1));
	        nodes.add(new Vertex("B",0,1,3));
	        nodes.add(new Vertex("C",0,1,0));
	        nodes.add(new Vertex("D",0,3,2));
	        nodes.add(new Vertex("E",0,4,3));
	        nodes.add(new Vertex("F",0,5,0));
	        nodes.add(new Vertex("G",0,6,2));
	        
	        Vertex A=nodes.get(0);
	        Vertex B=nodes.get(1);
	        Vertex C=nodes.get(2);
	        Vertex D=nodes.get(3);
	        Vertex E=nodes.get(4);
	        Vertex F=nodes.get(5);
	        Vertex G=nodes.get(6);
	        
	        
	        edges.add (new Edge("BA",0,B,A, 4));
	        edges.add (new Edge("CA",0,C,A, 8));
	        edges.add (new Edge("CB",0,C,B, 7));
	        edges.add (new Edge("BD",0,B,D, 18));
	        edges.add (new Edge("DC",0,D,C, 10));
	        edges.add (new Edge("CF",0,C,F, 25));
	        edges.add (new Edge("BE",0,B,E, 21));
	        edges.add (new Edge("EF",0,E,F, 10));
	        edges.add (new Edge("DF",0,D,F, 12));
	        edges.add (new Edge("ED",0,E,D, 15));
	        edges.add (new Edge("GE",0,G,E, 17));
	        edges.add (new Edge("GF",0,G,F, 7));
	   
	        boolean Or = false;
            Result result=null;
            DijkstraAlgorithm dijkstra=null;
            
	        System.out.print("\n\n\n Oriented = false\n");
	        
	        int k=0;
	        for(int i=0;i<nodes.size();i++) {
	        	for(int j=0;j<nodes.size();j++) {
	        		if(!nodes.get(i).equals(nodes.get(j))){
	        			k++;
	        			Graph graph = new Graph(nodes, edges, Or);
	        			dijkstra = new DijkstraAlgorithm(graph);
	        			result=dijkstra.execute(nodes.get(i),nodes.get(j));	
	        			System.out.println(k+"   "+nodes.get(i).getName()+":"+nodes.get(j).getName()+" -> "+result.getDistance());
	        		}
	        	}
	        }
	        Or = true;
	        k=0;
	        System.out.print("\n\n\n Oriented = true\n");
	        for(int i=0;i<nodes.size();i++) {
	        	for(int j=0;j<nodes.size();j++) {
	        		if(!nodes.get(i).equals(nodes.get(j))){
	        			k++;
	        			Graph graph = new Graph(nodes, edges, Or);
	        			dijkstra = new DijkstraAlgorithm(graph);
	        			result=dijkstra.execute(nodes.get(i),nodes.get(j));			
	        			System.out.println(k+"   "+nodes.get(i).getName()+":"+nodes.get(j).getName()+" -> "+result.getDistance());;
	        		}
	        	}
	        }
	        
   /*     LinkedList<Vertex> path = dijkstra.getPath(nodes.get(10));

        assertNotNull(path);
        assertTrue(path.size() > 0);

        for (Vertex vertex : path) {
            System.out.println(vertex.getName());
        }
        */

    }

   
}