<template>
  <v-container>
    <v-chip-group active-class="primary" column>
      <v-row align="start" justify="space-around">
        <PlaceCategoryFilter
          v-for="category in getCategories"
          :key="category.name"
          :category="category"
        />
      </v-row>
    </v-chip-group>
  </v-container>
</template>

<script>
import { mapGetters } from "vuex";
import PlaceCategoryFilter from "./PlaceCategoryFilter";

export default {
  components: { PlaceCategoryFilter },

  computed: {
    ...mapGetters(["getCategories"]),
  },
};
</script>

<style lang="stylus" scoped>
.container {
  padding: 0;
}

.v-slide-group__content {
  padding: 0 !important;
}

.v-chip--label {
  border: 2px solid;
}

.v-application .primary {
  background-color: white !important;
}

.theme--light.v-chip {
  border-color: black !important;
}

.v-chip {
  color: white !important;
}

.v-chip:not(.v-chip--outlined).primary {
  color: black !important;
}
</style>
