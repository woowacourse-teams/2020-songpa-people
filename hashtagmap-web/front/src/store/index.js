import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    kakaoMap: "",
    kakaoMapApi: "",
    isShowDetailModal: false,
    nowPlaceByDetailModal: "",
    places: [],
  },
  mutations: {
    initKakaoMapApi(state, kakaoMapApi) {
      state.kakaoMapApi = kakaoMapApi;
    },
    initKakaoMap(state, kakaoMap) {
      state.kakaoMap = kakaoMap;
    },
    initShowDetailModal(state, place) {
      state.isShowDetailModal = true;
      state.nowPlaceByDetailModal = place;
    },
    initCloseDetailModal(state) {
      state.isShowDetailModal = false;
    },
    initPlaces(state, places) {
      state.places = places;
    },
  },
  actions: {
    showDetailModal({ commit }, place) {
      commit("initShowDetailModal", place);
    },
    async getPlaces({ commit }) {
      try {
        const places = await axios.get("/markers");
        commit("initPlaces", places.data.data);
      } catch (error) {
        // todo : 스낵바로 에러내용 출력
        alert(error);
      }
    },
  },
  getters: {},
  modules: {},
});
