const navigatorUtils = {
    getCurrentPosition() {
        return new Promise((resolve, reject) => {
            navigator.geolocation.getCurrentPosition(resolve, reject, {
                timeout: 4000
            });
        });
    },
    extractGeolocationPosition(position) {
        return {
            longitude: position.coords.longitude,
            latitude: position.coords.latitude
        }
    }
}

export default navigatorUtils;