<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places&key=AIzaSyBPHqIMR9dGhP1LyOI5wZPuSGrhUVqLwRY"></script>
<link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Employee</title>
<script src="<%=request.getContextPath()%>/js/jquery-1.12.0.min.js" type="text/javascript"></script>
 <script src="<%=request.getContextPath()%>/js/jquery-ui.min.js" type="text/javascript"></script>
 
</head>
<body>
<form method="POST" action='EmployeeController' name="frmAddUser">
<input type="hidden" name="action" value="addEmployee" />
<p><b>Add New Record</b></p>
<table>
	<tr>
		<td>Employee Id</td>
		<td><input type="text" name="empId" /></td>
	</tr>
	<tr>
		<td>Employee Name</td>
		<td><input type="text" name="empName" /></td>
	</tr>
	<tr>
		<td>Employee Address</td>
		<td><textarea placeholder="Enter Area name to populate Latitude and Longitude" name="address" onFocus="initializeAutocomplete()" id="addressaddress1" ></textarea><br></td>
		<td><a href="javascript:void(0)" onclick="popupMapView(18.5321814,73.93315280000002);" style="width: 96px"><img src="<%=request.getContextPath()%>/images/map-icon-1.png" alt="Map" width="24" height="24" border="0">
                                </a>
                                <td>
	</tr>
	<tr>
		<td>Latitude</td>
		<td><input type="text" name="latitude" id="addresslatitude" placeholder="Latitude" value="" /></td>
		
	</tr>
	
	<tr>
		<td>Longitude</td>
		<td><input type="text" name="longitude" id="addresslongitude" placeholder="Longitude" value=""/></td>
	</tr>
	<tr>
		<td>Pick Up Point</td>
		<td><input type="text" name="pickUpPoint" /></td>
	</tr>
	<tr>
		<td>PinCode</td>
		<td><input type="text" name="pinCode" /></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" value="Submit" /></td>
	</tr>
</table>
</form>
<p><a href="EmployeeController?action=listAllEmployee">View-All-Records</a></p>

