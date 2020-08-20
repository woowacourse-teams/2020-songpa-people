import { CATEGORY } from "../../utils/constants";

export default {
  state: {
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

  getters: {
    getCategories: state => {
      return state.categories;
    },
    getActiveCategories: state => {
      return state.categories
        .filter(category => category.active)
        .map(category => category.name);
    },
  },

  mutations: {
    SET_CATEGORY(state, category) {
      state.categories = state.categories.map(c =>
        c.name === category.name
          ? { ...c, active: !category.active }
          : { ...c, active: true },
      );
    },
  },

  actions: {},
};
