import {customWrapAxios} from "@/request";

const blackListApi = {
  getSubBlackList() {
    return customWrapAxios().get("/blacklist/semi");
  },
  updateInstagramAfterAddBlackList(blackList) {
    return customWrapAxios().put("/blacklist/instagram", blackList, {
      headers: {
        "Content-Type": "application/json"
      }
    });
  },
  deleteInstagramAfterAddBlackList(deleteBlackListData) {
    return customWrapAxios().delete("/blacklist/instagram", {
      headers: {
        "Content-Type": "application/json"
      },
      data: deleteBlackListData
    });
  }
};

export default blackListApi;
