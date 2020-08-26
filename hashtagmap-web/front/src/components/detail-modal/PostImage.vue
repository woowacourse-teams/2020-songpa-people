<template>
  <v-container>
    <img
      :id="post.id"
      class="img-content"
      :src="post.imageUrl"
      @error="onImgError"
      @click="onClickInstagramPostImage"
      alt=""
    />
  </v-container>
</template>

<script>
export default {
  props: {
    post: {
      type: Object,
      required: true,
    },
  },

  methods: {
    onImgError(e) {
      e.target.src = require("../../assets/No-Image.png");
    },
    onClickInstagramPostImage() {
      return window.open(this.post.postUrl);
    },
  },
};
</script>

<style scoped>
.img-content {
  width: 600px;
  height: auto;
}

@media screen and (max-width: 600px) {
  .img-content {
    position: relative;
    width: 100vw;
    height: auto;
  }
}
</style>
