import kakaoSearch from "@/request/kakao.js";
import { KAKAO_WEB_KEY } from "@/secret";
import navigatorUtils from "@/libs/navigator/navigator.js";
import dotImgSrc from "@/assets/dot.png";
import { KAKAO_MAP } from "@/utils/constants.js";
import { Notyf } from "notyf";
import "notyf/notyf.min.css";
import { getMarkerImage, SIZE } from "@/utils/markerImages";

/**
 * main.js 에 Vue.use(KaKaoMap) 을 해야 한다.
 * 사용할 컴포넌트에서 <div id="kakao-map"></div> 작성후
 * mounted 에 this.$loadMap() 호출을 하면 카카오 지도를 띄운다.
 *
 * kakao-map을 로드후에 map을 이용한 로직이 필요하다면 mounted 에 async 를 하고 await로 $loadMap()의 실행을 기다려야 한다.
 *
 * 맵의 초기 위치를 지정하고 싶다면 {latitude:위도값, longitute:경도값} 를 매개변수로 주입하면 된다.
 * 현재 좌표를 받아오지 못 한다면 기본 좌표는 잠실역 8번 출구이다.
 */
export default {
  install(Vue) {
    const script = document.createElement("script");
    script.src =
      KAKAO_MAP.API_SRC_ADDRESS +
      KAKAO_WEB_KEY +
      KAKAO_MAP.API_EXTENSION_LIBRARY;
    document.head.appendChild(script);
    const notyf = new Notyf();
    var userMarker = null;

    /* global kakao */
    const loadApi = new Promise(resolve => {
      script.onload = () => kakao.maps.load(resolve);
    });

    Vue.prototype.$initKakaoMapApi = async () => {
      await loadApi;
      this.$kakaoApi = kakao.maps;
      return kakao.maps;
    };

    Vue.prototype.$kakaoApi = () => {
      return this.$kakaoApi;
    };

    Vue.prototype.$loadMap = nowPosition => {
      if (this.map) {
        return this.map;
      }
      const options = createOptions(nowPosition);
      const container = document.getElementById("kakao-map");

      this.map = new kakao.maps.Map(container, options);
      this.map.setMapTypeId(kakao.maps.MapTypeId.NORMAL);

      return this.map;
    };

    Vue.prototype.$loadClusterer = () => {
      this.clusterer = new kakao.maps.MarkerClusterer({
        map: this.map,
        averageCenter: true,
        minLevel: 6,
        texts: getTexts,
      });
      return this.clusterer;
    };

    const getTexts = count => {
      return count / 2;
    };

    const createOptions = nowPosition => {
      return {
        center: createKakaoMapsLatLng(nowPosition),
        level: 4,
      };
    };

    Vue.prototype.$makeTextBalloonOverlay = (textBalloonContent, marker) => {
      return new kakao.maps.CustomOverlay({
        content: textBalloonContent,
        position: marker.getPosition(),
        yAnchor: 2,
      });
    };

    Vue.prototype.$createMaker = place => {
      const imageSize = new kakao.maps.Size(SIZE.width, SIZE.height);
      const markerImage = new kakao.maps.MarkerImage(
        getMarkerImage(place.tagLevel),
        imageSize,
      );
      return new kakao.maps.Marker({
        position: new kakao.maps.LatLng(place.latitude, place.longitude),
        title: place.placeName,
        image: markerImage,
      });
    };

    Vue.prototype.$loadCurrentPosition = () => {
      const currentGeolocation = navigatorUtils.getCurrentPosition();

      currentGeolocation.then(position => {
        const currentPosition = navigatorUtils.extractGeolocationPosition(
          position,
        );
        setPositionCenter.call(this, currentPosition);
        displayUserMarker.call(this, currentPosition);
        notyf.success("사용자 위치를 불러왔습니다.");
        return;
      });
      notyf.error("현재 위치를 불러오지 못했습니다.");
    };

    Vue.prototype.$searchKeywordAndLoadPosition = async keyword => {
      kakaoSearch.getKeywordSearch(keyword).then(res => {
        const currentPosition = navigatorUtils.convertToLatLon(
          res.data.documents[0].x,
          res.data.documents[0].y,
        );
        setPositionCenter.call(this, currentPosition);
        return;
      });
      notyf.error("검색에 실패했습니다.");
    };

    const setPositionCenter = position => {
      this.map.setCenter(createKakaoMapsLatLng(position));
    };

    const createKakaoMapsLatLng = nowPosition => {
      const position = nowPosition || KAKAO_MAP.JAMSIL_STATION_8_EXIT;
      return new kakao.maps.LatLng(position.latitude, position.longitude);
    };

    const displayUserMarker = position => {
      if (userMarker != null) {
        userMarker.setMap(null);
      }
      const imageSize = new kakao.maps.Size(
        KAKAO_MAP.USER_MAKER_SIZE,
        KAKAO_MAP.USER_MAKER_SIZE,
      );
      const markerImage = new kakao.maps.MarkerImage(dotImgSrc, imageSize);
      userMarker = new kakao.maps.Marker({
        position: createKakaoMapsLatLng(position),
        image: markerImage,
      });

      userMarker.setMap(this.map);
    };
  },
};
