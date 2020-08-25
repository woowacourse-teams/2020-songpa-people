export default {
  state: {
    mapOverlays: [], // { textBalloon, marker, place }
    boundedOverLays: [],
    clusterer: {},
  },

  getters: {
    getMapOverlays: state => {
      return state.mapOverlays;
    },
    getClusterer: state => {
      return state.clusterer;
    },
    getBoundedMapOverLays: state => {
      return state.boundedOverLays;
    },
  },

  mutations: {
    ADD_MAP_OVERLAYS(state, overlayObj) {
      state.mapOverlays.push(overlayObj);
    },
    SET_CLUSTERER(state, clusterer) {
      state.clusterer = clusterer;
    },
    ADD_CLUSTER(state, marker) {
      state.clusterer.addMarker(marker);
    },
    CLEAR_CLUSTERER(state) {
      state.clusterer.clear();
    },
    CHANGE_BOUNDED_OVERLAYS(state, bounds) {
      const rightTopLatitude = bounds.getNorthEast().getLat();
      const rightTopLongitude = bounds.getNorthEast().getLng();
      const bottomLeftLatitude = bounds.getSouthWest().getLat();
      const bottomLeftLongitude = bounds.getSouthWest().getLng();

      state.boundedOverLays = state.mapOverlays.filter(overLay => {
        const markerLatitude = overLay.marker.getPosition().getLat();
        const markerLongitude = overLay.marker.getPosition().getLng();
        return (
          bottomLeftLatitude <= markerLatitude &&
          markerLatitude <= rightTopLatitude &&
          bottomLeftLongitude <= markerLongitude &&
          markerLongitude <= rightTopLongitude
        );
      });
    },
  },

  actions: {},
};
