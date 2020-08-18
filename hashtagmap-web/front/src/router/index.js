import Vue from "vue";
import VueRouter from "vue-router";
import KakaoMap from "@/views/KakaoMap.vue";
import MainPage from "@/views/MainPage.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/map",
    name: "KakaoMap",
    component: KakaoMap,
  },
  {
    path: "/",
    name: "MainPage",
    component: MainPage,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
