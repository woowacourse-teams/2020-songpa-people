<template>
  <div>
    <div id="kakao-map"></div>
  </div>
</template>

<script>
export default {
  name: "KakaoMap",
  async mounted() {
    await this.$loadMap();
    this.$loadCurrentPosition();
    this.$loadPlaces();
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
