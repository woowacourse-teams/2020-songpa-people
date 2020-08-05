<template>
  <div>
    <div id="kakao-map"></div>
    <DetailModal />
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions, mapGetters } from "vuex";
import { EVENT_TYPE, KAKAO_MAP } from "../utils/constants";
import { textBalloonTemplate } from "../utils/templates";
import DetailModal from "../components/DetailModal";

export default {
  name: "KakaoMap",
  async mounted() {
    this.initKakaoMapApi(await this.$initKakaoMapApi());
    this.initKakaoMap(this.$loadMap());
    this.loadMarker(this.getPlaces);
    this.$loadCurrentPosition();
  },
  computed: {
    ...mapState(["kakaoMap", "kakaoMapApi"]),
    ...mapGetters(["getPlaces"]),
  },
  methods: {
    ...mapMutations(["initKakaoMapApi", "initKakaoMap"]),
    ...mapActions(["showDetailModal"]),
    loadMarker(places) {
      places.map(place => {
        const map = this.kakaoMap;
        const marker = this.createMaker(place);
        marker.setMap(map);
        const textBalloon = this.createTextBalloon(place, marker);
        this.kakaoMapApi.event.addListener(
          marker,
          EVENT_TYPE.CLICK,
          this.onAddTextBalloonToMarker(map, place, textBalloon),
        );
      });
    },
    onAddTextBalloonToMarker(map, place, textBalloon) {
      return () => {
        textBalloon.setMap(map);
        const $textBalloon = document.getElementById(`${place.id}`);
        $textBalloon.addEventListener(
          EVENT_TYPE.CLICK,
          this.onAddModalToTextBalloon(place),
        );
      };
    },
    onAddModalToTextBalloon(place) {
      return event => {
        event.preventDefault();
        this.showDetailModal(place);
      };
    },
    createMaker(place) {
      const mapApi = this.kakaoMapApi;
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
