<template>
  <v-slide-item>
    <v-card color="grey lighten-1" class="ma-5" height="300" width="300">
      <v-img
        height="100%"
        width="100%"
        :src="onLoadInstagramPostImage(post.imageUrl)"
        v-on:error="onImgError"
      ></v-img>
    </v-card>
  </v-slide-item>
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
  },
};
</script>

<style scoped></style>
