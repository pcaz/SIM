/**
 * 
 */
package org.cazabat.sim.model;

/**
 * @author pascaz10
 *
 */
public class Point {

	private float latitude;
	private float longitude;
	private float altitude;
	private String systeme;
	
	public Point(float latitude, float longitude, float altitude, String systeme)
	{
		this.latitude=latitude;
		this.longitude=longitude;
		this.altitude=altitude;
		this.systeme=systeme;
	}
	
	public Point() {
		this.latitude=0;
		this.longitude=0;
		this.altitude=0;
		this.systeme="";
		
	}
	public Point(float latitude, float longitude) {
		this.latitude=latitude;
		this.longitude=longitude;
		this.altitude=0;
		this.systeme="WSG84";
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

	public String getSysteme() {
		return systeme;
	}


}
