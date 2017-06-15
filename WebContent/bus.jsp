<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
<title>Add New Employee</title>


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

						<img src="images/siemens-logo.png" width="160" height="50" alt="">


					</div>
					<!--//menu-right-->
					<div class="clearfix"></div>
				</div>

				<div class="set-1">
					<div class="col-md-12">
						<h3 class="inner-tittle two">Add Bus Type</h3>
						<div class="grid-1">
							<div class="form-body">

								<form method="POST" class="form-horizontal" action='BusController' name="frmAddUser">
									<input type="hidden" name="action" value="addBus" />

									<div class="form-group">
										<label for="inputEmail3" class="col-sm-2 control-label">Bus
											Id</label>
										<div class="col-sm-9">
											<input type="text" name="busId" class="form-control"
												id="inputEmail3" placeholder="Bus Id">
										</div>
									</div>

									<div class="form-group">
										<label for="inputEmail3" class="col-sm-2 control-label">Capacity</label>
										<div class="col-sm-9">
											<input input type="text" name="capacity" class="form-control"
												id="inputEmail3" placeholder="Capacity">
										</div>
									</div>

									

									<div class="form-group">
										<label class="col-sm-2 control-label">Cost</label>
										<div class="col-sm-9">
											<input input type="text" class="form-control"
												name="cost"   id="addresslatitude"
												placeholder="Cost">
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-2 control-label">Bus Type</label>
										<div class="col-sm-9">
											<input input type="text" class="form-control"
												name="type" id="addresslongitude"
												placeholder="Type">
										</div>
									</div>

									<div class="form-group">
									<div class="col-sm-offset-2">
										<button type="submit" class="btn btn-default">Add</button>
									</div>
									<div class="col-sm-offset-2">
									<button class="btn btn-default"><a href="BusController?action=listAllBus">List of Bus Types</a></button>
								</div>
								</form>
							</div>

						</div>
					</div>



					<p>
						
					</p>

					<div id="mapPopupDiv" style="display: none;">
						<h1 style="font-size: large; text-align: center;">Edit
							address</h1>
						<br>

						<div class="tabs">


							<ul class="tab-links">
								<li style="float: left; width: 85%; margin-right: 5%;">
									Search Address:<br>
									<div class="search_box">
										<textarea id="pac-input" placeholder="Search location"
											rows="3" cols="40"></textarea>
										&nbsp; <img
											src="<%=request.getContextPath()%>/images/directionImg_red.png"
											alt="Edit" width="20" height="34" border="0">
									</div>
								</li>
							</ul>
							<br>
							<ul class="tab-links">
								<li>Latitude: <input type="text" id="mLat"
									placeholder="Latitude" readonly=""></li>
								<li>Longitude: <input type="text" id="mLng"
									placeholder="Longitude" readonly=""></li>
							</ul>
							<div class="tab-content">
								<div id="tab1" class="tab active">
									<div id="popupContent">
										<div id="map-canvas" style="width: 100%; height: 300px;"></div>
									</div>
								</div>
							</div>
							<div>
								<a href="javascript:void(0)" class="btn btn-default"
									id="saveAddr">Set</a>&nbsp;&nbsp; <a href="javascript:void(0)"
									 class="btn btn-default" id="cancelAddrUpdate">Cancel</a>
							</div>
						</div>
					</div>
</body>

