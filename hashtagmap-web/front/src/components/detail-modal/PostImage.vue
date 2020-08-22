<template>
  <v-img
    class="img-content"
    :src="onLoadInstagramPostImage(post.imageUrl)"
    @error="onImgError"
    @click="onClickInstagramPostImage"
  />
</template>

<script>
export default {
  props: {
    post: {
      type: Object,
      required: true,
    },
  },

  data: () => ({
    isFailLoadImage: false,
  }),

  methods: {
    onLoadInstagramPostImage(imageUrl) {
      return this.isFailLoadImage
        ? require("../../assets/No-Image.png")
        : imageUrl;
    },
    onImgError() {
      this.isFailLoadImage = true;
    },
    onClickInstagramPostImage() {
      return window.open(this.post.postUrl);
    },
  },
};
</script>

<style>
</style>
