package com.siemens.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.siemens.bean.Employee;
import com.siemens.bean.LatLong;
import com.siemens.bean.PickUpPoint;

import com.siemens.bean.distanceapi.EndLocation;
import com.siemens.bean.distanceapi.StartLocation;
import com.siemens.controller.GoogleApis;
import com.siemens.controller.GoogleApisImpl;
import com.siemens.dao.EmployeeDao;

public class RouterUtil {

	static GoogleApis service = new GoogleApisImpl();
	static EmployeeDao empDao = new EmployeeDao();
	final static String DEF_LOCATION_NAME = "Siemens Employee PickUp Location";

	public static double getDistanceBwLatlong(LatLong position1, LatLong position2) {

		StartLocation startloc = new StartLocation();
		startloc.setLat(Double.parseDouble(position1.getLatitude()));
		startloc.setLng(Double.parseDouble(position1.getLongitude()));

		EndLocation endLoc = new EndLocation();
		endLoc.setLat(Double.parseDouble(position2.getLatitude()));
		endLoc.setLng(Double.parseDouble(position2.getLongitude()));
		String distance = service.getDistanceBetLocations(startloc, endLoc);

		if (distance.contains(" ")) {
			return Double.parseDouble(distance.substring(0, distance.indexOf(' ')));
		}

		return 0.00;
	}

	public static String getGeoHashCode(LatLong l1) throws Exception {
		final String geoHashUrl = "http://geohash.org/?q=";
		final String headerString = "http://geohash.org/";
		URL url = new URL(geoHashUrl + l1.getLatitude() + "," + l1.getLongitude() + "&format=url&redirect=0");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		String line, outputString = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		while ((line = reader.readLine()) != null) {
			outputString += line;
		}
		System.out.println(" outputString " + outputString);

		String geoHashCode = outputString.substring(headerString.length());

		return geoHashCode;

	}

	public static List<Employee> getGeoCodeAttachedEmployeeList(List<Employee> empList) {
		List<Employee> empwithGeoList = new ArrayList<>();
		for (Employee emp : empList) {
			try {
				emp.setGeoHashCode(getGeoHashCode(new LatLong(emp.getLatitude(), emp.getLongitude())));
				empwithGeoList.add(emp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return empwithGeoList;
	}

	public static HashMap<String, List<Employee>> findClusteredEmployee(List<Employee> employeeList) {

		HashMap<String, List<Employee>> clusteredMap = new HashMap<>();

		List<Employee> employeeWithGeoCodes = getGeoCodeAttachedEmployeeList(empDao.getAllEmployee());

		for (Employee emp : employeeWithGeoCodes) {

			String formatedGeoCode = emp.getGeoHashCode().substring(0, 4);

			List<Employee> empListWithsimilarGeocode = clusteredMap.get(formatedGeoCode);
			if (empListWithsimilarGeocode != null) {

				empListWithsimilarGeocode.add(emp);
				clusteredMap.put(formatedGeoCode, empListWithsimilarGeocode);
			} else {
				ArrayList<Employee> freshList = new ArrayList<>();
				freshList.add(emp);
				clusteredMap.put(emp.getGeoHashCode().substring(0, 4), freshList);

			}

		}
		System.out.println("number of clusters+ " + clusteredMap.size());
		return clusteredMap;

	}

	public static LatLong getCentralLocationToCluster(List<LatLong> latLongList) {

		// List<LatLong> latLongList
		double centralLatitude = 0.0;
		double centralLongitude = 0.0;
		for (LatLong latlong : latLongList) {
			centralLatitude += Double.parseDouble(latlong.getLatitude());
			centralLongitude += Double.parseDouble(latlong.getLongitude());
		}

		return new LatLong(String.valueOf(centralLatitude / latLongList.size()),
				String.valueOf(centralLongitude / latLongList.size()));

	}

	public static String getLocationName(LatLong latLong) throws Exception {
		String locationName;
		locationName = service.getAddressForLocation(Double.parseDouble(latLong.getLatitude()),
				Double.parseDouble(latLong.getLongitude()));
		if (null == locationName) {
			locationName = DEF_LOCATION_NAME;
		}
		return locationName;
	}

	/*
	 * 
	 * 
	 * nearBy location Probably bus stop
	 */
	public static LatLong getNearByPlace(LatLong latLong) throws Exception {
		com.siemens.bean.placeapi.Location location = new com.siemens.bean.placeapi.Location();
		location.setLat(Double.parseDouble(latLong.getLatitude()));
		location.setLng(Double.parseDouble(latLong.getLongitude()));

		com.siemens.bean.placeapi.Location nearByPlace = service.getNearByPlace(location);
		if (null == nearByPlace) {
			System.out.println("Near By Place Not Found For :" + latLong);
			nearByPlace = location;
		}
		return new LatLong(String.valueOf(nearByPlace.getLat()), String.valueOf(nearByPlace.getLng()));

	}

	public static List<PickUpPoint> getPickUpPoints(HashMap<String, List<Employee>> clusterdMap) throws Exception {

		List<PickUpPoint> pickUpPointList = new ArrayList<>();

		// Get All clusterPoint
		Set<String> custerset = clusterdMap.keySet();
		List<LatLong> latlongList = new ArrayList<>();
		for (String cluster : custerset) {

			List<Employee> employeeList = clusterdMap.get(cluster);

			for (Employee emp : employeeList) {
				latlongList.add(new LatLong(emp.getLatitude(), emp.getLongitude()));
			}
			LatLong cetralLatLong = getCentralLocationToCluster(latlongList);
			System.out.println("central Location For " + cluster + " " + cetralLatLong);

			LatLong nearestLocationToCentralPoint = null;
			try {
				nearestLocationToCentralPoint = getNearByPlace(cetralLatLong);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PickUpPoint pickPoint = null;
			if (null == nearestLocationToCentralPoint) {
				pickPoint = new PickUpPoint(nearestLocationToCentralPoint, employeeList);
			} else {
				pickPoint = new PickUpPoint(nearestLocationToCentralPoint, employeeList);
			}
			LatLong nearByPlace = getNearByPlace(cetralLatLong);
			String locationName = getLocationName(nearByPlace);
			if (null != locationName) {
				pickPoint.setPickUpLocation(locationName);
				pickPoint.setId(Math.abs(locationName.hashCode()));
			} else {
				pickPoint.setPickUpLocation(DEF_LOCATION_NAME);
				pickPoint.setId(Math.abs(nearestLocationToCentralPoint.hashCode()));
				System.out.println("Pick Up Id Is " + pickPoint.getId());

			}
			pickUpPointList.add(pickPoint);

		}
		System.out.println("All pickUp Points are " + pickUpPointList);
		return pickUpPointList;

	}

}
