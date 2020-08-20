import axios from "axios";

function createAxios(timeout) {
  return axios.create({
    timeout: timeout ? timeout : 5000,
    validateStatus: status => {
      return status >= 200 && status < 400;
    }
  });
}

function customAxios(timeout) {
  return createAxios(timeout);
}

export function customWrapAxios(timeout) {
  let axiosInstance = createAxios(timeout);
  setInterceptors(axiosInstance);
  return axiosInstance;
}

function setInterceptors(axiosInstance) {
  axiosInstance.interceptors.response.use(response => {
    return {
      body: {
        code: response.data.code,
        message: response.data.message,
        data: response.data.data
      },
      headers: response.headers,
      status: response.status,
      config: response.config
    };
  }, errorHandler);
}

function errorHandler(error) {
  const data = {
    body: {
      code: error.response.data.code,
      message: error.response.data.message,
      data: error.response.data.data
    },
    headers: error.response.headers,
    status: error.response.status,
    config: error.response.config
  };
  return Promise.reject(data);
}

export function isOk(res) {
  return [200, 201, 202].includes(res.status);
}

export default customAxios;