<script type="text/javascript">
  
  

  var addressLocations = {};
  var tmpMarker, searchBox, placeAreaMarker, activeAddressId;
  var markerPinGreenIcon = '<%=request.getContextPath()%>/images/directionImg_green.png';
  var markerPinRedIcon = '<%=request.getContextPath()%>/images/directionImg_red.png';
	var addressType

	$(document).ready(function() {
		$('.latlong').keypress(function(event) {
			return isNumber(event, this)
		});

		$(".pop").click(function() {
			$("#pop-up").fadeIn(1000);
			$(".overlay").show();
		});

		//close popup
		$("#close").click(function() {
			$("#pop-up").fadeOut(500);
			$(".overlay").hide();
			$("#mapPopupDiv").hide();

		});

		$("#saveAddr").click(function() {
			$("#mapPopupDiv").fadeOut(500);
			$(".overlay").hide();

			$("#addressaddress1").val($("#pac-input").val())
			$("#addresslatitude").val($("#mLat").val())
			$("#addresslongitude").val($("#mLng").val())

		});
		//close popup
		$("#close").click(function() {
			$("#mapPopupDiv").fadeOut(500);
			$(".overlay").hide();
		});

		$("#cancelAddrUpdate").click(function() {
			$("#mapPopupDiv").fadeOut(500);
			$(".overlay").hide();
			if (placeAreaMarker)
				placeAreaMarker.setMap(null);
			placeAreaMarker = null;
			activeAddressId = null;
		});

		//open popup
		$(".pop").click(function() {
			$("#pop-up").fadeIn(1000);
			$(".overlay").show();
		});

		//close popup
		$("#close").click(function() {
			$("#pop-up").fadeOut(500);
			$(".overlay").hide();

		});

	});
	// THE SCRIPT THAT CHECKS IF THE KEY PRESSED IS A NUMERIC OR DECIMAL VALUE.
	/* function isNumber(evt, element) {

	    var charCode = (evt.which) ? evt.which : event.keyCode

	    if (
	            (charCode != 45 || $(element).val().indexOf('-') != -1) &&      // “-” CHECK MINUS, AND ONLY ONE.
	            (charCode != 46 || $(element).val().indexOf('.') != -1) &&      // “.” CHECK DOT, AND ONLY ONE.
	            (charCode < 48 || charCode > 57)
	    )
	        return false;

	    return true;
	} */

	function popupMapView(lat, lng) {
		console.log(lat + " " + lng);
		$("#mapPopupDiv").fadeIn(1000);
		$(".overlay").show();
		initializeMap(); // initialize Map
		document.getElementById('pac-input').innerHTML = $("#addressaddress1")
				.val()
		if (lat != 0 || lng != 0) {
			showAddressOnMap(lat, lng)
		}
	}

	function showAddressOnMap(lat, lng) {
		console.log(lat + " " + lng);
		if (lat && lng) {
			if (tmpMarker) {
				tmpMarker.setMap(null);
				tmpMarker = null;
			}

			tmpMarker = new google.maps.Marker({
				position : new google.maps.LatLng(lat, lng),
				map : map,
				animation : google.maps.Animation.DROP,
				icon : markerPinGreenIcon
			});

			tmpMarker.setMap(map);
			map.setZoom(14);
			map.panTo(tmpMarker.getPosition());

		}
	}
	function initializeMap() {

		initialize();
		console.log("map.panTo google lat lng")
		// Create the search box and link it to the UI element.
		var input = document.getElementById('pac-input');
		searchBox = new google.maps.places.SearchBox(input);
		//searchBox = new google.maps.places.SearchBox(document.getElementById('pac-input'));
		google.maps.event.addListener(searchBox, 'places_changed', function() {
			onPlaceChanged();
		});

		google.maps.event.addListener(map, "click", function(e) {
			if (placeAreaMarker)
				placeAreaMarker.setMap(null);
			placeAreaMarker = null;

			placeAreaMarker = new google.maps.Marker({
				position : e.latLng,
				map : map,
				animation : google.maps.Animation.DROP,
				icon : markerPinRedIcon,
				draggable : true
			});
			document.getElementById('mLat').value = placeAreaMarker
					.getPosition().lat();
			document.getElementById('mLng').value = placeAreaMarker
					.getPosition().lng();
			placeAreaMarker.setMap(map);

			google.maps.event.addListener(placeAreaMarker, 'dragend',
					function() {
						document.getElementById('mLat').value = this
								.getPosition().lat();
						document.getElementById('mLng').value = this
								.getPosition().lng();
					});
		});
	}

	//When the user selects a city, get the place details for the city and zoom the map in on the city.
	function onPlaceChanged() {
		//console.log("in onPlaceChanged...");
		var places = searchBox.getPlaces();
		if (places) {
			if (places.length > 0) {
				for (var i = 0; i < places.length; i++) {
					place = places[i];
					if (place.geometry) {
						map.panTo(place.geometry.location);
						map.setZoom(16);
						console.log(place.name)
						var placeName = place.name;
						var placeAddress = '';
						if (place.address_components) {
							placeAddress = [
									(place.address_components[0]
											&& place.address_components[0].short_name || ''),
									(place.address_components[1]
											&& place.address_components[1].short_name || ''),
									(place.address_components[2]
											&& place.address_components[2].short_name || '') ]
									.join(' ');
						}

						if (placeAreaMarker)
							placeAreaMarker.setMap(null);
						placeAreaMarker = null;

						placeAreaMarker = new google.maps.Marker({
							position : place.geometry.location,
							map : map,
							title : placeName,
							animation : google.maps.Animation.DROP,
							icon : markerPinRedIcon,
							draggable : true
						});

						document.getElementById('mLat').value = placeAreaMarker
								.getPosition().lat();
						document.getElementById('mLng').value = placeAreaMarker
								.getPosition().lng();

						var infoWindowTxt = '<table border="0" cellspacing="1" cellpadding="1">'
								+ '<tr><td><b>Name</b></td><td>&nbsp;:&nbsp;</td><td>'
								+ placeName
								+ '</td></tr>'
								+ '<tr><td><b>Address 1</b></td><td>&nbsp;:&nbsp;</td><td>'
								+ placeAddress + '</td></tr>' + '</table>';

						placeAreaMarker.infoWindowTxt = infoWindowTxt;

						var tmpInfoWindow = new google.maps.InfoWindow({
							content : ""
						});

						google.maps.event
								.addListener(
										placeAreaMarker,
										'dragend',
										function() {
											document.getElementById('mLat').value = this
													.getPosition().lat();
											document.getElementById('mLng').value = this
													.getPosition().lng();
										});

						google.maps.event.addListener(placeAreaMarker, 'click',
								function() {
									tmpInfoWindow
											.setContent(this.infoWindowTxt);
									tmpInfoWindow.open(map, this);
								});
						placeAreaMarker.setMap(map);
					} else {
						document.getElementById('pac-input').placeholder = 'Search location';
					}
				}
			}
		}
	}

	var map;
	var homeLat = 18.59, homeLng = 73.85;
	var initLat = 18.59, initLng = 73.85;
	var initZoom = 11;

	function initialize() {
		var mapProp = {
			center : new google.maps.LatLng(initLat, initLng),
			mapTypeControl : true,
			zoomControl : true,
			zoom : initZoom,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		map = new google.maps.Map(document.getElementById('map-canvas'),
				mapProp);

	}
</script>

<style>
.addressTextArea {
	width: 200px;
	height: 70px;
}
</style>

<style>
#mapPopupDiv {
	background: none repeat scroll 0 0 #FFFFFF;
	border: 5px solid #808080;
	left: 20%;
	max-height: 600px;
	overflow: scroll;
	padding: 10px;
	position: fixed;
	top: 10%;
	width: 60%;
}

