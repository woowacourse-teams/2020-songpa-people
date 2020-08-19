<template>
  <v-content>
    <carousel class="carousel" autoplay="true" perPage="1">
      <slide class="caruosel-component">
        <img class="img-content" src="../../assets/mainpage/content1.png" />
      </slide>
      <slide class="caruosel-component">
        <img class="img-content" src="../../assets/mainpage/content2.png" />
      </slide>
      <slide class="caruosel-component">
        <img class="img-content" src="../../assets/mainpage/content3.png" />
      </slide>
      <slide class="caruosel-component">
        <img class="img-content" src="../../assets/mainpage/content4.png" />
      </slide>
      <slide class="caruosel-component">
        <img class="img-content" src="../../assets/mainpage/content5.png" />
      </slide>
    </carousel>
  </v-content>
</template>
<script>
import { Carousel, Slide } from "vue-carousel";

export default {
  name: "Slider",
  components: {
    Carousel,
    Slide,
  },
};
</script>

<style>
.carousel {
  align-items: center;
}
.caruosel-component {
  display: flex;
  justify-content: center;
  align-items: center;
}
.img-content {
  width: 400px;
  height: 400px;
}
</style>
