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


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBPHqIMR9dGhP1LyOI5wZPuSGrhUVqLwRY">
    </script>

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


						<h3 class="inner-tittle two">Routes</h3>
						<div class="grid-1">
							<div id="dvMap" style="width: 80%; height: 700px"></div>
						

						<%@include file="side_menu.jsp"%>
						<div class="clearfix"></div>
					</div>
</body>


<script type="text/javascript">
var directionsDisplay = [];
var directionsService = [];
var map = null;
var bounds = null;

function calcRoute() {

  map = new google.maps.Map(document.getElementById('dvMap'), mapOptions);
  var msg ="41.077354,-81.511337:41.080647,-81.516579:41.077435,-81.521561:41.075253,-81.521492:41.074604,-81.520309:41.07415,-81.516335:41.073158,-81.514931:41.070534,-81.516563:41.066677,-81.516502:41.063942,-81.516502:41.06514,-81.513458:41.067383,-81.513412:41.069546,-81.513397:41.070778,-81.513382:41.072514,-81.512619:41.071106,-81.507614:41.073326,-81.506195";
  var input_msg=msg.split(":");
  var locations = new Array();      

  for (var i = 0; i < input_msg.length; i++) {
    var tmp_lat_lng =input_msg[i].split(",");
    locations.push(new google.maps.LatLng(tmp_lat_lng[0], tmp_lat_lng[1])); 
  }


  var mapOptions = {
  center: locations[0],
  zoom: 12,
  mapTypeId: google.maps.MapTypeId.ROADMAP  
  }

  var i =locations.length;
  var index =0;

  while(i !=0 ){

        if(i<3){
          var tmp_locations =new Array();
          for (var j=index;j<locations.length;j++) {
            tmp_locations.push(locations[index]);
          }
        drawRouteMap(tmp_locations); 
        i=0; 
        index=locations.length;  
       }

        if( i>= 3 && i<=10) {
           console.log("before :fun < 10: i value "+i+" index value"+index);
           var tmp_locations =new Array();
           for (var j=index;j<locations.length;j++) {
             tmp_locations.push(locations[j]);
           }
        drawRouteMap(tmp_locations);
        i=0;
        index=locations.length;
        console.log("after fun < 10: i value "+i+" index value"+index);
        }

        if(i > 10) {
        console.log("before :fun > 10: i value "+i+" index value"+index);
        var tmp_locations =new Array();
        for (var j=index;j<index+10;j++) {
         tmp_locations.push(locations[j]);
        }
        drawRouteMap(tmp_locations);
        i=i-10; 
        index =index+10;
        console.log("after fun > 10: i value "+i+" index value"+index);
        }
   }
}


 function drawRouteMap(locations){

  var start,end;
  var waypts = [];

  for(var k =0;k<locations.length;k++){
  if (k>=1 && k<=locations.length-2) {
      waypts.push({
          location:locations[k],
          stopover:true});
  }
  if(k==0) 
    start=locations[k];

  if(k==locations.length-1) 
     end=locations[k];

 }
   var request = {
      origin: start,
      destination: end,
      waypoints: waypts,
      optimizeWaypoints: true,
      travelMode: google.maps.TravelMode.DRIVING
  };
      console.log(request);
     
  directionsService.push(new google.maps.DirectionsService());
  var instance = directionsService.length-1;
     directionsDisplay.push(new google.maps.DirectionsRenderer({preservViewport:true}));
  directionsDisplay[instance].setMap(map);
  directionsService[instance].route(request, function(response, status) {
    if (status == google.maps.DirectionsStatus.OK) {
      console.log(status);
      directionsDisplay[instance].setDirections(response);
    }
  });
 }

google.maps.event.addDomListener(window, 'load', calcRoute);
</script>

<style>
#map-canvas {
	height: 80%;
	margin: 0px;
	padding: 0px
}
</style>


</html>