<template>
  <v-main>
    <carousel
      ref="carousel"
      :perPage="perPage"
      :navigation-enabled="navigationEnabled"
      :paginationPadding="paginationPadding"
      :paginationSize="paginationSize"
      :loop="loop"
    >
      <slide v-for="post in posts" :key="post.id">
        <PostImage :post="post"></PostImage>
      </slide>
    </carousel>
  </v-main>
</template>

<script>
import { Carousel, Slide } from "vue-carousel";
import PostImage from "./PostImage";

export default {
  components: {
    Slide,
    Carousel,
    PostImage,
  },

  mounted() {
    setTimeout(this.$refs.carousel.computeCarouselWidth, 300);
  },

  data: () => ({
    autoplay: true,
    navigationEnabled: true,
    perPage: 1,
    paginationPadding: 5,
    paginationSize: 8,
    loop: true,
  }),

  props: {
    posts: {
      type: Array,
      required: true,
    },
  },

  methods: {},
};
</script>
<style>
.VueCarousel-navigation-button {
  position: relative !important;
  top: 2em !important;
}
</style>
<style scoped>
.container {
  padding: 0;
}
</style>
