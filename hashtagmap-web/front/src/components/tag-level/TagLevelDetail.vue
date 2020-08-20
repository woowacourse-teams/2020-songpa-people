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
        <div class="tag-level-detail">
          <ul class="progressbar">
            <li class="level-one">25k</li>
            <li class="level-two">20k</li>
            <li class="level-three">15k</li>
            <li class="level-four">10k</li>
            <li class="level-five"></li>
          </ul>
        </div>
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
  }),

  methods: {
    toggleTagLevelDetail() {
      this.toggle = !this.toggle;
    },
    showIcon() {
      return !this.toggle ? this.chevronDown : this.chevronUp;
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

.tag-level-detail {
  width: 100%;
}

.progressbar li {
  list-style-type: none;
  float: left;
  height: 10px;
  width: 20%;
  position: relative;
  text-align: center;
  z-index: 10;
}

.progressbar li::after {
  content: "";
  position: absolute;
  width: 100%;
  height: 1px;
  background-color: #ddd;
  top: -15px;
  left: -50%;
}

.progressbar li:first-child::after {
  border: 3px solid rgb(65,12,162);
  background-color: rgb(65,12,162);
}

.progressbar li.level-one:before {
  border-color: #ddd;
}

.progressbar li.level-one + li:after {
  border: 3px solid rgb(116,22,227);
  background-color: rgb(116,22,227);
}

.progressbar li.level-two:before {
  border-color: #ddd;
}

.progressbar li.level-two + li:after {
  border: 3px solid rgb(158,76,237);
  background-color: rgb(158,76,237);
}

.progressbar li.level-three:before {
  border-color: #ddd;
}

.progressbar li.level-three + li:after {
  border: 3px solid rgb(185,112,243);
  background-color: rgb(185,112,243);
}

.progressbar li.level-four:before {
  border-color: #ddd;
}

.progressbar li.level-four + li:after {
  border: 3px solid rgb(216,160,250);
  background-color: rgb(216,160,250);
}

.progressbar li.level-five::before {
  background-color: rgb(216,160,250) !important;
  content: "";
  position: absolute;
  top: -15px;
  border: 1px solid rgb(216,160,250);
  width: 5px;
  height: 6px;
}
</style>
