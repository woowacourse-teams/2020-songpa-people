import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    kakaoMap: "",
  },
  mutations: {
    initKakaoMap(state, map) {
      state.kakaoMap = map;
    },
  },
  actions: {},
  modules: {},
});
