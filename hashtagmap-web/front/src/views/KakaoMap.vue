<template>
  <div>
    <div id="kakao-map"></div>
  </div>
</template>

<script>
import { EVENT_TYPE, KAKAO_MAP } from "../utils/constants";
import { markerInfoTemplate } from "../utils/templates";

export default {
  name: "KakaoMap",
  async mounted() {
    const mapApi = await this.$loadMapApi();
    this.$store.commit("initMapApi", mapApi);

    const map = this.$loadMap();
    this.$store.commit("initKakaoMap", map);

    const places = this.$store.getters.getPlaces;
    this.loadMarker(places);

    this.$loadCurrentPosition();
  },
  methods: {
    loadMarker(places) {
      const mapApi = this.$store.state.mapApi;
      const map = this.$store.state.kakaoMap;
      places.forEach(place => {
        //마커 셋팅
        const marker = this.createMaker(place, mapApi);
        marker.setMap(this.$store.state.kakaoMap);

        //마커에 말풍선(overlay)달기
        let customOverlay = this.createOverlay(place, marker, mapApi);

        //오버레이에 말풍선 제거 이벤트 등록
        mapApi.event.addListener(marker, EVENT_TYPE.CLICK, function() {
          customOverlay.setMap(map);
          const $infoBoxCloseBtn = document.querySelector(".info-box");
          $infoBoxCloseBtn.addEventListener(EVENT_TYPE.CLICK, function() {
            customOverlay.setMap(null);
          });
        });
      });
    },
    createMaker(place, mapApi) {
      const imageSize = new mapApi.Size(
        KAKAO_MAP.KAKAO_DEFAULT_MARKER.default_marker_width,
        KAKAO_MAP.KAKAO_DEFAULT_MARKER.default_marker_height,
      );
      const markerImage = new mapApi.MarkerImage(
        KAKAO_MAP.KAKAO_DEFAULT_MARKER.default_image_url,
        imageSize,
      );
      return new mapApi.Marker({
        position: place.latlng,
        title: place.title,
        image: markerImage,
      });
    },
    createOverlay(place, marker, mapApi) {
      return new mapApi.CustomOverlay({
        content: markerInfoTemplate(place),
        position: marker.getPosition(),
      });
    },
  },
};
</script>

<style scoped>
#kakao-map {
  margin: 0;
  width: 100%;
  height: 100%;
  z-index: 1;
}
</style>
