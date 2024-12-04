import axios from 'axios';

/* axios功能封装  */

const service = axios.create({
    timeout: 5000,
    baseURL:  'http://localhost:8081',
    withCredentials:  true
});

// request interceptor(请求拦截器)
service.interceptors.request.use(
    config => {
        return config;
    },
    error => {
        console.log(error);
        return Promise.reject();
    }
);

// response interceptor（接收拦截器）
service.interceptors.response.use(
    response => {
        if (response.status === 200) {
            return response.data;
        } else {
            Promise.reject();
        }
    },
    error => {
        console.log(error);
        return Promise.reject();
    }
);

export default service;
