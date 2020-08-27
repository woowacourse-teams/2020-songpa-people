import axios from "axios";
import { KAKAO_REST_KEY } from "@/secret";

const kakaoSearch = {
  getKeywordSearch(keyword) {
    return axios.get("https://dapi.kakao.com/v2/local/search/keyword.json", {
      headers: {
        Authorization: "KakaoAK " + KAKAO_REST_KEY
      },
      params: {
        query: keyword,
        x: 126.99094,
        y: 37.550929,
        rect:
          "126.75578831035362,37.41847533960485,127.2251487382762,37.70625487247741",
      },
    });
  },
};

export default kakaoSearch;
