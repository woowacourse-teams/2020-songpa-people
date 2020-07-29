<template>
  <div>
    <img
      id="current-location-button-img"
      src="@/assets/current-location-button.png"
      @click="moveToCurrentLocation"
    />
  </div>
</template>

<script>
export default {
  methods: {
    moveToCurrentLocation() {
      this.$loadCurrentPosition();
    },
  },
};
</script>

<style scoped>
#current-location-button-img {
  width: 60px;
}
</style>
