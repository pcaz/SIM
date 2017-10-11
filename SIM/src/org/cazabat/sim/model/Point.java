/**
 * 
 */
package org.cazabat.sim.model;

import org.cazabat.sim.Constant.SYSTEM;

/**
 * @author pascaz10
 *
 */
public class Point {

	protected float latitude;
	protected float longitude;
	protected float altitude;
	private String system="WSG84";
	
	public Point(float latitude, float longitude, float altitude, String system)
	{
		this.latitude=latitude;
		this.longitude=longitude;
		this.altitude=altitude;
		if(system.equals(this.system))
			{
			return;
			}
		else {
		for(SYSTEM s : SYSTEM.values()) {
			if(s.name().equals(system))
			{
				if(Convert(s.name(),system)) {return;}
				else throw new IllegalArgumentException();
				
			}		
		} throw new IllegalArgumentException();
		}
	}
	
	public Point() {
		this.latitude=0;
		this.longitude=0;
		this.altitude=0;
		this.system="";
		
	}
	public Point(float latitude, float longitude) {
		this.latitude=latitude;
		this.longitude=longitude;
		this.altitude=0;
		this.system="WSG84";
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getAltitude() {
		return altitude;
	}

	public void setAltitude(float altitude) {
		this.altitude = altitude;
	}

	public String getSystem() {
		return system;
	}

    private boolean Convert(String from, String to)   {
    	return false;
    }
}