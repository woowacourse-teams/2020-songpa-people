import tagLevelApi from "@/request/api/tagLevel.js";

export default {
  namespaced: true,
  state: {
    tagLevels: [
      {
        tagLevel: "",
        minHashtagCount: "",
        maxHashtagCount: ""
      }
    ]
  },

  getters: {
    getTagLevels: state => {
      return state.tagLevels;
    }
  },

  mutations: {
    ADD_TAG_LEVEL: (state, tagLevel) => {
      state.tagLevels.push(tagLevel);
    },
    CLEAR_TAG_LEVELS: state => {
      state.tagLevels = [];
    }
  },

  actions: {
    updateTagLevel: async ({ dispatch }) => {
      try {
        const response = await tagLevelApi.findAll();
        dispatch("fetchTagLevels");
        return response;
      } catch (error) {
        return error;
      }
    },

    fetchTagLevels: async ({ commit }) => {
      try {
        const response = await tagLevelApi.findAll();
        commit("CLEAR_TAG_LEVELS");
        response.body.data.map(tagLevel => commit("ADD_TAG_LEVEL", tagLevel));
        return response;
      } catch (error) {
        return error;
      }
    }
  }
};
