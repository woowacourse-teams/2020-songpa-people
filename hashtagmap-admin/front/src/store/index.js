import Vue from "vue";
import Vuex from "vuex";
import kakao from "@/store/modules/kakao";
import snackbar from "@/store/modules/snackbar";

Vue.use(Vuex);
export default new Vuex.Store({
  modules: {
    snackbar,
    kakao
  }
});
