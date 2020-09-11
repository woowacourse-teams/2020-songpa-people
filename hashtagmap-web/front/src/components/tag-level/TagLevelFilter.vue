<template>
  <div>
    <v-chip
      class="ma-0 chip-size"
      label
      :color="tagLevel.rgb"
      @click.stop="showIcon"
    >
      <v-icon color="white" style="visibility: visible;">{{ marked }}</v-icon>
    </v-chip>
  </div>
</template>

<script>
import { mapMutations } from "vuex";
import { mdiCheck } from "@mdi/js";

export default {
  data: () => ({
    marked: mdiCheck,
  }),

  props: {
    tagLevel: {
      type: Object,
      required: true,
    },
  },

  methods: {
    ...mapMutations(["SET_TAG_LEVEL"]),
    showIcon(eventHistory) {
      this.SET_TAG_LEVEL(this.tagLevel);
      const markerStyle =
        eventHistory.currentTarget.childNodes[0].firstChild.style.cssText;
      if (markerStyle === "visibility: visible;") {
        eventHistory.currentTarget.childNodes[0].firstChild.style.cssText =
          "visibility: hidden;";
      } else {
        eventHistory.currentTarget.childNodes[0].firstChild.style.cssText =
          "visibility: visible;";
      }
    },
  },
};
</script>

<style lang="stylus" scoped>
.chip-size {
  width: 45px !important;
}

.theme--light.v-chip {
  color: rgba(0, 0, 0, 0.87) !important;
}
</style>
