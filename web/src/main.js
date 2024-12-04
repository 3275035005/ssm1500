import Vue from 'vue';
import App from './App.vue';
import router from './router';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import 'babel-polyfill';

/*
* Vue.prototype.$axios = axios;

给Vue函数添加一个原型属性$axios 指向Axios

这样做的好处是在vue实例或组件中不用再去重复引用Axios
直接用this.$axios就能执行axios 方法了
*
* */

// import api from '@/api/index.js';
// Vue.prototype.$api = api;

let globalData={
    userInfo:{
        nickname:''
    }
};
let sta={
    isLogin:false,
    adminName:''
};
Vue.prototype.$sta = sta;

Vue.prototype.$globalData=globalData;


/* 让它少提示一个错误信息 */
Vue.config.productionTip = false;

Vue.use(ElementUI, {
    size: 'medium'
});


router.beforeEach((to, from, next) => {
    if ((to.path === '/inheritor'
        || to.path === '/iproject'
        || to.path === '/theme'
        || to.path === '/notice'
        || to.path === '/forum'
        || to.path === '/personal')) {
        var item = localStorage.getItem('username');
        if(item == null || item == "" || item == undefined){
            next("login");
        }else{
            next();
        }


    }else{
        next();
    }
});

new Vue({
    router,
    render: h => h(App)
}).$mount('#app');
