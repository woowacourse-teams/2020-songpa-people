<template>
  <div>
    <div id="kakao-map">
      <img
        id="current-position"
        src="@/assets/current-location-button.png"
        @click="moveToCurrentLocation"
      />
    </div>
  </div>
</template>

<script>
export default {
  name: "KakaoMap",
  async mounted() {
    await this.$loadMap();
    this.$loadCurrentPosition();
  },
  methods: {
    moveToCurrentLocation: function() {
      this.$loadCurrentPosition();
    },
  },
};
</script>

<style scoped>
#kakao-map {
  width: 100%;
  height: 600px;
  z-index: 1;
}
#current-position {
  z-index: 10;
  height: 10%;
  position: absolute;
  left: 7px;
  bottom: 7px;
}
</style>
