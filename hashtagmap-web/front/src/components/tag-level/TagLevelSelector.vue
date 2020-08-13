<template>
  <v-container>
    <v-chip-group column multiple>
      <v-row class="ma-0" align="start" justify="space-around">
        <v-chip
          class="ma-0 chip-size"
          label
          v-for="color in colors"
          :color="color"
          :key="color"
          @click.stop="showMarker"
        >
          <v-icon color="white" style="visibility: hidden;">{{
            marked
          }}</v-icon>
        </v-chip>
      </v-row>
    </v-chip-group>
  </v-container>
</template>

<script>
import { mdiCheck } from "@mdi/js";

export default {
  data: () => ({
    marked: mdiCheck,
    colors: [
      "rgb(65,12,162)",
      "rgb(116,22,227)",
      "rgb(158,76,237)",
      "rgb(185,112,243)",
      "rgb(216,160,250)",
    ],
    active: true,
  }),

  methods: {
    showMarker(event) {
      const markerStyle =
        event.currentTarget.childNodes[0].firstChild.style.cssText;
      if (markerStyle === "visibility: visible;") {
        event.currentTarget.childNodes[0].firstChild.style.cssText =
          "visibility: hidden;";
      } else {
        event.currentTarget.childNodes[0].firstChild.style.cssText =
          "visibility: visible;";
      }
    },
  },
};
</script>

<style lang="stylus" scoped>
.container {
  padding: 0 !important;
}

.chip-size {
  width: 45px !important;
}

.theme--light.v-chip {
  color: rgba(0, 0, 0, 0.87) !important;
}
</style>
