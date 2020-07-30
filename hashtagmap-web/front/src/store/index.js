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
      {
        id: 2052340190,
        title: "메이더블유",
        latitude: 37.5134907216433,
        longitude: 127.061545396033,
        hashtag_count: 20000,
      },
      {
        id: 23707172,
        title: "스타벅스 코엑스몰점",
        latitude: 37.51182328107812,
        longitude: 127.05915972198929,
        hashtag_count: 20000,
      },
      {
        id: 17884761,
        title: "스타벅스 가락본동점",
        latitude: 37.4948610690639,
        longitude: 127.118812839145,
        hashtag_count: 20000,
      },
      {
        id: 22547048,
        title: "스타벅스 석촌호수점",
        latitude: 37.5095241977995,
        longitude: 127.105340515353,
        hashtag_count: 20000,
      },
      {
        id: 46950905,
        title: "라딧프로젝트",
        latitude: 37.5068492787026,
        longitude: 127.106164647857,
        hashtag_count: 20000,
      },
      {
        id: 1131738214,
        title: "오더에스프레소",
        latitude: 37.5114250202989,
        longitude: 127.055481173149,
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
