<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.siemens.bean.Bus"%>
<%@ page import="com.siemens.dao.BusDao"%>
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

						<img src=" width=" 160" height="50" alt="">


					</div>
					<!--//menu-right-->
					<div class="clearfix"></div>
				</div>

				<div class="set-1">
					<div class="col-md-12">
						<%
							//empBean emp = new empBean();
							BusDao dao = new BusDao();
							List<Bus> busList = dao.getAllBus();
						%>



						<h3 class="inner-tittle two">Bus Type List</h3>
						<button class="btn btn-default">
							<a href="BusController?action=insert">Add Bus Type</a>
						</button>
						<div class="graph">
							<div class="tables">

								<table class="table table-bordered">
									<thead>
										<tr>
											<th>Id</th>
											<th>Capacity</th>
											<th>Cost Per KM Per Month</th>
											<th>Type</th>
										</tr>
									</thead>
									<tbody>

										<%
											for (Bus bus : busList) {
										%>
										<tr>
											<td><%=bus.getBusID()%></td>
											<td><%=bus.getBusCapacity()%></td>
											<td><%=bus.getBusCostPKPM()%></td>
											<td><%=bus.getType()%></td>

											<td><a
												href="BusController?action=editform&busId=<%=bus.getBusID()%>">Update</a>
												<a
												href="BusController?action=delete&busId=<%=bus.getBusID()%>">Delete</a></td>

										</tr>
										<%
											}
										%>
									</tbody>
								</table>
							</div>
							<p></p>
						</div>

						<%@include file="side_menu.jsp"%>
						<div class="clearfix"></div>
					</div>
</body>
</html>