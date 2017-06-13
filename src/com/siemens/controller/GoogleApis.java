package com.siemens.controller;

import com.siemens.bean.EndLocation;
import com.siemens.bean.StartLocation;

public interface GoogleApis {

	String getDistanceBetLocations(StartLocation start , EndLocation end);
	
}
