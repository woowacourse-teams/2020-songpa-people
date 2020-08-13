export default {
  namespaced: true,
  state: {
    snackbar: { show: false, type: "", message: "", code: "" }
  },
  getters: {
    getSnackbar(state) {
      return state.snackbar;
    }
  },
  mutations: {
    SHOW_SNACKBAR(state, input) {
      state.snackbar.show = true;
      state.snackbar.type = input.type ? input.type : "info";
      state.snackbar.message = input.message;
      state.snackbar.code = input.code;
    }
  }
};
