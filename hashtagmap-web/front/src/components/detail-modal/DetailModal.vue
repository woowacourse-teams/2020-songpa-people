<template>
  <v-dialog
          class="detail-modal"
          :value="getDetailModal.isShow"
          @input="SET_DETAIL_MODAL_CLOSE"
          width="600px"
  >
    <v-card>
      <div>
        <div class="modal-profile-image">
          <img alt="" src="@/assets/mainpage/miso_circle.png" />
        </div>

        <v-row class="modal-info">
          <v-col>
            <v-row>
              <p class="placeName">
                {{ getDetailModal.placeName }}
              </p>
            </v-row>
            <v-row>
              <p class="hashtagCount">
                {{ makeHashtagCount }}k 개의 게시글이 있는 가게입니다.
              </p>
            </v-row>
            <v-row>
              <v-btn @click="onClickPlaceName" class="instagram-page-button">
                인스타그램
              </v-btn>
              <v-btn @click="onClickModalDetail" class="detail-info-button"
              >상세 정보
              </v-btn>
            </v-row>
          </v-col>
        </v-row>
        <Posts class="posts" :posts="getDetailModal.posts" />
      </div>
    </v-card>
  </v-dialog>
</template>

<script>
  import { mapGetters, mapMutations } from "vuex";
  import Posts from "./Posts.vue";

  export default {
    components: {
      Posts,
    },

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

<style>
  .placeName {
    font-size: 20px;
  }

  .hashtagCount {
    font-size: 15px;
  }

  .modal-profile-image {
    float: left;
    width: calc(33.333% - 1rem);
    display: flex;
    justify-content: center;
    align-items: center;
    margin-right: 3rem;
    margin-top: 0.5rem;
  }

  .modal-profile-image img {
    border-radius: 50%;
    width: 130px;
    height: 130px;
  }

  .detail-info-button,
  .instagram-page-button {
    display: inline-block;
    background: none;
    border: none;
    color: inherit;
    padding: 0;
    margin-right: 8px;
  }

  .modal-info {
    margin-left: 3px;
  }

  @media screen and (max-width: 600px) {
    .detail-modal {
      width: 100%;
    }
  }
</style>
