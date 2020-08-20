import { customWrapAxios } from "@/request";

const tagLevelApi = {
  findAll() {
    return customWrapAxios().get("/tag-levels", {
      headers: {
        "Content-Type": "application/json"
      }
    });
  },
  update() {
    return customWrapAxios().put("/tag-levels");
  }
};

export default tagLevelApi;
