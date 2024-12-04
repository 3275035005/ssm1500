<template>
  <div class="header">
    <div class="header-container">
      <div class="app-name">
        <router-link to="/">非物质文化遗产推广平台</router-link>
      </div>
      <el-menu router :default-active="$route.path" class="el-menu-demo" mode="horizontal">
        <el-menu-item index="/home">首页</el-menu-item>
        <el-submenu index="2">
          <template slot="title">非遗名录</template>
          <el-menu-item index="/inheritor">非遗传承人</el-menu-item>
          <el-menu-item index="/iproject">非遗项目</el-menu-item>
          <el-menu-item index="/goods">非遗物品</el-menu-item>
        </el-submenu>
        <el-menu-item index="/theme">热门主题</el-menu-item>
        <el-menu-item index="/notice">政策通知</el-menu-item>
        <el-menu-item index="/forum">论坛讨论</el-menu-item>
      </el-menu>
      <router-link v-if="!isLogin" class="user-name-text" to="/login">登录</router-link>
      <el-dropdown trigger="click" v-else>
        <div style="cursor:pointer;display: flex;align-items: center;">
          <div style="font-size: 16px;color: #409EFF;padding-right: 5px;">{{name}}
          </div>
          <el-avatar :src="avatar"></el-avatar>
        </div>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>
            <div @click="toMe">个人中心</div>
          </el-dropdown-item>
          <el-dropdown-item divided style="color: red;">
            <div @click="loginOut">退出登录</div>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>
<script>
import { getUserInfo} from '@/api';
export default {
  name: 'Header',
  data() {
    return {
      activeIndex: '/',
      name: '登录',
      avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
      isLogin: false
    };
  },
  created() {
    let userId = localStorage.getItem('username');
    if (userId) {
      getUserInfo(userId).then(res => {
        if (res.code === 200) {
          this.name = res.row.name;
          this.avatar = res.row.image;
          this.isLogin = true;
        }
      });
    } else {
      this.isLogin = false;
    }
  },
  methods: {
    // 跳转个人中心
    toMe() {
      this.$router.push({ path: '/personal' });
    },

    // 退出登录
    loginOut() {
      localStorage.removeItem('username');
      if ('/index' === this.$route.path) {
        this.$router.go(0);
      } else {
        this.$router.push({ path: '/index' });
      }
    }
  }
};
</script>
<style scoped>
.header {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  width: 100%;
  height: 58px;
  background: #ffffff;
  display: flex;
  justify-content: center;
  border-bottom: #eeeeee solid 2px;
  z-index: 1000;
}

.header-container {
  width: 1000px;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.app-name a {
  color: #409EFF;
  font-size: 24px;
  text-decoration: none;
}

.search-container {
  width: 300px;
}

.user-name-text {
  font-size: 16px;
  font-weight: 600;
  color: #409EFF;
  cursor: pointer;
  text-decoration: none;
}
</style>
