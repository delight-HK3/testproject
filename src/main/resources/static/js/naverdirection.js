/**
 * naverdirection.js
 * 2024/11/21
 */

let startgoallist = []; // 시작점,도착점 마커 리스트
let waypointlist = []; // 경유지 마커 리스트
let polylinelist = null; // polyline 저장

let startgoalcnt = 0;
let waypointcnt = 0;

let startgoalnew = []; // 신규 시작점,도착점 좌표 리스트
let waypointnew = []; // 신규 경유지 좌표 리스트

let startgoalevent = null;
let waypointevent = null;

function startgoal(){ // 시작점, 도착점 지정 함수

    naver.maps.Event.removeListener(startgoalevent);

    startgoalevent = naver.maps.Event.addListener(map, 'click', function(e) {
        var lat = e.coord.lat(); // Latitude
        var lng = e.coord.lng(); // Longitude

        if(startgoalnew.length == 2){
            return;
        }

        var startgoal = new naver.maps.Marker({
            position: new naver.maps.LatLng(lat, lng),
            map: map
        });
            
        startgoal.customData = {
            id: startgoalcnt
        };

        startgoallist.push(startgoal);
        startgoalnew.push(`${lng}, ${lat}`);

        naver.maps.Event.addListener(startgoal, 'click', function(e) {
            startgoalcnt--;
            startgoalnew.splice(startgoal.customData.id,1);
            startgoal.setMap(null);
                
            if(startgoalcnt == 0){
                startgoalnew = [];
            }
            return;
        });

        startgoalcnt++;
    });
}

function waypoints(){ // 경유지 지정 함수

    naver.maps.Event.removeListener(waypointevent);

    waypointevent = naver.maps.Event.addListener(map, 'click', function(e) {
        var lat = e.coord.lat(); // Latitude
        var lng = e.coord.lng(); // Longitude

        if(waypointnew.length == 5){
            return;
        }

        var waypoint = new naver.maps.Marker({
            position: new naver.maps.LatLng(lat, lng),
            map: map
        });
             
        waypoint.customData = {
            id: waypointcnt
        };

        waypointlist.push(waypoint);
        waypointnew.push(`${lng}, ${lat}`);

        naver.maps.Event.addListener(waypoint, 'click', function(e) {
            waypointcnt--;
            waypointnew.splice(waypoint.customData.id,1);
            waypoint.setMap(null);

            if(waypointcnt == 0){
                waypointnew = [];
            }
            return;
        });

        waypointcnt++;
    });
}

function searchmap(){ // 검색

    $.ajax({
        url: "./directionGps"
        , type: 'GET'
        , data: {
            startgoal : startgoalnew,
            waypoint : waypointnew
        }
        , dataType: 'json'
        , contentType : 'application/charset=utf-8'
        , success: function (data) {
            
            clearmap();

            const items = data.route.trafast[0].path;
            
            // start[1] (lat) , start[0] (lng)
            const start = data.route.trafast[0].summary.start.location;
            const goal = data.route.trafast[0].summary.goal.location;

            const waypoints = data.route.trafast[0].summary.waypoints;

            console.log(waypoints);

            const startmark = new naver.maps.Marker({
                position: new naver.maps.LatLng(start[1], start[0]),
                map: map,
                icon: {
                    content: '<div style="background: #007bff; color: white; padding: 5px; border-radius: 5px;">출발지</div>'
                }
            });

            const goalmark = new naver.maps.Marker({
                position: new naver.maps.LatLng(goal[1], goal[0]),
                map: map,
                icon: {
                    content: '<div style="background: #007bff; color: white; padding: 5px; border-radius: 5px;">도착지</div>'
                }
            });

            startgoallist.push(startmark);
            startgoallist.push(goalmark);

            if(waypoints != null){
                for(var i = 0; i < waypoints.length; i++){
                    const lag = waypoints[i].location[1]; 
                    const lng = waypoints[i].location[0];
    
                    const waypointmark = new naver.maps.Marker({
                        position: new naver.maps.LatLng(lag, lng),
                        map: map,
                        icon: {
                            content: '<div style="background: #007bff; color: white; padding: 5px; border-radius: 5px;">경유지 '+(i+1)+'</div>'
                        }
                    });
    
                    waypointlist.push(waypointmark);
                }
            }
    
            const path = items.map(([lng, lat]) => new naver.maps.LatLng(lat, lng));
            
            const polyline = new naver.maps.Polyline({
                map: map,
                path: path,
                strokeColor: "#33b4ff", // Route color
                strokeWeight: 4,       // Route width
                strokeLineCap: "round",
                startIcon: 3,
                endIcon: 3
            });

            polylinelist = polyline;
            map.setCenter(new naver.maps.LatLng(start[1], start[0]));
        }
    });
}

function clearmap(){ // 지도 초기화

    for(var i = 0; i < startgoallist.length; i++){
        startgoallist[i].setMap(null);
    }

    for(var i = 0; i < waypointlist.length; i++){
        waypointlist[i].setMap(null);
    }

    startgoallist = [];
    waypointlist = [];

    startgoalnew = [];
    waypointnew = [];

    startgoalcnt = 0;
    waypointcnt = 0;
    
    naver.maps.Event.removeListener(startgoalevent);
    naver.maps.Event.removeListener(waypointevent);

    if(polylinelist != null){
        polylinelist.setMap(null);
        polylinelist = null;
    }

}