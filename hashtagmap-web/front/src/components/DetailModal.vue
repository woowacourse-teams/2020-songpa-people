<template>
  <v-row justify="center">
    <v-dialog
      :value="detailModal.isShow"
      @input="SET_DETAIL_MODAL_CLOSE"
      max-width="70%"
    >
      <v-card>
        <div>
          <p class="place-title">{{ detailModal.placeName }}</p>
          <p class="hashtag-count">{{ makeHashtagCount }}k</p>
          <p class="buttons">
            <v-btn
              x-large
              class="instagram-page-button"
              color="rgb(116,22,227)"
            >
              {{ detailModal.placeName }}
            </v-btn>
            <v-btn x-large class="detail-info" color="rgb(116,22,227)"
              >상세 정보</v-btn
            >
          </p>
          <v-sheet class="mx-auto" max-width="100%">
            <v-slide-group
              class="pa-0"
              :prev-icon="mdiLeft"
              :next-icon="mdiRight"
              show-arrows
            >
              <v-slide-item v-for="post in detailModal.posts" :key="post.id">
                <v-card
                  color="grey lighten-1"
                  class="ma-5"
                  height="300"
                  width="300"
                >
                  <v-img
                    height="100%"
                    width="100%"
                    :src="post.imageUrl"
                    onerror="this.src='../assets/No-Image.png'"
                  ></v-img>
                </v-card>
              </v-slide-item>
            </v-slide-group>
          </v-sheet>
        </div>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { mapMutations, mapState } from "vuex";
import { mdiChevronLeft, mdiChevronRight } from "@mdi/js";

export default {
  data: () => ({
    mdiLeft: mdiChevronLeft,
    mdiRight: mdiChevronRight,
  }),
  computed: {
    ...mapState(["detailModal"]),
    makeHashtagCount() {
      return (this.detailModal.hashtagCount / 1000).toFixed(1);
    },
  },
  methods: {
    ...mapMutations(["SET_DETAIL_MODAL_CLOSE"]),
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
  font-size: 2vw;
  color: white !important;
  margin-right: 0.7vw;
  box-sizing: content-box;
}
</style>
