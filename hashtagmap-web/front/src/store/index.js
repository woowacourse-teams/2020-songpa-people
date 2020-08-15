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
    markerDetails: [],
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
    CHECK_TAG_LEVEL(state, tagLevel) {
      state.tagLevels = state.tagLevels.map(t =>
        t.level === tagLevel.level ? { ...t, active: !tagLevel.active } : t,
      );
    },
    ADD_MARKER_DETAIL(state, markerDetail) {
      state.markerDetails.push(markerDetail);
    },
    CHECK_CATEGORY(state, category) {
      const activeCategoriesCount = state.categories.filter(
        category => category.active,
      ).length;
      if (activeCategoriesCount === state.categories.length) {
        state.categories = state.categories.map(c =>
          c.name === category.name
            ? { ...c, active: true }
            : { ...c, active: false },
        );
      } else {
        const activeCategory = state.categories.filter(
          category => category.active,
        )[0];
        if (activeCategory.name === category.name) {
          state.categories = state.categories.map(c => {
            return {
              name: c.name,
              active: true,
            };
          });
        } else {
          state.categories = state.categories.map(c =>
            c.name === category.name
              ? { ...c, active: true }
              : { ...c, active: false },
          );
        }
      }
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

  getters: {
    activeMarker: state => {
      const activeCategory = state.categories
        .filter(category => category.active)
        .map(category => category.name);
      const activeTagLevels = state.tagLevels
        .filter(tagLevel => tagLevel.active)
        .map(tagLevel => tagLevel.level);
      return state.markerDetails.filter(markerDetail => {
        if (
          activeTagLevels.includes(markerDetail.tagLevel) &&
          activeCategory.includes(markerDetail.category)
        ) {
          markerDetail.marker.setMap(state.kakaoMap);
        } else {
          markerDetail.marker.setMap(null);
        }
      });
    },
  },

  modules: {},
});
