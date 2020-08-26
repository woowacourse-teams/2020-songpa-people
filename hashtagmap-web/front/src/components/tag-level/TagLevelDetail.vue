<template>
  <div>
    <v-list>
      <v-list-item dense class="tag-level-detail-title">
        구간별 해시태그 개수
      </v-list-item>
      <v-list-item dense class="tag-levels">
        <v-list-item-content class="border">
          <div class="tag-level-detail">
            <ul class="progressbar">
              <li class="level-one">{{ this.getTagLevelDetails[4] }}k</li>
              <li class="level-two">{{ this.getTagLevelDetails[3] }}k</li>
              <li class="level-three">{{ this.getTagLevelDetails[2] }}k</li>
              <li class="level-four">{{ this.getTagLevelDetails[1] }}k</li>
              <li class="level-five"></li>
            </ul>
          </div>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </div>
</template>

<script>
import { mdiChevronDown, mdiChevronUp } from "@mdi/js";
import { mapActions, mapGetters } from "vuex";
export default {
  async created() {
    await this.setTagLevelDetails();
  },

  data: () => ({
    chevronDown: mdiChevronDown,
    chevronUp: mdiChevronUp,
    toggle: false,
  }),

  methods: {
    ...mapActions(["setTagLevelDetails"]),
    toggleTagLevelDetail() {
      this.toggle = !this.toggle;
    },
    showIcon() {
      return !this.toggle ? this.chevronDown : this.chevronUp;
    },
  },

  computed: {
    ...mapGetters(["getTagLevelDetails"]),
  },
};
</script>

<style lang="stylus">
.tag-level-detail-title {
  margin: 0 1px 0 0;
  padding-left: 1px !important;
  font-size: medium;
}

.tag-levels {
  padding-left: 10px !important;
  padding-right: 0 !important;
}

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
  width: 100px
}

.progressbar li {
  list-style-type: none;
  float: left;
  height: 10px;
  width: 20%;
  position: relative;
  text-align: center;
  z-index: 10;
  font-size: 14px !important;
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
  border: 6px solid rgb(65,12,162);
  background-color: rgb(65,12,162);
}

.progressbar li.level-one:before {
  border-color: #ddd;
}

.progressbar li.level-one + li:after {
  border: 6px solid rgb(116,22,227);
  background-color: rgb(116,22,227);
}

.progressbar li.level-two:before {
  border-color: #ddd;
}

.progressbar li.level-two + li:after {
  border: 6px solid rgb(158,76,237);
  background-color: rgb(158,76,237);
}

.progressbar li.level-three:before {
  border-color: #ddd;
}

.progressbar li.level-three + li:after {
  border: 6px solid rgb(185,112,243);
  background-color: rgb(185,112,243);
}

.progressbar li.level-four:before {
  border-color: #ddd;
}

.progressbar li.level-four + li:after {
  border: 6px solid rgb(216,160,250);
  background-color: rgb(216,160,250);
}
</style>
