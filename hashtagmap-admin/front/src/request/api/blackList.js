import { customWrapAxios } from "@/request";

const blackListApi = {
  getSubBlackList() {
    return customWrapAxios().get("/blacklist/sub");
  },
  addBlackList(blackList) {
    return customWrapAxios().post("/blacklist", blackList, {
      headers: {
        "Content-Type": "application/json"
      }
    });
  }
};

export default blackListApi;
