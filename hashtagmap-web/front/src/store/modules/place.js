import axios from "axios";

export default {
  state: {
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
  },

  getters: {
    getPlaces: state => {
      return state.places;
    },
  },

  mutations: {
    SET_PLACES(state, places) {
      state.places = places;
    },
  },

  actions: {
    async setPlaces({ commit }) {
      const places = await axios.get("/markers");
      commit("SET_PLACES", places.data.data);
    },
  },
};
