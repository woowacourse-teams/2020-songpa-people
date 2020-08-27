<template>
  <v-dialog
    class="detail-modal"
    :value="getDetailModal.isShow"
    @input="clickOut"
    width="600px"
    :fullscreen="$vuetify.breakpoint.xsOnly"
    scrollable="false"
  >
    <v-card>
      <div>
        <div class="modal-profile-image">
          <img alt="" src="@/assets/mainpage/miso_circle.png" />
        </div>
        <button class="close-btn" @click="SET_DETAIL_MODAL_CLOSE">
          X
        </button>
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
      </div>
      <Posts class="posts" :posts="getDetailModal.posts" />
      <v-container></v-container>
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

  created() {
    const unregisterRouterGuard = this.$router.beforeEach((to, from, next) => {
      this.back();

      next();
    });

    this.$once("hook:destroyed", () => {
      unregisterRouterGuard();
    });
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
    back() {
      this.SET_DETAIL_MODAL_CLOSE();
    },
    clickOut() {
      this.$router.go(-1);
      this.SET_DETAIL_MODAL_CLOSE();
    },
  },
};
</script>

<style scoped>
.posts {
  margin-bottom: -9px;
}
.placeName {
  font-size: 20px;
}

.hashtagCount {
  font-family: Nexon-gothic;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
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

.close-btn {
  display: none !important;
}

@media screen and (max-width: 600px) {
  .close-btn {
    display: inline !important;
    position: fixed !important;
    right: 0;
    font-size: 2em;
    margin-right: 0.5em;
  }

  .detail-modal {
    width: 100%;
  }

  .modal-profile-image {
    margin-left: 2em;
  }

  .modal-profile-image img {
    max-width: 25vw;
    height: auto;
  }

  .hashtagCount {
    font-size: 1.5em;
    margin-right: 1em;
  }

  .detail-info-button,
  .instagram-page-button {
    width: 45%;
  }
}
</style>
