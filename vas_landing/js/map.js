var marker;
		function myMap() {

			var mapProp= {
    		center:new google.maps.LatLng(3.4749156,-76.4935469),
    		zoom:17,
			};
			var map=new google.maps.Map(document.getElementById("googleMap"),mapProp);

			marker = new google.maps.Marker({
          	map: map,
          	draggable: false,
          	animation: google.maps.Animation.DROP,
          	position: {lat: 3.474752, lng: -76.493862} 
        	});
        	
		}