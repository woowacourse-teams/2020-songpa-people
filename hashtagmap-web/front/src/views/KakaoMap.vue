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

<style scoped></style>
