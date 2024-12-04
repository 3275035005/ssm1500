<template>
  <div>
    <app-head></app-head>

    <app-body>
      <el-form ref="form" style="margin-top: 10px" :model="queryData" label-width="120px">
        <el-form-item label="公告标题">
          <el-input v-model="queryData.title" style="width: 300px;margin-right: 30px"></el-input>
          <el-button type="primary" @click="getData">搜索</el-button>
        </el-form-item>
      </el-form>
      <el-divider></el-divider>
      <div style="margin: 0 20px; height: 500px">
        <el-empty description="暂无数据" v-if="list.length===0"></el-empty>
        <el-row :gutter="30" v-else>
          <el-col :span="8" v-for="(item,index) in list">
            <el-card class="idle-card">
              <div slot="header" class="clearfix">
                <div style="font-size: 20px">{{ item.title }}</div>
                <div style="text-align: right">{{ item.createTime.substring(0, 11)}}</div>
              </div>
              <div class="text item">
                {{ item.content }}
              </div>
            </el-card>
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
import { noticePageQuery } from '@/api';

export default {
  name: 'index',
  components: {
    AppHead,
    AppBody,
    AppFoot
  },
  data() {
    return {
      list: [], //
      pageSize: 6, // 每页大小
      currentPage: 1, //当前页码
      totalItem: 1, // 数据总条数
      queryData: {
        title: ''
      } // 存储查询条件

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
      noticePageQuery(this.currentPage, this.pageSize, this.queryData).then(res => {
        this.list = res.list.records;
        this.totalItem = res.list.total;
        loading.close();
      });
    },

    // 当前页码
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getData();
    }
  }
};
</script>

<style scoped>
.idle-card {
  height: 230px;
  border: #eeeeee solid 1px;
  margin-bottom: 15px;
  cursor: pointer;
  overflow-y: auto;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.fenye {
  display: flex;
  justify-content: center;
  height: 60px;
  align-items: center;
}

</style>
