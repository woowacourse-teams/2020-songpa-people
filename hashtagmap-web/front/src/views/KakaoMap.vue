<template>
  <v-app>
    <v-main>
      <div id="kakao-map">
        <template v-if="isMapLoad">
          <TagPin
            v-for="overlay in boundedOverLays"
            :key="overlay.place.kakaoId + '-' + new Date().getTime()"
            :overlay="overlay"
            :bounds="getBounds"
          />
        </template>
      </div>
      <AppBar />
      <CurrentLocationButton id="current-location-button" />
      <DetailModal />
    </v-main>
  </v-app>
</template>

<script>
import CurrentLocationButton from "@/components/CurrentLocationButton";
import DetailModal from "@/components/detail-modal/DetailModal";
import AppBar from "../components/AppBar";

import { mapActions, mapGetters, mapMutations } from "vuex";
import TagPin from "@/components/TagPin";

export default {
  name: "KakaoMap",

  data() {
    return {
      isMapLoad: false,
      boundedOverLays: [],
    };
  },

  async created() {
    this.SET_KAKAO_MAP_API(await this.$initKakaoMapApi());
    this.SET_KAKAO_MAP(this.$loadMap());
    this.SET_BOUNDS_EVENT();
    this.SET_CLUSTERER(this.$loadClusterer());
    if (this.getPlaces.length === 0) {
      await this.setPlaces();
    }
    this.setMapOverlays();
    this.CHANGE_BOUNDED_OVERLAYS(this.getKakaoMap.getBounds());
    this.boundedOverLays = this.getBoundedMapOverLays;
    this.isMapLoad = true;
  },

  computed: {
    ...mapGetters([
      "getKakaoMap",
      "getKakaoMapApi",
      "getPlaces",
      "getActiveTagLevels",
      "getActiveCategories",
      "getBounds",
      "getBoundedMapOverLays",
    ]),
  },

  watch: {
    getActiveTagLevels() {
      this.CLEAR_CLUSTERER();
    },
    getActiveCategories() {
      this.CLEAR_CLUSTERER();
    },
    getBounds() {
      this.CLEAR_CLUSTERER();
      this.CHANGE_BOUNDED_OVERLAYS(this.getBounds);
      this.boundedOverLays = this.getBoundedMapOverLays;
    },
  },

  methods: {
    ...mapMutations([
      "SET_KAKAO_MAP_API",
      "SET_KAKAO_MAP",
      "ADD_MAP_OVERLAYS",
      "SET_CLUSTERER",
      "ADD_CLUSTER",
      "CLEAR_CLUSTERER",
      "CHANGE_BOUNDED_OVERLAYS",
      "SET_BOUNDS_EVENT",
    ]),
    ...mapActions(["setDetailModal", "setPlaces"]),
    setMapOverlays() {
      this.getPlaces.map(place => {
        const marker = this.$createMaker(place);
        this.ADD_MAP_OVERLAYS({ place, marker });
      });
    },
  },

  components: {
    TagPin,
    DetailModal,
    CurrentLocationButton,
    AppBar,
  },
};
</script>

<style scoped>
@import "../assets/css/textBalloon.css";
@import "../assets/css/app.css";

#kakao-map {
  margin: 0;
  width: 100%;
  height: 100%;
}
</style>
