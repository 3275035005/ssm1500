import Vue from 'vue';
import Router from 'vue-router';

const originalReplace = Router.prototype.replace;
Router.prototype.replace = function replace(location) {
    return originalReplace.call(this, location).catch(err => err);
};
const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
};

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/home'
        },
        {
            path: '/home',
            component: () => import('../views/home/index.vue'),
            meta: { title: '非物质文化遗产推广平台' }
        },
        {
            path: '/inheritor',
            component: () => import('../views/inheritor/index.vue'),
            meta: { title: '传承人信息 | 非物质文化遗产推广平台' }
        },
        {
            path: '/idetails',
            component: () => import('../views/inheritor/details.vue'),
            meta: { title: '详情 | 非物质文化遗产推广平台' }
        },
        {
            path: '/iproject',
            component: () => import('../views/project/index.vue'),
            meta: { title: '非遗项目信息 | 非物质文化遗产推广平台' }
        },
        {
            path: '/ipdetails',
            component: () => import('../views/project/details.vue'),
            meta: { title: '非遗详情 | 非物质文化遗产推广平台' }
        },
        {
            path: '/theme',
            component: () => import('../views/theme/index.vue'),
            meta: { title: '主题信息 | 非物质文化遗产推广平台' }
        },
        {
            path: '/tdetails',
            component: () => import('../views/theme/details.vue'),
            meta: { title: '主题详情 | 非物质文化遗产推广平台' }
        },
        {
            path: '/notice',
            component: () => import('../views/notice/index.vue'),
            meta: { title: '政策通知 | 非物质文化遗产推广平台' }
        },
        {
            path: '/forum',
            component: () => import('../views/forum/index.vue'),
            meta: { title: '论坛讨论 | 非物质文化遗产推广平台' }
        },
        {
            path: '/fdetails',
            component: () => import('../views/forum/details.vue'),
            meta: { title: '论坛详情 | 非物质文化遗产推广平台' }
        },
        {
            path: '/personal',
            component: () => import('../views/personal/index.vue'),
            meta: { title: '个人中心 | 非物质文化遗产推广平台' }
        },
        {
            path: '/login',
            component: () => import('../views/login/login.vue'),
            meta: { title: '登录 | 非物质文化遗产推广平台' }
        },
        {
            path: '/register',
            component: () => import('../views/login/register.vue'),
            meta: { title: '注册 | 非物质文化遗产推广平台' }
        },
        {
            path: '/order',
            component: () => import('../views/order/index.vue'),
            meta: { title: '订单 | 非物质文化遗产推广平台' }
        },
        {
            path: '/gdetails',
            component: () => import('../views/goods/details.vue'),
            meta: { title: '非遗物品详情 | 非物质文化遗产推广平台' }
        },
        {
            path: '/goods',
            component: () => import('../views/goods/index.vue'),
            meta: { title: '非遗物品 | 非物质文化遗产推广平台' }
        },
        {
            path: '/order',
            component: () => import('../views/order/index.vue'),
            meta: { title: '订单 | 非物质文化遗产推广平台' }
        },
        {
            path: '*',
            redirect: '/'
        }
    ]
});
