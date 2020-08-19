<template>
  <v-expansion-panels flat>
    <v-expansion-panel>
      <v-expansion-panel-header dense @click="toggleTagLevelDetail">
        검색
        <div>
          <v-icon color="darkgrey">{{ showIcon() }}</v-icon>
        </div>
      </v-expansion-panel-header>
      <v-expansion-panel-content>
        <PlaceSearchInput />
        <PlaceCategories />
      </v-expansion-panel-content>
    </v-expansion-panel>
  </v-expansion-panels>
</template>

<script>
import PlaceSearchInput from "@/components/place/PlaceSearchInput";
import PlaceCategories from "@/components/place/PlaceCategories";
import { mdiChevronDown, mdiChevronUp } from "@mdi/js";

export default {
  name: "PlaceSearchContainer",
  data: () => ({
    chevronDown: mdiChevronDown,
    chevronUp: mdiChevronUp,
    active: false,
  }),

  components: {
    PlaceSearchInput,
    PlaceCategories,
  },

  methods: {
    toggleTagLevelDetail() {
      this.active = !this.active;
    },
    showIcon() {
      return this.active ? this.chevronUp : this.chevronDown;
    },
  },
};
</script>

<style lang="stylus"></style>
