<#import "parts/common.ftlh" as c>

<@c.page>
<div>
    <h1 class="display-6">О нас</h1>
    <div class="container" style="margin-top: 45px;">
        <div class="row">
            <div class="col">
                <p>"Tour Operator" Дипломдық жоба. Ақпараттық технологиялар факультетінің ФИТ 17-1 (СИБ) студенті Жекей Ерғазының дипломдық жобасы</p>
                <img src="/static/img/logo-1.png">
            </div>

            <div class="col">
                <h3>My Google Maps Demo</h3>
                <!--The div element for the map -->
                <div id="map"></div>
            </div>
        </div>
    </div>
</div>
    <script>
        function initMap() {
            const uluru = { lat: 49.81862411584706, lng: 73.10437201720188 };
            const map = new google.maps.Map(document.getElementById("map"), {
                zoom: 50,
                center: uluru,
            });
            const marker = new google.maps.Marker({
                position: uluru,
                map: map,
            });
        }
    </script>
    <script
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDV2ccHc_wsC7wGRxbeuM13j3qAzKWDXrs&callback=initMap&libraries=&v=weekly"
            async
    ></script>
</@c.page>