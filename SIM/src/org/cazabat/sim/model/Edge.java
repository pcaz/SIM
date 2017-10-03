/**
 * 
 */
package org.cazabat.sim.model;

import static org.cazabat.sim.Constant.*;


/**
 * @author pascaz10
 *
 */
public class Edge {
	
	public final Vertex source;	
	public final Vertex destination;
	public float weight=UNDEFINED;

	public Edge(Vertex source, Vertex destination, float weight) {
		this.source=source;
		this.destination=destination;
		this.weight=weight;
				}
	public Edge(Vertex source, Vertex destination) {
		this.source=source;
		this.destination=destination;
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
	
}
