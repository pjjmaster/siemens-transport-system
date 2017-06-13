<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.siemens.bean.Employee"%>
<%@ page import="com.siemens.dao.EmployeeDao"%>
<%@ page import="java.util.*"%>

<html>
<head>
<title>Siemens Employee Transport Services</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Siemens Employee Transport Services" />
<script type="application/x-javascript">
	
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 


</script>
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<!-- Graph CSS -->
<link href="css/font-awesome.css" rel="stylesheet">
<!-- jQuery -->
<link
	href='//fonts.googleapis.com/css?family=Roboto:700,500,300,100italic,100,400'
	rel='stylesheet' type='text/css'>
<!-- lined-icons -->
<link rel="stylesheet" href="css/icon-font.min.css" type='text/css' />
<!-- //lined-icons -->
<script src="<%=request.getContextPath()%>/js/jquery-1.12.0.min.js"
	type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.min.js"
	type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/amcharts.js"></script>
<script src="<%=request.getContextPath()%>/js/serial.js"></script>
<script src="<%=request.getContextPath()%>/js/light.js"></script>
<script src="<%=request.getContextPath()%>/js/radar.js"></script>
<link href="css/barChart.css" rel='stylesheet' type='text/css' />
<link href="css/fabochart.css" rel='stylesheet' type='text/css' />
<!--clock init-->
<script src="<%=request.getContextPath()%>/js/css3clock.js"></script>
<!--Easy Pie Chart-->
<!--skycons-icons-->
<script src="<%=request.getContextPath()%>/js/skycons.js"></script>

<script src="<%=request.getContextPath()%>/js/jquery.easydropdown.js"></script>
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places&key=AIzaSyBPHqIMR9dGhP1LyOI5wZPuSGrhUVqLwRY"></script>
<link type="text/css" rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Siemens Employee Transport Services</title>


</head>
<body>
	<div class="page-container">
		<!--/content-inner-->
		<div class="left-content">
			<div class="inner-content">
				<!-- header-starts -->
				<div class="header-section">
					<!--menu-right-->
					<div class="top_menu">

						<img src=" width="160" height="50" alt="">


					</div>
					<!--//menu-right-->
					<div class="clearfix"></div>
				</div>

				<div class="set-1">
					<div class="col-md-12">
					<%
		//empBean emp = new empBean();
		EmployeeDao dao = new EmployeeDao();
		List<Employee> employeeList = dao.getAllEmployee();
		//Iterator<empBean> itr = empList.iterator();
	%>
	


	<h3 class="inner-tittle two">Employee List</h3> <button class="btn btn-default"><a href="EmployeeController?action=insert">Add Employee</a></button>
	<div class="graph">
		<div class="tables">

			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Address</th>
						<th>Latitude</th>
						<th>Longitude</th>
						<th>Pick Up Point</th>
						<th>Pin</th>
						<th>Edit</th>
						
					</tr>
				</thead>
				<tbody>
					
					<%
						for (Employee emp : employeeList) {
							
					%>
					<tr>
			<td><%=emp.getId()%></td>
			<td><%=emp.getName()%></td>
			<td><%=emp.getAddress()%></td>
			<td><%=emp.getLatitude()%></td>
			<td><%=emp.getLongitude()%></td>
			<td><%=emp.getPickUpPoint()%></td>
			<td><%=emp.getPincode()%></td>
			<td><a href="EmployeeController?action=editform&empId=<%=emp.getId()%>">Update</a>
				<a href="EmployeeController?action=delete&empId=<%=emp.getId()%>">Delete</a></td>
				
				</tr>
		<%
			}
		%>
									</tbody>
			</table>
		</div>
		<p>
		
	</p>
	</div>
	
	
	<div class="sidebar-menu">
					<header class="logo">
					<img src="images/siemens-logo.png" width="160" height="50" alt="">
				  </a> 
				</header>
			<div style="border-top:1px solid rgba(69, 74, 84, 0.7)"></div>
			<!--/down-->
							<div class="down">	
									  <a href="index.html"><img src="images/admin.jpg"></a>
									  <a href="index.html"><span class=" name-caret">Ssdgj sdgsg</span></a>
									 <p>Transport Administrator</p>
									<ul>
									<li><a class="tooltips" href="index.html"><span>Profile</span><i class="lnr lnr-user"></i></a></li>
										<li><a class="tooltips" href="index.html"><span>Settings</span><i class="lnr lnr-cog"></i></a></li>
										<li><a class="tooltips" href="index.html"><span>Log out</span><i class="lnr lnr-power-switch"></i></a></li>
										</ul>
									</div>
							   <!--//down-->
								<div class="menu">
									<ul id="menu" >
										<li><a href="EmployeeController?action=listAllEmployee"><i class="fa fa-tachometer"></i> <span>Employee List</span></a></li>
										<li><a href="EmployeeController?action=insert"><i class="fa fa-tachometer"></i> <span>Add Employee</span></a></li>
										 
										 <li id="menu-academico" ><a href="#"><i class="fa fa-file-text-o"></i> <span>Pickup Points</span> <span class="fa fa-angle-right" style="float: right"></span></a>
											 	
									<li><a href="typography.html"><i class="lnr lnr-pencil"></i> <span>Clusters</span></a></li>
									<li id="menu-academico" ><a href="#"><i class="lnr lnr-book"></i> <span>Routes</span> <span class="fa fa-angle-right" style="float: right"></span></a>
									</li>
									 
							        
									<li><a href="chart.html"><i class="lnr lnr-chart-bars"></i> <span>Charts</span> <span class="fa fa-angle-right" style="float: right"></span></a>
									  <ul>
										<li><a href="map.html"><i class="lnr lnr-map"></i> Maps</a></li>
										<li><a href="graph.html"><i class="lnr lnr-apartment"></i> Graph Visualization</a></li>
									</ul>
									</li>
									
								  </ul>
								</div>
							  </div>
							  <div class="clearfix"></div>		
							</div>
</body>
</html>