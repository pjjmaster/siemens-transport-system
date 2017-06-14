package com.siemens.bean;

public class Employee {

    private String id;
    private String name;
    private String address;
    private String latitude;
    private String longitude;
    private String pickUpPoint;
    private String pincode;
    private String	geoHashCode;
	public String getId() {
		return id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((geoHashCode == null) ? 0 : geoHashCode.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pickUpPoint == null) ? 0 : pickUpPoint.hashCode());
		result = prime * result + ((pincode == null) ? 0 : pincode.hashCode());
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
		if (!(obj instanceof Employee)) {
			return false;
		}
		Employee other = (Employee) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
			return false;
		}
		if (geoHashCode == null) {
			if (other.geoHashCode != null) {
				return false;
			}
		} else if (!geoHashCode.equals(other.geoHashCode)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (latitude == null) {
			if (other.latitude != null) {
				return false;
			}
		} else if (!latitude.equals(other.latitude)) {
			return false;
		}
		if (longitude == null) {
			if (other.longitude != null) {
				return false;
			}
		} else if (!longitude.equals(other.longitude)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (pickUpPoint == null) {
			if (other.pickUpPoint != null) {
				return false;
			}
		} else if (!pickUpPoint.equals(other.pickUpPoint)) {
			return false;
		}
		if (pincode == null) {
			if (other.pincode != null) {
				return false;
			}
		} else if (!pincode.equals(other.pincode)) {
			return false;
		}
		return true;
	}
	public void setId(String empId) {
		this.id = empId;
	}
	public String getName() {
		return name;
	}
	public void setEmpName(String empName) {
		this.name = empName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
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
	public String getPickUpPoint() {
		return pickUpPoint;
	}
	public void setPickUpPoint(String pickUpPoint) {
		this.pickUpPoint = pickUpPoint;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	public String getGeoHashCode() {
		return geoHashCode;
	}
	public void setGeoHashCode(String geoHashCode) {
		this.geoHashCode = geoHashCode;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", address=" + address + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", pickUpPoint=" + pickUpPoint + ", pincode=" + pincode
				+ ", geoHashCode=" + geoHashCode + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getAddress()=" + getAddress() + ", getLatitude()=" + getLatitude() + ", getLongitude()="
				+ getLongitude() + ", getPickUpPoint()=" + getPickUpPoint() + ", getPincode()=" + getPincode()
				+ ", getGeoHashCode()=" + getGeoHashCode() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
        
    
  	
}