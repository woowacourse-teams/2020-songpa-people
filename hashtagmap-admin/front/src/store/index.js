import Vue from "vue";
import Vuex from "vuex";
import kakao from "@/store/modules/kakao";
import snackbar from "@/store/modules/snackbar";
import district from "./modules/district";
import modal from "./modules/modal";

Vue.use(Vuex);
export default new Vuex.Store({
  modules: {
    snackbar,
    kakao,
    district,
    modal
  }
});
