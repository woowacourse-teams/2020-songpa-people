<template>
  <div>
    <v-select
      :items="getDistrictNames"
      :value="getSelectedName"
      @click:append-outer="fetchDistrictNames"
      @input="SET_SELECTED_DISTRICT_NAME"
      append-outer-icon="mdi-refresh"
      label="지역 선택"
      solo
    >
    </v-select>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapMutations } from "vuex";

export default {
  name: "DistrictNameSelectBox",
  computed: {
    ...mapGetters("zone", ["getDistrictNames", "getSelectedName"])
  },
  methods: {
    ...mapMutations("zone", ["SET_SELECTED_DISTRICT_NAME"]),
    ...mapActions("zone", ["fetchDistrictNames"])
  }
};
</script>

<style scoped></style>
