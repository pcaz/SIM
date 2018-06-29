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
import org.cazabat.sim.draw.JImagePanel;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Vertex> nodes;
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
	        
	        
	        edges.add (new Edge("AB",0,A,B, 4));
	        edges.add (new Edge("AC",0,A,C, 8));
	        edges.add (new Edge("CB",0,C,B, 7));
	        edges.add (new Edge("BD",0,B,D, 18));
	        edges.add (new Edge("DC",0,D,C, 10));
	        edges.add (new Edge("CF",0,C,F, 25));
	        edges.add (new Edge("BE",0,B,E, 21));
	        edges.add (new Edge("EF",0,E,F, 10));
	        edges.add (new Edge("DF",0,D,F, 12));
	        edges.add (new Edge("ED",0,E,D, 15));
	        edges.add (new Edge("EG",0,E,G, 17));
	        edges.add (new Edge("FG",0,F,G, 7));
	        
	        
	        Graph graph = new Graph(nodes, edges, false);
	        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
	        Result result=dijkstra.execute(A,G);
	        boolean Or=graph.isOriented();
	        GraphViz gv=new GraphViz();
	        
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
	        
	        JImagePanel imagePanel = new JImagePanel(Constant.TempDir+"/demo.png");
	        //Centrer l'image
	        imagePanel.setStretch(false);
	        //Etirer l'image
	       // imagePanel.setStretch(true);
	       
	        		
	       
	        JFrame frame = new JFrame();
	        JPanel panel=new JPanel();
	        panel.setLayout(new FlowLayout());
	
	      

	        JLabel label=new JLabel("Distance = "+result.getDistance()+"    ");
	        panel.add(label);

	        panel.add(new JLabel(new ImageIcon(Constant.TempDir+"/demo.png")));
	        frame.add(panel);
	        
	      
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       // frame.add(imagePanel);
	        frame.setSize(800, 800);
	        frame.setLocation(800, 100);
	        frame.setVisible(true);
	        
	       
	      
	        if(Or){ System.out.println("Oriented");} else{System.out.println("Non oriented");}
	        System.out.print(result.toString());


	}

}
