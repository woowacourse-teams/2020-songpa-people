<template>
  <v-container>
    <v-chip-group active-class="primary" column>
      <v-row align="start" justify="space-around">
        <PlaceCategoryFilter
          v-for="category in categories"
          :key="category.name"
          :category="category"
        />
      </v-row>
    </v-chip-group>
  </v-container>
</template>

<script>
import { mapState } from "vuex";
import PlaceCategoryFilter from "./PlaceCategoryFilter";

export default {
  components: { PlaceCategoryFilter },

  computed: {
    ...mapState(["categories"]),
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
  background-color: darkgray !important;
}

.theme--light.v-chip {
  border-color: black !important;
}
</style>
