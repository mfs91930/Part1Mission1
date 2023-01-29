const myLocation = document.getElementById("myLocation");
myLocation.addEventListener('click', (function() {
    if (navigator.geolocation) {
        //위치 정보를 얻기
        navigator.geolocation.getCurrentPosition (function(pos) {
            var latitude = pos.coords.latitude.toFixed(6);
            var longitude = pos.coords.longitude.toFixed(6);
            $('#latitude').val(latitude);  // 위도
            $('#longitude').val(longitude); // 경도
            console.log("Latitude: " + latitude + ", Longitude: " + longitude);
        });
    } else {
        alert("geolocation 사용 불가")
    }
}))

