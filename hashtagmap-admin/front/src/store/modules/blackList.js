import blackListApi from "@/request/api/blackList";

export default {
  namespaced: true,

  state: {
    subBlackList: [
      {
        kakaoId: "",
        placeName: "",
        hashtagCount: "",
        roadAddressName: ""
      }
    ]
  },

  mutations: {
    SET_SUB_BLACKLIST: (state, subBlackList) => {
      state.subBlackList = subBlackList;
    }
  },

  getters: {
    getSubBlackList: state => {
      return state.subBlackList;
    }
  },

  actions: {
    async setSubBlackList({ commit }) {
      const res = await blackListApi.getSubBlackList();
      commit("SET_SUB_BLACKLIST", res.body.data);
    },
    async addBlackList(commit, blackList) {
      const res = await blackListApi.addBlackList(blackList);
      console.log(res);
    }
  }
};
