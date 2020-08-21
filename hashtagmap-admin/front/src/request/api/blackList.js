import { customWrapAxios } from "@/request";

const blackListApi = {
  getSubBlackList() {
    return customWrapAxios().get("/blacklist/semi");
  },
  addBlackList(blackList) {
    return customWrapAxios().post("/blacklist", blackList, {
      headers: {
        "Content-Type": "application/json"
      }
    });
  },
  deleteBlackListInstagram(placeId) {
    return customWrapAxios().delete("/blacklist/instagram", {
      params: {
        placeId: placeId
      }
    });
  }
};

export default blackListApi;
