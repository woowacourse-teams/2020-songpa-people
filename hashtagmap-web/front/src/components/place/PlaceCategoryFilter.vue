<template>
  <v-chip label color="#a9a9a9" @click.stop="onClickCategory">
    {{ category.name }}
  </v-chip>
</template>

<script>
import { mapMutations } from "vuex";

export default {
  props: {
    category: {
      type: Object,
      required: true,
    },
  },

  methods: {
    ...mapMutations(["SET_CATEGORY"]),
    onClickCategory() {
      this.SET_CATEGORY(this.category);
    },
  },
};
</script>

<style lang="stylus">
.theme--light.v-chip:focus::before {
  opacity: 0 !important;
}

.v-chip-group .v-chip {
  margin: 0 !important;
}

.theme--light.v-chip--active::before {
  opacity: 0 !important;
}
</style>
