import axios from 'axios';

export const checkUser = () => {
  const config = {
    method: 'get',
    url: '/users/stst',
  };
  return axios(config) //
    .then((res) => res.data)
    .catch((err) => console.log(err));
};
