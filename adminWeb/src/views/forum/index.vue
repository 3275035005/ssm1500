<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>论坛数据信息</el-breadcrumb-item>
    </el-breadcrumb>

    <div class="role-menu-box">
      <el-row :gutter="30">
        <el-col :span="11">
          <!-- 搜索筛选 -->
          <el-form :inline="true" :model="formInline" class="user-search">

            <el-form-item label="发布用户">
              <el-select v-model="formInline.uid" placeholder="请选择发布论坛用户">
                <el-option  v-for="item in selectList"  :label="item.name" :value="item.id" :key="item.id"/>
              </el-select>
            </el-form-item>
            <el-form-item label="论坛标题">
              <el-input clearable size="small" v-model="formInline.title" placeholder="请输入论坛标题"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
            </el-form-item>
          </el-form>


          <!--列表-->
          <el-table size="small" :data="list"
                    highlight-current-row
                    v-loading="loading"
                    border element-loading-text="拼命加载中"
                    style="width: 100%;"  @row-click="rowClick">
            <el-table-column label="序号" type="index" width="80" align="center">
              <template slot-scope="scope">
                {{(formInline.page - 1) * formInline.limit + scope.$index + 1}}
              </template>
            </el-table-column>
            <el-table-column align="center"  prop="name" label="发布用户" >
            </el-table-column>
            <el-table-column align="center"  prop="title" label="论坛标题" >
            </el-table-column>

            <el-table-column align="center"  prop="content" label="论坛内容" >
              <template slot-scope="scope">
                <el-popover trigger="hover" placement="top">
                  <p>{{scope.row.content}}</p>
                  <div slot="reference" class="name-wrapper">
                    {{scope.row.content.substring(0, 10)}}...
                  </div>
                </el-popover>
              </template>
            </el-table-column>

            <el-table-column align="center"  prop="createTime" label="发布时间">
            </el-table-column>
            <el-table-column align="center"  label="操作" width="80">
              <template slot-scope="scope">
                <el-button size="mini" type="danger" @click="deleteUser(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <!-- 分页组件 -->
          <Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination>
        </el-col>
        <el-col :span="13">
          <el-card class="box-card" shadow="never" style="margin-top: 80px">
            <div slot="header" >
              <span>最新评论</span>
            </div>
            <!--列表-->
            <el-table size="small" :data="checkList" highlight-current-row v-loading="loading" border element-loading-text="拼命加载中" style="width: 100%;">
              <el-table-column align="center"  prop="uname" label="留言用户" >
              </el-table-column>
              <el-table-column align="center"  prop="content" label="留言内容" >
                <template slot-scope="scope">
                  <el-popover trigger="hover" placement="top">
                    <p>{{scope.row.content}}</p>
                    <div slot="reference" class="name-wrapper">
                      {{scope.row.content.substring(0, 10)}}...
                    </div>
                  </el-popover>
                </template>
              </el-table-column>
              <el-table-column align="center"  prop="createTime" label="留言时间" min-width="120">
              </el-table-column>
              <el-table-column align="center"  prop="fname" label="回复用户" >
              </el-table-column>
              <el-table-column align="center"  prop="fcontent" label="回复内容" >
              </el-table-column>
              <el-table-column align="center"  label="操作" width="160">
                <template slot-scope="scope">
                  <el-button size="mini" type="danger" @click="deleteForumComment(scope.row)">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-card>
        </el-col>
      </el-row>
    </div>



  </div>
</template>

<script>
// 导入请求方法
import {list,save,deleteById,update } from '@/api/forum'
import {deleteForumCommentById, getListByForumId } from '@/api/forumComment'
import {getUserAll } from '@/api/user'
import Pagination from '../../components/Pagination'
export default {
  // 注册组件
  components: {
    Pagination
  },
  data() {
    return {
      loading: false, //是显示加载
      title: '',
      // 编辑与添加
      editForm: {},
      // rules表单验证
      rules: {
      },
      editFormVisible: false,
      // 请求数据参数
      formInline: {
        page: 1,
        limit: 10,
      },
      fileList:[],
      //用户数据
      list: [],
      checkList:[],
      selectList:[],
      checkId:"",
      // 分页参数
      pageparm: {
        currentPage: 1,
        pageSize: 10,
        total: 10
      },
    }
  },
  /**
   * 创建完毕
   */
  created() {
   //获取信息
   this.getData(this.formInline)
    this.selectAll();
  },

  /**
   * 里面的方法只有被调用才会执行
   */
  methods: {
    // 用户筛选框数据
    selectAll(){
      getUserAll().then(res=>{
        this.selectList = res.row;
      })
    },
    // 获取点击事件某行数据
    rowClick(row, column, event){
      this.checkId = row.id
      // 查询当前id的绑定设备
      getListByForumId(this.checkId).then(res=>{
        this.checkList = res.list;
      })
    },
    // 获取数据方法
    getData(parameter) {
      this.loading = true
      // 获取用户列表
      list(parameter).then(res => {
        this.loading = false
        this.list = res.list.records
        // 分页赋值
        this.pageparm.currentPage = this.formInline.page
        this.pageparm.pageSize = this.formInline.limit
        this.pageparm.total = res.list.total
      })
    },
    // 分页插件事件
    callFather(parm) {
      this.formInline.page = parm.currentPage
      this.formInline.limit = parm.pageSize
      this.getData(this.formInline)
    },

    //搜索事件
    search() {
      this.getData(this.formInline)
    },
    deleteForumComment(row) {
      this.$confirm('确定要删除吗?', '信息', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        // 删除
        deleteForumCommentById(row.id).then(res => {
          this.$message({
            type: 'success',
            message: '数据已删除!'
          })
          this.rowClick({id: this.checkId})
        }).catch(err => {
          this.loading = false
          this.$message.error('数据删除失败，请稍后再试！')
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除！'
        })
      })
    },
    // 删除
    deleteUser(row) {
      this.$confirm('确定要删除吗?', '信息', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {

        // 删除
        deleteById(row.id).then(res => {
          this.$message({
            type: 'success',
            message: '数据已删除!'
          })
          this.getData(this.formInline)
        }).catch(err => {
          this.loading = false
          this.$message.error('数据删除失败，请稍后再试！')
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除！'
        })
      })
    },
  }
}
</script>

<style scoped>
.user-search {
  margin-top: 20px;
}

.role-menu-box {
  height: calc(100% - 100px);
}
.el-row, .el-col {
  height: 100%;
}
</style>
