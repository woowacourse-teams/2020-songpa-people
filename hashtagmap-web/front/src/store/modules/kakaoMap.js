export default {
  state: {
    kakaoMap: "",
    kakaoMapApi: "",
  },

  getters: {
    getKakaoMap: state => {
      return state.kakaoMap;
    },
    getKakaoMapApi: state => {
      return state.kakaoMapApi;
    },
  },

  mutations: {
    SET_KAKAO_MAP_API(state, kakaoMapApi) {
      state.kakaoMapApi = kakaoMapApi;
    },
    SET_KAKAO_MAP(state, kakaoMap) {
      state.kakaoMap = kakaoMap;
    },
  },

  actions: {},
};
