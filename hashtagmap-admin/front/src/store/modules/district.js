import customAxios from "@/request";
import { MESSAGE, SNACK_BAR_TYPE } from "@/utils/constants";

export default {
  namespaced: true,
  state: {
    districts: [],
    districtInput: ""
  },
  getters: {
    getDistricts: state => {
      return state.districts;
    },
    getDistrictInput: state => {
      return state.districtInput;
    }
  },
  mutations: {
    INPUT_DISTRICT_TEXT: (state, newDistrict) => {
      state.districtInput = newDistrict;
    },
    CLEAR_DISTRICT_INPUT: state => {
      state.districtInput = "";
    },
    ADD_DISTRICT: (state, district) => {
      state.districts.push(district);
    },
    CLEAR_DISTRICTS: state => {
      state.districts = [];
    }
  },
  actions: {
    setDistrict: async ({ commit, state }) => {
      const snackbarContents = {
        type: SNACK_BAR_TYPE.SUCCESS,
        message: MESSAGE.SUCCESS,
        code: ""
      };
      try {
        const newDistrict = state.districtInput;
        if (!newDistrict || newDistrict.trim().length === 0) {
          snackbarContents.type = SNACK_BAR_TYPE.INFO;
          snackbarContents.message = MESSAGE.NO_INPUT;
          return snackbarContents;
        }
        const saveDto = {
          districtName: newDistrict
        };
        await customAxios().post("/districts", saveDto, {
          headers: {
            "Content-Type": "application/json"
          }
        });
        return snackbarContents;
      } catch (error) {
        snackbarContents.type = SNACK_BAR_TYPE.ERROR;
        snackbarContents.message = error.response.data.message;
        snackbarContents.code = error.response.data.code;
      }
      commit("CLEAR_DISTRICT_INPUT");
      return snackbarContents;
    },
    fetchDistricts: async ({ commit }) => {
      const snackbarContents = {
        type: SNACK_BAR_TYPE.INFO,
        message: MESSAGE.NO_CONTENT,
        code: ""
      };
      try {
        const response = await customAxios().get("/districts");
        commit("CLEAR_DISTRICTS");
        const responseDistricts = response.data.data;
        if (responseDistricts.length === 0) {
          return snackbarContents;
        }
        responseDistricts.map(district => commit("ADD_DISTRICT", district));
        snackbarContents.type = SNACK_BAR_TYPE.SUCCESS;
        snackbarContents.message = MESSAGE.SUCCESS;
      } catch (error) {
        snackbarContents.type = SNACK_BAR_TYPE.ERROR;
        snackbarContents.message = error.response.data.message;
        snackbarContents.code = error.response.data.code;
      }
      return snackbarContents;
    }
  }
};
