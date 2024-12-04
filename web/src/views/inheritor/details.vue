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
            <div class="user-info-details-text-nickname">{{ item.name }}</div>
            <div class="user-info-details-text-time">性别：{{ item.sex === 0?"女":"男" }}</div>
            <div class="user-info-details-text-time">民族：{{ item.nation }}</div>

            <div class="user-info-details-text-time">生日：{{ item.birthDate }}</div>
          </div>
        </div>
      </div>
      <div class="idle-details-container">
        <div class="details-info">
          <div class="details-info-title">传承人简介:</div>
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
import { getInheritorById } from '@/api';

export default {
  name: 'idle-details',
  components: {
    AppHead,
    AppBody,
    AppFoot
  },
  data() {
    return {
      item: {},
      id: ''
    };
  },
  created() {
    this.id = this.$route.query.id;
    this.getInit();
  },
  methods: {
    getInit() {
      getInheritorById(this.id).then(res => {
        this.item = res.data;
      });
    }
  }
};
</script>

<style scoped>

.idle-details-container {
  min-height: 85vh;
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

</style>
