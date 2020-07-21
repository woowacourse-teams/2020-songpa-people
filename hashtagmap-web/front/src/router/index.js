import Vue from "vue";
import VueRouter from "vue-router";
import KakaoMap from "@/views/KakaoMap.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/map",
    name: "KakaoMap",
    component: KakaoMap,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