<div id="mapPopupDiv" style="display: none;">
        <h1 style="font-size: large;text-align: center;">Edit address</h1>
        <br>

        <div class="tabs">


            <ul class="tab-links">
                <li style="float:left;width:85%;margin-right:5%;">
                    Search Address:<br>
                    <div class="search_box">
                        <textarea id="pac-input" placeholder="Search location" rows="3" cols="40"></textarea>&nbsp;
                        <img src="<%=request.getContextPath()%>/images/directionImg_red.png" alt="Edit" width="20" height="34" border="0">
                    </div>
                </li>
            </ul>
            <br>
            <ul class="tab-links">
                <li>Latitude: <input type="text" id="mLat" placeholder="Latitude" readonly=""></li>
                <li>Longitude: <input type="text" id="mLng" placeholder="Longitude" readonly=""></li>
            </ul>
            <div class="tab-content">
                <div id="tab1" class="tab active">
                    <div id="popupContent">
                        <div id="map-canvas" style="width: 100%; height: 300px;"></div>
                    </div>
                </div>
            </div>
            <div>
                <a href="javascript:void(0)" class="hc-button-blue" id="saveAddr">Set</a>&nbsp;&nbsp;
                <a href="javascript:void(0)" class="hc-button-blue" id="cancelAddrUpdate">Cancel</a>
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
      $('.latlong').keypress(function (event) {
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
          if(placeAreaMarker)
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



  function popupMapView(lat,lng)
  {
      console.log(lat+" "+lng);
      $("#mapPopupDiv").fadeIn(1000);
      $(".overlay").show();
      initializeMap(); // initialize Map
      document.getElementById('pac-input').innerHTML = $("#addressaddress1").val()
      if(lat!=0 || lng!=0){
       showAddressOnMap(lat,lng)
      }
  }

  function showAddressOnMap(lat,lng) {
      console.log(lat+" "+lng);
      if (lat && lng) {
          if (tmpMarker) {
              tmpMarker.setMap(null);
              tmpMarker = null;
          }


          tmpMarker = new google.maps.Marker({
              position: new google.maps.LatLng(lat, lng),
              map: map,
              animation: google.maps.Animation.DROP,
              icon: markerPinGreenIcon
          });


          tmpMarker.setMap(map);
          map.setZoom(14);
          map.panTo(tmpMarker.getPosition());

      }
  }
  function initializeMap()
  {

      initialize();
      console.log("map.panTo google lat lng")
      // Create the search box and link it to the UI element.
      var input = document.getElementById('pac-input');
      searchBox = new google.maps.places.SearchBox(input);
      //searchBox = new google.maps.places.SearchBox(document.getElementById('pac-input'));
      google.maps.event.addListener(searchBox, 'places_changed', function() {
          onPlaceChanged();
      });

      google.maps.event.addListener(map, "click", function (e) {
          if(placeAreaMarker)
              placeAreaMarker.setMap(null);
          placeAreaMarker = null;

          placeAreaMarker = new google.maps.Marker({
              position: e.latLng,
              map: map,
              animation: google.maps.Animation.DROP,
              icon: markerPinRedIcon,
              draggable:true
          });
          document.getElementById('mLat').value = placeAreaMarker.getPosition().lat();
          document.getElementById('mLng').value = placeAreaMarker.getPosition().lng();
          placeAreaMarker.setMap(map);

          google.maps.event.addListener(placeAreaMarker, 'dragend', function() {
              document.getElementById('mLat').value = this.getPosition().lat();
              document.getElementById('mLng').value = this.getPosition().lng();
          });
      });
  }

  //When the user selects a city, get the place details for the city and zoom the map in on the city.
  function onPlaceChanged()
  {
      //console.log("in onPlaceChanged...");
      var places = searchBox.getPlaces();
      if(places)
      {
          if(places.length>0)
          {
              for(var i=0;i<places.length;i++)
              {
                  place = places[i];
                  if (place.geometry)
                  {
                      map.panTo(place.geometry.location);
                      map.setZoom(16);
                      console.log( place.name)
                      var placeName = place.name;
                      var placeAddress = '';
                      if (place.address_components) {
                          placeAddress = [
                              (place.address_components[0] && place.address_components[0].short_name || ''),
                              (place.address_components[1] && place.address_components[1].short_name || ''),
                              (place.address_components[2] && place.address_components[2].short_name || '')
                          ].join(' ');
                      }

                      if(placeAreaMarker)
                          placeAreaMarker.setMap(null);
                      placeAreaMarker = null;

                      placeAreaMarker = new google.maps.Marker({
                          position: place.geometry.location,
                          map: map,
                          title: placeName,
                          animation: google.maps.Animation.DROP,
                          icon: markerPinRedIcon,
                          draggable:true
                      });

                      document.getElementById('mLat').value = placeAreaMarker.getPosition().lat();
                      document.getElementById('mLng').value = placeAreaMarker.getPosition().lng();

                      var infoWindowTxt = '<table border="0" cellspacing="1" cellpadding="1">'+
                              '<tr><td><b>Name</b></td><td>&nbsp;:&nbsp;</td><td>'+placeName+'</td></tr>'+
                              '<tr><td><b>Address 1</b></td><td>&nbsp;:&nbsp;</td><td>'+placeAddress+'</td></tr>'+
                              '</table>';

                      placeAreaMarker.infoWindowTxt = infoWindowTxt;

                      var tmpInfoWindow = new google.maps.InfoWindow({
                          content: ""
                      });

                      google.maps.event.addListener(placeAreaMarker, 'dragend', function() {
                          document.getElementById('mLat').value = this.getPosition().lat();
                          document.getElementById('mLng').value = this.getPosition().lng();
                      });

                      google.maps.event.addListener(placeAreaMarker, 'click', function() {
                          tmpInfoWindow.setContent(this.infoWindowTxt);
                          tmpInfoWindow.open(map,this);
                      });
                      placeAreaMarker.setMap(map);
                  }
                  else
                  {
                      document.getElementById('pac-input').placeholder = 'Search location';
                  }
              }
          }
      }
  }



var map;
var homeLat=18.59, homeLng=73.85;
var initLat=18.59, initLng=73.85;
var initZoom = 11;

function initialize() 
{	
var mapProp = {
	    center: new google.maps.LatLng(initLat, initLng),
      mapTypeControl: true,
      zoomControl: true,
      zoom: initZoom,
      mapTypeId: google.maps.MapTypeId.ROADMAP
	  };
  map = new google.maps.Map(document.getElementById('map-canvas'),mapProp); 
    
}
</script>

<style>
.addressTextArea{
  width:200px;
  height:70px;
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

.popBtn  {
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
  width:100%;
  display:inline-block;
}

.tab-links:after {
  display:block;
  clear:both;
  content:'';
}

.tab-links li {
  margin:0px 5px;
  float:left;
  list-style:none;
}

.tab-links a {
  padding:9px 15px;
  display:inline-block;
  font-size:16px;
  font-weight:600;
  color:#4c4c4c;
  transition:all linear 0.15s;
}

.tab-links a:hover {
  text-decoration:none;
}

li.active a, li.active a:hover {
  color:red;
  font-weight:bold;
}

.tab-content {
  padding:15px;
  border-radius:3px;
}

.tab {
  display:none;
}

.tab.active {
  display:block;
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