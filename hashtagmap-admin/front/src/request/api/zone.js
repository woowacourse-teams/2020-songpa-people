import customAxios, { customWrapAxios } from "@/request";

const zoneApi = {
  save(zoneInput) {
    return customWrapAxios().post("/districts/zones", zoneInput, {
      headers: {
        "Content-Type": "application/json"
      }
    });
  },
  update(updateDto) {
    return customWrapAxios().patch("/districts/zones", updateDto, {
      headers: {
        "Content-Type": "application/json"
      }
    });
  },
  findAll() {
    return customWrapAxios().get("/districts/zones");
  },
  delete(zoneIds) {
    return customAxios().delete("/districts/zones", {
      headers: {
        "Content-Type": "application/json"
      },
      data: zoneIds
    });
  }
};

export default zoneApi;
