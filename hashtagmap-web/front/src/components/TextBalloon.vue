<template>
  <div
    ref="textBalloon"
    :id="overlay.place.kakaoId"
    class="text-balloon-box"
    @click="showDetailModal"
  >
    <div class="close" @click.stop="closeTextBalloonOverLay"></div>
    <div class="text-balloon-title">
      {{ overlay.place.placeName }}
    </div>
    <div class="text-balloon-text">❤️ {{ overlay.place.hashtagCount }}</div>
  </div>
</template>

<script>
import { mapGetters, mapMutations, mapActions } from "vuex";
import { EVENT_TYPE } from "@/utils/constants";

export default {
  name: "TextBalloon",

  props: {
    overlay: {
      isOpen: {
        type: Boolean,
        require: true,
      },
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
      () => {
        this.ADD_CLUSTER(this.textBalloonOverlay);
        this.OPEN_TEXT_BALLOON(this.overlay.place.kakaoId);
      },
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
    ...mapMutations([
      "ADD_CLUSTER",
      "REMOVE_CLUSTER",
      "CLOSE_TEXT_BALLOON",
      "OPEN_TEXT_BALLOON",
    ]),
    ...mapActions(["setDetailModal"]),
    showActiveOverlay() {
      const tagLevelAndCategory = {
        tagLevel: this.overlay.place.tagLevel,
        category: this.overlay.place.category,
      };
      if (this.isActiveCategoryAndTagLevel(tagLevelAndCategory)) {
        this.showPinMarker();
        if (this.overlay.isOpen) {
          this.showTextBalloonOverlay();
        }
      }
    },
    showTextBalloonOverlay() {
      this.textBalloonOverlay.setZIndex(10);
      this.ADD_CLUSTER(this.textBalloonOverlay);
    },
    showPinMarker() {
      this.ADD_CLUSTER(this.overlay.marker);
    },
    closeTextBalloonOverLay() {
      this.CLOSE_TEXT_BALLOON(this.overlay.place.kakaoId);
      this.REMOVE_CLUSTER(this.textBalloonOverlay);
    },
    isActiveCategoryAndTagLevel(tagLevelAndCategory) {
      return (
        this.getActiveTagLevels.includes(tagLevelAndCategory.tagLevel) &&
        this.getActiveCategories.includes(tagLevelAndCategory.category)
      );
    },
    showDetailModal() {
      this.$router.push("/detail");
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
