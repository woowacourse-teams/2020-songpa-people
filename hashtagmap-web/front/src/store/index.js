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
      },
    ],
    tagLevels: [
      {
        level: 5,
        rgb: "rgb(65,12,162)",
        active: true,
      },
      {
        level: 4,
        rgb: "rgb(116,22,227)",
        active: true,
      },
      {
        level: 3,
        rgb: "rgb(158,76,237)",
        active: true,
      },
      {
        level: 2,
        rgb: "rgb(185,112,243)",
        active: true,
      },
      {
        level: 1,
        rgb: "rgb(216,160,250)",
        active: true,
      },
    ],
    markers: [],
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
    ADD_MARKER(state, marker) {
      state.markers.push(marker);
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

  getters: {
    activeMarker: state => {
      const activeTagLevels = state.tagLevels
        .filter(tagLevel => tagLevel.active)
        .map(tagLevel => tagLevel.level);
      const activePlaceNames = state.places
        .filter(place => {
          if (activeTagLevels.includes(place.tagLevel)) {
            return place;
          }
        })
        .map(place => place.placeName);
      state.markers.filter(marker => {
        if (activePlaceNames.includes(marker.mc)) {
          marker.setMap(state.kakaoMap);
        } else {
          marker.setMap(null);
        }
      });
    },
  },

  modules: {},
});
