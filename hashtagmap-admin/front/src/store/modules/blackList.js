import blackListApi from "@/request/api/blackList";

export default {
  namespaced: true,

  state: {
    subBlackList: [
      {
        placeId: "142",
        placeName: "신이문",
        hashtagCount: "2125",
        roadAddressName: "한천로"
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
    async addBlackList(context, blackList) {
      try {
        return await blackListApi.addBlackList(blackList);
      } catch (e) {
        return e;
      }
    },
    async deleteBlackListInstagram(context, placeId) {
      try {
        return await blackListApi.deleteBlackListInstagram(placeId);
      } catch (e) {
        return e;
      }
    }
  }
};
