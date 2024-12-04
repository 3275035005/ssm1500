<template>
  <div>
    <app-head></app-head>

    <app-body>
      <el-form ref="form" style="margin-top: 10px" :model="queryData" label-width="120px">
        <el-form-item label="非遗物品名称">
          <el-input v-model="queryData.title" style="width: 300px;margin-right: 30px"></el-input>
          <el-button type="primary" @click="getData">搜索</el-button>
        </el-form-item>
      </el-form>
      <el-divider></el-divider>
      <div style="margin: 0 20px; height: 500px">
        <el-empty description="暂无匹配的非遗物品" v-if="list.length===0"></el-empty>
        <el-row :gutter="30" v-else>
          <el-col :span="6" v-for="(idle,index) in list">
            <div class="idle-card" @click="toDetails(idle.id)">
              <el-image
                  style="width: 100%; height: 160px"
                  :src="idle.image"
                  fit="contain">
                <div slot="error" class="image-slot">
                  <i class="el-icon-picture-outline">无图</i>
                </div>
              </el-image>
              <div class="idle-title">
                {{ idle.title }}
              </div>
              <el-row style="margin: 5px 10px;">
                <el-col :span="12">
                  <div class="idle-prive">￥{{ idle.price }}</div>
                </el-col>
              </el-row>
            </div>
          </el-col>
        </el-row>
      </div>
      <div class="fenye">
        <el-pagination
            background
            @current-change="handleCurrentChange"
            :current-page.sync="currentPage"
            :page-size="pageSize"
            layout="prev, pager, next, jumper"
            :total="totalItem">
        </el-pagination>
      </div>

      <app-foot></app-foot>
    </app-body>
  </div>
</template>

<script>
import AppHead from '../../components/common/AppHeader.vue';
import AppBody from '../../components/common/AppPageBody.vue';
import AppFoot from '../../components/common/AppFoot.vue';

import { getPageQuery } from '@/api';
export default {
  name: 'index',
  components: {
    AppHead,
    AppBody,
    AppFoot
  },
  data() {
    return {
      list: [],
      pageSize: 8, // 每页大小
      currentPage: 1, //当前页码
      totalItem: 1, // 数据总条数
      queryData:{
        name:'',
        status:'1'
      }, // 存储查询条件
    };
  },
  created() {
    this.getData();
  },
  methods: {

    // 分页条件查询
    getData() {
      const loading = this.$loading({
        lock: true,
        text: '加载数据中',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0)'
      });
      getPageQuery(this.currentPage, this.pageSize, this.queryData).then(res=>{
        this.list = res.list.records
        this.totalItem= res.list.total
        loading.close();
      })
    },

    // 当前页码
    handleCurrentChange(val) {
      this.currentPage = val;
    },

    // 跳转页码详情
    toDetails(id) {
      console.log(id);
      this.$router.push({ path: '/gdetails', query: { id: id } });
    }
  }
};
</script>

<style scoped>

.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}


.idle-card {
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
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin: 10px;
}

.idle-prive {
  font-size: 16px;
  color: red;
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
