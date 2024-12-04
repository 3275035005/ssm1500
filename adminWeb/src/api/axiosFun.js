import axios from 'axios';

// 登录请求方法
const loginreq = (method, url, params) => {
    return axios({
        method: method,
        url: 'http://127.0.0.1:8081'+url,
        headers: {
            'Content-Type': 'application/json',
        },
        data: params,
    }).then(res => res.data);
};
// 通用公用方法
const req = (method, url, params) => {
    return axios({
        method: method,
        url: 'http://127.0.0.1:8081'+url,
        // headers: {
        //     'Content-Type': 'application/x-www-form-urlencoded',
        //     token: localStorage.getItem('account')
        // },
        data: params,
        // traditional: true,
        // transformRequest: [
        //     function(data) {
        //         let ret = ''
        //         for (let it in data) {
        //             ret +=
        //                 encodeURIComponent(it) +
        //                 '=' +
        //                 encodeURIComponent(data[it]) +
        //                 '&'
        //         }
        //         return ret
        //     }
        // ]
    }).then(res => res.data);
};

export {
    loginreq,
    req
}
