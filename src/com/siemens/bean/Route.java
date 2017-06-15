package com.siemens.bean;

public class Route {
	
	int routeID;
	String routeName;
    String routeCost;
    String routeDistance;
    String routeTravelTime;
    String busID;
    
    public Route()
    {
    	
    }
	public Route(int routeID, String routeName, String routeCost, String routeDistance, String routeTravelTime,
			String busID) {
		super();
		this.routeID = routeID;
		this.routeName = routeName;
		this.routeCost = routeCost;
		this.routeDistance = routeDistance;
		this.routeTravelTime = routeTravelTime;
		this.busID = busID;
	}
	

	
	public Route( int routeID, String busID ) {
		super();
		this.routeID = routeID;
		this.busID = busID;
	}
	public int getRouteID() {
		return routeID;
	}
	public void setRouteID(int routeID) {
		this.routeID = routeID;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public String getRouteCost() {
		return routeCost;
	}
	public void setRouteCost(String routeCost) {
		this.routeCost = routeCost;
	}
	public String getRouteDistance() {
		return routeDistance;
	}
	public void setRouteDistance(String routeDistance) {
		this.routeDistance = routeDistance;
	}
	public String getRouteTravelTime() {
		return routeTravelTime;
	}
	public void setRouteTravelTime(String routeTravelTime) {
		this.routeTravelTime = routeTravelTime;
	}
	public String getBusID() {
		return busID;
	}
	public void setBusID(String busID) {
		this.busID = busID;
	}
	
	  @Override
		public String toString() {
			return "Route [routeID=" + routeID + ", routeName=" + routeName + ", routeCost=" + routeCost
					+ ", routeDistance=" + routeDistance + ", routeTravelTime=" + routeTravelTime + ", busID=" + busID
					+ "]";
   }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((busID == null) ? 0 : busID.hashCode());
		result = prime * result + ((routeCost == null) ? 0 : routeCost.hashCode());
		result = prime * result + ((routeDistance == null) ? 0 : routeDistance.hashCode());
		result = prime * result + routeID;
		result = prime * result + ((routeName == null) ? 0 : routeName.hashCode());
		result = prime * result + ((routeTravelTime == null) ? 0 : routeTravelTime.hashCode());
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
		Route other = (Route) obj;
		if (busID == null) {
			if (other.busID != null)
				return false;
		} else if (!busID.equals(other.busID))
			return false;
		if (routeCost == null) {
			if (other.routeCost != null)
				return false;
		} else if (!routeCost.equals(other.routeCost))
			return false;
		if (routeDistance == null) {
			if (other.routeDistance != null)
				return false;
		} else if (!routeDistance.equals(other.routeDistance))
			return false;
		if (routeID != other.routeID)
			return false;
		if (routeName == null) {
			if (other.routeName != null)
				return false;
		} else if (!routeName.equals(other.routeName))
			return false;
		if (routeTravelTime == null) {
			if (other.routeTravelTime != null)
				return false;
		} else if (!routeTravelTime.equals(other.routeTravelTime))
			return false;
		return true;
	}
	  
	  
}
