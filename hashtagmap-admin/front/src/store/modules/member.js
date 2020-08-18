import { customWrapAxios } from "@/request";
import { CONST } from "@/utils/constants";

export default {
  namespaced: true,
  state: {},
  mutations: {},
  getters: {
    isLogin: () => {
      return localStorage.getItem(CONST.ADMIN_LOGIN_KEY);
    }
  },
  actions: {
    loginRequest: async (commit, member) => {
      try {
        await customWrapAxios().post("/admin-member/login", member);
        localStorage.setItem(CONST.ADMIN_LOGIN_KEY, true);
        location.href = "/";
      } catch (e) {
        return e;
      }
    },
    logout: () => {
      localStorage.removeItem(CONST.ADMIN_LOGIN_KEY);
      location.href = "/";
    }
  }
};
