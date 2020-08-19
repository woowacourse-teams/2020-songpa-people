import { customWrapAxios } from "@/request";

const blackListApi = {
  getSubBlackList() {
    return customWrapAxios().get("/blacklist/sub");
  }
};

export default blackListApi;
