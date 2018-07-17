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

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.cazabat.sim.engine.DijkstraAlgorithm;
import org.cazabat.sim.model.Edge;
import org.cazabat.sim.model.Vertex;
import org.cazabat.sim.util.Problem;
import org.cazabat.sim.util.GetProblemFactory;
import org.cazabat.sim.draw.GraphViz;



public class Main {

	public static void main(String[] args) {
	
		
		int Dpi=0;
		String pathSeparator = System.getProperty("file.separator");
		
        String os = (String)System.getProperty("os.name").split(" ")[0].toLowerCase();
        System.out.println(os);
        
		final String cfgProp = System.getProperty("user.dir")+pathSeparator+os+"_config.properties";
		final Properties configFile = new Properties() {
		
			private final static long serialVersionUID = 1L; {
		       try {
		           load(new FileInputStream(cfgProp));
		       } catch (Exception e) {}
		   }
		};
		
		String TempDir = configFile.getProperty("tempDir");
		String ConfigDpi=configFile.getProperty("dpi"); 
		String TypeProblem = configFile.getProperty("typeProblem");
		String NameProblem = configFile.getProperty("nameProblem");
		if (ConfigDpi != null){Dpi = Integer.parseInt(ConfigDpi);}
		
	
		
			Problem problem;
		
	         

	         GetProblemFactory gb=new GetProblemFactory(TypeProblem);
	         if (gb==null) {
	        	 System.out.println("typeProblem not implemented");
	        	 System.exit(0);
	         }
	    
	          problem = gb.get(NameProblem);
	    	  DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(problem.getGraph());
	          Result result=dijkstra.execute(problem.getSource(),problem.getDestination());
	          Boolean Or=problem.getGraph().isOriented();
	          
	          
	          if(result.getDistance()==Constant.UNDEFINED) {
	
	        	GraphViz gv=new GraphViz();
	        	if(Dpi > 0) {
	        		for(int i=0;i<Dpi;i++){ gv.increaseDpi();}
	        	}
	        	else{
	        	if(Dpi < 0){	
	        		for (int i=0;i>-Dpi;i++){gv.decreaseDpi();
	        		}
	        	}
	        	}
	        	
	        		
		        
		        
		        
		        if(Or){
		        	gv.addln(gv.start_digraph());
		        } else{ gv.addln(gv.start_graph());}
		        for(Iterator<Edge> it=problem.getGraph().getTrueEdges().iterator();it.hasNext();){
		       	Edge val2=it.next();
		       	gv.add(val2.getSource().getName()+(Or?" -> ":"--")+val2.getDestination().getName()+" [label="+String.valueOf(val2.getWeight())+"];\n");
		        }
		       	gv.addln(gv.end_graph());
		       	String val3 = gv.getDotSource();
		        byte[] ret=gv.getGraph(gv.getDotSource(), "png");
		        gv.writeGraphToFile(ret, TempDir+pathSeparator+"demo.png");
		        
		        
	        	JFrame frame = new JFrame();
		        JPanel panel=new JPanel();
		        panel.setLayout(new FlowLayout());
		        JLabel 	label = new JLabel("Destination "+problem.getDestination().getName()+" is unreachable from "+problem.getSource().getName());
		        panel.add(label);
		        panel.add(new JLabel(new ImageIcon(TempDir+pathSeparator+"demo.png")));
	            frame.add(panel);
	    	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	    frame.setSize(800, 800);
	    	    frame.setLocation(800, 100);
	    	    frame.setVisible(true);
	        } else {
	  
	        GraphViz gv=new GraphViz();
	        if(Dpi > 0) {
        		for(int i=0;i<Dpi;i++){ gv.increaseDpi();}
        	}
        	else{
        	if(Dpi < 0){	
        		for (int i=0;i>-Dpi;i++){gv.decreaseDpi();
        		}
        	}
        	}
	        
	        if(Or){
	        	gv.addln(gv.start_digraph());
	        } else{ gv.addln(gv.start_graph());}
	
	
	        for(Iterator<Vertex> it=problem.getGraph().getVertices().iterator();it.hasNext();){
	        	gv.addln(it.next().getName()+";");
	        }
	        for(Iterator<Edge> it=problem.getGraph().getTrueEdges().iterator();it.hasNext();){
	        	Edge val=it.next();
	        	gv.add(val.getSource().getName()+(Or?" -> ":"--")+val.getDestination().getName()+" [label="+String.valueOf(val.getWeight()));
	        		if(result.getPath().contains((Edge)val) || (!Or && result.getPath().contains((val.inverseEdge())))){
	        			gv.add("color=red");
	        			}
	        		
	        		gv.addln("];");
	        	}
	        gv.addln(gv.end_graph());
	
	        byte[] ret=gv.getGraph(gv.getDotSource(), "png");
	        gv.writeGraphToFile(ret, TempDir+pathSeparator+"demo.png");
	         
            JFrame frame=new JFrame();
            JLabel label=new JLabel("Distance from "+problem.getSource().getName()+" to "+problem.getDestination().getName()+" = "+result.getDistance()+"    ");
            JPanel  panel = new JPanel();
            panel.setLayout(new FlowLayout());
	        panel.add(label);
	        panel.add(new JLabel(new ImageIcon(TempDir+pathSeparator+"demo.png")));
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
