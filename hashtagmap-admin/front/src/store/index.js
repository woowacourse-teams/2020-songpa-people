import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    snackbar: { show: false, type: "", message: "", code: "" }
  },
  mutations: {
    SHOW_SNACKBAR(state, input) {
      state.snackbar.show = true;
      state.snackbar.type = input.type ? input.type : "info";
      state.snackbar.message = input.message;
      state.snackbar.code = input.code;
    }
  },
  actions: {},
  modules: {}
});
