package com.siemens.bean;

import java.util.List;

import com.siemens.bean.LatLong;

public class PickUpPoint {
	
	private int id;

	// change it to PickUp Name
	private String pickUpLocation;
	private LatLong geoLocation;
	private List<Employee> employees;
    private String routID;
	
	public PickUpPoint(int id, String pickUpLocation, LatLong geoLocation, List<Employee> employees, String routID) {
		super();
		this.id = id;
		this.pickUpLocation = pickUpLocation;
		this.geoLocation = geoLocation;
		this.employees = employees;
		this.routID = routID;
	}
	
	public PickUpPoint(LatLong geoLocation, List<Employee> empList) {
		this.geoLocation = geoLocation;
		this.employees = empList;

	}
	public PickUpPoint() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPickUpLocation() {
		return pickUpLocation;
	}
	public void setPickUpLocation(String pickUpLocation) {
		this.pickUpLocation = pickUpLocation;
	}
	public LatLong getGeoLocation() {
		return geoLocation;
	}
	public void setGeoLocation(LatLong geoLocation) {
		this.geoLocation = geoLocation;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public String getRoutID() {
		return routID;
	}
	public void setRoutID(String routID) {
		this.routID = routID;
	}


	@Override
	public String toString() {
		return "PickUpPoint [id=" + id + ", pickUpLocation=" + pickUpLocation + ", geoLocation=" + geoLocation
				+ ", employees=" + employees + ", routID=" + routID + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employees == null) ? 0 : employees.hashCode());
		result = prime * result + ((geoLocation == null) ? 0 : geoLocation.hashCode());
		result = prime * result + id;
		result = prime * result + ((pickUpLocation == null) ? 0 : pickUpLocation.hashCode());
		result = prime * result + ((routID == null) ? 0 : routID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PickUpPoint other = (PickUpPoint) obj;
		if (employees == null) {
			if (other.employees != null)
				return false;
		} else if (!employees.equals(other.employees))
			return false;
		if (geoLocation == null) {
			if (other.geoLocation != null)
				return false;
		} else if (!geoLocation.equals(other.geoLocation))
			return false;
		if (id != other.id)
			return false;
		if (pickUpLocation == null) {
			if (other.pickUpLocation != null)
				return false;
		} else if (!pickUpLocation.equals(other.pickUpLocation))
			return false;
		if (routID == null) {
			if (other.routID != null)
				return false;
		} else if (!routID.equals(other.routID))
			return false;
		return true;
	}
}
