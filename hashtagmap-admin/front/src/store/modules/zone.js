import customAxios from "@/request";
import { MESSAGE, SNACK_BAR_TEMPLATE } from "@/utils/constants";

export default {
  namespaced: true,
  state: {
    zones: [],
    districtNames: [],
    selectedDistrictName: "",
    zoneInput: {
      districtName: "",
      topLeftLatitude: "",
      topLeftLongitude: "",
      bottomRightLatitude: "",
      bottomRightLongitude: ""
    },
    editTargetZone: {
      districtName: "",
      topLeftLatitude: "",
      topLeftLongitude: "",
      bottomRightLatitude: "",
      bottomRightLongitude: ""
    }
  },
  getters: {
    getZones: state => {
      return state.zones;
    },
    getZoneInput: state => {
      return state.zoneInput;
    },
    getEditTargetZone: state => {
      return state.editTargetZone;
    },
    getDistrictNames: state => {
      return state.districtNames;
    },
    getSelectedName: state => {
      return state.selectedDistrictName;
    }
  },
  mutations: {
    INPUT_ZONE: (state, newZone) => {
      state.zoneInput = newZone;
    },
    CLEAR_ZONE_INPUT: state => {
      state.zoneInput = {
        districtName: "",
        topLeftLatitude: "",
        topLeftLongitude: "",
        bottomRightLatitude: "",
        bottomRightLongitude: ""
      };
    },
    ADD_ZONE: (state, zone) => {
      state.zones.push(zone);
    },
    ADD_DISTRICT_NAME: (state, name) => {
      state.districtNames.push(name);
    },
    CLEAR_ZONES: state => {
      state.zones = [];
    },
    CLEAR_DISTRICT_NAME: state => {
      state.districtNames = [];
    },
    INPUT_NEW_TOP_LEFT_LATITUDE: (state, value) => {
      state.zoneInput.topLeftLatitude = value;
    },
    INPUT_NEW_TOP_LEFT_LONGITUDE: (state, value) => {
      state.zoneInput.topLeftLongitude = value;
    },
    INPUT_NEW_BOTTOM_RIGHT_LATITUDE: (state, value) => {
      state.zoneInput.bottomRightLatitude = value;
    },
    INPUT_NEW_BOTTOM_RIGHT_LONGITUDE: (state, value) => {
      state.zoneInput.bottomRightLongitude = value;
    },
    INPUT_EDIT_TOP_LEFT_LATITUDE: (state, editData) => {
      state.editTargetZone.topLeftLatitude = editData;
    },
    INPUT_EDIT_TOP_LEFT_LONGITUDE: (state, editData) => {
      state.editTargetZone.topLeftLongitude = editData;
    },
    INPUT_EDIT_BOTTOM_RIGHT_LATITUDE: (state, editData) => {
      state.editTargetZone.bottomRightLatitude = editData;
    },
    INPUT_EDIT_BOTTOM_RIGHT_LONGITUDE: (state, editData) => {
      state.editTargetZone.bottomRightLongitude = editData;
    },
    SET_EDIT_TARGET_ZONE: (state, editTarget) => {
      state.editTargetZone = editTarget;
    },
    SET_SELECTED_DISTRICT_NAME: (state, selected) => {
      state.selectedDistrictName = selected;
    },
    CLEAR_SELECT_DISTRICT_NAME: state => {
      state.selectedDistrictName = "";
    }
  },
  actions: {
    setZone: async ({ commit, dispatch, state }) => {
      let snackbarContents = SNACK_BAR_TEMPLATE.SUCCESS();

      try {
        state.zoneInput.districtName = state.selectedDistrictName;
        const newZone = state.zoneInput;
        if (!newZone) {
          snackbarContents = SNACK_BAR_TEMPLATE.INFO(MESSAGE.NO_INPUT);
          return snackbarContents;
        }
        await customAxios().post("/districts/zones", newZone, {
          headers: {
            "Content-Type": "application/json"
          }
        });
        commit("CLEAR_ZONE_INPUT");
        dispatch("fetchZones");
        return snackbarContents;
      } catch (error) {
        snackbarContents = SNACK_BAR_TEMPLATE.ERROR(error);
      }
      return snackbarContents;
    },
    removeZones: async ({ dispatch }, selectedZones) => {
      if (!selectedZones || selectedZones.length === 0) {
        return;
      }
      const zoneIds = {
        zoneIds: selectedZones.map(zone => zone.zoneId)
      };
      await customAxios().delete("/districts/zones", {
        headers: {
          "Content-Type": "application/json"
        },
        data: zoneIds
      });

      dispatch("fetchZones");
    },
    modifyZone: async ({ state }) => {
      const target = state.editTargetZone;
      delete target.districtId;
      target.districtName = state.selectedDistrictName;
      console.log(target);
      let snackbarContents = SNACK_BAR_TEMPLATE.SUCCESS();
      try {
        await customAxios().patch("/districts/zones", target, {
          headers: {
            "Content-Type": "application/json"
          }
        });
      } catch (error) {
        snackbarContents = SNACK_BAR_TEMPLATE.ERROR(error);
      }
      return snackbarContents;
    },
    fetchZones: async ({ commit }) => {
      let snackbarContents = SNACK_BAR_TEMPLATE.INFO(MESSAGE.NO_CONTENT);
      try {
        const response = await customAxios().get("/districts/zones");
        commit("CLEAR_ZONES");
        const responseZones = response.data.data;
        if (responseZones.length === 0) {
          return snackbarContents;
        }
        responseZones.map(zone => commit("ADD_ZONE", zone));
        snackbarContents = SNACK_BAR_TEMPLATE.SUCCESS();
      } catch (error) {
        snackbarContents = SNACK_BAR_TEMPLATE.ERROR(error);
      }
      return snackbarContents;
    },
    fetchDistrictNames: async ({ commit }) => {
      const response = await customAxios().get("/districts/names");
      commit("CLEAR_DISTRICT_NAME");
      const responseNames = response.data.data;
      if (responseNames.length === 0) {
        return;
      }
      responseNames.map(name => commit("ADD_DISTRICT_NAME", name));
    }
  }
};
