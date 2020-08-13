import customAxios from "@/request";
import { MESSAGE, SNACK_BAR_TEMPLATE } from "@/utils/constants";

export default {
  namespaced: true,
  state: {
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
      let snackbarContents = SNACK_BAR_TEMPLATE.SUCCESS();

      try {
        const newDistrict = state.districtInput;
        if (!newDistrict || newDistrict.trim().length === 0) {
          snackbarContents = SNACK_BAR_TEMPLATE.INFO(MESSAGE.NO_INPUT);
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
        snackbarContents = SNACK_BAR_TEMPLATE.ERROR(error);
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
      let snackbarContents = SNACK_BAR_TEMPLATE.SUCCESS();
      try {
        await customAxios().patch("/districts", editDistrict, {
          headers: {
            "Content-Type": "application/json"
          }
        });
      } catch (error) {
        snackbarContents = SNACK_BAR_TEMPLATE.ERROR(error);
      }
      return snackbarContents;
    },
    fetchDistricts: async ({ commit }) => {
      let snackbarContents = SNACK_BAR_TEMPLATE.INFO(MESSAGE.NO_CONTENT);
      try {
        const response = await customAxios().get("/districts");
        commit("CLEAR_DISTRICTS");
        const responseDistricts = response.data.data;
        if (responseDistricts.length === 0) {
          return snackbarContents;
        }
        responseDistricts.map(district => commit("ADD_DISTRICT", district));
        snackbarContents = SNACK_BAR_TEMPLATE.SUCCESS();
      } catch (error) {
        snackbarContents = SNACK_BAR_TEMPLATE.ERROR(error);
      }
      return snackbarContents;
    }
  }
};
