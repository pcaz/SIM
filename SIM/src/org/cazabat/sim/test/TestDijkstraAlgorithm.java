package org.cazabat.sim.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import org.cazabat.sim.engine.DijkstraAlgorithm;
import org.cazabat.sim.model.*;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestDijkstraAlgorithm {

    private List<Vertex> nodes;
    private List<Edge> edges;

    @Test
    public void testExcute() {
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        for (int i = 0; i < 11; i++) {
            Vertex location = new Vertex("Node_" + i, 0);
            nodes.add(location);
        }

        edges.add (new Edge("Edge_0",0,nodes.get(0), nodes.get(1), 85));
        edges.add (new Edge("Edge_1",0,nodes.get(0), nodes.get(2), 217));
        edges.add (new Edge("Edge_2",0,nodes.get(0), nodes.get(4), 173));
        edges.add (new Edge("Edge_3",0,nodes.get(2), nodes.get(6), 186));
        edges.add (new Edge("Edge_4",0,nodes.get(2), nodes.get(7), 103));
        edges.add (new Edge("Edge_5",0,nodes.get(3), nodes.get(7),183));
        edges.add (new Edge("Edge_6",0,nodes.get(5), nodes.get(8), 250));
        edges.add (new Edge("Edge_7",0,nodes.get(8), nodes.get(9), 84));
        edges.add (new Edge("Edge_8",0,nodes.get(7), nodes.get(9), 167));
        edges.add (new Edge("Edge_9",0,nodes.get(4), nodes.get(9), 502));
        edges.add (new Edge("Edge_10",0,nodes.get(9), nodes.get(10), 40));
        edges.add (new Edge("Edge_11",0,nodes.get(1), nodes.get(10), 600));
        

        // Lets check from location Loc_1 to Loc_10
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(nodes.get(0));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(10));

        assertNotNull(path);
        assertTrue(path.size() > 0);

        for (Vertex vertex : path) {
            System.out.println(vertex.getName());
        }

    }

   
}