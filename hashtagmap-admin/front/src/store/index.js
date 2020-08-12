import Vue from "vue";
import Vuex from "vuex";
import kakao from "@/store/modules/kakao";
import snackbar from "@/store/modules/snackbar";
import district from "@/store/modules/district";

Vue.use(Vuex);
export default new Vuex.Store({
  modules: {
    snackbar,
    kakao,
    district
  }
});
