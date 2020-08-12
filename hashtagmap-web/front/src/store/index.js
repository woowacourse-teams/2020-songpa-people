import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    kakaoMap: "",
    kakaoMapApi: "",
    detailModal: {
      isShow: false,
      placeName: "",
      hashtagCount: "",
      posts: "",
    },
    places: [
      {
        hashtagCount: "",
        instagramId: "",
        kakaoId: "",
        latitude: "",
        longitude: "",
        placeName: "",
        tagLevel: "",
      },
    ],
  },
  mutations: {
    SET_KAKAO_MAP_API(state, kakaoMapApi) {
      state.kakaoMapApi = kakaoMapApi;
    },
    SET_KAKAO_MAP(state, kakaoMap) {
      state.kakaoMap = kakaoMap;
    },
    SET_DETAIL_MODAL(state, detailModal) {
      state.detailModal = detailModal;
    },
    SET_DETAIL_MODAL_CLOSE(state) {
      state.detailModal.isShow = false;
    },
    SET_PLACES(state, places) {
      state.places = places;
    },
  },
  actions: {
    async setDetailModal({ commit }, place) {
      const posts = await axios.get(`/instagram/${place.instagramId}/post`);
      const detailModal = {
        isShow: true,
        placeName: place.placeName,
        hashtagCount: place.hashtagCount,
        posts: posts.data.data,
      };
      commit("SET_DETAIL_MODAL", detailModal);
    },
    async setPlaces({ commit }) {
      try {
        const places = await axios.get("/markers");
        commit("SET_PLACES", places.data.data);
      } catch (error) {
        // todo : 스낵바로 에러내용 출력
        alert(error);
      }
    },
  },
  getters: {},
  modules: {},
});
