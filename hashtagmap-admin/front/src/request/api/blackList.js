import { customWrapAxios } from "@/request";

const blackListApi = {
  getSubBlackList() {
    return customWrapAxios().get("/blacklist/semi");
  },
  updateInstagramAfterAddBlackList(blackList) {
    return customWrapAxios().post("/blacklist/update-instagram", blackList, {
      headers: {
        "Content-Type": "application/json"
      }
    });
  },
  deleteInstagramAfterAddBlackList(deleteBlackListData) {
    return customWrapAxios().post(
      "/blacklist/delete-instagram",
      deleteBlackListData,
      {
        headers: {
          "Content-Type": "application/json"
        }
      }
    );
  }
};

export default blackListApi;
