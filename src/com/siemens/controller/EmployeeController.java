package com.siemens.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.expression.MapAccessor;

import com.google.gson.Gson;
import com.siemens.bean.Employee;
import com.siemens.dao.EmployeeDao;
import com.siemens.service.PickUpService;


public class EmployeeController extends HttpServlet {    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String INSERT = "/employee.jsp";
    private static String Edit = "/edit.jsp";
    private static String EmployeeRecord = "/listUser.jsp";
    private EmployeeDao dao;
    PrintWriter out;
   
    public EmployeeController() {
        super();
        dao = new EmployeeDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect="";
        String empId = request.getParameter("empId"); 
        String name=   request.getParameter("empName");
        String address=request.getParameter("address");
        String latitude= request.getParameter("latitude");
        String longitude= request.getParameter("longitude");
        String pickUpPoint=request.getParameter("pickUpPoint");
        String action = request.getParameter("action");
        String pincode=request.getParameter("pinCode");
        if((null!=empId) && action.equalsIgnoreCase("addEmployee"))
        {
        	
        	 Employee emp = new Employee();
        	 emp.setId(empId);
        	 emp.setEmpName(name);
        	 emp.setAddress(address);
        	 emp.setLatitude(latitude);
        	 emp.setLongitude(longitude);
        	 emp.setPickUpPoint(pickUpPoint);
        	 emp.setPincode(pincode);
        	dao.addEmployee(emp);
        	redirect = EmployeeRecord;
            request.setAttribute("employeeList", dao.getAllEmployee());    
           	System.out.println("Record Added Successfully");
        }
        else if (action.equalsIgnoreCase("delete")){
            dao.removeEmployee(empId);
            redirect = EmployeeRecord;
            request.setAttribute("employeeList", dao.getAllEmployee());
            System.out.println("Record Deleted Successfully");
        }else if (action.equalsIgnoreCase("editform")){        	
        	redirect = Edit;            
        } else if (action.equalsIgnoreCase("edit")){
            Employee employee = new Employee();
            System.out.println("Record updated Successfully");
            employee.setId(empId);
            employee.setEmpName(name);
            employee.setAddress(address);
            employee.setLatitude(latitude);
            employee.setLongitude(longitude);
            employee.setPickUpPoint(pickUpPoint);
            employee.setPincode((pincode));
            dao.updateEmployee(employee);
            request.setAttribute("employee", employee);
            redirect = EmployeeRecord;
            System.out.println("Record updated Successfully"+employee.toString());
         } else if (action.equalsIgnoreCase("listAllEmployee")){
            redirect = EmployeeRecord;
            request.setAttribute("users", dao.getAllEmployee());
         } 
         else if(action.equalsIgnoreCase("getLatLongsPickUpsOfRoutes")){
        	 Map<Integer,String>allPickUps=PickUpService.getInstance().getAllPickUpLatLong();
        	 String pickUpJson=new Gson().toJson(allPickUps);
        	 response.setContentType("application/json");
        	 response.getWriter().write(pickUpJson);
         }
        else {
         
            redirect = INSERT;
        }

        RequestDispatcher rd = request.getRequestDispatcher(redirect);
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}