<template>
  <v-app>
    <v-main>
      <swiper class="swiper" :options="swiperOption">
        <swiper-slide class="content"> <MainPageButton /> </swiper-slide>
        <swiper-slide class="content">Slide 2</swiper-slide>
        <swiper-slide class="content">Slide 3</swiper-slide>
        <swiper-slide class="content">Slide 4</swiper-slide>
        <swiper-slide class="content">Slide 5</swiper-slide>
        <div class="swiper-pagination" slot="pagination"></div>
      </swiper>
    </v-main>
  </v-app>
</template>

<script>
import MainPageButton from "@/components/main-page/MainPageButton";
import { Swiper, SwiperSlide } from "vue-awesome-swiper";
import "swiper/swiper-bundle.css";

export default {
  name: "swiper-example-vertical",
  title: "Vertical slider",
  components: {
    Swiper,
    SwiperSlide,
    MainPageButton,
  },
  data() {
    return {
      swiperOption: {
        direction: "vertical",
        pagination: {
          el: ".swiper-pagination",
          clickable: true,
        },
      },
    };
  },
};
</script>

<style>
.swiper {
  width: 100%;
  height: 100%;
}
.content {
  width: 100%;
  height: 100%;
}
</style>
