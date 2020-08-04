import Vue from "vue";
import Vuex from "vuex";
import kakao from "@/store/modules/kakao";

Vue.use(Vuex);

import snackbar from "./modules/snackbar";

export default new Vuex.Store({
  modules: {
    snackbar,
    kakao
  }
});
