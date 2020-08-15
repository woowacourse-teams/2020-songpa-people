import { customWrapAxios } from "@/request";

const districtApi = {
  save(districtName) {
    const saveDto = {
      districtName: districtName
    };
    return customWrapAxios().post("/districts", saveDto, {
      headers: {
        "Content-Type": "application/json"
      }
    });
  },
  findAll() {
    return customWrapAxios().get("/districts");
  },
  findAllDistrictName(){
    return customWrapAxios().get("/districts/names");
  },
  updateDistrictName(editDistrict) {
    return customWrapAxios().patch("/districts", editDistrict, {
      headers: {
        "Content-Type": "application/json"
      }
    });
  },
  delete(districtIds) {
    return customWrapAxios().delete("/districts", {
      headers: {
        "Content-Type": "application/json"
      },
      data: districtIds
    });
  }
};

export default districtApi;
