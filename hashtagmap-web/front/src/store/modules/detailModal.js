import axios from "axios";

export default {
  state: {
    detailModal: {
      isShow: false,
      placeName: "",
      placeUrl: "",
      hashtagCount: "",
      hashtagName: "",
      posts: [],
    },
  },

  getters: {
    getDetailModal: state => {
      return state.detailModal;
    },
  },

  mutations: {
    SET_DETAIL_MODAL(state, detailModal) {
      state.detailModal = detailModal;
    },
    SET_DETAIL_MODAL_CLOSE(state) {
      state.detailModal.isShow = false;
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
  },
};
