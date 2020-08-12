import customAxios from "@/request";
import { MESSAGE, SNACK_BAR_TYPE } from "@/utils/constants";

export default {
  namespaced: true,
  state: {
    selectedDistricts: [],
    districts: [],
    districtInput: "",
    editTargetDistrict: {}
  },
  getters: {
    getDistricts: state => {
      return state.districts;
    },
    getDistrictInput: state => {
      return state.districtInput;
    },
    getEditTargetDistrictName: state => {
      return state.editTargetDistrict.districtName;
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
    },
    INPUT_EDIT_TARGET_DISTRICT_NAME: (state, editName) => {
      state.editTargetDistrict.districtName = editName;
    },
    SET_EDIT_TARGET_DISTRICT: (state, editTarget) => {
      state.editTargetDistrict = { ...editTarget };
    }
  },
  actions: {
    setDistrict: async ({ commit, dispatch, state }) => {
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
      } finally {
        commit("CLEAR_DISTRICT_INPUT");
        dispatch("fetchDistricts");
      }
      return snackbarContents;
    },
    removeDistricts: async ({ dispatch }, selectedDistricts) => {
      if (!selectedDistricts || selectedDistricts.length === 0) {
        return;
      }
      const districtIds = {
        districtIds: selectedDistricts.map(district => district.districtId)
      };
      await customAxios().delete("/districts", {
        headers: {
          "Content-Type": "application/json"
        },
        data: districtIds
      });

      dispatch("fetchDistricts");
    },
    modifyDistrict: async ({ state }) => {
      const target = state.editTargetDistrict;
      const editDistrict = {
        districtId: target.districtId,
        districtName: target.districtName
      };
      const snackbarContents = {
        type: SNACK_BAR_TYPE.SUCCESS,
        message: MESSAGE.SUCCESS,
        code: ""
      };
      try {
        await customAxios().patch("/districts", editDistrict, {
          headers: {
            "Content-Type": "application/json"
          }
        });
      } catch (error) {
        snackbarContents.type = SNACK_BAR_TYPE.ERROR;
        snackbarContents.message = error.response.data.message;
        snackbarContents.code = error.response.data.code;
      }
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
