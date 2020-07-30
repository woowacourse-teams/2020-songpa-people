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
  bottom: 35px;
  left: 35px;
}

.marker-box {
  position: relative;
  bottom: 77px;
  border-radius: 30px / 30px;
  box-shadow: 1px 1px 1px 1px darkgrey;
  background-color: white;
}

.marker-box:hover {
  border: 3px solid darkgray;
}

.marker-text {
  font-weight: bold;
}

.marker-title {
  width: auto;
  right: 90%;
}
</style>

<!--.marker-box:after {-->
<!--content: "";-->
<!--position: absolute;-->
<!--border-style: solid;-->
<!--border-width: 20px 13px 0px 13px;-->
<!--border-color: white transparent;-->
<!--display: block;-->
<!--width: 0;-->
<!--z-index: 1;-->
<!--bottom: -18.5px;-->
<!--left: 59px;-->
<!--}-->

<!--.marker-box::after {-->
<!--background-color: white;-->
<!--box-shadow: 1px 1px 1px 1px darkgray;-->
<!--content: "";-->
<!--display: block;-->
<!--height: 20px;-->
<!--left: 50px;-->
<!--position: relative;-->
<!--top: 13px;-->
<!--transform:             rotate( 45deg );-->
<!-- -moz-transform:    rotate( 45deg );-->
<!-- -ms-transform:     rotate( 45deg );-->
<!-- -o-transform:      rotate( 45deg );-->
<!-- -webkit-transform: rotate( 45deg );-->
<!--width:  20px;-->
<!--z-index: -1;-->
<!--}-->
