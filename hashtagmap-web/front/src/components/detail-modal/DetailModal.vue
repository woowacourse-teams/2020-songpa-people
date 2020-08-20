<template>
  <v-row justify="center">
    <v-dialog
      :value="getDetailModal.isShow"
      @input="SET_DETAIL_MODAL_CLOSE"
      max-width="70%"
    >
      <v-card>
        <div>
          <p class="place-title">{{ getDetailModal.placeName }}</p>
          <p class="hashtag-count">{{ makeHashtagCount }}k</p>
          <p class="buttons">
            <v-btn
              x-large
              class="instagram-page-button"
              color="rgb(116,22,227)"
              @click="onClickPlaceName"
            >
              {{ getDetailModal.placeName }}
            </v-btn>
            <v-btn
              x-large
              class="detail-info"
              color="rgb(116,22,227)"
              @click="onClickModalDetail"
              >상세 정보
            </v-btn>
          </p>
          <v-sheet class="mx-auto" max-width="100%">
            <v-slide-group
              class="pa-4"
              :prev-icon="mdiLeft"
              :next-icon="mdiRight"
              show-arrows
            >
              <InstagramPost
                v-for="post in getDetailModal.posts"
                :key="post.id"
                :post="post"
              />
            </v-slide-group>
          </v-sheet>
        </div>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { mapMutations, mapGetters } from "vuex";
import { mdiChevronLeft, mdiChevronRight } from "@mdi/js";
import InstagramPost from "./InstagramPost";

export default {
  components: { InstagramPost },

  data: () => ({
    mdiLeft: mdiChevronLeft,
    mdiRight: mdiChevronRight,
  }),

  computed: {
    ...mapGetters(["getDetailModal"]),
    makeHashtagCount() {
      return (this.getDetailModal.hashtagCount / 1000).toFixed(1);
    },
  },

  methods: {
    ...mapMutations(["SET_DETAIL_MODAL_CLOSE"]),
    onClickPlaceName() {
      return window.open(
        `https://www.instagram.com/explore/tags/${this.getDetailModal.hashtagName}/?hl=ko`,
      );
    },
    onClickModalDetail() {
      return window.open(this.getDetailModal.placeUrl);
    },
  },
};
</script>

<style lang="stylus" scoped>
.place-title {
  font-size: 5vw;
}

.hashtag-count {
  margin-top: -3vw;
  font-size: 4vw;
}

.buttons {
  margin-top: -1.5vw;
}

.instagram-page-button,
.detail-info {
  height: 3.5vw;
  font-size: 2vw !important;
  color: white !important;
  margin-right: 0.7vw;
  box-sizing: content-box;
}
</style>
