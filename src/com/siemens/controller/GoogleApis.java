package com.siemens.controller;

import com.siemens.bean.distanceapi.EndLocation;
import com.siemens.bean.distanceapi.StartLocation;
import com.siemens.bean.placeapi.Location;

public interface GoogleApis {

	String getDistanceBetLocations(StartLocation start, EndLocation end);

	Location getNearByPlace(Location location);

}
