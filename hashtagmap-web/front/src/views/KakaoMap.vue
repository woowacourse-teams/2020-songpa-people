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
    this.showActiveOverlays();
    this.$loadCurrentPosition();
  },

  computed: {
    ...mapGetters([
      "getKakaoMap",
      "getKakaoMapApi",
      "getPlaces",
      "getActiveTagLevels",
      "getActiveCategories",
      "getMapOverlays",
    ]),
  },

  watch: {
    getActiveTagLevels() {
      this.showActiveOverlays();
    },
    getActiveCategories() {
      this.showActiveOverlays();
    },
  },

  methods: {
    ...mapMutations(["SET_KAKAO_MAP_API", "SET_KAKAO_MAP", "ADD_MAP_OVERLAYS"]),
    ...mapActions(["setDetailModal", "setPlaces"]),
    setMapOverlays() {
      this.getPlaces.map(place => {
        const marker = this.createMaker(place);
        const textBalloon = this.createTextBalloon(place, marker);
        this.onAddTextBalloonToMarker(marker, textBalloon);
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
        position: new this.getKakaoMapApi.LatLng(
          place.latitude,
          place.longitude,
        ),
        title: place.placeName,
        image: markerImage,
      });
    },
    createTextBalloon(place, marker) {
      const $content = textBalloonTemplate(place);
      const textBalloon = new this.getKakaoMapApi.CustomOverlay({
        content: $content,
        position: marker.getPosition(),
        yAnchor: 2,
      });
      this.onTextBalloonEvent(textBalloon, place, $content);
      return textBalloon;
    },
    onTextBalloonEvent(textBalloon, place, $content) {
      $content.addEventListener(EVENT_TYPE.CLICK, event => {
        if (event.target.className === "close") {
          textBalloon.setMap(null);
        } else {
          this.setDetailModal(place);
        }
        event.preventDefault();
      });
    },
    onAddTextBalloonToMarker(marker, textBalloon) {
      this.getKakaoMapApi.event.addListener(marker, EVENT_TYPE.CLICK, () =>
        textBalloon.setMap(this.getKakaoMap),
      );
    },
    showActiveOverlays() {
      this.getMapOverlays.filter(mapOverlay => {
        const tagLevelAndCategory = {
          tagLevel: mapOverlay.place.tagLevel,
          category: mapOverlay.place.category,
        };
        if (this.isActiveCategoryAndTagLevel(tagLevelAndCategory)) {
          mapOverlay.marker.setMap(this.getKakaoMap);
          mapOverlay.textBalloon.setMap(this.getKakaoMap);
          mapOverlay.textBalloon.setZIndex(1);
        } else {
          mapOverlay.marker.setMap(null);
          mapOverlay.textBalloon.setMap(null);
        }
      });
    },
    isActiveCategoryAndTagLevel(tagLevelAndCategory) {
      return (
        this.getActiveTagLevels.includes(tagLevelAndCategory.tagLevel) &&
        this.getActiveCategories.includes(tagLevelAndCategory.category)
      );
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
}
</style>
