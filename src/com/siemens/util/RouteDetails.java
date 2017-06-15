package com.siemens.util;

public class RouteDetails {
	
	 int routNumber;
	 String vehicleName;
	 String activity;
	 String pickUpID;
	 Double arrTime;
	 long endTime;
	 double costs;
	public int getRoutNumber() {
		return routNumber;
	}
	public void setRoutNumber(int routNumber) {
		this.routNumber = routNumber;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getpickUpID() {
		return pickUpID;
	}
	public void setpickUpID(String pickUpID) {
		this.pickUpID = pickUpID;
	}
	public Double getArrTime() {
		return arrTime;
	}
	public void setArrTime(Double arrTime) {
		this.arrTime = arrTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public double getCosts() {
		return costs;
	}
	public void setCosts(double costs) {
		this.costs = costs;
	}
	@Override
	public String toString() {
		return "RouteDetails [routNumber=" + routNumber + ", vehicleName=" + vehicleName + ", activity=" + activity
				+ ", pickUpID=" + pickUpID + ", arrTime=" + arrTime + ", endTime=" + endTime + ", costs=" + costs
				+ "]";
	}
	public RouteDetails(int routNumber, String vehicleName, String activity, String pickUpID,
			long endTime, double costs) {
		super();
		this.routNumber = routNumber;
		this.vehicleName = vehicleName;
		this.activity = activity;
		this.pickUpID = pickUpID;
		this.endTime = endTime;
		this.costs = costs;
	}

	public RouteDetails(int routNumber, String vehicleName) {
		super();
		this.routNumber = routNumber;
		this.vehicleName = vehicleName;
	
	}


	

}
