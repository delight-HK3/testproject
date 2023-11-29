/**
 * @license
 * Copyright 2019 Google LLC. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */
function initMap() {
    const geocoder = new google.maps.Geocoder();
    const infowindow = new google.maps.InfoWindow();

    const inputText = document.createElement("input");
    const submitButton = document.createElement("input");
    const responseDiv = document.createElement("pre");
    const response = document.createElement("div");

    inputText.type = "text";
    inputText.id = "latlng";
    //inputText.onkeyup = "enterkey()";
    inputText.style.cssText = "width : 30%; margin-top : 10px";
    inputText.classList.add("form-control");

    submitButton.type = "input";
    submitButton.value = "검색";
    submitButton.id = "submit";
    submitButton.style.cssText = "width : 10%; margin-top : 10px";
    submitButton.classList.add("btn", "btn-primary");

    response.id = "response";
    response.innerText = "";

    responseDiv.id = "response-container";
    responseDiv.appendChild(response);

    const map = new google.maps.Map(document.getElementById("map"), {
      zoom: 12,
      center: { lat: 37.5518911, lng: 126.9917937 },
      mapTypeId: 'satellite'
    }); 

    map.controls[google.maps.ControlPosition.TOP_LEFT].push(inputText);
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(submitButton);

    
  
    map.addListener("center_changed", () => {
      // 3 seconds after the center of the map has changed, pan back to the
      // marker.
    });
    
    // 클릭한 곳 좌표 변환
    map.addListener("click", function(e) {
        const check_carway = document.querySelector("#carway");
        var carway_x = document.getElementById("carway_x").value;
        const carway = check_carway.checked;

        const check_weigth = document.querySelector("#weigth");
        var weigth_x = document.getElementById("weigth_x").value;
        const weigth = check_weigth.checked;

        const check_cctv = document.querySelector("#cctv");
        var cctv_x = document.getElementById("cctv_x").value;
        const cctv = check_cctv.checked;

        var latlng = {
            x: e.latLng.lat(),
            y: e.latLng.lng()
        }

        var x = latlng.x.toString();
        var y = latlng.y.toString();
        
        //console.log(x.substring(0, 12));
        //console.log(y.substring(0, 12));

        if(carway == true && carway_x == "0") {
            document.getElementById("carway_x").value = x.substring(0, 9);
            document.getElementById("carway_y").value = y.substring(0, 9);
        } 
        if (weigth == true && weigth_x == "0") {
            document.getElementById("weigth_x").value = x.substring(0, 9);
            document.getElementById("weigth_y").value = y.substring(0, 9);
        }
        if(cctv == true && cctv_x == "0") {
            document.getElementById("cctv_x").value = x.substring(0, 9);
            document.getElementById("cctv_y").value = y.substring(0, 9);
        }

    });

    submitButton.addEventListener("click", () =>
        geocodeLatLng(geocoder, map, infowindow)
    );


}

/*function enterkey(){
    if (window.event.keyCode == 13) {
        geocodeLatLng(geocoder, map, infowindow)
    }
} */

function geocodeLatLng(geocoder, map, infowindow) {
    var input = document.getElementById("latlng").value;
    const latlngStr = input.split(" ", 2);
    const latlng = {
        lat: parseFloat(latlngStr[0]),
        lng: parseFloat(latlngStr[1]),
    };

    geocoder.geocode({ location: latlng })
    .then((response) => {
        if (response.results[0]) {
            map.setZoom(20);

            const marker = new google.maps.Marker({
                position: latlng,
                map: map
            });

            infowindow.setContent(response.results[0].formatted_address);
            infowindow.open(map, marker);
        } else {
            window.alert("No results found");
        }
    }).catch((e) => window.alert("Geocoder failed due to: " + e));
}
  
window.initMap = initMap;