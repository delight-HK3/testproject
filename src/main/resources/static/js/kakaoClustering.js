// ==================================== 카카오 클러스터 Map ==================================== //

function kakaoMap(myItem){
    var kakaoMap = new kakao.maps.Map(document.getElementById('kakaoMap'), { // 지도를 표시할 div
        center : new kakao.maps.LatLng(36.2683, 127.6358), // 지도의 중심좌표 
        level : 13 // 지도의 확대 레벨 
    });
    
    // 마커 클러스터러를 생성합니다 
    var clusterer = new kakao.maps.MarkerClusterer({
        map: kakaoMap, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
        minLevel: 10, // 클러스터 할 최소 지도 레벨 
    });
    
    //$.get("/json/jijung.json", function(data) {
    //$.get("/json/xytest.json", function(data) {
        // 데이터에서 좌표 값을 가지고 마커를 표시합니다
        // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
        var markers = $(myItem).map(function(i, myItem) {
            return new kakao.maps.Marker({
                position : new kakao.maps.LatLng(myItem.latitude, myItem.longitude)
            });
        });
    
        // 클러스터러에 마커들을 추가합니다
        clusterer.addMarkers(markers);
   // });
}

// ==================================== 카카오 클러스터 Map ==================================== //