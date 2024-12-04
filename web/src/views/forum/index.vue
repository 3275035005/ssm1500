<template>
  <div>
    <app-head></app-head>
    <app-body>
      <el-form ref="form" style="margin-top: 10px" :model="queryData" label-width="120px">
        <el-form-item label="论坛标题">
          <el-input v-model="queryData.title" style="width: 300px;margin-right: 30px"></el-input>
          <el-button type="primary" @click="getData">搜索</el-button>
          <el-button type="primary" @click="sendForumData">发布论坛</el-button>
        </el-form-item>
      </el-form>
      <el-divider></el-divider>
      <div style="margin-left: 40px; margin-top: 20px">
        <div v-for="item in list" :key="item.id" style="margin: 10px">
          <el-card class="box-card" style="height: 280px; width: 900px">
            <h2 @click="toDetails(item.id)" style="text-decoration: underline">{{item.title}}</h2>
            <div style="height: 120px">
              {{item.content}}
            </div>
            <div style="margin-top: 30px">
              <span><i style="margin-right: 30px " class="el-icon-user-solid">&nbsp;{{item.name}}</i>  <i class="el-icon-date">&nbsp;  {{item.createTime}}</i></span>
            </div>

          </el-card>
        </div>

      </div>

      <!-- 编辑界面 -->
      <el-dialog
          title="发布论坛"
          :visible.sync="editFormVisible"
          width="40%"
          @click='closeDialog("edit")'>
        <el-form label-width="120px" ref="editForm" :model="data">
          <el-form-item label="论坛标题" prop="title">
            <el-input size="small" v-model="data.title" auto-complete="off" placeholder="请输入论坛标题"></el-input>
          </el-form-item>
          <el-form-item label="论坛内容" prop="content">
            <el-input
                type="textarea"
                :rows="3"
                placeholder="请输入论坛内容"
                v-model="data.content"
                maxlength="200"
                show-word-limit>
            </el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button size="small" @click='closeDialog'>关闭</el-button>
          <el-button size="small" type="primary" class="title" @click="submitForm">保存</el-button>
        </div>
      </el-dialog>

      <app-foot>
      </app-foot>
    </app-body>
  </div>
</template>

<script>
import AppHead from '../../components/common/AppHeader.vue';
import AppBody from '../../components/common/AppPageBody.vue';
import AppFoot from '../../components/common/AppFoot.vue';
import { getForumAll, sendForum } from '@/api';

export default {
  name: 'index',
  components: {
    AppHead,
    AppBody,
    AppFoot
  },
  data() {
    return {
      queryData:{},
      data:{},
      list: [],
      editFormVisible: false
    };
  },
  created() {
    this.getData();
  },
  methods: {

    // 发布论坛执行了
    sendForumData(){
      this.editFormVisible = true;
      this.data = {};
    },
    // 关闭编辑、增加弹出框
    closeDialog() {
      this.editFormVisible = false
    },
    // 发布论坛成功
    submitForm(){
      this.data.uid = localStorage.getItem('username');
      sendForum(this.data).then(res=>{
        this.getData();
        this.editFormVisible = false
        this.$message.success('发布论坛成功！');
      })
    },
    // 分页条件查询
    getData() {
      const loading = this.$loading({
        lock: true,
        text: '加载数据中',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0)'
      });
      getForumAll().then(res => {
        this.list = res.data;
        console.log(this.list)
        loading.close();
      });

    },
    // 跳转页码详情
    toDetails(id) {
      console.log("执行了")
      this.$router.push({ path: '/fdetails', query: { id: id } });
    }
  }
};
</script>
<style scoped>
.box-card {
  width: 480px;
}
</style>
