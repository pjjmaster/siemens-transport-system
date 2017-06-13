package com.siemens.controller;

import org.springframework.web.client.RestTemplate;

import com.siemens.bean.Distance;
import com.siemens.bean.EndLocation;
import com.siemens.bean.Example;
import com.siemens.bean.StartLocation;
import com.siemens.config.RestTemplateSingleton;

public class GoogleApisImpl implements GoogleApis {

	private RestTemplate restTemplate = RestTemplateSingleton.getRestTemplate();

	@Override
	public String getDistanceBetLocations(StartLocation start, EndLocation end) {
		Example response = restTemplate.getForObject("http://maps.googleapis.com/maps/api/directions/json" + "?origin="
				+ start.getLat() + "," + start.getLng() + "&destination=" + end.getLat() + "," + end.getLng()
				+ "&sensor=false&units=metric&mode=driving", Example.class);

		Distance distance = response.getRoutes().get(0).getLegs().get(0).getDistance();
		return distance.getText();
	}

}
