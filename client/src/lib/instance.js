import Axios from 'axios';
import { getFromLocalStorage } from './localStorage';
import { getRefreshToken } from './api';

const apiInstance = Axios.create({
  // eslint-disable-next-line no-undef
  baseURL: process.env.REACT_APP_API_URL,
});

apiInstance.interceptors.request.use(
  (config) => {
    if (!config.headers) return config;

    let token;

    if (config.url === `/api/v1/auth/reissue`) {
      token = getFromLocalStorage('refreshToken');
    } else {
      token = getFromLocalStorage('accessToken');
    }

    if (token !== null) {
      config.headers.Authorization = token;
    }

    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

apiInstance.interceptors.response.use(
  (response) => {
    return response;
  },

  async (error) => {
    const {
      config,
      response: { status },
    } = error;
    if (
      config.url === `/api/v1/auth/reissue` ||
      status !== 401 ||
      config.sent
    ) {
      return Promise.reject(error);
    }
    config.sent = true;
    const accessToken = await getRefreshToken();

    if (accessToken) {
      config.headers.Authorization = `${accessToken}`;
    }

    return apiInstance(config);
  }
);

export default apiInstance;
