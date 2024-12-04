<template>
  <div>
    <app-head></app-head>
    <app-body>
      <div class="user-info-container">
        <div class="user-info-details">
          <el-image
              style="width: 120px; height: 120px;border-radius: 10px;"
              :src="item.image"
              fit="contain"></el-image>
          <div class="user-info-details-text">
            <div class="user-info-details-text-nickname">{{ item.title }}</div>
            <div class="user-info-details-text-time">传承人：{{ item.inheritorName }}</div>
            <div class="user-info-details-text-time">发源地：{{ item.area }}</div>
            <div class="user-info-details-text-time">发源时间：{{ item.originTime }}</div>
            <div class="user-info-details-text-time">项目分类：{{item.classifyName}}</div>
          </div>
        </div>
        <div class="details-header-buy" style="'width:150px;">

          <el-button type="primary" plain @click="favoriteButton()">{{item.collectId?'取消收藏':'加入收藏'}}</el-button>
        </div>
      </div>

      <div class="user-info-container2">
        <div id="video-player">
          <video id="video" controls autoplay :poster="item.image">
            <source src="" type="video/mp4">
          </video>
        </div>
      </div>
      <div class="idle-details-container">
        <div class="details-info">
          <div class="details-info-title">项目简介:</div>
          <div class="details-info-main" v-html="item.intro">
            {{ item.intro }}
          </div>
        </div>
      </div>
      <app-foot></app-foot>
    </app-body>
  </div>
</template>

<script>
import AppHead from '../../components/common/AppHeader.vue';
import AppBody from '../../components/common/AppPageBody.vue';
import AppFoot from '../../components/common/AppFoot.vue';
import { getProjectById, getOneByProjectIdAndUserId } from '@/api';

export default {
  name: 'idle-details',
  components: {
    AppHead,
    AppBody,
    AppFoot
  },
  data() {
    return {
      id:"",
      item: {},
      isFavorite: false
    };
  },
  created() {
    this.id = this.$route.query.id;
    this.getInit();
  },
  methods: {
    getInit() {
      getProjectById(this.id).then(res => {
        this.item = res.data
        document.getElementById("video").src = this.item.videoPath;
      });
    },
    favoriteButton(){
      getOneByProjectIdAndUserId({
        userId: localStorage.getItem('username'),
        projectId: this.item.id
      }).then(res => {
        this.$message({
              message: res.message,
              type: 'success'
        });
        this.getInit();
      });
    }
  }
};
</script>
<style scoped>
.user-info-container2 {
  width: 100%;
  height: 500px;
  border-bottom: 15px solid #f6f6f6;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.idle-details-container {
  min-height: 85vh;
}
#video-player {
  text-align: center;
  width: 100%;
  height: 490px;
  background-color: #000;
  position: relative;
}

#video-player video {
  width: 100%;
  height: 100%;
  object-fit: contain;
}

#screenshot-button {
  position: absolute;
  bottom: 250px;
  right: 1px;
  background-color: #4CAF50;
  color: #fff;
  padding: 6px 10px;
  border-radius: 19px;
  cursor: pointer;
  visibility: hidden;
}

#video-player:hover #screenshot-button{
  visibility: visible;
}
.user-info-container {
  width: 100%;
  height: 200px;
  border-bottom: 15px solid #f6f6f6;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.user-info-details {
  display: flex;
  height: 140px;
  align-items: center;
  margin: 20px 40px;
}

.user-info-details-text {
  margin-left: 20px;
}

.user-info-details-text-nickname {
  font-size: 26px;
  font-weight: 600;
  margin: 10px 0;
}

.user-info-details-text-time {
  font-size: 14px;
  margin-bottom: 10px;
}

.details-info {
  padding: 20px 50px;
}

.details-info-title {
  font-size: 22px;
  font-weight: 600;
  margin-bottom: 20px;

}

.details-info-main {
  font-size: 17px;
  color: #121212;
  line-height: 160%;
}
.details-header-buy {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 50px;
  width: 180px;
  margin-right: 30px;
}

</style>
