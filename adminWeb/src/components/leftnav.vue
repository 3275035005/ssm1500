/**
* 左边菜单
*/
<template>
  <el-menu default-active="2" :collapse="collapsed" collapse-transition router :default-active="$route.path" unique-opened class="el-menu-vertical-demo" background-color="#334157" text-color="#fff" active-text-color="#ffd04b">
    <div class="logobox">
      <!-- <img class="logoimg" src="../assets/img/logo.png" alt="">-->
      非物质文化遗产管理系统
    </div>
    <el-submenu v-for="menu in allmenu" :key="menu.menuid" :index="menu.menuname">
      <template slot="title">
        <i class="iconfont" :class="menu.icon"></i>
        <span>{{menu.menuname}}</span>
      </template>
      <el-menu-item-group>
        <el-menu-item v-for="chmenu in menu.menus" :index="'/'+chmenu.url" :key="chmenu.menuid">
          <i class="iconfont" :class="chmenu.icon"></i>
          <span>{{chmenu.menuname}}</span>
        </el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>
<script>
export default {
  name: 'leftnav',
  data() {
    return {
      collapsed: false,
      allmenu: []
    }
  },
  // 创建完毕状态(里面是操作)
  created() {
    this.allmenu = [
      {
        menuid: 128,
        icon: 'li-icon-shangchengxitongtubiaozitihuayuanwenjian91',
        menuname: '非遗管理',
        hasThird: null,
        url: null,
        menus: [
          {
            menuid: 129,
            icon: 'icon-provider-manage',
            menuname: '传承人信息',
            hasThird: 'N',
            url: 'inheritor/index',
            menus: null
          },
          {
            menuid: 130,
            icon: 'icon-provider-manage',
            menuname: '非遗项目信息',
            hasThird: 'N',
            url: 'project/index',
            menus: null
          },
          {
            menuid: 131,
            icon: 'icon-provider-manage',
            menuname: '非遗项目分类',
            hasThird: 'N',
            url: 'classify/index',
            menus: null
          },
        ]
      },
      {
        menuid: 210,
        icon: 'li-icon-shangchengxitongtubiaozitihuayuanwenjian91',
        menuname: '非遗物品管理',
        hasThird: null,
        url: null,
        menus: [
          {
            menuid: 211,
            icon: 'icon-provider-manage',
            menuname: '非遗物品信息',
            hasThird: 'N',
            url: 'goods/index',
            menus: null
          },
          {
            menuid: 130,
            icon: 'icon-provider-manage',
            menuname: '非遗物品订单',
            hasThird: 'N',
            url: 'order/index',
            menus: null
          },
        ]
      },
      {
        menuid: 29,
        icon: 'li-icon-xiangmuguanli',
        menuname: '主题管理',
        hasThird: null,
        url: null,
        menus: [
          {
            menuid: 30,
            icon: 'icon-cat-skuQuery',
            menuname: '主题信息',
            hasThird: 'N',
            url: 'theme/index',
            menus: null
          }
        ]
      },
      {
        menuid: 31,
        icon: 'li-icon-xiangmuguanli',
        menuname: '论坛管理',
        hasThird: null,
        url: null,
        menus: [
          {
            menuid: 32,
            icon: 'icon-cat-skuQuery',
            menuname: '论坛信息',
            hasThird: 'N',
            url: 'forum/index',
            menus: null
          }
        ]
      },
      {
        menuid: 33,
        icon: 'li-icon-xiangmuguanli',
        menuname: '公告管理',
        hasThird: null,
        url: null,
        menus: [
          {
            menuid: 34,
            icon: 'icon-cat-skuQuery',
            menuname: '公告信息',
            hasThird: 'N',
            url: 'notice/index',
            menus: null
          }
        ]
      },
      {
        menuid: 35,
        icon: 'li-icon-xiangmuguanli',
        menuname: '系统管理',
        hasThird: null,
        url: null,
        menus: [
          {
            menuid: 36,
            icon: 'icon-cat-skuQuery',
            menuname: '用户信息',
            hasThird: 'N',
            url: 'users/index',
            menus: null
          },{
            menuid: 37,
            icon: 'icon-provider-manage',
            menuname: '个人信息',
            hasThird: 'N',
            url: 'personage/index',
            menus: null
          }
        ]
      }
    ]

    // 监听
    this.$root.Bus.$on('toggle', value => {
      this.collapsed = !value
    })
  }
}
</script>
<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 240px;
  min-height: 400px;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  border: none;
  text-align: left;
}
.el-menu-item-group__title {
  padding: 0px;
}
.el-menu-bg {
  background-color: #1f2d3d !important;
}
.el-menu {
  border: none;
}
.logobox {
  height: 40px;
  font-weight: bold;
  line-height: 40px;
  color: #9d9d9d;
  font-size: 20px;
  text-align: center;
  padding: 20px 0px;
}
.logoimg {
  height: 40px;
}
</style>
