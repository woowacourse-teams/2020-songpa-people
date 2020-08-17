import districtApi from "@/request/api/district";

export default {
  namespaced: true,
  state: {
    districts: [],
    editTargetDistrict: {}
  },
  getters: {
    getDistricts: state => {
      return state.districts;
    },
    getEditTargetDistrictName: state => {
      return state.editTargetDistrict.districtName;
    }
  },
  mutations: {
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
    setDistrict: async ({ dispatch }, districtName) => {
      try {
        return await districtApi.save(districtName);
      } catch (error) {
        return error;
      } finally {
        dispatch("fetchDistricts");
      }
    },
    removeDistricts: async ({ dispatch }, selectedDistricts) => {
      if (!selectedDistricts || selectedDistricts.length === 0) {
        return;
      }
      const districtIds = {
        districtIds: selectedDistricts.map(district => district.districtId)
      };
      try {
        const response = await districtApi.delete(districtIds);
        dispatch("fetchDistricts");
        return response;
      } catch (error) {
        return error;
      }
    },
    modifyDistrict: async ({ state }, editDistrictName) => {
      const target = state.editTargetDistrict;
      const editDistrict = {
        districtId: target.districtId,
        districtName: editDistrictName
      };
      try {
        return await districtApi.updateDistrictName(editDistrict);
      } catch (error) {
        return error;
      }
    },
    fetchDistricts: async ({ commit }) => {
      try {
        const response = await districtApi.findAll();
        commit("CLEAR_DISTRICTS");
        const responseDistricts = response.body.data;
        responseDistricts.map(district => commit("ADD_DISTRICT", district));
        return response;
      } catch (error) {
        return error;
      }
    }
  }
};
