import blackListApi from "@/request/api/blackList";

export default {
  namespaced: true,

  state: {
    semiBlackList: [
      {
        placeId: "",
        placeName: "",
        hashtagCount: "",
        roadAddressName: ""
      }
    ]
  },

  mutations: {
    SET_SUB_BLACKLIST: (state, semiBlackList) => {
      state.semiBlackList = semiBlackList;
    }
  },

  getters: {
    getSemiBlackList: state => {
      return state.semiBlackList;
    }
  },

  actions: {
    async setSemiBlackList({ commit }) {
      const res = await blackListApi.getSubBlackList();
      commit("SET_SUB_BLACKLIST", res.body.data);
    },
    async updateInstagramAfterAddBlackList(context, blackList) {
      try {
        return await blackListApi.updateInstagramAfterAddBlackList(blackList);
      } catch (e) {
        return e;
      }
    },
    async deleteInstagramAfterAddBlackList(context, deleteBlackListData) {
      try {
        return await blackListApi.deleteInstagramAfterAddBlackList(
          deleteBlackListData
        );
      } catch (e) {
        return e;
      }
    }
  }
};
