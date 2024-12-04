// 导入组件
import Vue from 'vue';
import Router from 'vue-router';
// 登录
import login from '@/views/login';
// 首页
import index from '@/views/index';

// 传承人信息
import inheritor from '@/views/inheritor/index';
// 非遗项目信息
import project from '@/views/project/index';

// 用户信息
import users from '@/views/users/index';

// 论坛管理
import forum from '@/views/forum/index';

// 公告信息表
import notice from '@/views/notice/index';

// 主题信息
import theme from '@/views/theme/index';

// 个人信息
import personage from '@/views/personage/index';

import goods from '@/views/goods/index';

import classify from '@/views/classify/index';

import order from '@/views/order/index';

// 启用路由
Vue.use(Router);

// 导出路由
export default new Router({
    routes: [{
        path: '/',
        name: '',
        component: login,
        hidden: true,
        meta: {
            requireAuth: false
        }
    }, {
        path: '/login',
        name: '登录',
        component: login,
        hidden: true,
        meta: {
            requireAuth: false
        }
    }, {
        path: '/index',
        name: '首页',
        component: index,
        iconCls: 'el-icon-tickets',
        children: [
          {
            path: '/inheritor/index',
            name: '非遗传承人信息',
            component: inheritor,
            meta: {
              requireAuth: true
            }
          },
          {
            path: '/project/index',
            name: '非遗项目信息',
            component: project,
            meta: {
              requireAuth: true
            }
          },
          {
            path: '/forum/index',
            name: '论坛数据管理',
            component: forum,
            meta: {
              requireAuth: true
            }
          },
          {
            path: '/notice/index',
            name: '公告信息管理',
            component: notice,
            meta: {
              requireAuth: true
            }
          },
          {
            path: '/theme/index',
            name: '主题信息管理',
            component: theme,
            meta: {
              requireAuth: true
            }
          },
          {
            path: '/users/index',
            name: '用户信息',
            component: users,
            meta: {
              requireAuth: true
            }
          },
          {
            path: '/goods/index',
            name: '非遗物品信息',
            component: goods,
            meta: {
              requireAuth: true
            }
          },
          {
            path: '/classify/index',
            name: '非遗物品分类',
            component: classify,
            meta: {
              requireAuth: true
            }
          },

          {
            path: '/order/index',
            name: '订单信息',
            component: order,
            meta: {
              requireAuth: true
            }
          },

          {
            path: '/personage/index',
            name: '个人信息',
            component: personage,
            meta: {
              requireAuth: true
            }
          }]
    }]
})
