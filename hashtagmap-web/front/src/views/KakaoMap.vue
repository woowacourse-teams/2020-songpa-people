<template>
  <div>
    <div id="kakao-map"></div>
    <DetailModal />
  </div>
</template>

<script>
import { mapActions, mapMutations, mapGetters } from "vuex";
import { EVENT_TYPE } from "../utils/constants";
import { textBalloonTemplate } from "../utils/templates";
import DetailModal from "../components/detail-modal/DetailModal";
import { getMarkerImage, SIZE } from "../utils/markerImages";

export default {
  name: "KakaoMap",

  async created() {
    this.SET_KAKAO_MAP_API(await this.$initKakaoMapApi());
    this.SET_KAKAO_MAP(this.$loadMap());
    await this.setPlaces();
    this.loadMarker();
    this.$loadCurrentPosition();
  },

  mounted() {
    this.$store.watch(() => {
       this.$store.getters.activeMarker;
    });
  },

  computed: {
    ...mapGetters(["getKakaoMap", "getKakaoMapApi", "getPlaces"]),
  },

  methods: {
    ...mapMutations([
      "SET_KAKAO_MAP_API",
      "SET_KAKAO_MAP",
      "ADD_MARKER_DETAIL",
    ]),
    ...mapActions(["setDetailModal", "setPlaces"]),
    loadMarker() {
      this.getPlaces.map(place => {
        const marker = this.createMaker(place);
        marker.setMap(this.getKakaoMap);
        this.ADD_MARKER_DETAIL({
          marker,
          tagLevel: place.tagLevel,
          category: place.category,
        });
        const textBalloon = this.createTextBalloon(place, marker);
        this.getKakaoMapApi.event.addListener(marker, EVENT_TYPE.CLICK, () => {
          this.onAddTextBalloonToMarker(this.getKakaoMap, place, textBalloon);
        });
      });
    },
    onAddTextBalloonToMarker(kakaoMap, place, textBalloon) {
      textBalloon.setMap(kakaoMap);
      const $textBalloon = document.getElementById(`${place.kakaoId}`);
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
      const imageSize = new this.getKakaoMapApi.Size(SIZE.width, SIZE.height);
      const markerImage = new this.getKakaoMapApi.MarkerImage(
        getMarkerImage(place.tagLevel),
        imageSize,
      );
      return new this.getKakaoMapApi.Marker({
        position: new this.getKakaoMapApi.LatLng(place.latitude, place.longitude),
        title: place.placeName,
        image: markerImage,
      });
    },
    createTextBalloon(place, marker) {
      return new this.getKakaoMapApi.CustomOverlay({
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
