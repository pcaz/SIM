package org.cazabat.sim.model;

import java.util.List;

public class Lane extends Edge {
	
	private String name="";
	private int type=0;
	private List<Point> path;
	
	public Lane(String name, int type, Vertex source, Vertex destination, int weight) {
		super(source,destination,weight);
		this.name=name;
		this.type=type;
		
	}
	
	public Lane(String name,Vertex source, Vertex destination, int weight) {
		super(source,destination,weight);
		this.name=name;		
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<Point> getPath() {
		return path;
	}

	public void setPath(List<Point> path) {
		this.path = path;
	}
	
	public void addPoint(Point p) {
		this.path.add(p);
	}

	public boolean removePoint(Point p) {
		return this.path.remove(p);
	}
}
