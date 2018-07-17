package org.cazabat.sim.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.cazabat.sim.model.Edge;
import org.cazabat.sim.model.Graph;
import org.cazabat.sim.model.Vertex;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

class GetDomProblem  extends GetProblemImplement {

	private String Isoriented = "ISORIENTED";
	private String Nodes = "NODES";
	private String Node = "NODE";			
	private String Edges="EDGES";
	private String Edge="EDGE";
	private String Weight = "WEIGHT";
	private String Source="SOURCE";
	private String Destination="DESTINATION";
	
	@Override
	public Problem getProblem(String nameProblem) {
		
		List<Vertex> nodes = new ArrayList<Vertex>();
		List<Edge> edges = new ArrayList<Edge>();
		boolean Or=false;
		Vertex source=null;
		Vertex destination=null;
	
		String fileName= System.getProperty("user.dir")+System.getProperty("file.separator")+nameProblem;
		
		Document document = null;
		DocumentBuilderFactory factory = null;
		
		  
	    try{
	    	factory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder builder = factory.newDocumentBuilder();
	    	document = builder.parse(fileName);		
	    	
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	    Element racine = document.getDocumentElement();
	    NodeList racineNoeuds = racine.getChildNodes();
	    
	    // First, we get the nodes
	    

	    Element xmlNodes=null;
	    
	    for(int i=0; i< racineNoeuds.getLength();i++){
	    	if(racineNoeuds.item(i).getNodeName().equals ( Nodes)){
	    		xmlNodes = (Element) racineNoeuds.item(i);
	    		break;
	    	}
	    }
	    NodeList xmlNode = xmlNodes.getElementsByTagName(Node);
	    for(int i=0;i<xmlNode.getLength();i++)
	    {
	    	nodes.add(new Vertex(xmlNode.item(i).getTextContent().trim()));
	    }
        // then, the edges.
	    
	   
	    Element xmlEdges=null;
	    for (int i=0;i<racineNoeuds.getLength();i++) {
	    	if(racineNoeuds.item(i).getNodeName().equals ( Edges)){
	    		xmlEdges = (Element) racineNoeuds.item(i);
	    		break;
	    	}
	    }
	     
	    NodeList xmlEdge = xmlEdges.getElementsByTagName(Edge);
	    
	    Element xmlEntry;
	    for (int i=0;i<xmlEdge.getLength();i++) {
	    		 xmlEntry = (Element) xmlEdge.item(i);
	    		 NodeList xmlSource = xmlEntry.getElementsByTagName(Source);
	    		 String xSource = xmlSource.item(0).getTextContent().trim();
	    		 NodeList xmlDestination = xmlEntry.getElementsByTagName(Destination);
	    		 String xDestination = xmlDestination.item(0).getTextContent().trim();
	    		 NodeList xmlWeight = xmlEntry.getElementsByTagName(Weight);
	    		 String xWeight = xmlWeight.item(0).getTextContent().trim();
	    		 Vertex xxSource=null;
    			 Vertex xxDestination=null;
	    		 for(Iterator<Vertex> it=nodes.iterator();it.hasNext();)
	    		 {
	    			 
	    			 Vertex val=it.next(); 
	    			 if(val.getName().equals(xSource)) {xxSource=val;}		 
	    		     if(val.getName().equals(xDestination)) {xxDestination=val;}
                     if(xxSource != null && xxDestination != null) {break;}
                   }
	    		 float xxWeight = Float.parseFloat(xWeight);
	    		 edges.add(new Edge(xxSource,xxDestination,xxWeight));
	    }
	    
	    
	    // at last for the graph, get the orientation
	    Element xmlSource=null;
	    Element xmlDestination=null;
	     
	    NodeList xmlOrientation = racine.getElementsByTagName(Isoriented);
	    String xOrientation=xmlOrientation.item(0).getTextContent().trim();
	     if(xOrientation.equals("1")) {Or=true;} else {Or=false;}
	    
	     
	    // so,now for the source and destination of the research
	     for(int i=0; i< racineNoeuds.getLength();i++){
		    	if(racineNoeuds.item(i).getNodeName().equals ( Source)){
		    		xmlSource = (Element) racineNoeuds.item(i);
		    		break;
		    	}
		    }
 
		    String xmlAlgoSource=xmlSource.getTextContent().trim();
		    for(Iterator<Vertex> it=nodes.iterator();it.hasNext();)
   		 {
   			 Vertex val=it.next(); 
   			 if(val.getName().equals(xmlAlgoSource)) {
   				 source=val;		 
                break;
   			 }
           }
	     
		    for(int i=0; i< racineNoeuds.getLength();i++){
		    	if(racineNoeuds.item(i).getNodeName().equals ( Destination)){
		    		xmlDestination = (Element) racineNoeuds.item(i);
		    		break;
		    	}
		    }
 
		    String xmlAlgoDestination=xmlDestination.getTextContent().trim();
		    for(Iterator<Vertex> it=nodes.iterator();it.hasNext();)
   		 {
   			 Vertex val=it.next(); 
   			 if(val.getName().equals(xmlAlgoDestination)) {
   				 destination=val;		 
                break;
   			 }
           }

		    return new Problem (new Graph(nodes,edges,Or), source, destination);
	}

	
	

}
