import { KAKAO_WEB_KEY } from "@/secret";
import navigatorUtils from "@/libs/navigator/navigator.js";
import dotImgSrc from "@/assets/dot.png";
import { KAKAO_MAP } from "@/utils/constants.js";

/**
 * main.js 에 Vue.use(KaKaoMap) 을 해야 한다.
 * 사용할 컴포넌트에서 <div id="kakao-map"></div> 작성후
 * mounted 에 this.$loadMap() 호출을 하면 카카오 지도를 띄운다.
 *
 * kakao-map을 로드후에 map을 이용한 로직이 필요하다면 mounted 에 async 를 하고 await로 $loadMap()의 실행을 기다려야 한다.
 *
 * 맵의 초기 위치를 지정하고 싶다면 {latitude:위도값, longitute:경도값} 를 매개변수로 주입하면 된다.
 * 현재 좌표를 받아오지 못 한다면 기본 좌표는 잠실역 8번 출구이다.
 */
export default {
  install(Vue) {
    const script = document.createElement("script");
    script.src =
      "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" + KAKAO_WEB_KEY;
    document.head.appendChild(script);

    /* global kakao */
    const loadApi = new Promise(resolve => {
      script.onload = () => kakao.maps.load(resolve);
    });

    Vue.prototype.$loadMap = async nowPosition => {
      await loadApi;

      const options = createOptions(nowPosition);
      const container = document.getElementById("kakao-map");

      this.map = new kakao.maps.Map(container, options);
      this.map.setMapTypeId(kakao.maps.MapTypeId.NORMAL);
    };

    Vue.prototype.$loadCurrentPosition = async () => {
      const currentGeolocation = navigatorUtils.getCurrentPosition();

      currentGeolocation
        .then(position => {
          const currentPosition = navigatorUtils.extractGeolocationPosition(
            position,
          );
          setPositionCenter.call(this, currentPosition);
          displayMarker.call(this, currentPosition);
          displayPlaceMarker.call(this);
        })
        .catch(() => {
          //TODO snackbar 로 교체 필요
          alert("현재 위치를 불러오지 못했습니다.");
        });
    };

    const setPositionCenter = position => {
      this.map.setCenter(createKakaoMapsLatLng(position));
    };

    const displayMarker = position => {
      setPositionCenter.call(this, position);

      const imageSize = new kakao.maps.Size(
        KAKAO_MAP.MAKER_SIZE,
        KAKAO_MAP.MAKER_SIZE,
      );
      const markerImage = new kakao.maps.MarkerImage(dotImgSrc, imageSize);
      const marker = new kakao.maps.Marker({
        position: createKakaoMapsLatLng(position),
        image: markerImage,
      });

      marker.setMap(this.map);
    };

    const createOptions = nowPosition => {
      return {
        center: createKakaoMapsLatLng(nowPosition),
        level: 5,
      };
    };

    const createKakaoMapsLatLng = nowPosition => {
      const position = nowPosition || KAKAO_MAP.JAMSIL_STATION_8_EXIT;
      return new kakao.maps.LatLng(position.latitude, position.longitude);
    };

    const displayPlaceMarker = () => {
      const places = [
        {
          title: "나나쓰",
          latlng: new kakao.maps.LatLng(37.5381046564076, 127.086792901784),
        },
        {
          title: "요거프레소 구의강변로점",
          latlng: new kakao.maps.LatLng(37.53486918679486, 127.0915365995048),
        },
        {
          title: "할리스커피 테크노마트점",
          latlng: new kakao.maps.LatLng(37.535626484476936, 127.09550998848691),
        },
        {
          title: "커피빈 강변테크노마트점",
          latlng: new kakao.maps.LatLng(37.535996508279, 127.09586460086),
        },
        {
          title: "감도",
          latlng: new kakao.maps.LatLng(37.5375643256869, 127.086425681531),
        },
        {
          title: "헤픈커피 구의점",
          latlng: new kakao.maps.LatLng(37.5396250478842, 127.092203205599),
        },
        {
          title: "토프레소 동서울터미널점",
          latlng: new kakao.maps.LatLng(37.5342585793261, 127.092345944862),
        },
        {
          title: "카페다비다",
          latlng: new kakao.maps.LatLng(37.5387735602152, 127.088730772893),
        },
        {
          title: "공차 강변테크노마트점",
          latlng: new kakao.maps.LatLng(37.53572634794616, 127.09569227689693),
        },
        {
          title: "란다방",
          latlng: new kakao.maps.LatLng(37.537635773175, 127.087285676636),
        },
        {
          title: "공간책바람",
          latlng: new kakao.maps.LatLng(37.5381402592453, 127.096636745883),
        },
        {
          title: "동부다방",
          latlng: new kakao.maps.LatLng(37.5375926989237, 127.08705028205),
        },
        {
          title: "커피베이 동서울터미널점",
          latlng: new kakao.maps.LatLng(37.53447768976573, 127.09441331250545),
        },
        {
          title: "카페탱고",
          latlng: new kakao.maps.LatLng(37.5398571742372, 127.094896455529),
        },
        {
          title: "말랑말랑레인보우슬라임",
          latlng: new kakao.maps.LatLng(37.536581771111585, 127.09634055823005),
        },
        {
          title: "나나쓰",
          latlng: new kakao.maps.LatLng(37.5381046564076, 127.086792901784),
        },
        {
          title: "요거프레소 구의강변로점",
          latlng: new kakao.maps.LatLng(37.53486918679486, 127.0915365995048),
        },
        {
          title: "할리스커피 테크노마트점",
          latlng: new kakao.maps.LatLng(37.535626484476936, 127.09550998848691),
        },
        {
          title: "커피빈 강변테크노마트점",
          latlng: new kakao.maps.LatLng(37.535996508279, 127.09586460086),
        },
        {
          title: "감도",
          latlng: new kakao.maps.LatLng(37.5375643256869, 127.086425681531),
        },
        {
          title: "헤픈커피 구의점",
          latlng: new kakao.maps.LatLng(37.5396250478842, 127.092203205599),
        },
        {
          title: "토프레소 동서울터미널점",
          latlng: new kakao.maps.LatLng(37.5342585793261, 127.092345944862),
        },
        {
          title: "카페다비다",
          latlng: new kakao.maps.LatLng(37.5387735602152, 127.088730772893),
        },
        {
          title: "공차 강변테크노마트점",
          latlng: new kakao.maps.LatLng(37.53572634794616, 127.09569227689693),
        },
        {
          title: "란다방",
          latlng: new kakao.maps.LatLng(37.537635773175, 127.087285676636),
        },
        {
          title: "공간책바람",
          latlng: new kakao.maps.LatLng(37.5381402592453, 127.096636745883),
        },
        {
          title: "동부다방",
          latlng: new kakao.maps.LatLng(37.5375926989237, 127.08705028205),
        },
        {
          title: "커피베이 동서울터미널점",
          latlng: new kakao.maps.LatLng(37.53447768976573, 127.09441331250545),
        },
        {
          title: "카페탱고",
          latlng: new kakao.maps.LatLng(37.5398571742372, 127.094896455529),
        },
        {
          title: "말랑말랑레인보우슬라임",
          latlng: new kakao.maps.LatLng(37.536581771111585, 127.09634055823005),
        },
        {
          title: "나나쓰",
          latlng: new kakao.maps.LatLng(37.5381046564076, 127.086792901784),
        },
        {
          title: "요거프레소 구의강변로점",
          latlng: new kakao.maps.LatLng(37.53486918679486, 127.0915365995048),
        },
        {
          title: "할리스커피 테크노마트점",
          latlng: new kakao.maps.LatLng(37.535626484476936, 127.09550998848691),
        },
        {
          title: "커피빈 강변테크노마트점",
          latlng: new kakao.maps.LatLng(37.535996508279, 127.09586460086),
        },
        {
          title: "감도",
          latlng: new kakao.maps.LatLng(37.5375643256869, 127.086425681531),
        },
        {
          title: "헤픈커피 구의점",
          latlng: new kakao.maps.LatLng(37.5396250478842, 127.092203205599),
        },
        {
          title: "토프레소 동서울터미널점",
          latlng: new kakao.maps.LatLng(37.5342585793261, 127.092345944862),
        },
        {
          title: "카페다비다",
          latlng: new kakao.maps.LatLng(37.5387735602152, 127.088730772893),
        },
        {
          title: "공차 강변테크노마트점",
          latlng: new kakao.maps.LatLng(37.53572634794616, 127.09569227689693),
        },
        {
          title: "란다방",
          latlng: new kakao.maps.LatLng(37.537635773175, 127.087285676636),
        },
        {
          title: "뉴질랜드스토리",
          latlng: new kakao.maps.LatLng(37.509703949514, 127.105842950648),
        },
        {
          title: "벨라스가든",
          latlng: new kakao.maps.LatLng(37.5100603323302, 127.106304921233),
        },
        {
          title: "릴리움",
          latlng: new kakao.maps.LatLng(37.510031801056, 127.105970090847),
        },
        {
          title: "카페이클립스",
          latlng: new kakao.maps.LatLng(37.50620319113905, 127.09897855784652),
        },
        {
          title: "라쁘띠",
          latlng: new kakao.maps.LatLng(37.5020541896279, 127.104072429291),
        },
        {
          title: "머머",
          latlng: new kakao.maps.LatLng(37.5044463307822, 127.097777417826),
        },
        {
          title: "서정적살롱",
          latlng: new kakao.maps.LatLng(37.510200948839035, 127.10623838872736),
        },
        {
          title: "스타벅스 석촌호수점",
          latlng: new kakao.maps.LatLng(37.5095241977995, 127.105340515353),
        },
        {
          title: "오린지",
          latlng: new kakao.maps.LatLng(37.509462192419555, 127.10616382420757),
        },
        {
          title: "에브리데이몬데이",
          latlng: new kakao.maps.LatLng(37.508853483565794, 127.10575126747445),
        },
        {
          title: "도화서가",
          latlng: new kakao.maps.LatLng(37.5100299612049, 127.108001448663),
        },
        {
          title: "설빙 석촌호수동호점",
          latlng: new kakao.maps.LatLng(37.51038810178674, 127.10652254813617),
        },
        {
          title: "뜬금커피",
          latlng: new kakao.maps.LatLng(37.50644988996618, 127.10078564495832),
        },
        {
          title: "스타벅스 잠실역점",
          latlng: new kakao.maps.LatLng(37.5152671337231, 127.099168114076),
        },
        {
          title: "비엔나커피하우스 잠실역점",
          latlng: new kakao.maps.LatLng(37.5151237502826, 127.102479855216),
        },
        {
          title: "엔제리너스 석촌호수DI점",
          latlng: new kakao.maps.LatLng(37.5071774088431, 127.101885393436),
        },
        {
          title: "유달리 석촌호수점",
          latlng: new kakao.maps.LatLng(37.5090880638354, 127.10539419204),
        },
        {
          title: "디트로네 잠실점",
          latlng: new kakao.maps.LatLng(37.50924338691711, 127.10500081019005),
        },
        {
          title: "테이크어샤워",
          latlng: new kakao.maps.LatLng(37.5074924097212, 127.104351420631),
        },
        {
          title: "스타벅스 석촌서호점",
          latlng: new kakao.maps.LatLng(37.5064683050376, 127.096537093223),
        },
        {
          title: "스타벅스 석촌역점",
          latlng: new kakao.maps.LatLng(37.5043516638645, 127.106197301854),
        },
        {
          title: "투썸플레이스 잠실역점",
          latlng: new kakao.maps.LatLng(37.515028730438914, 127.10191755741124),
        },
        {
          title: "겟썸커피 LMoA점",
          latlng: new kakao.maps.LatLng(37.51283044310748, 127.10173020057535),
        },
        {
          title: "스타벅스 잠실대교남단R점",
          latlng: new kakao.maps.LatLng(37.5156060017498, 127.099059973879),
        },
        {
          title: "스타벅스 송파구청",
          latlng: new kakao.maps.LatLng(37.514823971329, 127.107266342466),
        },
        {
          title: "스타벅스 잠실시그마타워점",
          latlng: new kakao.maps.LatLng(37.514586428406616, 127.10285238870503),
        },
        {
          title: "콰이어트크림티",
          latlng: new kakao.maps.LatLng(37.50650510536124, 127.09104051006283),
        },
        {
          title: "투썸플레이스 송파구청사거리점",
          latlng: new kakao.maps.LatLng(37.5150978924486, 127.107248636544),
        },
        {
          title: "카페마마스 잠실점",
          latlng: new kakao.maps.LatLng(37.5144261788278, 127.100610302042),
        },
        {
          title: "모드니",
          latlng: new kakao.maps.LatLng(37.50527894332, 127.089724827576),
        },
        {
          title: "쿠차라 잠실역점",
          latlng: new kakao.maps.LatLng(37.5166285679487, 127.101270454941),
        },
        {
          title: "퍼햅스",
          latlng: new kakao.maps.LatLng(37.5056373004036, 127.094654071051),
        },
        {
          title: "사브레",
          latlng: new kakao.maps.LatLng(37.509140294116, 127.107425601793),
        },
        {
          title: "비엔나커피하우스 석촌고분역점",
          latlng: new kakao.maps.LatLng(37.5027155792496, 127.097681293009),
        },
        {
          title: "사르르 잠실점",
          latlng: new kakao.maps.LatLng(37.504524002612, 127.098638182738),
        },
        {
          title: "고양이랑",
          latlng: new kakao.maps.LatLng(37.5089686660558, 127.105914298431),
        },
        {
          title: "피피샐러드",
          latlng: new kakao.maps.LatLng(37.50627853264733, 127.09938694487846),
        },
        {
          title: "설빙 석촌호수서호점",
          latlng: new kakao.maps.LatLng(37.50648256839937, 127.09661367985544),
        },
        {
          title: "케키하우스",
          latlng: new kakao.maps.LatLng(37.5087206158392, 127.105212711058),
        },
        {
          title: "블랙드롭커피 석촌호수점",
          latlng: new kakao.maps.LatLng(37.50519354818134, 127.09743344522265),
        },
        {
          title: "라딧프로젝트",
          latlng: new kakao.maps.LatLng(37.5068492787026, 127.106164647857),
        },
        {
          title: "카페애드",
          latlng: new kakao.maps.LatLng(37.503177630413916, 127.09112242128155),
        },
        {
          title: "옴니버스커피",
          latlng: new kakao.maps.LatLng(37.5015995217894, 127.096232261571),
        },
        {
          title: "스타벅스 갤러리아팰리스점",
          latlng: new kakao.maps.LatLng(37.5116133771753, 127.093883440399),
        },
        {
          title: "스타벅스 잠실푸르지오월드점",
          latlng: new kakao.maps.LatLng(37.517289081769015, 127.10326105199225),
        },
        {
          title: "쿠차라 잠실역점",
          latlng: new kakao.maps.LatLng(37.5166285679487, 127.101270454941),
        },
        {
          title: "퍼햅스",
          latlng: new kakao.maps.LatLng(37.5056373004036, 127.094654071051),
        },
        {
          title: "사브레",
          latlng: new kakao.maps.LatLng(37.509140294116, 127.107425601793),
        },
        {
          title: "비엔나커피하우스 석촌고분역점",
          latlng: new kakao.maps.LatLng(37.5027155792496, 127.097681293009),
        },
        {
          title: "사르르 잠실점",
          latlng: new kakao.maps.LatLng(37.504524002612, 127.098638182738),
        },
        {
          title: "고양이랑",
          latlng: new kakao.maps.LatLng(37.5089686660558, 127.105914298431),
        },
        {
          title: "피피샐러드",
          latlng: new kakao.maps.LatLng(37.50627853264733, 127.09938694487846),
        },
        {
          title: "설빙 석촌호수서호점",
          latlng: new kakao.maps.LatLng(37.50648256839937, 127.09661367985544),
        },
        {
          title: "케키하우스",
          latlng: new kakao.maps.LatLng(37.5087206158392, 127.105212711058),
        },
        {
          title: "블랙드롭커피 석촌호수점",
          latlng: new kakao.maps.LatLng(37.50519354818134, 127.09743344522265),
        },
        {
          title: "라딧프로젝트",
          latlng: new kakao.maps.LatLng(37.5068492787026, 127.106164647857),
        },
        {
          title: "카페애드",
          latlng: new kakao.maps.LatLng(37.503177630413916, 127.09112242128155),
        },
        {
          title: "옴니버스커피",
          latlng: new kakao.maps.LatLng(37.5015995217894, 127.096232261571),
        },
        {
          title: "스타벅스 갤러리아팰리스점",
          latlng: new kakao.maps.LatLng(37.5116133771753, 127.093883440399),
        },
        {
          title: "스타벅스 잠실푸르지오월드점",
          latlng: new kakao.maps.LatLng(37.517289081769015, 127.10326105199225),
        },
        {
          title: "쿠차라 잠실역점",
          latlng: new kakao.maps.LatLng(37.5166285679487, 127.101270454941),
        },
        {
          title: "퍼햅스",
          latlng: new kakao.maps.LatLng(37.5056373004036, 127.094654071051),
        },
        {
          title: "사브레",
          latlng: new kakao.maps.LatLng(37.509140294116, 127.107425601793),
        },
        {
          title: "비엔나커피하우스 석촌고분역점",
          latlng: new kakao.maps.LatLng(37.5027155792496, 127.097681293009),
        },
        {
          title: "사르르 잠실점",
          latlng: new kakao.maps.LatLng(37.504524002612, 127.098638182738),
        },
        {
          title: "고양이랑",
          latlng: new kakao.maps.LatLng(37.5089686660558, 127.105914298431),
        },
        {
          title: "피피샐러드",
          latlng: new kakao.maps.LatLng(37.50627853264733, 127.09938694487846),
        },
        {
          title: "설빙 석촌호수서호점",
          latlng: new kakao.maps.LatLng(37.50648256839937, 127.09661367985544),
        },
        {
          title: "케키하우스",
          latlng: new kakao.maps.LatLng(37.5087206158392, 127.105212711058),
        },
        {
          title: "블랙드롭커피 석촌호수점",
          latlng: new kakao.maps.LatLng(37.50519354818134, 127.09743344522265),
        },
        {
          title: "라딧프로젝트",
          latlng: new kakao.maps.LatLng(37.5068492787026, 127.106164647857),
        },
        {
          title: "카페애드",
          latlng: new kakao.maps.LatLng(37.503177630413916, 127.09112242128155),
        },
        {
          title: "옴니버스커피",
          latlng: new kakao.maps.LatLng(37.5015995217894, 127.096232261571),
        },
        {
          title: "스타벅스 갤러리아팰리스점",
          latlng: new kakao.maps.LatLng(37.5116133771753, 127.093883440399),
        },
        {
          title: "스타벅스 잠실푸르지오월드점",
          latlng: new kakao.maps.LatLng(37.517289081769015, 127.10326105199225),
        },
      ];
      const imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

      for (let i = 0; i < places.length; i++) {
        const imageSize = new kakao.maps.Size(24, 35);
        const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
        const marker = new kakao.maps.Marker({
          position: places[i].latlng,
          title: places[i].title,
          image: markerImage,
        });

        marker.setMap(this.map);
      }
    };
  },
};
