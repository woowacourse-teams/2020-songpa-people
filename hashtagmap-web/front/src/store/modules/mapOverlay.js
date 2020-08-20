export default {
  state: {
    mapOverlays: [], // { textBalloon, marker, place }
  },

  getters: {
    getMapOverlays: state => {
      return state.mapOverlays;
    },
  },

  mutations: {
    ADD_MAP_OVERLAYS(state, overlayObj) {
      state.mapOverlays.push(overlayObj);
    },
  },

  actions: {},
};
