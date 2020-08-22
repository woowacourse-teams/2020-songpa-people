import { TAG_LEVEL } from "@/utils/constants";
import axios from "axios";
import { convertHashtagCount } from "@/utils/hashtagCountConverter";

export default {
  state: {
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
    tagLevelDetails: [
      {
        level: "",
        minHashtagCount: "",
        maxHashtagCount: "",
      },
    ],
  },

  getters: {
    getTagLevels: state => {
      return state.tagLevels;
    },
    getActiveTagLevels: state => {
      return state.tagLevels
        .filter(tagLevel => tagLevel.active)
        .map(tagLevel => tagLevel.level);
    },
    getTagLevelDetails: state => {
      return state.tagLevelDetails.map(tagLevelDetail =>
        convertHashtagCount(tagLevelDetail.minHashtagCount),
      );
    },
  },

  mutations: {
    SET_TAG_LEVEL(state, tagLevel) {
      state.tagLevels = state.tagLevels.map(t =>
        t.level === tagLevel.level ? { ...t, active: !tagLevel.active } : t,
      );
    },
    SET_TAG_LEVEL_DETAILS(state, tagLevelDetails) {
      state.tagLevelDetails = tagLevelDetails;
    },
  },

  actions: {
    async setTagLevelDetails({ commit }) {
      const tagLevelDetails = await axios.get("/tag-levels");
      commit("SET_TAG_LEVEL_DETAILS", tagLevelDetails.data.data);
    },
  },
};
