<template>
  <v-text-field
    class="ma-0 pa-0"
    dense
    filled
    placeholder="지역을 입력하세요."
    solo
  >
    <v-icon color="darkgray" slot="append">{{ magnify }}</v-icon>
  </v-text-field>
</template>

<script>
import { mdiMagnify } from "@mdi/js";

export default {
  data: () => ({
    magnify: mdiMagnify,
  }),
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
