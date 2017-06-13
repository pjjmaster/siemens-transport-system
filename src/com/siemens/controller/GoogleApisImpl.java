package com.siemens.controller;

import org.springframework.web.client.RestTemplate;

import com.siemens.bean.addressapi.Address;
import com.siemens.bean.distanceapi.Distance;
import com.siemens.bean.distanceapi.EndLocation;
import com.siemens.bean.distanceapi.Example;
import com.siemens.bean.distanceapi.StartLocation;
import com.siemens.bean.placeapi.Location;
import com.siemens.bean.placeapi.Place;
import com.siemens.config.RestTemplateSingleton;

public class GoogleApisImpl implements GoogleApis {

	private RestTemplate restTemplate = RestTemplateSingleton.getRestTemplate();

	String googleapikey = "AIzaSyBPHqIMR9dGhP1LyOI5wZPuSGrhUVqLwRY";

	String placeApi = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location={longitude},{lattitude}&radius=250&type=bus_station&key="
			+ googleapikey;

	@Override
	public String getDistanceBetLocations(StartLocation start, EndLocation end) {
		Distance distance = null;
		try {
			Example response = restTemplate.getForObject("http://maps.googleapis.com/maps/api/directions/json"
					+ "?origin=" + start.getLat() + "," + start.getLng() + "&destination=" + end.getLat() + ","
					+ end.getLng() + "&sensor=false&units=metric&mode=driving", Example.class);
			distance = response.getRoutes().get(0).getLegs().get(0).getDistance();
		} catch (Exception e) {
			return null;
		}
		return distance.getText();
	}

	@Override
	public Location getNearByPlace(Location startLocation) {
		Location location = null;
		try {
			Place place = restTemplate.getForObject(placeApi, Place.class, startLocation.getLat(),
					startLocation.getLng());
			location = place.getResults().get(0).getGeometry().getLocation();
		} catch (Exception e) {
			return null;
		}
		return location;
	}

	public String getAddressForLocation(double lat, double lng) {
		String add = null;
		try {
			Address address = restTemplate.getForObject(
					"https://maps.googleapis.com/maps/api/geocode/json?latlng={lat},{lng}&key=AIzaSyBPHqIMR9dGhP1LyOI5wZPuSGrhUVqLwRY",
					Address.class, lat, lng);
			add = address.getResults().get(0).getFormattedAddress();
		} catch (Exception e) {
			return null;
		}
		return add;
	}


}
