export default {
  namespaced: true,
  state: {
    token: null
  },
  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token;
    }
  },
  getters: {
    isLoginMember: state => {
      return state.token !== null;
    }
  },
  actions: {
    loginRequest: (commit, member) => {
      console.log(member);
    }
  }
};