#popupContent {
	max-height: 600px;
}

#pac-input {
	height: 30px;
	width: 85%;
	resize: none;
	margin-bottom: 0.5em;
	margin-left: 0.3em;
	padding-left: 0.5em;
	padding-right: 0.5em;
	padding-top: 0.5em;
	padding-bottom: 0.5em;
}

.popBtn {
	background-color: none !important;
	border: none;
	display: block;
	width: 24px;
	height: 24px;
	cursor: pointer;
}

#close {
	position: fixed;
	top: 7%;
	left: 80%;
	cursor: pointer;
}

.overlay {
	position: fixed;
	height: 100%;
	background-color: #000;
	width: 100%;
	top: 0px;
	left: 0px;
	opacity: 0.4;
}

.tabs {
	width: 100%;
	display: inline-block;
}

.tab-links:after {
	display: block;
	clear: both;
	content: '';
}

.tab-links li {
	margin: 0px 5px;
	float: left;
	list-style: none;
}

.tab-links a {
	padding: 9px 15px;
	display: inline-block;
	font-size: 16px;
	font-weight: 600;
	color: #4c4c4c;
	transition: all linear 0.15s;
}

.tab-links a:hover {
	text-decoration: none;
}

li.active a, li.active a:hover {
	color: red;
	font-weight: bold;
}

.tab-content {
	padding: 15px;
	border-radius: 3px;
}

.tab {
	display: none;
}

.tab.active {
	display: block;
}

li.active {
	color: #fff;
	background: grey;
}

ul {
	margin: 0px;
	padding: 0px;
}

li {
	list-style: none;
	margin: 0px;
	padding: 2px;
}

Section {
	text-align: left;
	width: 300px;
	height: 200px;
	border: 2px solid grey;
	padding: 15px;
	overflow-x: auto;
}

#pop-up {
	position: fixed;
	top: 20%;
	left: 25%;
	overflow: scroll;
	border: 5px solid gray;
	padding: 10px;
	background: white; /*width: 370px;*/
	width: 60%;
	height: 30%;
	overflow-x: auto;
}

.pop {
	border: none;
	background: transparent;
	cursor: pointer;
	text-align: center;
	padding: 0px;
	text-decoration: none;
	margin: 0 auto;
	font-weight: bold;
	margin-top: -3px;
}

#close {
	position: fixed;
	top: 18%;
	left: 86%;
	cursor: pointer;
}

.overlay {
	position: fixed;
	height: 100%;
	background-color: #000;
	width: 100%;
	top: 0px;
	left: 0px;
	opacity: 0.4;
}
</style>
</html>





