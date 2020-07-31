<template>
  <v-row justify="center">
    <v-dialog :value="isShowDetailModal" @input="initCloseDetailModal">
      <v-card actions class="justify-center">
        <div>
          <p id="place-title">{{ nowPlaceByDetailModal.title }}</p>
          <p id="hashtag-count">{{ makeHashtagCount }}k</p>
        </div>

        <v-card-text>가운데 정렬</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { mapState, mapMutations } from "vuex";
export default {
  data() {
    return {};
  },
  computed: {
    ...mapState(["isShowDetailModal", "nowPlaceByDetailModal"]),
    makeHashtagCount() {
      return this.nowPlaceByDetailModal.hashtag_count / 1000;
    },
  },
  methods: {
    ...mapMutations(["initCloseDetailModal"]),
  },
};
</script>

<style scoped>
#place-title {
  font-size: 50px;
}
  #hashtag-count {
    margin-top: -25px;
    font-size: 40px;
  }
</style>
