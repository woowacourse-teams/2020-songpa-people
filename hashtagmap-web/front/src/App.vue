<template>
  <v-app>
    <v-main>
      <KakaoMap id="kakao-map" />
      <CurrentLocationButton id="current-location-button" />
    </v-main>
  </v-app>
</template>

<script>
import KakaoMap from "@/views/KakaoMap";
import CurrentLocationButton from "./components/CurrentLocationButton";

export default {
  name: "App",

  components: {
    KakaoMap,
    CurrentLocationButton,
  },

  data: () => ({
    //
  }),
};
</script>

<style lang="stylus">
  @import "assets/css/textBalloon.css"
  @import "assets/css/app.css"
</style>
