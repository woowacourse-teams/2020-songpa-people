import { EVENT_TYPE } from "@/utils/constants";

export default {
  state: {
    kakaoMap: "",
    kakaoMapApi: "",
    mapBounds: {},
  },

  getters: {
    getKakaoMap: state => {
      return state.kakaoMap;
    },
    getKakaoMapApi: state => {
      return state.kakaoMapApi;
    },
    getBounds: state => {
      return state.mapBounds;
    },
  },

  mutations: {
    SET_KAKAO_MAP_API(state, kakaoMapApi) {
      state.kakaoMapApi = kakaoMapApi;
    },
    SET_KAKAO_MAP(state, kakaoMap) {
      state.kakaoMap = kakaoMap;
    },
    SET_BOUNDS_EVENT(state) {
      [EVENT_TYPE.DRAG_END, EVENT_TYPE.ZOOM_CHANGED].forEach(eventType => {
        state.kakaoMapApi.event.addListener(state.kakaoMap, eventType, () => {
          state.mapBounds = state.kakaoMap.getBounds();
        });
      });
    },
  },

  actions: {},
};
