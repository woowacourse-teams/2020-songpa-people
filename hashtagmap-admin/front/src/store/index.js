import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

import snackbar from "./modules/snackbar";

export default new Vuex.Store({
  modules: {
    snackbar
  }
});
