import {KAKAO_WEB_KEY} from "@/secret";

/**
 * main.js 에 Vue.use(KaKaoMap) 을 해야한다.
 * 사용할 컴포넌트에서 <div id="kakao-map"></div> 작성후
 * mounted 에 this.$createMap() 호출을 하면 카카오 지도를 로드한다.
 * 현재 좌표를 받아오지 못한다면 기본좌표는 잠실역 8번출구(37.513906,127.0991843)이다.
 */
export default {
    install(Vue) {
        /* global kakao */
        const script = document.createElement('script');
        script.onload = () => kakao.maps.load(Vue.initMap);
        script.src = '//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=' + KAKAO_WEB_KEY;
        document.head.appendChild(script);

// TODO nowPosition 에 현재 좌표를 동적으로 받아와야한다.
        Vue.initMap = function (nowPosition) {
            const container = document.getElementById('kakao-map');
            const position = nowPosition || {
                longitude: 37.513906,
                latitude: 127.0991843
            };
            const options = {
                center: new kakao.maps.LatLng(position.longitude, position.latitude),
                level: 3
            };

            Vue.create(container, options);
        }

        Vue.create = function (container, options) {
            const map = new kakao.maps.Map(container, options);
            map.setMapTypeId(kakao.maps.MapTypeId.NORMAL);
        }


        Vue.prototype.$createMap = function () {
            if (window.kakao && window.kakao.maps) {
                Vue.initMap();
            }
        }

    }
}