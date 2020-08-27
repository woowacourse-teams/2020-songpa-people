<template>
  <div ref="textBalloon" :id="overlay.place.kakaoId" class="text-balloon-box">
    <div class="close" @click.prevent="closeTextBalloonOverLay"></div>
    <div class="text-balloon-title" @click.prevent="showDetailModal">
      <router-link to="/map/detail" class="modal-router">
        {{ overlay.place.placeName }}
      </router-link>
    </div>
    <div class="text-balloon-text">#{{ overlay.place.hashtagCount }}</div>
  </div>
</template>

<script>
import { mapGetters, mapMutations, mapActions } from "vuex";
import { EVENT_TYPE } from "@/utils/constants";

export default {
  name: "TagPin",

  props: {
    overlay: {
      place: {
        type: Object,
        require: true,
      },
      marker: {
        type: Object,
        require: true,
      },
    },
    bounds: {},
  },

  data() {
    return {
      textBalloonOverlay: {},
    };
  },

  mounted() {
    this.textBalloonOverlay = new this.$makeTextBalloonOverlay(
      this.$refs.textBalloon,
      this.overlay.marker,
    );
    this.showActiveOverlay();
    this.$kakaoApi().event.addListener(
      this.overlay.marker,
      EVENT_TYPE.CLICK,
      () => this.ADD_CLUSTER(this.textBalloonOverlay),
    );
  },

  watch: {
    getActiveTagLevels() {
      this.showActiveOverlay();
    },
    getActiveCategories() {
      this.showActiveOverlay();
    },
  },

  computed: {
    ...mapGetters(["getActiveTagLevels", "getActiveCategories"]),
  },

  methods: {
    ...mapMutations(["ADD_CLUSTER", "REMOVE_CLUSTER"]),
    ...mapActions(["setDetailModal"]),
    showActiveOverlay() {
      const tagLevelAndCategory = {
        tagLevel: this.overlay.place.tagLevel,
        category: this.overlay.place.category,
      };
      if (this.isActiveCategoryAndTagLevel(tagLevelAndCategory)) {
        this.showPinMarker();
        this.showTextBalloonOverlay();
      }
    },
    showTextBalloonOverlay() {
      this.ADD_CLUSTER(this.textBalloonOverlay);
    },
    showPinMarker() {
      this.ADD_CLUSTER(this.overlay.marker);
    },
    closeTextBalloonOverLay() {
      this.REMOVE_CLUSTER(this.textBalloonOverlay);
    },
    isActiveCategoryAndTagLevel(tagLevelAndCategory) {
      return (
        this.getActiveTagLevels.includes(tagLevelAndCategory.tagLevel) &&
        this.getActiveCategories.includes(tagLevelAndCategory.category)
      );
    },
    showDetailModal() {
      this.setDetailModal(this.overlay.place);
    },
  },
};
</script>

<style scoped>
.modal-router {
  text-decoration: none;
  color: #2c3e50;
}
</style>
