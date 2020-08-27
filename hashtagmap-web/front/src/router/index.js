import Vue from "vue";
import VueRouter from "vue-router";
import KakaoMap from "@/views/KakaoMap.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "KakaoMap",
    component: KakaoMap,
    children: [{ path: "detail" }],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
