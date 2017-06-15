package com.siemens.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.siemens.bean.Bus;
import com.siemens.dbconnection.ConnectionProvider;

public class BusDao {
	
	
	  private Connection conn;

	    public BusDao() {
	    	conn = ConnectionProvider.getConnection();
	    }

	    public void addBus(Bus bus) {
	        try {
	        	String sql = "INSERT INTO bus(BUSID,BUSNAME,BUSCAPACITY,BUSCOSTPKPM)" +
	    		" VALUES (?, ?, ? ,?)";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            
	            ps.setString(1, bus.getBusID());
	            ps.setString(2, bus.getBusName());
	            ps.setInt(3, bus.getBusCapacity()); 
	            ps.setDouble(4, bus.getBusCostPKPM());
	            ps.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void removeBus(String busId) {
	        try {
	        	String sql = "DELETE FROM bus WHERE BUSID=?";
	            PreparedStatement ps = conn
	                    .prepareStatement(sql);
	            ps.setString(1, busId);
	            ps.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	      }

	    public void editBus(Bus bus) {    	
	    	try {
	    		String sql = "UPDATE bus SET BUSNAME=?, BUSCAPACITY=?,BUSCOSTPKPM=?"+
	            " WHERE BUSID=?";
	            PreparedStatement ps = conn
	                    .prepareStatement(sql);
	            ps.setString(1, bus.getBusName());
	            ps.setInt(2, bus.getBusCapacity()); 
	            ps.setDouble(3, bus.getBusCostPKPM());
	            ps.executeUpdate();            

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public List<Bus> getAllBus() {
	        List<Bus> BusList= new ArrayList<>();
	        try {
	        	String sql = "SELECT * FROM bus";
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                Bus bus = new Bus();
	                bus.setBusID(rs.getString("BUSID"));
	                bus.setBusName(rs.getString("BUSNAME"));
	                bus.setBusCapacity(rs.getInt("BUSCAPACITY")); 
	                bus.setBusCostPKPM((rs.getDouble("BUSCOSTPKPM")));
	           
	                BusList.add(bus);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return BusList;
	    }

	    public Bus getBusById(String busId) {
	    	Bus bus = new Bus();
	        try {
	        	String sql = "SELECT * FROM Bus WHERE BUSID=?";
	            PreparedStatement ps = conn.
	                    prepareStatement(sql);
	            ps.setString(1, busId);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                    bus.setBusID(busId);
		                bus.setBusName(rs.getString("BUSNAME"));
		                bus.setBusCapacity(rs.getInt("BUSCAPACITY")); 
		                bus.setBusCostPKPM((rs.getDouble("BUSCOSTPKPM")));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return bus;
	    }
	    
	    public boolean clearAllBus() {
			boolean recordDeleted = false;
			try {
				String sql = "DELETE FROM bus";
				PreparedStatement ps = conn.prepareStatement(sql);
				recordDeleted = ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return recordDeleted;
		}

}
