<template>
  <v-text-field
    v-on:keypress.enter="search"
    v-model="input"
    class="ma-1 pa-0"
    dense
    filled
    placeholder="지역을 입력하세요."
    solo
  >
    <v-icon color="darkgray" slot="append" @click="search">{{
      magnify
    }}</v-icon>
  </v-text-field>
</template>

<script>
import { mdiMagnify } from "@mdi/js";

export default {
  data: () => ({
    magnify: mdiMagnify,
    input: "",
  }),
  methods: {
    search() {
      this.$searchKeywordAndLoadPosition(this.input);
      this.input = "";
    },
  },
};
</script>

<style lang="stylus">
.v-input--dense > .v-input__control > .v-input__slot {
  margin-bottom: 0px !important;
}

.v-text-field.v-text-field--enclosed .v-text-field__details {
  margin-bottom: 0px !important;
}
</style>
