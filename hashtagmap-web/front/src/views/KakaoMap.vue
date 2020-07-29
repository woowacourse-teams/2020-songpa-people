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
        const marker = this.createMaker(place, mapApi);
        marker.setMap(this.$store.state.kakaoMap);

        let textBalloon = this.createTextBalloon(place, marker, mapApi);

        mapApi.event.addListener(marker, EVENT_TYPE.CLICK, function() {
          textBalloon.setMap(map);
          const $textBalloon = document.querySelector(".text-balloon");
          $textBalloon.addEventListener(EVENT_TYPE.CLICK, function() {
            textBalloon.setMap(null);
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
    createTextBalloon(place, marker, mapApi) {
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
