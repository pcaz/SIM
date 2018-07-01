package org.cazabat.sim;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.cazabat.sim.engine.DijkstraAlgorithm;
import org.cazabat.sim.model.Edge;
import org.cazabat.sim.model.Graph;
import org.cazabat.sim.model.Vertex;
import org.cazabat.sim.draw.GraphViz;

import org.w3c.dom.*;
import javax.xml.parsers.*;



public class Main {

	public static void main(String[] args) {
	
		List<Vertex> nodes;
	    List<Edge> edges;
	    
	    nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        boolean Or = false;
        Vertex source = null;
        Vertex destination = null;
        
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
	    
	    NodeList xml = racine.getElementsByTagName("Nodes");
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
	    NodeList nl=null;
	     
	    NodeList xmlOrientation = racine.getElementsByTagName("ISORIENTED");
	    String xOrientation=xmlOrientation.item(0).getTextContent().trim();
	     if(xOrientation.equals("1")) {Or=true;} else {Or=false;}
	    
	     
	    // so,now for the source and destination of the research
	     for(int i=0; i< racineNoeuds.getLength();i++){
		    	if(racineNoeuds.item(i).getNodeName().equals ( "SOURCE")){
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
		    	if(racineNoeuds.item(i).getNodeName().equals ( "DESTINATION")){
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

		    
	        Vertex A=nodes.get(0);
	        Vertex B=nodes.get(1);
	        Vertex C=nodes.get(2);
	        Vertex D=nodes.get(3);
	        Vertex E=nodes.get(4);
	        Vertex F=nodes.get(5);
	        Vertex G=nodes.get(6);
	        
	       ;
	       
	        
	        Graph graph = new Graph(nodes, edges, Or);
	        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
	        Result result=dijkstra.execute(source,destination);
	        if(result.getDistance()==Constant.UNDEFINED) {
	
	        	GraphViz gv=new GraphViz();
		        gv.decreaseDpi();
		        
		        if(Or){
		        	gv.addln(gv.start_digraph());
		        } else{ gv.addln(gv.start_graph());}
		        for(Iterator<Edge> it=edges.iterator();it.hasNext();){
		       	Edge val2=it.next();
		       	gv.add(val2.getSource().getName()+(Or?" -> ":"--")+val2.getDestination().getName()+" [label="+String.valueOf(val2.getWeight())+"];\n");
		        }
		       	gv.addln(gv.end_graph());
		       	String val3 = gv.getDotSource();
		        byte[] ret=gv.getGraph(gv.getDotSource(), "png");
		        gv.writeGraphToFile(ret, Constant.TempDir+"/demo.png");
		        
		        
	        	JFrame frame = new JFrame();
		        JPanel panel=new JPanel();
		        panel.setLayout(new FlowLayout());
		        JLabel 	label = new JLabel("Destination "+destination.getName()+" is unreachable from "+source.getName());
		        panel.add(label);
		        panel.add(new JLabel(new ImageIcon(Constant.TempDir+"/demo.png")));
	            frame.add(panel);
	    	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	    frame.setSize(800, 800);
	    	    frame.setLocation(800, 100);
	    	    frame.setVisible(true);
	        } else {
	  
	        GraphViz gv=new GraphViz();
	        //gv.decreaseDpi();
	        
	        if(Or){
	        	gv.addln(gv.start_digraph());
	        } else{ gv.addln(gv.start_graph());}
	
	
	        for(Iterator<Vertex> it=nodes.iterator();it.hasNext();){
	        	gv.addln(it.next().getName()+";");
	        }
	        for(Iterator<Edge> it=edges.iterator();it.hasNext();){
	        	Edge val=it.next();
	        	gv.add(val.getSource().getName()+(Or?" -> ":"--")+val.getDestination().getName()+" [label="+String.valueOf(val.getWeight()));
	        		if(result.getPath().contains((Edge)val) || (!Or && result.getPath().contains((val.inverseEdge())))){
	        			gv.add("color=red");
	        			}
	        		
	        		gv.addln("];");
	        	}
	        gv.addln(gv.end_graph());
	        String val=gv.getDotSource();
	        byte[] ret=gv.getGraph(gv.getDotSource(), "png");
	        gv.writeGraphToFile(ret, Constant.TempDir+"/demo.png");
	         
            JFrame frame=new JFrame();
            JLabel label=new JLabel("Distance from "+source.getName()+" to "+destination.getName()+" = "+result.getDistance()+"    ");
            JPanel  panel = new JPanel();
            panel.setLayout(new FlowLayout());
	        panel.add(label);
	        panel.add(new JLabel(new ImageIcon(Constant.TempDir+"/demo.png")));
	        frame.add(panel);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(800, 800);
	        frame.setLocation(800, 100);
	        frame.setVisible(true);
	        
	       
	      
	        if(Or){ System.out.println("Oriented");} else{System.out.println("Non oriented");}
	        System.out.print(result.toString());

	        }
	        
	}

}
