import zoneApi from "@/request/api/zone";
import districtApi from "@/request/api/district";

export default {
  namespaced: true,
  state: {
    zones: [],
    districtNames: []
  },
  getters: {
    getZones: state => {
      return state.zones;
    },
    getDistrictNames: state => {
      return state.districtNames;
    }
  },
  mutations: {
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
    }
  },
  actions: {
    setZone: async ({ dispatch }, zoneInput) => {
      try {
        const response = await zoneApi.save(zoneInput);
        dispatch("fetchZones");
        return response;
      } catch (error) {
        return error;
      }
    },
    removeZones: async ({ dispatch }, selectedZones) => {
      if (!selectedZones || selectedZones.length === 0) {
        return;
      }
      const zoneIds = {
        zoneIds: selectedZones.map(zone => zone.zoneId)
      };
      try {
        const response = await zoneApi.delete(zoneIds);
        dispatch("fetchZones");
        return response;
      } catch (error) {
        return error;
      }
    },
    fetchZones: async ({ commit }) => {
      try {
        const response = await zoneApi.findAll();
        commit("CLEAR_ZONES");
        response.body.data.map(zone => commit("ADD_ZONE", zone));
      } catch (error) {
        return error;
      }
    },
    updateZone: async ({ dispatch }, editZone) => {
      try {
        return await zoneApi.update(editZone);
      } catch (error) {
        return error;
      } finally {
        dispatch("fetchZones");
      }
    },
    fetchDistrictNames: async ({ commit }) => {
      try {
        const response = await districtApi.findAllDistrictName();
        commit("CLEAR_DISTRICT_NAME");
        response.body.data.map(name => commit("ADD_DISTRICT_NAME", name));
        return response;
      } catch (error) {
        return error;
      }
    }
  }
};
