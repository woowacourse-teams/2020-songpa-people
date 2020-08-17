import customAxios from "@/request";

export default {
  namespaced: true,
  state: {
    isLogin: false
  },
  mutations: {
    SET_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    }
  },
  getters: {
    isLogin: state => {
      return state.isLogin;
    }
  },
  actions: {
    loginRequest: async ({ commit }, member) => {
      try {
        await customAxios().post("/admin-member", member);
        commit("SET_LOGIN", true);
      } catch (e) {
        console.log(e.message);
      }
    }
  }
};
