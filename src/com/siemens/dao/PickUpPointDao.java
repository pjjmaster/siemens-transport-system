package com.siemens.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.siemens.bean.LatLong;
import com.siemens.bean.PickUpPoint;
import com.siemens.dbconnection.ConnectionProvider;

public class PickUpPointDao {
	private Connection conn;

	public PickUpPointDao() {
		conn = ConnectionProvider.getConnection();
	}

	public void savePickUpPoint(PickUpPoint pickUpPoint) {

		try {
			String sql = "INSERT INTO PICKUPPOINT (PICKUPPOINTID,GEOLOCATION_LAT,GEOLOCATION_LONG,ADDRESS)"
					+ " VALUES (?, ?, ? ,?)";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, pickUpPoint.getId());
			ps.setString(2, pickUpPoint.getGeoLocation().getLatitude());
			ps.setString(3, pickUpPoint.getGeoLocation().getLongitude());
			ps.setString(4, pickUpPoint.getPickUpLocation());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void savePickUpPointsList(List<PickUpPoint> picikUpPointList){
		
		for(PickUpPoint pickPoint:picikUpPointList){
			savePickUpPoint(pickPoint);
		}
		
	}

	public PickUpPoint getPickUpPointById(int pickUpId) {
		PickUpPoint pickUpPoint = new PickUpPoint();
		try {
			String sql = "SELECT * FROM PICKUPPOINT WHERE PICKUPPOINTID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, pickUpId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				pickUpPoint.setPickUpLocation(rs.getString("ADDRESS"));
				LatLong latlong = new LatLong(rs.getString("GEOLOCATION_LAT"), rs.getString("GEOLOCATION_LONG"));
				pickUpPoint.setGeoLocation(latlong);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pickUpPoint;
	}
	
	public void updateRouteId(int pickUpId, int routeId) {
		try {
			String sql = "UPDATE PICKUPPOINT SET ROUTEID=? WHERE PICKUPPOINTID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, routeId);
			ps.setInt(2, pickUpId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	} 

	public boolean clearAllPickUpPoits() {
		boolean recordDeleted = false;
		try {
			String sql = "DELETE FROM PICKUPPOINT";
			PreparedStatement ps = conn.prepareStatement(sql);
			recordDeleted = ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recordDeleted;
	}
	 
		 public List<PickUpPoint> getAllPickUpPoints() {
			        List<PickUpPoint> PickUpPointList= new ArrayList<>();
			        try {
			        	String sql = "SELECT * FROM PICKUPPOINT";
			            PreparedStatement ps = conn.prepareStatement(sql);
			            ResultSet rs = ps.executeQuery();
			            while (rs.next()) {
			                PickUpPoint pickUpPoint = new PickUpPoint();
			                pickUpPoint.setId(rs.getInt("PICKUPPOINTID"));
			                LatLong latlong = new LatLong(rs.getString("GEOLOCATION_LAT"), rs.getString("GEOLOCATION_LONG"));
							pickUpPoint.setGeoLocation(latlong);
			                pickUpPoint.setPickUpLocation(rs.getString("ADDRESS")); 
			                pickUpPoint.setEmployees(new EmployeeDao().getEmployeesByPickUpPoint(String.valueOf(pickUpPoint.getId())));
			                PickUpPointList.add(pickUpPoint);
			            }
			        } catch (SQLException e) {
			            e.printStackTrace();
			        }

			        return PickUpPointList;
			    } 

}
