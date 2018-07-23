package org.cazabat.sim.util;

import java.util.Enumeration;

public class GetProblemFactory{
	
	public String type=null;
	final GetProblemImplement getPb=null;
	

	@SuppressWarnings("unused")
	private GetProblemFactory() {
	}
	
	public GetProblemFactory(String which){
	this.type=which;
	}
	
	public Problem get(String Name) {
		
			
		final  GetProblemImplement getPb;
		
		switch(this.type) {
		case "dom":
			getPb=new GetDomProblem();
			return getPb.getProblem(Name);
		case "sax":
			getPb=new GetDomProblem();
			return getPb.getProblem(Name);	
		default:
			return null;
		}
		
	}

}
