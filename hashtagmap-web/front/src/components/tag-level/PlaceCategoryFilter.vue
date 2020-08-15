<template>
  <v-chip label color="white" @click.stop="onClickCategory">
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
    ...mapMutations(["CHECK_CATEGORY"]),
    onClickCategory() {
      this.CHECK_CATEGORY(this.category);
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

.v-application .primary {
  background-color: darkgray !important;
}

.theme--light.v-chip {
  border-color: black !important;
}
</style>
