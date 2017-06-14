package com.siemens.bean;

import java.util.List;

import com.siemens.bean.LatLong;

public class PickUpPoint {
	
	private int id;

	// change it to PickUp Name
	private String pickUpLocation;
	private LatLong geoLocation;
	private List<Employee> employees;

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

	public PickUpPoint() {
	};

	public PickUpPoint(LatLong geoLocation, List<Employee> empList) {
		this.geoLocation = geoLocation;
		this.employees = empList;

	}

	/*
	 * (non-Javadoc)
	 * 
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
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		return true;
	}

	@Override
	public String toString() {
		return "PickUpPoint [id=" + id + ", pickUpLocation=" + pickUpLocation + ", geoLocation=" + geoLocation
				+ ", employees=" + employees + ", getId()=" + getId() + ", getPickUpLocation()=" + getPickUpLocation()
				+ ", getGeoLocation()=" + getGeoLocation() + ", getEmployees()=" + getEmployees() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
