package org.cazabat.sim.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.cazabat.sim.Result;
import org.cazabat.sim.engine.DijkstraAlgorithm;
import org.cazabat.sim.model.*;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestDijkstraAlgorithm {

	List<Vertex> nodes;
    List<Edge> edges;
    
    nodes = new ArrayList<Vertex>();
    edges = new ArrayList<Edge>();
    boolean Or = false;
    
    Document document = null;
	DocumentBuilderFactory factory = null;
	
    String userPath = System.getProperty("user.dir");
    String pathSeparator = System.getProperty("file.separator"); 
  
    try{
    	factory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder builder = factory.newDocumentBuilder();
    	document = builder.parse(userPath+pathSeparator+"graph.xml");		
    	
    }catch(Exception e){
    	e.printStackTrace();
    }
    
    Element racine = document.getDocumentElement();
    NodeList racineNoeuds = racine.getChildNodes();
    
    // First, we get the nodes
    
    Element xmlNodes=null;
    
    for(int i=0; i< racineNoeuds.getLength();i++){
    	if(racineNoeuds.item(i).getNodeName().equals ( "NODES")){
    		xmlNodes = (Element) racineNoeuds.item(i);
    		break;
    	}
    }
    NodeList xmlNode = xmlNodes.getElementsByTagName("NODE");
    for(int i=0;i<xmlNode.getLength();i++)
    {
    	nodes.add(new Vertex(xmlNode.item(i).getTextContent().trim()));
    }
    // then, the edges.
    
   
    Element xmlEdges=null;
    for (int i=0;i<racineNoeuds.getLength();i++) {
    	if(racineNoeuds.item(i).getNodeName().equals ( "EDGES")){
    		xmlEdges = (Element) racineNoeuds.item(i);
    		break;
    	}
    }
     
    NodeList xmlEdge = xmlEdges.getElementsByTagName("EDGE");
    
    Element xmlEntry;
    for (int i=0;i<xmlEdge.getLength();i++) {
    		 xmlEntry = (Element) xmlEdge.item(i);
    		 NodeList xmlSource = xmlEntry.getElementsByTagName("SOURCE");
    		 String xSource = xmlSource.item(0).getTextContent().trim();
    		 NodeList xmlDestination = xmlEntry.getElementsByTagName("DESTINATION");
    		 String xDestination = xmlDestination.item(0).getTextContent().trim();
    		 NodeList xmlWeight = xmlEntry.getElementsByTagName("WEIGHT");
    		 String xWeight = xmlWeight.item(0).getTextContent().trim();
    		 Vertex xxSource=null;
			 Vertex xxDestination=null;
    		 for(Iterator<Vertex> it=nodes.iterator();it.hasNext();)
    		 {
    			 
    			 Vertex val=it.next();
    			 String c1=val.getName();
    			 String c2=xSource;		 
    			 if(val.getName().equals(xSource)) {xxSource=val;}		 
    		     if(val.getName().equals(xDestination)) {xxDestination=val;}
                 if(xxSource != null && xxDestination != null) {break;}
               }
    		 float xxWeight = Float.parseFloat(xWeight);
    		 edges.add(new Edge(xxSource,xxDestination,xxWeight));
    }
    
    
    // at last, the orientation
    
    NodeList xmlOrientation = racine.getElementsByTagName("ISORIENTED");
    String xOrientation=xmlOrientation.item(0).getTextContent().trim();
     if(xOrientation.equals("1")) {Or=true;} else {Or=false;}

	        Vertex A=nodes.get(0);
	        Vertex B=nodes.get(1);
	        Vertex C=nodes.get(2);
	        Vertex D=nodes.get(3);
	        Vertex E=nodes.get(4);
	        Vertex F=nodes.get(5);
	        Vertex G=nodes.get(6);
	        
	   

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