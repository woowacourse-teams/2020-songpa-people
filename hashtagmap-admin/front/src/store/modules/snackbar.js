import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

const snackbar = {
  state: {
    snackbar: { show: false, type: "", message: "", code: "" }
  },
  getters: {
    snackbar(state) {
      return state.snackbar;
    }
  },
  mutations: {
    showSnackbar(state, input) {
      state.snackbar.show = true;
      state.snackbar.type = input.type ? input.type : "info";
      state.snackbar.message = input.message;
      state.snackbar.code = input.code;
    }
  }
};

export default snackbar;
