<template>
  <div>
    <div id="kakao-map"></div>
    <DetailModal />
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
import { EVENT_TYPE, KAKAO_MAP } from "../utils/constants";
import { textBalloonTemplate } from "../utils/templates";
import DetailModal from "../components/DetailModal";

export default {
  name: "KakaoMap",
  async mounted() {
    this.initKakaoMapApi(await this.$initKakaoMapApi());
    this.initKakaoMap(this.$loadMap());
    await this.getPlaces();
    this.loadMarker();
    this.$loadCurrentPosition();
  },
  computed: {
    ...mapState(["kakaoMap", "kakaoMapApi", "places"]),
  },
  methods: {
    ...mapMutations(["initKakaoMapApi", "initKakaoMap"]),
    ...mapActions(["showDetailModal", "getPlaces"]),
    loadMarker() {
      this.places.map(place => {
        const marker = this.createMaker(place);
        marker.setMap(this.kakaoMap);
        const textBalloon = this.createTextBalloon(place, marker);
        this.kakaoMapApi.event.addListener(marker, EVENT_TYPE.CLICK, () => {
          this.onAddTextBalloonToMarker(this.kakaoMap, place, textBalloon);
        });
      });
    },
    onAddTextBalloonToMarker(kakaoMap, place, textBalloon) {
      textBalloon.setMap(kakaoMap);
      const $textBalloon = document.getElementById(`${place.id}`);
      $textBalloon.addEventListener(EVENT_TYPE.CLICK, event => {
        if (event.target.classList.contains("marker-title")) {
          this.onAddModalToTextBalloon(event, place);
        } else {
          textBalloon.setMap(null);
        }
      });
    },
    onAddModalToTextBalloon(event, place) {
      event.preventDefault();
      this.showDetailModal(place);
    },
    createMaker(place) {
      const imageSize = new this.kakaoMapApi.Size(
        KAKAO_MAP.PLACE_MARKER.width,
        KAKAO_MAP.PLACE_MARKER.height,
      );
      const markerImage = new this.kakaoMapApi.MarkerImage(
        KAKAO_MAP.PLACE_MARKER.image_url,
        imageSize,
      );
      return new this.kakaoMapApi.Marker({
        position: new this.kakaoMapApi.LatLng(place.latitude, place.longitude),
        title: place.placeName,
        image: markerImage,
      });
    },
    createTextBalloon(place, marker) {
      return new this.kakaoMapApi.CustomOverlay({
        content: textBalloonTemplate(place),
        position: marker.getPosition(),
      });
    },
  },
  components: {
    DetailModal,
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
