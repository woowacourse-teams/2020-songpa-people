<template>
  <v-main>
    <carousel :autoplay="autoplay" :perPage="perPage" class="carousel">
      <slide class="carousel-component">
        <div class="image-wrap">
          <img class="img-content" src="../../assets/mainpage/content1.png" />
        </div>
      </slide>
      <slide class="carousel-component">
        <div class="image-wrap">
          <img class="img-content" src="../../assets/mainpage/content2.png" />
        </div>
      </slide>
      <slide class="carousel-component">
        <div class="image-wrap">
          <img class="img-content" src="../../assets/mainpage/content3.png" />
        </div>
      </slide>
      <slide class="carousel-component">
        <div class="image-wrap">
          <img class="img-content" src="../../assets/mainpage/content4.png" />
        </div>
      </slide>
      <slide class="carousel-component">
        <div class="image-wrap">
          <img class="img-content" src="../../assets/mainpage/content5.png" />
        </div>
      </slide>
    </carousel>
  </v-main>
</template>
<script>
import { Carousel, Slide } from "vue-carousel";

export default {
  name: "Slider",
  components: {
    Carousel,
    Slide,
  },
  data() {
    return {
      autoplay: true,
      perPage: 1,
    };
  },
};
</script>

<style>
.carousel {
  align-items: center;
  margin-top: 10px;
}
.carousel-component {
  display: flex;
  justify-content: center;
  align-items: center;
}
.img-content {
  width: 600px;
  height: 600px;
}

@media (max-width: 40rem) {
  .img-content {
    position: relative;
    width: 100%;
    height: auto;
  }
}
</style>
