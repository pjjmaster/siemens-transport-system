package com.siemens.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.siemens.bean.distanceapi.LatLong;

public class RouterUtil {

public static void main(String ...args){
	LatLong l1= new LatLong("18.5031113","73.82860440000002");
	LatLong l2= new LatLong("18.4524761","73.85580470000002");
	getDistanceBwLatlong(l1, l2);
	
}
	public static double getDistanceBwLatlong(LatLong position1, LatLong position2) {
		try {

			URL url = new URL("http://maps.googleapis.com/maps/api/directions/json?origin=" + position1.getLatitude()
					+ "," + position1.getLongitude() + "&destination=" + position2.getLatitude() + ","
					+ position2.getLongitude() + "&sensor=false&units=metric&mode=driving");
			// URL url = new
			// URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins=Vancouver+BC|Seattle&destinations=San+Francisco|Victoria+BC");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			String line, outputString = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				outputString += line;
			}
			System.out.println(" outputString "+outputString);
			JSONParser parser = new JSONParser();

			Object jsonObj = parser.parse(outputString);

			JSONObject jsonObject = (JSONObject) jsonObj;

			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0.0;
	}
}