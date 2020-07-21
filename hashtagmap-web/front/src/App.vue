<template>
  <div id="app">
    <KakaoMap />
  </div>
</template>

<script>
import KakaoMap from "@/views/KakaoMap";

export default {
  components: {
    KakaoMap,
  },
};
</script>

<style lang="stylus">
#app
  font-family Avenir, Helvetica, Arial, sans-serif
  -webkit-font-smoothing antialiased
  -moz-osx-font-smoothing grayscale
  text-align center
  color #2c3e50
  margin-top 60px
</style>
