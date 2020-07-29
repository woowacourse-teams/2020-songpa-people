<template>
  <v-app>
    <v-main>
      <KakaoMap id="kakao-map" />
      <CurrentLocationButton />
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
  font-family Avenir, Helvetica, Arial, sans-serif
  -webkit-font-smoothing antialiased
  -moz-osx-font-smoothing grayscale
  text-align center
  color #2c3e50
}

#kakao-map {
  margin 0;
  width: 100%;
  height: 100%;
  z-index: 1;
}
</style>
