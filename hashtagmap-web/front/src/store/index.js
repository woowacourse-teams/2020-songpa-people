import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    kakaoMap: "",
    kakaoMapApi: "",
    //mock데이터
    places: [
      {
        id: 1028853888,
        title: "사브레",
        latitude: 37.509140294116,
        longitude: 127.107425601793,
        hashtag_count: 10000,
      },
      {
        id: 27184288,
        title: "멍멍멍",
        latitude: 37.49887677102432,
        longitude: 127.11799592169268,
        hashtag_count: 20000,
      },
    ],
  },
  mutations: {
    initKakaoMap(state, map) {
      state.kakaoMap = map;
    },
    initKakaoMapApi(state, api) {
      state.kakaoMapApi = api;
    },
  },
  actions: {},
  getters: {
    getPlaces: state => {
      return state.places.map(function(place) {
        let kakaoPlace = {};
        kakaoPlace["title"] = place.title;
        kakaoPlace["latlng"] = new state.kakaoMapApi.LatLng(
          place.latitude,
          place.longitude,
        );
        kakaoPlace["hashtag_count"] = place.hashtag_count;
        return kakaoPlace;
      });
    },
  },
  modules: {},
});
