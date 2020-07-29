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
#app {
  font-family: 'Jua', sans-serif;
  -webkit-font-smoothing antialiased
  -moz-osx-font-smoothing grayscale
  text-align center
  color #2c3e50
}

#current-location-button {
  z-index: 10;
  position: fixed;
  bottom: 5%;
  left: 5%;
}
</style>
