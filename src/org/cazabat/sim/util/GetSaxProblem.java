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
import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class GetSaxProblem extends GetProblemImplement{

	String Graph = "GRAPH";
	String Isoriented = "ISORIENTED";
	String Nodes = "NODES";
	String Node = "NODE";			
	String Edges="EDGES";
	String Edge="EDGE";
	String Weight = "WEIGHT";
	String Source="SOURCE";
	String Destination="DESTINATION";
	
	List<Vertex> nodes = new ArrayList<Vertex>();
	List<Edge> edges = new ArrayList<Edge>();
	boolean Or=false;
	Vertex source=null;
	Vertex destination=null;
	
	@Override
	public Problem getProblem(String nameProblem) {
		
		String fileName= System.getProperty("user.dir")+System.getProperty("file.separator")+nameProblem;	
	
		if(!XMLValidator.validate("Problem.xsd",fileName)) {
			System.out.println("Problem not valide");
			System.exit(0);
		}
		Document document = null;
		DocumentBuilderFactory factory = null;
		
		  
	    try{
	    	factory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder builder = factory.newDocumentBuilder();
	    	document = builder.parse(fileName);		
	    	
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	}
}
