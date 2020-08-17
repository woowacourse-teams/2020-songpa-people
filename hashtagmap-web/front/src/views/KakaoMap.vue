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
    this.setMapOverlays();
    this.$loadCurrentPosition();
  },

  mounted() {
    this.$store.watch(() => {
      this.$store.getters.activeOverlays;
    });
  },

  computed: {
    ...mapGetters(["getKakaoMap", "getKakaoMapApi", "getPlaces"]),
  },

  methods: {
    ...mapMutations(["SET_KAKAO_MAP_API", "SET_KAKAO_MAP", "ADD_MAP_OVERLAYS"]),
    ...mapActions(["setDetailModal", "setPlaces"]),
    async setMapOverlays() {
      await this.places.map(place => {
        const marker = this.createMaker(place);
        const textBalloon = this.createTextBalloon(place, marker);
        this.ADD_MAP_OVERLAYS({ place, marker, textBalloon });
      });
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
      const $content = textBalloonTemplate(place);
      this.onAddModalToTextBalloon(place, $content);
      return new this.kakaoMapApi.CustomOverlay({
        content: $content,
        position: marker.getPosition(),
        yAnchor: 2,
      });
    },
    onAddModalToTextBalloon(place, $content) {
      $content.addEventListener(EVENT_TYPE.CLICK, event => {
        event.preventDefault();
        this.setDetailModal(place);
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
