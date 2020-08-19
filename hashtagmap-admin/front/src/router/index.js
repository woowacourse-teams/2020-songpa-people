import Vue from "vue";
import VueRouter from "vue-router";
import KakaoScheduler from "../views/KakaoScheduler";
import InstagramScheduler from "../views/InstagramScheduler";
import TagLevel from "../views/TagLevel";
import Home from "../views/Home";
import DistrictContainer from "../views/district/DistrictContainer";
import BlackListManager from "@/views/BlackListManager";

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
    path: "/tag-level",
    name: "TagLevel",
    component: TagLevel
  },
  {
    path: "/district-manager",
    name: "DistrictContainer",
    component: DistrictContainer
  },
  {
    path: "/blacklist-manager",
    name: "BlackListManager",
    component: BlackListManager
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
