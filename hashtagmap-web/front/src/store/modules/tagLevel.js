import { TAG_LEVEL } from "../../utils/constants";

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
  },

  mutations: {
    SET_TAG_LEVEL(state, tagLevel) {
      state.tagLevels = state.tagLevels.map(t =>
        t.level === tagLevel.level ? { ...t, active: !tagLevel.active } : t,
      );
    },
  },

  actions: {},
};
