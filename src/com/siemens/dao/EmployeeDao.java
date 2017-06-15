package com.siemens.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.siemens.bean.Employee;
import com.siemens.dbconnection.ConnectionProvider;

public class EmployeeDao {

	private Connection conn;

	public EmployeeDao() {
		conn = ConnectionProvider.getConnection();
	}

	public void addEmployee(Employee emp) {
		try {
			String sql = "INSERT INTO employee(emp_id, emp_name,address,latitude,longitude,way_point,pincode)"
					+ " VALUES (?, ?, ? ,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, emp.getId());
			ps.setString(2, emp.getName());
			ps.setString(3, emp.getAddress());
			ps.setString(4, emp.getLatitude());
			ps.setString(5, emp.getLongitude());
			ps.setString(6, emp.getPickUpPoint());
			ps.setString(7, emp.getPincode());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateEmployeeList(Employee emp) {
		
	}

	public void removeEmployee(String empId) {
		try {
			String sql = "DELETE FROM employee WHERE emp_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, empId);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateEmployee(Employee emp) {
		try {
			String sql = "UPDATE employee SET emp_name=?, address=?,latitude=?, longitude=?, way_point=?, pincode=?"
					+ " WHERE emp_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, emp.getName());
			ps.setString(2, emp.getAddress());
			ps.setString(3, emp.getLatitude());
			ps.setString(4, emp.getLongitude());
			ps.setString(5, emp.getPickUpPoint());
			ps.setString(6, emp.getPincode());
			ps.setString(7, emp.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Employee> getAllEmployee() {
		List<Employee> employeeList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM employee";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getString("emp_id"));
				emp.setEmpName(rs.getString("emp_name"));
				emp.setAddress(rs.getString("address"));
				emp.setLongitude(rs.getString("longitude"));
				emp.setLatitude(rs.getString("latitude"));
				emp.setPickUpPoint(rs.getString("way_point"));
				emp.setPincode(rs.getString("pincode"));
				employeeList.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employeeList;
	}

	public Employee getEmployeeById(String empId) {
		Employee emp = new Employee();
		try {
			String sql = "SELECT * FROM employee WHERE emp_id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, empId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				emp.setId(rs.getString("emp_id"));
				emp.setEmpName(rs.getString("emp_name"));
				emp.setAddress(rs.getString("address"));
				emp.setLatitude(rs.getString("latitude"));
				emp.setLongitude(rs.getString("longitude"));
				emp.setPickUpPoint(rs.getString("way_point"));
				emp.setPincode(rs.getString("pincode"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}
	
	public List<Employee> getEmployeesByPickUpPoint(String PickUpPointId) {
		List<Employee> employeeList = new ArrayList<>();
		try {

			String sql = "SELECT * FROM Employee WHERE way_point=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, PickUpPointId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getString("emp_id"));
				emp.setEmpName(rs.getString("emp_name"));
				emp.setAddress(rs.getString("address"));
				emp.setLatitude(rs.getString("latitude"));
				emp.setLongitude(rs.getString("longitude"));
				emp.setPincode(rs.getString("pincode"));
				employeeList.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employeeList;

	}

}