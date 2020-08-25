<template>
  <v-container>
    <img
      :id="post.id"
      class="img-content"
      @error="onImgError"
      @click="onClickInstagramPostImage"
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

  mounted() {
    document.getElementById(this.post.id).src = this.post.imageUrl;
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

<style>
.img-content {
  width: 600px;
  height: auto;
}

@media screen and (max-width: 600px) {
  .img-content {
    position: relative;
    width: 100%;
    height: auto;
  }
}
</style>
