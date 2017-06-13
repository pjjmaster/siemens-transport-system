package com.siemens.bean;

import java.util.List;

public class Route {
	private int id;
	private String routeName;
	private List<PickupPoint> pickupPoints;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public List<PickupPoint> getPickupPoints() {
		return pickupPoints;
	}
	public void setPickupPoints(List<PickupPoint> pickupPoints) {
		this.pickupPoints = pickupPoints;
	}

}
