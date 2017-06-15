package com.siemens.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.graphhopper.jsprit.core.algorithm.VehicleRoutingAlgorithm;
import com.graphhopper.jsprit.core.algorithm.box.Jsprit;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem.FleetSize;
import com.graphhopper.jsprit.core.problem.job.Service;
import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.graphhopper.jsprit.core.problem.solution.route.VehicleRoute;
import com.graphhopper.jsprit.core.problem.solution.route.activity.TourActivity;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleType;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleTypeImpl;
import com.graphhopper.jsprit.core.reporting.SolutionPrinter;
import com.graphhopper.jsprit.core.util.Solutions;
import com.siemens.bean.Bus;
import com.siemens.bean.PickUpPoint;
import com.siemens.bean.Route;
import com.siemens.dao.BusDao;
import com.siemens.dao.EmployeeDao;
import com.siemens.dao.PickUpPointDao;
import com.siemens.dao.RouteDao;

/**
 * customers (id,x,y,demand)
 * 1 22 22 18
 * 2 36 26 26
 * 3 21 45 11
 * 4 45 35 30
 * 5 55 20 21
 * 6 33 34 19
 * 7 50 50 15
 * 8 55 45 16
 * 9 26 59 29
 * 10 40 66 26
 * 11 55 65 37
 * 12 35 51 16
 * 13 62 35 12
 * 14 62 57 31
 * 15 62 24 8
 * 16 21 36 19
 * 17 33 44 20
 * 18 9 56 13
 * 
 * sele100
 * 19 62 48 15
 * 20 66 14 22
 * <p>
 * vehicles (id,cap,fixed costs, perDistance, #vehicles) at location (40,40)
 * 1 120 1000 1.0 2
 * 2 160 1500 1.1 1
 * 3 300 3500 1.4 1
 *
 * @author schroeder
 */
public class VehicleRoutingUtil {

	static VehicleRoutingProblem.Builder vrpTaskBuilder = VehicleRoutingProblem.Builder.newInstance();;

	///Write Singleton For Each Dao.
	 public static PickUpPointDao PICKUPPOINTDAO = new PickUpPointDao();
	 public  static EmployeeDao EMPLOYEEDAO= new EmployeeDao();
	 public  static BusDao BUSDAO= new BusDao();
	 public  static RouteDao ROUTEDAO= new RouteDao();
	
		
	 //call this method from Outside to find Optimal path.
	 public static void findOptimalRoute(){
		 addJobsToVRPTaskBuilder();
		 createVehicleVsRouteMapping ();
		 
		 
	 }
		public static void addJobsToVRPTaskBuilder()
		{
			List<PickUpPoint>pickUpPointsList = PICKUPPOINTDAO.getAllPickUpPoints();
			for (PickUpPoint pickUpPoint : pickUpPointsList) {
				
				VehicleRoutingUtil.vrpTaskBuilder.addJob(Service.Builder.newInstance(String.valueOf(pickUpPoint.getId())).addSizeDimension(0, pickUpPoint.getEmployees().size()).setLocation(Location.newInstance( Double.parseDouble( pickUpPoint.getGeoLocation().getLatitude()), Double.parseDouble( pickUpPoint.getGeoLocation().getLongitude()))).build());
			}
			
		}

		 
		public static int getNumberOfVehicle()
		{
			int numberOfEmployees = EMPLOYEEDAO.getAllEmployee().size();
			System.out.println(numberOfEmployees);
			System.out.println(numberOfEmployees/ConstantPool.VEHICLECAPACITY);
			return  numberOfEmployees/ConstantPool.VEHICLECAPACITY; 
		}
		
		 //add vehicle - finite fleet
		public static void createVehicleVsRouteMapping ()
		{	
			
			List<Bus> busList= BUSDAO.getAllBus();
			System.out.println(getNumberOfVehicle());
			int numberOfVehicle= getNumberOfVehicle();
			for (int i=0;i<numberOfVehicle;i++)
			{
				System.out.println(busList.get(i).getBusName());
				VehicleType vehicleType= VehicleTypeImpl.Builder.newInstance(busList.get(i).getBusName()).addCapacityDimension(0, ConstantPool.VEHICLECAPACITY).setCostPerDistance(ConstantPool.COSTPERVEHICLE).build();
				VehicleImpl vehicle = VehicleImpl.Builder.newInstance(busList.get(i).getBusName()).setStartLocation(Location.newInstance(ConstantPool.SIEMENS_LATITUDE, ConstantPool.SIEMENS_LONGITUDE)).setType(vehicleType).build();
				VehicleRoutingUtil.vrpTaskBuilder.addVehicle(vehicle);
			}
			
			//set fleetsize finite
		    //VehicleRoutingUtil.vrpTaskBuilder.setFleetSize(FleetSize.FINITE);
		    //build problem
		    VehicleRoutingProblem vehicleRoutingProblem = VehicleRoutingUtil.vrpTaskBuilder.build();
		    VehicleRoutingAlgorithm vehicleRoutingAlgorithm = Jsprit.createAlgorithm(vehicleRoutingProblem);
		    Collection<VehicleRoutingProblemSolution> solutions = vehicleRoutingAlgorithm.searchSolutions();
		    VehicleRoutingProblemSolution bestSolution = Solutions.bestOf(solutions);
		    SolutionPrinter.print(vehicleRoutingProblem, bestSolution, SolutionPrinter.Print.VERBOSE);
		    getOptimalRoute(vehicleRoutingProblem,bestSolution);  
		}
				
