import axios from 'axios';

function createAxios(timeout) {
    return axios.create({
        timeout: timeout ? timeout : 5000,
        validateStatus: (status) => {
            return status >= 200 && status < 400;
        },
    });
}

function customAxios(timeout) {
    return createAxios(timeout);
}

export default customAxios;