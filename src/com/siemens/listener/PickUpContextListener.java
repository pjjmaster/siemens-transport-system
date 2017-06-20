package com.siemens.listener;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.siemens.bean.Employee;
import com.siemens.bean.PickUpPoint;
import com.siemens.dao.EmployeeDao;
import com.siemens.dao.PickUpPointDao;
import com.siemens.dao.RouteDao;
import com.siemens.util.RouterUtil;
import com.siemens.util.VehicleRoutingUtil;

public class PickUpContextListener implements ServletContextListener {

	ServletContext ctx;
	EmployeeDao empDao = new EmployeeDao();
	PickUpPointDao pdao = new PickUpPointDao();
	RouteDao rdao = new RouteDao();

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Context Listener intialization Started");
		System.out.println("Clearing Exsisting Pickup Points");
		pdao.clearAllPickUpPoits();
		System.out.println("Clearing Exsisting Routes");
		rdao.clearAllRoot();
		System.out.println("Context Listener intialization Complete");
		try {
			List<Employee> empList = new EmployeeDao().getAllEmployee();
			HashMap<String, List<Employee>> clustedEmpList = RouterUtil.findClusteredEmployee(empList);
			List<PickUpPoint> pickUpPoints = RouterUtil.getPickUpPoints(clustedEmpList);
			// save pickUp Points to database
			pdao.savePickUpPointsList(pickUpPoints);
			// update each employee Pickup point
			for (PickUpPoint pickUp : pickUpPoints) {
				for (Employee emp : pickUp.getEmployees()) {
					emp.setPickUpPoint(String.valueOf(pickUp.getId()));
					empDao.updateEmployee(emp);
				}

			}

			// add entry in Route Id and PickPoint for Optimal path

			VehicleRoutingUtil.findOptimalRoute();
			System.out.println("added routing data");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {

		// ConnectionProvider.closeConnection();
	}

}
