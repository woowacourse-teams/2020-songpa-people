import {KAKAO_WEB_KEY} from "@/secret";

/**
 * main.js 에 Vue.use(KaKaoMap) 을 해야한다.
 * 사용할 컴포넌트에서 <div id="kakao-map"></div> 작성후
 * mounted 에 this.$loadMap() 호출을 하면 카카오 지도를 로드한다.
 * 특정 위치를 보여주고 싶다면 {longitute, latitude} 를 설정하여 주입하면 된다.
 * 현재 좌표를 받아오지 못한다면 기본좌표는 잠실역 8번출구(37.513906,127.0991843)이다.
 */
export default {
    install(Vue) {
        const script = document.createElement('script');
        script.src = '//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=' + KAKAO_WEB_KEY;
        document.head.appendChild(script);

        /* global kakao */
        const loadApi = new Promise((resolve) => {
            script.onload = () => kakao.maps.load(resolve);
        });

        Vue.prototype.$loadMap = async (nowPosition) => {
            await loadApi;

            const options = createOptions(nowPosition);
            const container = document.getElementById('kakao-map');

            this.map = new kakao.maps.Map(container, options);
            this.map.setMapTypeId(kakao.maps.MapTypeId.NORMAL);
            this.map.setCenter(options.center);
        }

        const createOptions = (nowPosition) => {
            return {
                center: createKakaoMapsLatLng(nowPosition),
                level: 3
            };
        }

        const createKakaoMapsLatLng = (nowPosition) => {
            const position = nowPosition || {
                longitude: 37.513906,
                latitude: 127.0991843
            };
            return new kakao.maps.LatLng(position.longitude, position.latitude)
        }

    }
}