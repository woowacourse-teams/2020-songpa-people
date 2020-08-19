import Vue from "vue";
import Vuex from "vuex";
import kakao from "@/store/modules/kakao";
import snackbar from "@/store/modules/snackbar";
import member from "@/store/modules/member";
import district from "@/store/modules/district";
import zone from "@/store/modules/zone";
import tagLevel from "@/store/modules/tagLevel";
import modal from "./modules/modal";
import blacklist from "@/store/modules/blacklist";

Vue.use(Vuex);
export default new Vuex.Store({
  modules: {
    snackbar,
    member,
    kakao,
    district,
    zone,
    tagLevel,
    modal,
    blacklist
  }
});
