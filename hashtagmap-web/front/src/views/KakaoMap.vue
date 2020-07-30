<template>
  <div>
    <div id="kakao-map"></div>
  </div>
</template>

<script>
import { EVENT_TYPE, KAKAO_MAP } from "../utils/constants";
import { textBalloonTemplate } from "../utils/templates";

export default {
  name: "KakaoMap",
  async mounted() {
    this.$store.commit("initKakaoMapApi", await this.$initKakaoMapApi());
    this.$store.commit("initKakaoMap", this.$loadMap());

    this.loadMarker(this.$store.getters.getPlaces);

    this.$loadCurrentPosition();
  },
  methods: {
    loadMarker(places) {
      places.forEach(place => {
        const map = this.$store.state.kakaoMap;
        const marker = this.createMaker(place);
        marker.setMap(map);

        let textBalloon = this.createTextBalloon(place, marker);

        this.$store.state.kakaoMapApi.event.addListener(
          marker,
          EVENT_TYPE.CLICK,
          function() {
            textBalloon.setMap(map);
            const $textBalloon = document.querySelector(".text-balloon");
            $textBalloon.addEventListener(EVENT_TYPE.CLICK, function() {
              textBalloon.setMap(null);
            });
          },
        );
      });
    },
    createMaker(place) {
      const mapApi = this.$store.state.kakaoMapApi;
      const imageSize = new mapApi.Size(
        KAKAO_MAP.PLACE_MARKER.width,
        KAKAO_MAP.PLACE_MARKER.height,
      );
      const markerImage = new mapApi.MarkerImage(
        KAKAO_MAP.PLACE_MARKER.image_url,
        imageSize,
      );
      return new mapApi.Marker({
        position: place.latlng,
        title: place.title,
        image: markerImage,
      });
    },
    createTextBalloon(place, marker) {
      return new this.$store.state.kakaoMapApi.CustomOverlay({
        content: textBalloonTemplate(place),
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
