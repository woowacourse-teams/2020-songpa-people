<template>
  <v-container>
    <v-chip-group column multiple>
      <v-row class="ma-0" align="start" justify="space-around">
        <TagLevelFilter
          v-for="tagLevel in getTagLevels"
          :key="tagLevel.level"
          :tag-level="tagLevel"
        />
      </v-row>
    </v-chip-group>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
import TagLevelFilter from "./TagLevelFilter";

export default {
  components: { TagLevelFilter },

  computed: {
    ...mapGetters(["getTagLevels"]),
  },
};
</script>

<style lang="stylus" scoped>
.container {
  padding: 0 !important;
}
</style>
