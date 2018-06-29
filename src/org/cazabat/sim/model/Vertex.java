package org.cazabat.sim.model;


import java.util.Calendar;

public class Vertex extends Point{
	
	static private int number=0;

	public int numero;

	private String name;
	private int type;
	private Calendar time;
	
	
	public Vertex(String name, int type, float longitude, float latitude) {
		super(longitude,latitude);
		this.name=name;
		this.type=type;
		Vertex.number++;
		this.numero = Vertex.number;
	}
	
	public Vertex(String name, int type) {
		super();
		this.name=name;
		this.type=type;
		Vertex.number++;
		this.numero = Vertex.number;
	}
	
	public int getNumero() {
		return this.numero;
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

	public Calendar getTime() {
		return time;
	}

	public void setTime(Calendar time) {
		this.time = time;
	}

	public boolean equal(Vertex vertex) {
		return vertex.getNumero() == this.numero;
	}
	
}
