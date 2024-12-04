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
            </div>
          </div>
          <div class="details-header-buy" style="'width:150px;">
            <div style="color: red;font-size: 18px;font-weight: 600;">￥{{ item.price }}</div>
            <el-button type="danger" plain @click="buyButton()">立即购买</el-button>
          </div>
        </div>
        <div class="idle-details-container">
          <div class="details-info">
            <div class="details-info-title">非遗物品介绍:</div>
            <div class="details-info-main" v-html="item.content">

            </div>
          </div>
        </div>
        <app-foot></app-foot>
      </app-body>
    </div>
</template>

<script>
    import AppHead from '../../components/common/AppHeader.vue';
    import AppBody from '../../components/common/AppPageBody.vue'
    import AppFoot from '../../components/common/AppFoot.vue'

    import { getGoodsById, sendOrder } from '@/api';
    export default {
        name: "idle-details",
        components: {
            AppHead,
            AppBody,
            AppFoot
        },
        data() {
            return {
              item:{},
              userId:''
            };
        },
        created(){
            let id=this.$route.query.id;
          this.userId=  localStorage.getItem('username');
          if(this.userId == null || this.userId == undefined || this.userId == ''){
            this.$router.replace({path: '/login'});
          }else{
            // 获取产品信息
            getGoodsById(id).then(res=>{
              this.item= res.row;
            });
          }

        },
        methods: {
            buyButton(){
              // 获取产品信息
              sendOrder({
                userId: this.userId,
                goodsId: this.item.id,
                status: '0',
                price: this.item.price,
              }).then(res=>{
                this.$router.push({path: '/order', query: { id: res.data }});
              });

            },
        },
    }
</script>

<style scoped>

.idle-details-container {
  min-height: 85vh;
}
.details-header-buy {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 50px;
  width: 180px;
  margin-right: 30px;
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
