// ==================================== 네이버 클러스터 Map ==================================== //

function naverMap(myItem){

    var naverMap = new naver.maps.Map("naverMap", {
        zoom: 7,
        center: new naver.maps.LatLng(36.2253017, 127.6460516),
        zoomControl: true,
        zoomControlOptions: {
            position: naver.maps.Position.TOP_LEFT,
            style: naver.maps.ZoomControlStyle.SMALL 
        }
    });

    var markers = []; // 마커 배열
    var infoWindows = []; // 정보창 배열

    //$.get("/json/jijung.json", function(data) {
    for (var i = 0, ii = myItem.length; i < ii; i++) {
        //var spot = data.positions[i];
        var spot = myItem[i],
        latlng = new naver.maps.LatLng(spot.latitude, spot.longitude),
        marker = new naver.maps.Marker({
            position: latlng,
            draggable: true
        }); 
        
        var InfoWindow = new naver.maps.InfoWindow({
            content: '<div style="width:200px; text-align:center">' + spot.schoolNm + '</div>'
        });

        markers.push(marker);
        infoWindows.push(InfoWindow);
    }
    
    function getClickHandler(seq){
        return function(e) {
            var marker = markers[seq],
                InfoWindow = infoWindows[seq];
            
            if(InfoWindow.getMap()){
                InfoWindow.close();
            } else {
                InfoWindow.open(naverMap, marker); 
            }
        }
    }

    for (var i=0, ii=markers.length; i<ii; i++) {
        //console.log(markers[i] , getClickHandler(i));
        naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i)); // 클릭한 마커 핸들러
    }

    var htmlMarker1 = { // 마커설정
        content: '<div style="cursor:pointer;width:40px;height:40px;line-height:42px;font-size:10px;color:white;text-align:center;font-weight:bold;background:url(../img/cluster-marker-1.png);background-size:contain;"></div>',
        size: N.Size(40, 40),
        anchor: N.Point(20, 20)
    },
    htmlMarker2 = {
        content: '<div style="cursor:pointer;width:40px;height:40px;line-height:42px;font-size:10px;color:white;text-align:center;font-weight:bold;background:url(../img/cluster-marker-2.png);background-size:contain;"></div>',
        size: N.Size(40, 40),
        anchor: N.Point(20, 20)
    },
    htmlMarker3 = {
        content: '<div style="cursor:pointer;width:40px;height:40px;line-height:42px;font-size:10px;color:white;text-align:center;font-weight:bold;background:url(../img/cluster-marker-3.png);background-size:contain;"></div>',
        size: N.Size(40, 40),
        anchor: N.Point(20, 20)
    },
    htmlMarker4 = {
        content: '<div style="cursor:pointer;width:40px;height:40px;line-height:42px;font-size:10px;color:white;text-align:center;font-weight:bold;background:url(../img/cluster-marker-4.png);background-size:contain;"></div>',
        size: N.Size(40, 40),
        anchor: N.Point(20, 20)
    },
    htmlMarker5 = {
        content: '<div style="cursor:pointer;width:40px;height:40px;line-height:42px;font-size:10px;color:white;text-align:center;font-weight:bold;background:url(../img/cluster-marker-5.png);background-size:contain;"></div>',
        size: N.Size(40, 40),
        anchor: N.Point(20, 20)
    };
    
    var markerClustering = new MarkerClustering({
        minClusterSize: 2,
        maxZoom: 13,
        map: naverMap,
        markers: markers,
        disableClickZoom: false,
        gridSize: 120,
        icons: [htmlMarker1, htmlMarker2, htmlMarker3, htmlMarker4, htmlMarker5],
        indexGenerator: [10, 100, 200, 500, 1000],
        stylingFunction: function(clusterMarker, count) {
            $(clusterMarker.getElement()).find('div:first-child').text(count);
        }
    });   
}

// ==================================== 네이버 클러스터 Map ==================================== //