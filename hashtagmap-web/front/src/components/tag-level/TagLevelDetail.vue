<template>
  <v-expansion-panels flat>
    <v-expansion-panel>
      <v-expansion-panel-header @click="toggleTagLevelDetail">
        태그 레벨 정보
        <div>
          <v-icon color="darkgrey">{{ showIcon() }}</v-icon>
        </div>
      </v-expansion-panel-header>
      <v-expansion-panel-content>
        <v-slider
          :tick-labels="ticksLabels"
          :max="5"
          step="1"
          ticks="always"
          tick-size="4"
          color="rgb(216, 160, 250)"
          @click="changeColor"
        ></v-slider>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </v-expansion-panels>
</template>

<script>
import { mdiChevronDown, mdiChevronUp } from "@mdi/js";

export default {
  data: () => ({
    chevronDown: mdiChevronDown,
    chevronUp: mdiChevronUp,
    toggle: false,
    ticksLabels: ["0", "5k", "10k", "15k", "20k", "25k"],
  }),

  methods: {
    toggleTagLevelDetail() {
      this.toggle = !this.toggle;
    },
    showIcon() {
      return !this.toggle ? this.chevronDown : this.chevronUp;
    },
    changeColor() {
      const value = document.querySelector(
        "#tag-level > div > div > div > " +
          "div.v-item-group.theme--light.v-expansion-panels.v-expansion-panels--flat > div > div > div > " +
          "div.v-input.theme--light.v-input__slider > div > div.v-input__slot > div > input",
      ).value;
      const slideBar = document.querySelector(
        "#tag-level > div > div > div > " +
          "div.v-item-group.theme--light.v-expansion-panels.v-expansion-panels--flat > div > div > div > " +
          "div.v-input.v-input--is-label-active.v-input--is-dirty.theme--light.v-input__slider > div > " +
          "div.v-input__slot > div > div.v-slider__track-container > div.v-slider__track-fill",
      );
      const slideThumb = document.querySelector(
        "#tag-level > div > div > div > " +
          "div.v-item-group.theme--light.v-expansion-panels.v-expansion-panels--flat > div > div > div > div > " +
          "div > div.v-input__slot > div > div.v-slider__thumb-container > div",
      );
      if (value === "5") {
        slideThumb.style.cssText =
          "background-color: rgb(65, 12, 162); border-color: rgb(65, 12, 162);";
        slideBar.style.cssText =
          "left: 0px; right: auto; width: 100%; background: rgb(65, 12, 162) !important;";
      }
      if (value === "4") {
        slideThumb.style.cssText =
          "background-color: rgb(116, 22, 227); border-color: rgb(116, 22, 227);";
        slideBar.style.cssText =
          "left: 0px; right: auto; width: 80%; background: rgb(116, 22, 227) !important;";
      }
      if (value === "3") {
        slideThumb.style.cssText =
          "background-color: rgb(158, 76, 237); border-color: rgb(158, 76, 237);";
        slideBar.style.cssText =
          "left: 0px; right: auto; width: 60%; background: rgb(158, 76, 237) !important;";
      }
      if (value === "2") {
        slideThumb.style.cssText =
          "background-color: rgb(185, 112, 243); border-color: rgb(185, 112, 243);";
        slideBar.style.cssText =
          "left: 0px; right: auto; width: 40%; background: rgb(185, 112, 243) !important;";
      }
      if (value === "1") {
        slideThumb.style.cssText =
          "background-color: rgb(216, 160, 250); border-color: rgb(216, 160, 250);";
        slideBar.style.cssText =
          "left: 0px; right: auto; width: 20%; background: rgb(216, 160, 250) !important;";
      }
      if (value === "0") {
        slideThumb.style.cssText =
          "background-color: rgb(216, 160, 250); border-color: rgb(216, 160, 250);";
      }
    },
  },
};
</script>

<style lang="stylus">
.v-expansion-panel-header {
  padding: 0 !important;
}

.v-application--is-ltr .v-expansion-panel-header {
  text-align: right !important;
}

.v-expansion-panel-content__wrap {
  padding: 0 !important;
}

.v-slider--horizontal .v-slider__track-container {
  height: 4px !important;
}
</style>
