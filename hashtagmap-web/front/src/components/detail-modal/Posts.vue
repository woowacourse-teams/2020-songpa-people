<template>
  <v-carousel cycle hide-delimiter-background>
    <v-carousel-item v-for="post in posts" :key="post.id">
      <v-row class="fill-height" align="center" justify="center">
        <img
          class="img-content"
          :src="post.imageUrl"
          @error="onImgError"
          @click="onClickInstagramPostImage(post)"
          alt=""
        />
      </v-row>
    </v-carousel-item>
  </v-carousel>
</template>

<script>
export default {
  props: {
    posts: {
      type: Array,
      required: true,
    },
  },

  methods: {
    onImgError(e) {
      e.target.src = require("@/assets/No-Image.png");
    },
    onClickInstagramPostImage(post) {
      return window.open(post.postUrl);
    },
  },
};
</script>

<style scoped>
.img-content {
  width: 550px;
  height: 550
  px;
  overflow: hidden;
}

@media screen and (max-width: 600px) {
  .img-content {
    position: relative;
    width: 100vw;
    height: 100vw;
    overflow: hidden;
  }
}
</style>
