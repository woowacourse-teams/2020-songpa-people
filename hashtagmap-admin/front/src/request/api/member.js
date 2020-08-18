import { customWrapAxios } from "@/request";

const memberApi = {
  login(member) {
    return customWrapAxios().post("/admin-member/login", member);
  }
};

export default memberApi;
