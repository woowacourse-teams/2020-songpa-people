import { customWrapAxios } from "@/request";

const tagLevelApi = {
  show() {
    return customWrapAxios().get("/tag-levels");
  },
  update() {
    return customWrapAxios().put("/tag-levels");
  }
};
