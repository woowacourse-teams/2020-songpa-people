import {KAKAO_WEB_KEY} from "@/secret";
import navigatorUtils from "@/utils/navigator.js"

/**
 * main.js 에 Vue.use(KaKaoMap) 을 해야한다.
 * 사용할 컴포넌트에서 <div id="kakao-map"></div> 작성후
 * mounted 에 this.$loadMap() 호출을 하면 카카오 지도를 로드한다.
 * 특정 위치를 보여주고 싶다면 {latitude:위도값, longitute:경도값} 를 매개변수로 주입하면 된다.
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
        }

        Vue.setPositionCenter = function (position) {
            // 새로고침하면 this.map이 undefined가 되는데 이유를 모르겠음
            if (this.map) {
                this.map.setCenter(position);
            } else {
                console.log("this.map is not defined")
            }
        }

        Vue.prototype.$loadCurrentPosition = () => {
            const currentGeolocation = navigatorUtils.getCurrentPosition();

            currentGeolocation.then(position => {
                console.log(1)
                const currentPosition = navigatorUtils.extractGeolocationPosition(position);
                Vue.setPositionCenter(createKakaoMapsLatLng(currentPosition));
            }).catch(() => {
                console.log(2)
                Vue.setPositionCenter(createKakaoMapsLatLng({
                    latitude: 37.5234037,
                    longitude: 126.9797361
                }));
            })
        }

        const createOptions = (nowPosition) => {
            return {
                center: createKakaoMapsLatLng(nowPosition),
                level: 5
            };
        }

        const createKakaoMapsLatLng = (nowPosition) => {
            const position = nowPosition || {
                latitude: 37.513906,
                longitude: 127.0991843
            };
            return new kakao.maps.LatLng(position.latitude, position.longitude)
        }

    }
}