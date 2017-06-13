package com.siemens.bean;

import java.util.List;

import com.siemens.bean.distanceapi.LatLong;

public class PickupPoint {
	private int id;
	private String pickUpLocation;
	private LatLong geoLocation;
	private List<Employee> employees;
}
