<template>
  <v-avatar
    class="white"
    id="current-location-button"
    @click="moveToCurrentLocation"
  >
    <v-icon id="gps" large color="red">{{ gps }}</v-icon>
  </v-avatar>
</template>

<script>
import { mdiCrosshairsGps } from "@mdi/js";

export default {
  data: () => ({
    gps: mdiCrosshairsGps,
  }),
  methods: {
    moveToCurrentLocation() {
      this.$loadCurrentPosition();
    },
  },
};
</script>

<style scoped>
#current-location-button {
  z-index: 10;
  position: fixed;
  bottom: 5%;
  left: 1%;
  height: 55px;
  width: 55px;
}
</style>
