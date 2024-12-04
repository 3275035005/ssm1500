<template>
  <div>
    <app-head></app-head>

    <app-body>
      <el-card class="TopCard" style="height:100px">
        <!-- 小喇叭 -->
        <i class="el-icon-chat-dot-round" style="color:#606266"/>
        <span class="tips">公告</span>
        <!-- 滚动文字外层div，文字能展示的区域-->
        <div class="noticeBox"
             :style="'width:'+noticeWidth+'px;height:20px;position:relative;overflow:hidden;display:inline-block;vertical-align:middle;margin-top:-5px'">
          <!-- 滚动div，marginLeft变化-->
          <div :style="'margin-left:'+marginLeft+'px;white-space:nowrap'">
            <span class="showNotice">{{ showNotice }}</span>
            <!-- 不会展示，用来测量第二条与第一条重合时的长度 -->
            <span class="notice">{{ notice }}</span>
          </div>
        </div>
      </el-card>

      <div id="banner">

        <!--动态将图片轮播图的容器高度设置成与图片一致-->
        <el-carousel :height="bannerHeight + 'px'">
          <!--遍历图片地址,动态生成轮播图-->
          <el-carousel-item v-for="item in projectList" :key="item.id">
            <img :src="item.image" alt @click="toProjectDetails(item.id)"/>
          </el-carousel-item>
        </el-carousel>
      </div>
      <div style="min-height: 70vh;">
        <el-divider><i class="el-icon-data-analysis"></i></el-divider>
        <div style="font-size: 40px; text-align: center;margin: 20px 0 20px 0">
          推荐主题
        </div>
        <el-divider><i class="el-icon-data-analysis"></i></el-divider>
        <div style="margin: 0 20px; height: 500px">
          <el-empty description="暂无数据" v-if="themeList.length===0"></el-empty>
          <el-row :gutter="30" v-else>
            <el-col :span="6" v-for="(item ,index) in themeList">
              <div class="idle-card" @click="toThemeDetails(item.id)">
                <el-image
                    style="width: 100%; height: 160px"
                    :src="item.image"
                    fit="contain">
                  <div slot="error" class="image-slot">
                    <i class="el-icon-picture-outline">无图</i>
                  </div>
                </el-image>
                <div class="idle-title">
                  <div>
                    {{ item.title }}
                  </div>
                  <div style="font-size: 10px;text-align: right">
                    {{ item.createTime }}
                  </div>
                </div>
              </div>
            </el-col>
          </el-row>
        </div>
      </div>
      <app-foot></app-foot>
    </app-body>
  </div>
</template>

<script>
import AppHead from '@/components/common/AppHeader.vue';
import AppBody from '@/components/common/AppPageBody.vue';
import AppFoot from '@/components/common/AppFoot.vue';
import { getHome } from '@/api/index';

export default {
  name: 'index',
  components: {
    AppHead,
    AppBody,
    AppFoot
  },
  data() {
    return {
      labelName: '0',
      noticeData: [], // 公告内容
      themeList: [], // 存储热门主题
      projectList: [], // 存储推荐非遗项目
      currentPage: 1,
      totalItem: 1,
      bannerHeight: 1000,
      screenWidth: 0,
      // 适应屏幕分辨率
      noticeWidth: window.screen.width - 150,
      // 公告展示（过长时会重复两遍）
      showNotice: '',
      // 用于公告过长时，获取重复两遍中第一遍的长度
      notice: '',
      // 公告初始位置
      marginLeft: 100,

    };
  },
  created() {
    this.getData();
  },
  methods: {
    init(){
      // 首次加载时,需要调用一次
      this.screenWidth = window.innerWidth;
      this.setSize();
      // 窗口大小发生改变时,调用一次
      window.onresize = () => {
        this.screenWidth = window.innerWidth;
        this.setSize();
      };

      this.noticeData.forEach((val, index) => {
        if (index === 0) {
          this.showNotice += '【' + (index + 1) + '】' + val;
        } else {
          this.showNotice += '\xa0\xa0\xa0\xa0\xa0\xa0【' + (index + 1) + '】' + val;
        }
      });
      // 公告上面先赋值，再获取宽度
      setTimeout(() => {
        // 公告div长度
        var oneNoticeWidth = document.getElementsByClassName('showNotice')[0].offsetWidth;
        // 公告外层div长度
        var noticeBoxWidth = document.getElementsByClassName('noticeBox')[0].offsetWidth;
        // 一条公告长度太大时，才滚动
        if (oneNoticeWidth > noticeBoxWidth) {
          // 滚动公告需要将公告重复两遍进行滚动，两遍中间需要加空格
          this.notice = this.showNotice + '\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0\xa0';
          // 上面先赋值，再获取宽度
          setTimeout(() => {
            // 获取一遍加中间空格的长度，即左移时第二遍与第一遍完全重合时的长度
            var oneNoticeAddEmptyWidth = document.getElementsByClassName('notice')[0].offsetWidth;
            // 公告内容重复两遍
            this.showNotice = this.notice + this.showNotice;
            this.timer = setInterval(() => {
              this.marginLeft -= 1;
              // 第二遍与第一遍起始位置重合时（第一条已完全移到左侧隐藏），marginLeft置0，即回到第一条
              if (this.marginLeft === (-1) * oneNoticeAddEmptyWidth) {
                this.marginLeft = 0;
              }
            }, 20);
          }, 10);
        } else { //公告并没有很长时不滚动
          this.marginLeft = 0;
        }
      }, 10);
    },
    setSize: function() {
      // 通过浏览器宽度(图片宽度)计算高度
      this.bannerHeight = (400 / 1920) * this.screenWidth;
    },
    getData() {
      const loading = this.$loading({
          lock: true,
          text: '加载数据中',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0)'
      });
      getHome().then(res=>{
        console.log(res.data.notice);
        this.noticeData = res.data.notice;
        this.themeList = res.data.theme;
        this.projectList = res.data.project;

        this.init();
        loading.close();
      })
    },


    // 跳转主题详情
    toThemeDetails(id) {
      this.$router.push({ path: '/tdetails', query: { id: id } });
    },
    // 跳转项目详情
    toProjectDetails(id) {
      this.$router.push({ path: '/ipdetails', query: { id: id } });
    }
  }
};
</script>

<style scoped>
.TopCard .el-card__body {
  padding: 0px 10px
}
/* 公告title */
.tips {
  line-height: 50px;
  color: #606266;
  font-weight: bold
}

.el-carousel__item h3 {
  color: #475669;

  font-size: 14px;

  opacity: 0.75;

  line-height: 300px;

  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

img {
  /*设置图片宽度和浏览器宽度一致*/

  width: 100%;

  height: inherit;
}


.idle-card {
  height: 230px;
  border: #eeeeee solid 1px;
  margin-bottom: 15px;
  cursor: pointer;
}

.fenye {
  display: flex;
  justify-content: center;
  height: 60px;
  align-items: center;
}

.idle-title {
  font-size: 18px;
  font-weight: 600;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin: 10px;
}

.idle-prive {
  font-size: 16px;
  color: red;
  margin-left: 100px;
}

.idle-place {
  font-size: 13px;
  color: #666666;
  float: right;
  padding-right: 20px;

}

.idle-time {
  color: #666666;
  font-size: 12px;
  margin: 0 10px;
}

.user-nickname {
  color: #999999;
  font-size: 12px;
  display: flex;
  align-items: center;
  height: 30px;
  padding-left: 10px;
}

.user-info {
  padding: 5px 10px;
  height: 30px;
  display: flex;
}
</style>
