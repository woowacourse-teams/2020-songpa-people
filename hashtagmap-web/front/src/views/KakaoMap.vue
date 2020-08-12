<template>
  <div>
    <div id="kakao-map"></div>
    <DetailModal />
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
import { EVENT_TYPE } from "../utils/constants";
import { textBalloonTemplate } from "../utils/templates";
import DetailModal from "../components/DetailModal";
import { getMarkerImage, SIZE } from "../utils/markerImages";

export default {
  name: "KakaoMap",
  async mounted() {
    this.SET_KAKAO_MAP_API(await this.$initKakaoMapApi());
    this.SET_KAKAO_MAP(this.$loadMap());
    await this.setPlaces();
    this.loadMarker();
    this.$loadCurrentPosition();
  },
  computed: {
    ...mapState(["kakaoMap", "kakaoMapApi", "places"]),
  },
  methods: {
    ...mapMutations(["SET_KAKAO_MAP_API", "SET_KAKAO_MAP"]),
    ...mapActions(["setDetailModal", "setPlaces"]),
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
      this.setDetailModal(place);
    },
    createMaker(place) {
      const imageSize = new this.kakaoMapApi.Size(SIZE.width, SIZE.height);
      const markerImage = new this.kakaoMapApi.MarkerImage(
        getMarkerImage(place.tagLevel),
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
