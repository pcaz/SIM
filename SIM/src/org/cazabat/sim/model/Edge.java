package org.cazabat.sim.model;

import static org.cazabat.sim.Constant.UNDEFINED;

public class Edge{
 
	static private int number=0;

	
	protected final Vertex source;	
	protected final Vertex destination;
	protected float weight=UNDEFINED;
	protected int numero;
	private String name="";
	private int type=0;

	public Edge(String name, int type,Vertex source, Vertex destination, float weight) {
		this.source=source;
		this.destination=destination;
		this.weight=weight;
		this.numero=++number;
		this.name=name;
		this.type=type;
				}
	
	public Edge (Vertex source, Vertex destination, float weight) {
		this.source=source;
		this.destination=destination;
		this.weight=weight;
		this.numero=++number;
		this.name="Arc"+numero;
		
	}
	
	
	public Edge(Vertex source, Vertex destination) {
		this.source=source;
		this.destination=destination;
		this.numero=++number;
		this.name="Arc"+ this.numero;
		
	}
	
	
	public Vertex getSource() {
		return source;
	}
	public Vertex getDestination() {
		return destination;
	}
	public float getWeight() {
		return weight;
	}
	
	public void setWeight(float weight) {
		this.weight = weight;
	}
    public int getNumero() {
    	return numero;
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
