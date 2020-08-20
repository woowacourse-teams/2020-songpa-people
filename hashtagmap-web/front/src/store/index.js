import Vue from "vue";
import Vuex from "vuex";
import kakaoMap from "./modules/kakaoMap";
import detailModal from "./modules/detailModal";
import place from "./modules/place";
import tagLevel from "./modules/tagLevel";
import category from "./modules/category";
import mapOverlay from "./modules/mapOverlay";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {},

  getters: {},

  mutations: {},

  actions: {},

  modules: {
    kakaoMap,
    detailModal,
    place,
    tagLevel,
    category,
    mapOverlay,
  },
});
