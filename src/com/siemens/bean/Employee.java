package com.siemens.bean;

public class Employee {

    private String id;
    private String name;
    private String address;
    private String latitude;
    private String longitude;
    private String pickUpPoint;
    private String    pincode;
	public String getId() {
		return id;
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
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", address=" + address + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", pickUpPoint=" + pickUpPoint + ", pincode=" + pincode + "]";
	}
        
    
  	
}