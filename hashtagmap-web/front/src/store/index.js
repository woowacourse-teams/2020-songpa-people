import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    kakaoMap: "",
    kakaoMapApi: "",
    isShowDetailModal: false,
    nowPlaceByDetailModal: "",
    //mock데이터
    places: [
      {
        id: 1028853888,
        title: "사브레",
        latitude: 37.509140294116,
        longitude: 127.107425601793,
        hashtagCount: 4121,
      },
      {
        id: 2052340190,
        title: "메이더블유",
        latitude: 37.5134907216433,
        longitude: 127.061545396033,
        hashtagCount: 64324,
      },
      {
        id: 23707172,
        title: "스타벅스 코엑스몰점",
        latitude: 37.51182328107812,
        longitude: 127.05915972198929,
        hashtagCount: 1234,
      },
      {
        id: 17884761,
        title: "스타벅스 가락본동점",
        latitude: 37.4948610690639,
        longitude: 127.118812839145,
        hashtagCount: 46745,
      },
      {
        id: 22547048,
        title: "스타벅스 석촌호수점",
        latitude: 37.5095241977995,
        longitude: 127.105340515353,
        hashtagCount: 2345,
      },
      {
        id: 46950905,
        title: "라딧프로젝트",
        latitude: 37.5068492787026,
        longitude: 127.106164647857,
        hashtagCount: 4567,
      },
      {
        id: 1131738214,
        title: "오더에스프레소",
        latitude: 37.5114250202989,
        longitude: 127.055481173149,
        hashtagCount: 7893,
      },
      {
        id: 484815048,
        title: "비엔나커피하우스 가락시장점",
        latitude: 37.4942549825733,
        longitude: 127.119417992607,
        hashtagCount: 7893,
      },
      {
        id: 620613877,
        title: "일라고",
        latitude: 37.507811521755045,
        longitude: 127.08379003675434,
        hashtagCount: 7893,
      },
      {
        id: 154147837,
        title: "이디야커피 석촌백제점",
        latitude: 37.5056344252843,
        longitude: 127.104478897235,
        hashtagCount: 7893,
      },
      {
        id: 848854645,
        title: "라프리앙",
        latitude: 37.5393856212013,
        longitude: 127.089550697244,
        hashtagCount: 7893,
      },
      {
        id: 1707843233,
        title: "스템커피 코엑스점",
        latitude: 37.5127172462817,
        longitude: 127.058821101221,
        hashtagCount: 7893,
      },
      {
        id: 159292358,
        title: "설빙 석촌호수동호점",
        latitude: 37.51038810178674,
        longitude: 127.10652254813617,
        hashtagCount: 7893,
      },
      {
        id: 1087045460,
        title: "젠젠 잠실점",
        latitude: 37.5106750462096,
        longitude: 127.108038571939,
        hashtagCount: 7893,
      },
      {
        id: 415932852,
        title: "헬로에클레시아",
        latitude: 37.50701436160438,
        longitude: 127.11647056695004,
        hashtagCount: 7893,
      },
      {
        id: 1166595135,
        title: "커피랑도서관 잠실나루점",
        latitude: 37.5207478460065,
        longitude: 127.104496573524,
        hashtagCount: 7893,
      },
      {
        id: 1259568533,
        title: "오스콘",
        latitude: 37.5016381280898,
        longitude: 127.088333962091,
        hashtagCount: 7893,
      },
      {
        id: 1005440058,
        title: "홈펫 1호점",
        latitude: 37.5037676652726,
        longitude: 127.114085999262,
        hashtagCount: 7893,
      },
      {
        id: 17154937,
        title: "이디야커피 송파동점",
        latitude: 37.5067210919029,
        longitude: 127.108417412502,
        hashtagCount: 7893,
      },
      {
        id: 27113391,
        title: "투썸플레이스 강남일원점",
        latitude: 37.49104977170967,
        longitude: 127.08038354967208,
        hashtagCount: 7893,
      },
      {
        id: 288452481,
        title: "서울리즘",
        latitude: 37.5097815878229,
        longitude: 127.111552566541,
        hashtagCount: 7893,
      },
      {
        id: 50787790,
        title: "스타벅스 잠실시그마타워점",
        latitude: 37.514586428406616,
        longitude: 127.10285238870503,
        hashtagCount: 7893,
      },
      {
        id: 672548769,
        title: "하니지니",
        latitude: 37.53883526987841,
        longitude: 127.09052311367752,
        hashtagCount: 7893,
      },
      {
        id: 21580664,
        title: "스타벅스 청담점",
        latitude: 37.5243543810765,
        longitude: 127.051585624477,
        hashtagCount: 7893,
      },
      {
        id: 1316863396,
        title: "더466루프탑",
        latitude: 37.5397498503373,
        longitude: 127.09390060313,
        hashtagCount: 7893,
      },
      {
        id: 1984634703,
        title: "카페 드 바디프랜드 청담점",
        latitude: 37.5244760678612,
        longitude: 127.05349528434,
        hashtagCount: 7893,
      },
      {
        id: 1137840382,
        title: "미림당",
        latitude: 37.5075481752851,
        longitude: 127.114195805247,
        hashtagCount: 7893,
      },
      {
        id: 27403306,
        title: "디오더",
        latitude: 37.509294355836126,
        longitude: 127.08152849095883,
        hashtagCount: 7893,
      },
      {
        id: 912733454,
        title: "파티세리도효",
        latitude: 37.509942571553836,
        longitude: 127.10897402202663,
        hashtagCount: 7893,
      },
      {
        id: 20943501,
        title: "잠실타로&사주",
        latitude: 37.5096536880232,
        longitude: 127.083063705141,
        hashtagCount: 7893,
      },
      {
        id: 242809039,
        title: "공간책바람",
        latitude: 37.5381402592453,
        longitude: 127.096636745883,
        hashtagCount: 7893,
      },
      {
        id: 8403572,
        title: "스타벅스 삼성역점",
        latitude: 37.5100800226801,
        longitude: 127.063990183621,
        hashtagCount: 7893,
      },
      {
        id: 1844759979,
        title: "도그존",
        latitude: 37.5091165624012,
        longitude: 127.083244075303,
        hashtagCount: 7893,
      },
      {
        id: 26905866,
        title: "커피에반하다 강변역점",
        latitude: 37.53654755898061,
        longitude: 127.09630883368706,
        hashtagCount: 7893,
      },
      {
        id: 223148556,
        title: "이월로스터스 송파점",
        latitude: 37.5097037337862,
        longitude: 127.110023281838,
        hashtagCount: 7893,
      },
      {
        id: 2069881158,
        title: "쿠차라 잠실역점",
        latitude: 37.5166285679487,
        longitude: 127.101270454941,
        hashtagCount: 7893,
      },
      {
        id: 254643912,
        title: "팻어케이크",
        latitude: 37.5077652221497,
        longitude: 127.107578580023,
        hashtagCount: 7893,
      },
      {
        id: 21539847,
        title: "푸른하늘 보드게임카페",
        latitude: 37.5071783394932,
        longitude: 127.104939105532,
        hashtagCount: 7893,
      },
      {
        id: 1274223814,
        title: "디초콜릿커피앤드 일원점",
        latitude: 37.4944736819983,
        longitude: 127.082870483585,
        hashtagCount: 7893,
      },
      {
        id: 125603741,
        title: "피치그레이",
        latitude: 37.5036445235894,
        longitude: 127.110937245377,
        hashtagCount: 7893,
      },
      {
        id: 1003191843,
        title: "요거프레소 구의강변로점",
        latitude: 37.53486918679486,
        longitude: 127.0915365995048,
        hashtagCount: 7893,
      },
      {
        id: 1062647229,
        title: "스타벅스 청담영동대로점",
        latitude: 37.5220924385651,
        longitude: 127.056457403591,
        hashtagCount: 7893,
      },
      {
        id: 1018158468,
        title: "비엔나커피하우스 송파사거리점",
        latitude: 37.50131480249455,
        longitude: 127.10869910940846,
        hashtagCount: 7893,
      },
      {
        id: 233218872,
        title: "카페희재",
        latitude: 37.5077744876412,
        longitude: 127.08002587038,
        hashtagCount: 7893,
      },
      {
        id: 1998404957,
        title: "키즈톡",
        latitude: 37.493440508218,
        longitude: 127.075740797621,
        hashtagCount: 7893,
      },
      {
        id: 710586475,
        title: "투썸플레이스 가락시장역2호점",
        latitude: 37.49313649752834,
        longitude: 127.11885760050963,
        hashtagCount: 7893,
      },
      {
        id: 26874201,
        title: "설빙 석촌호수서호점",
        latitude: 37.50648256839937,
        longitude: 127.09661367985544,
        hashtagCount: 7893,
      },
      {
        id: 1468287098,
        title: "카페텀블러 청담2호점",
        latitude: 37.51972814740647,
        longitude: 127.05658231588048,
        hashtagCount: 7893,
      },
      {
        id: 23279150,
        title: "커피트리",
        latitude: 37.4907264367973,
        longitude: 127.08657974646,
        hashtagCount: 7893,
      },
      {
        id: 1703570410,
        title: "투썸플레이스 경찰병원역점",
        latitude: 37.495269957205245,
        longitude: 127.12250675883755,
        hashtagCount: 7893,
      },
      {
        id: 2096042874,
        title: "카페 파이카지",
        latitude: 37.5089092877682,
        longitude: 127.080710337241,
        hashtagCount: 7893,
      },
      {
        id: 1316311547,
        title: "헬로방방 송파방이점",
        latitude: 37.5068072855989,
        longitude: 127.119917527948,
        hashtagCount: 7893,
      },
    ],
  },
  mutations: {
    initKakaoMapApi(state, kakaoMapApi) {
      state.kakaoMapApi = kakaoMapApi;
    },
    initKakaoMap(state, kakaoMap) {
      state.kakaoMap = kakaoMap;
    },
    initShowDetailModal(state, place) {
      state.isShowDetailModal = true;
      state.nowPlaceByDetailModal = place;
    },
    initCloseDetailModal(state) {
      state.isShowDetailModal = false;
    },
  },
  actions: {
    showDetailModal({ commit }, place) {
      commit("initShowDetailModal", place);
    },
  },
  getters: {
    getPlaces(state) {
      return state.places.map(place => {
        const { id, title, latitude, longitude, hashtagCount } = place;
        return {
          id,
          title,
          hashtagCount,
          latlng: new state.kakaoMapApi.LatLng(latitude, longitude),
        };
      });
    },
  },
  modules: {},
});
