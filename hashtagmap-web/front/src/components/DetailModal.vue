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
            <v-btn class="instagram-page-button">
              {{ detailModal.placeName }}
            </v-btn>
            <v-btn class="detail-info">상세 정보</v-btn>
          </p>
          <div class="image-container">
            <a
              :href="post.postUrl"
              :key="post.id"
              v-for="post in detailModal.posts"
            >
              <img
                class="post-image"
                alt="인스타그램 인기 게시물 이미지"
                :src="post.imageUrl"
              />
            </a>
          </div>
        </div>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { mapMutations, mapState } from "vuex";

export default {
  computed: {
    ...mapState(["detailModal"]),
    makeHashtagCount() {
      return (this.detailModal.hashtagCount / 1000).toFixed(0);
    },
  },
  methods: {
    ...mapMutations(["SET_DETAIL_MODAL_CLOSE"]),
  },
};
</script>

<style scoped>
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
  font-size: 1.5vw;
  margin-right: 0.7vw;
  border: fuchsia 1.5px solid;
  box-sizing: content-box;
}

.image-container {
  margin-top: 3vw;
}

.post-image {
  margin: 0.3vw;
  max-width: 30%;
  overflow: hidden;
  height: auto;
}
</style>
