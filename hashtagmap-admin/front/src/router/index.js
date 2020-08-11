import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home";
import KakaoScheduler from "../views/KakaoScheduler";
import InstagramScheduler from "../views/InstagramScheduler";
import DistrictManage from "../views/DistrictManage";

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
  },
  {
    path: "/district-manage",
    name: "DistrictManage",
    component: DistrictManage
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