		public static void getOptimalRoute(VehicleRoutingProblem vehicleRoutingProblem ,VehicleRoutingProblemSolution bestSolution)
		{
			
			Double costs=bestSolution.getCost();
			int routeNumber = 1000;
		    List<RouteDetails> routeDetailsList = new ArrayList<>();
		    List<RouteDetails> routeDetailsList2 = new ArrayList<>();
		    List<VehicleRoute> vehicleRoutelist = new ArrayList<VehicleRoute>(bestSolution.getRoutes());
		    Collections.sort(vehicleRoutelist , new com.graphhopper.jsprit.core.util.VehicleIndexComparator());
		    
		    for (VehicleRoute vehicleRoute : vehicleRoutelist)
		    {
		    	
		    	
		    	//RouteDetails routeInfo = new RouteDetails(routeNu,getVehicleString(route),route.getStart().getName(),"-", Math.round(costs));
		    	//The below commented Row was for first row which has not values
		    	//RouteDetails routeDetails= new RouteDetails(routeNumber, getVehicleString(route), route.getStart().getName(), "-", Math.round(route.getStart().getEndTime()),costs);
		    	//routeDetailsList.add(routeDetails);
		    	
		    	List<Route> routeList1 = new ArrayList<>();
		        RouteDetails details = new RouteDetails(routeNumber, getVehicleString(vehicleRoute));
		        Route route1=new Route(details.getRoutNumber(),details.getVehicleName());
		        routeList1.add(route1);
		        
		    	TourActivity prevAct = vehicleRoute.getStart();   	
		    	RouteDetails routeDetailstemp=null;
		  
	            for (TourActivity act : vehicleRoute.getActivities()) {
	                String jobId;
	                if (act instanceof TourActivity.JobActivity) {
	                    jobId = ((TourActivity.JobActivity) act).getJob().getId();
	                } else {
	                    jobId = "-";
	                }
	                double c = vehicleRoutingProblem.getTransportCosts().getTransportCost(prevAct.getLocation(), act.getLocation(), prevAct.getEndTime(), vehicleRoute.getDriver(),
	                    vehicleRoute.getVehicle());
	                c += vehicleRoutingProblem.getActivityCosts().getActivityCost(act, act.getArrTime(), vehicleRoute.getDriver(), vehicleRoute.getVehicle());
	                costs += c; 
	                
	                routeDetailstemp = new RouteDetails(routeNumber, getVehicleString(vehicleRoute), act.getName(), jobId, Math.round(vehicleRoute.getStart().getEndTime()),costs);
	                routeDetailsList.add(routeDetailstemp);
	                prevAct = act;
	            }
	            
	            double c = vehicleRoutingProblem.getTransportCosts().getTransportCost(prevAct.getLocation(), vehicleRoute.getEnd().getLocation(), prevAct.getEndTime(),
	                    vehicleRoute.getDriver(), vehicleRoute.getVehicle());
	                c += vehicleRoutingProblem.getActivityCosts().getActivityCost(vehicleRoute.getEnd(), vehicleRoute.getEnd().getArrTime(), vehicleRoute.getDriver(), vehicleRoute.getVehicle());
	                costs += c;
	                //The below commented Row was for last row which has not values
	                //routeDetailstemp = new RouteDetails(routeNumber, getVehicleString(vehicleRoute), vehicleRoute.getEnd().getName(),  "-", Math.round(vehicleRoute.getStart().getEndTime()),costs);
	                //routeDetailsList.add(routeDetailstemp);
	                routeNumber++;
	                
	                // Now  Populating Route table in DB and routeId in PickUpPoint table
	                Route route=null;
	                List<Route> routeList=new ArrayList<>();
	                List<String>pickUpIdList=new ArrayList<>();
	                for(RouteDetails routeDetails:routeDetailsList){
	                	route=new Route(routeDetails.getRoutNumber(),routeDetails.getVehicleName());
	                	routeList.add(route);
	                	pickUpIdList.add(routeDetails.getpickUpID());
	                	PICKUPPOINTDAO.updateRouteId(Integer.parseInt(routeDetails.getpickUpID()), routeDetails.getRoutNumber());
	                	
	                }
	               
	           ROUTEDAO.addAllRoute1(routeList1);                     
		    	
		    }
		 
		}
			
	    private static String getVehicleString(VehicleRoute route) {
	        return route.getVehicle().getId();
	    }	

}


