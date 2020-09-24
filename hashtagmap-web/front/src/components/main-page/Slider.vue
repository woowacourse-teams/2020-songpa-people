<template>
  <v-main>
    <carousel :autoplay="autoplay" :perPage="perPage" class="carousel">
      <slide class="carousel-component">
        <div class="image-wrap">
          <img class="img-content" src="../../assets/mainpage/content1.jpeg" />
        </div>
      </slide>
      <slide class="carousel-component">
        <div class="image-wrap">
          <img class="img-content" src="../../assets/mainpage/content2.jpeg" />
        </div>
      </slide>
      <slide class="carousel-component">
        <div class="image-wrap">
          <img class="img-content" src="../../assets/mainpage/content3.jpeg" />
        </div>
      </slide>
      <slide class="carousel-component">
        <div class="image-wrap">
          <img class="img-content" src="../../assets/mainpage/content4.jpeg" />
        </div>
      </slide>
      <slide class="carousel-component">
        <div class="image-wrap">
          <img class="img-content" src="../../assets/mainpage/content5.jpeg" />
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

<style scoped>
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

  .carousel :nth-child(2) :nth-child(1) {
    margin-top: 0 !important;
  }

  .carousel :nth-child(2) :nth-child(1) >>> button {
    margin-top: 0 !important;
  }
}
</style>
