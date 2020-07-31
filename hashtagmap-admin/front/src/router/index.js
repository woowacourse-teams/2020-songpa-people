import Vue from "vue";
import VueRouter from "vue-router";
import KakaoScheduler from "../views/KakaoScheduler";
import InstagramScheduler from "../views/InstagramScheduler";
import Home from "../views/Home";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/kakao-scheduler",
    name: "KakaoScheduler",
    component: KakaoScheduler
  },
  {
    path: "/instagram-scheduler",
    name: "InstagramScheduler",
    component: InstagramScheduler
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
