import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";
import { TAG_LEVEL, CATEGORY } from "../utils/constants";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    kakaoMap: "",
    kakaoMapApi: "",
    detailModal: {
      isShow: false,
      placeName: "",
      placeUrl: "",
      hashtagCount: "",
      hashtagName: "",
      posts: [],
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
        category: "",
      },
    ],
    tagLevels: [
      {
        level: TAG_LEVEL.LEVEL_FIVE.LEVEL,
        rgb: TAG_LEVEL.LEVEL_FIVE.RGB,
        active: true,
      },
      {
        level: TAG_LEVEL.LEVEL_FOUR.LEVEL,
        rgb: TAG_LEVEL.LEVEL_FOUR.RGB,
        active: true,
      },
      {
        level: TAG_LEVEL.LEVEL_THREE.LEVEL,
        rgb: TAG_LEVEL.LEVEL_THREE.RGB,
        active: true,
      },
      {
        level: TAG_LEVEL.LEVEL_TWO.LEVEL,
        rgb: TAG_LEVEL.LEVEL_TWO.RGB,
        active: true,
      },
      {
        level: TAG_LEVEL.LEVEL_ONE.LEVEL,
        rgb: TAG_LEVEL.LEVEL_ONE.RGB,
        active: true,
      },
    ],
    categories: [
      {
        name: CATEGORY.CAFE,
        active: true,
      },
      {
        name: CATEGORY.RESTAURANT,
        active: true,
      },
    ],
    mapOverlays: [],
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
    SET_TAG_LEVEL(state, tagLevel) {
      state.tagLevels = state.tagLevels.map(t =>
        t.level === tagLevel.level ? { ...t, active: !tagLevel.active } : t,
      );
    },
    SET_CATEGORY(state, category) {
      state.categories = state.categories.map(c =>
        c.name === category.name
          ? { ...c, active: !category.active }
          : { ...c, active: true },
      );
    },
    ADD_MAP_OVERLAYS(state, overlayObj) {
      state.mapOverlays.push(overlayObj);
    },
  },

  actions: {
    async setDetailModal({ commit }, place) {
      const posts = await axios.get(`/instagram/${place.instagramId}/post`);
      const detailModal = {
        isShow: true,
        placeName: place.placeName,
        placeUrl: place.placeUrl,
        hashtagCount: place.hashtagCount,
        hashtagName: place.hashtagName,
        posts: posts.data.data,
      };
      commit("SET_DETAIL_MODAL", detailModal);
    },
    async setPlaces({ commit }) {
      const places = await axios.get("/markers");
      commit("SET_PLACES", places.data.data);
    },
  },

  getters: {
    getKakaoMap: state => {
      return state.kakaoMap;
    },
    getKakaoMapApi: state => {
      return state.kakaoMapApi;
    },
    getPlaces: state => {
      return state.places;
    },
    getTagLevels: state => {
      return state.tagLevels;
    },
    getDetailModal: state => {
      return state.detailModal;
    },
    getCategories: state => {
      return state.categories;
    },
    activeOverlays: state => {
      const activeCategory = state.categories
        .filter(category => category.active)
        .map(category => category.name);
      const activeTagLevels = state.tagLevels
        .filter(tagLevel => tagLevel.active)
        .map(tagLevel => tagLevel.level);
      return state.mapOverlays.filter(mapOverlay => {
        if (
          activeTagLevels.includes(mapOverlay.place.tagLevel) &&
          activeCategory.includes(mapOverlay.place.category)
        ) {
          mapOverlay.marker.setMap(state.kakaoMap);
          mapOverlay.textBalloon.setMap(state.kakaoMap);
          mapOverlay.textBalloon.setZIndex(1);
        } else {
          mapOverlay.marker.setMap(null);
          mapOverlay.textBalloon.setMap(null);
        }
      });
    },
  },

  modules: {},
});
