package com.siemens.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.siemens.bean.Route;
import com.siemens.dbconnection.ConnectionProvider;

public class RouteDao {

	private Connection conn;

	public RouteDao() {
		conn = ConnectionProvider.getConnection();
	}

	public void addRoute(Route route) {
		try {
			String sql = "INSERT INTO route(ROUTEID, ROUTENAME,ROUTECOST,ROUTEDISTANCE,ROUTETRAVELTIME,BUSID)"
					+ " VALUES (?, ?, ? ,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, route.getRouteID());
			ps.setString(2, route.getRouteName());
			ps.setString(3, route.getRouteCost());
			ps.setString(4, route.getRouteDistance());
			ps.setString(5, route.getRouteTravelTime());
			ps.setString(6, route.getBusID());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeRoute(int routeId) {
		try {
			String sql = "DELETE FROM route WHERE ROUTEID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, routeId);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editRoute(Route route) {
		try {
			String sql = "UPDATE route SET ROUTENAME=?, ROUTECOST=?,ROUTEDISTANCE=?, ROUTETRAVELTIME=?, BUSID=?"
					+ " WHERE ROUTEID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, route.getRouteName());
			ps.setString(2, route.getRouteCost());
			ps.setString(3, route.getRouteDistance());
			ps.setString(4, route.getRouteTravelTime());
			ps.setString(5, route.getBusID());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Route> getAllRoute() {
		List<Route> RouteList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM route";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Route route = new Route();
				route.setRouteID(rs.getInt("ROUTEID"));
				route.setRouteName(rs.getString("ROUTENAME"));
				route.setRouteCost(rs.getString("ROUTECOST"));
				route.setRouteDistance(rs.getString("ROUTEDISTANCE"));
				route.setRouteTravelTime(rs.getString("ROUTETRAVELTIME"));
				route.setBusID(rs.getString("BUSID"));
				RouteList.add(route);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return RouteList;
	}

	public Route getRouteById(int routeId) {
		Route route = new Route();
		try {
			String sql = "SELECT * FROM Route WHERE ROUTEID=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, routeId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				route.setRouteName(rs.getString("ROUTENAME"));
				route.setRouteCost(rs.getString("ROUTECOST"));
				route.setRouteDistance(rs.getString("ROUTEDISTANCE"));
				route.setRouteTravelTime(rs.getString("ROUTETRAVELTIME"));
				route.setBusID(rs.getString("BUSID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return route;
	}

	public boolean clearAllRoot() {
		boolean recordDeleted = false;
		try {
			String sql = "DELETE FROM route";
			PreparedStatement ps = conn.prepareStatement(sql);
			recordDeleted = ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recordDeleted;
	}

	public void addAllRoute(List<Route> routeList) {
		for (Route route : routeList) {
			addRoute(route);
		}
	}
}
