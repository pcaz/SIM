package org.cazabat.sim.model;

public class Vertex extends Point {

	static private int number=0;
	
	private final int id;
	
	
	public Vertex() {
		super();
		this.id=++Vertex.number;
	}

	
	
	public int getId() {
		return id;
	}


	
}
