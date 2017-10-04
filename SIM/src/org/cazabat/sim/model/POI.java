package org.cazabat.sim.model;

public class POI extends Vertex {

	private String name;
	private int type;
	
	public POI(String name, int type, float longitude, float latitude) {
		super(longitude,latitude);
		this.name=name;
		this.type=type;
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
}
