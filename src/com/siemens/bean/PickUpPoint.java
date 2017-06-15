package com.siemens.bean;

import java.util.List;

import com.siemens.bean.LatLong;

public class PickUpPoint {
	
	private int id;

	// change it to PickUp Name
	private String pickUpLocation;
	private LatLong geoLocation;
	private List<Employee> employees;
    private int routeID;
	
	
	
	public PickUpPoint(LatLong geoLocation, List<Employee> empList) {
		this.geoLocation = geoLocation;
		this.employees = empList;

	}
	public PickUpPoint() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the pickUpLocation
	 */
	public String getPickUpLocation() {
		return pickUpLocation;
	}
	/**
	 * @param pickUpLocation the pickUpLocation to set
	 */
	public void setPickUpLocation(String pickUpLocation) {
		this.pickUpLocation = pickUpLocation;
	}
	/**
	 * @return the geoLocation
	 */
	public LatLong getGeoLocation() {
		return geoLocation;
	}
	/**
	 * @param geoLocation the geoLocation to set
	 */
	public void setGeoLocation(LatLong geoLocation) {
		this.geoLocation = geoLocation;
	}
	/**
	 * @return the employees
	 */
	public List<Employee> getEmployees() {
		return employees;
	}
	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	/**
	 * @return the routeID
	 */
	public int getRouteID() {
		return routeID;
	}
	/**
	 * @param routeID the routeID to set
	 */
	public void setRouteID(int routeID) {
		this.routeID = routeID;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PickUpPoint [id=" + id + ", pickUpLocation=" + pickUpLocation + ", geoLocation=" + geoLocation
				+ ", employees=" + employees + ", routeID=" + routeID + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employees == null) ? 0 : employees.hashCode());
		result = prime * result + ((geoLocation == null) ? 0 : geoLocation.hashCode());
		result = prime * result + id;
		result = prime * result + ((pickUpLocation == null) ? 0 : pickUpLocation.hashCode());
		result = prime * result + routeID;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PickUpPoint)) {
			return false;
		}
		PickUpPoint other = (PickUpPoint) obj;
		if (employees == null) {
			if (other.employees != null) {
				return false;
			}
		} else if (!employees.equals(other.employees)) {
			return false;
		}
		if (geoLocation == null) {
			if (other.geoLocation != null) {
				return false;
			}
		} else if (!geoLocation.equals(other.geoLocation)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (pickUpLocation == null) {
			if (other.pickUpLocation != null) {
				return false;
			}
		} else if (!pickUpLocation.equals(other.pickUpLocation)) {
			return false;
		}
		if (routeID != other.routeID) {
			return false;
		}
		return true;
	}
	
}
