package com.siemens.bean;

public class LatLong {
	private String latitude;
	private String longitude;
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "LatLong [latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	public LatLong(String latitude,String longitude){
		this.latitude=latitude;
		this.longitude=longitude;
	}

}
