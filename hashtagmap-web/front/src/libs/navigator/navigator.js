const navigatorUtils = {
  getCurrentPosition() {
    return new Promise((resolve, reject) => {
      navigator.geolocation.getCurrentPosition(resolve, reject, {
        enableHighAccuracy: false,
        maximumAge: 60000,
        timeout: 10000,
      });
    });
  },
  extractGeolocationPosition(position) {
    return {
      longitude: position.coords.longitude,
      latitude: position.coords.latitude,
    };
  },
  convertToLatLon(x, y) {
    return {
      longitude: x,
      latitude: y,
    };
  },
};

export default navigatorUtils;
