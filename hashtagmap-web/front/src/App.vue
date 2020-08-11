<template>
  <v-app>
    <v-main>
      <KakaoMap id="kakao-map" />
      <CurrentLocationButton id="current-location-button" />
      <TagLevelContainer id="tag-level" />
    </v-main>
  </v-app>
</template>

<script>
import KakaoMap from "@/views/KakaoMap";
import CurrentLocationButton from "./components/CurrentLocationButton";
import TagLevelContainer from "./components/tag-level/TagLevelContainer";

export default {
  name: "App",

  components: {
    KakaoMap,
    CurrentLocationButton,
    TagLevelContainer,
  },

  data: () => ({
    //
  }),
};
</script>

<style lang="stylus" scoped>
@import "assets/css/textBalloon.css"
@import "assets/css/app.css"
</style>
