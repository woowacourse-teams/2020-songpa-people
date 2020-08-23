<template>
  <carousel
    :autoplay="autoplay"
    :perPage="perPage"
    :paginationPadding="paginationPadding"
    :paginationSize="paginationSize"
    :loop="loop"
  >
    <slide v-for="post in posts" :key="post.id">
      <PostImage :post="post"></PostImage>
    </slide>
  </carousel>
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
  data: () => ({
    autoplay: true,
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
.container {
  padding: 0px;
}
</style>
