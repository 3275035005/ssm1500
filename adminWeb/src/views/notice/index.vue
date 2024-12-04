<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>公告信息</el-breadcrumb-item>
    </el-breadcrumb>


    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="标题">
        <el-input clearable size="small" v-model="formInline.title" placeholder="请输入标题"></el-input>
      </el-form-item>
      <el-form-item label="公告类型">
        <el-select v-model="formInline.type" placeholder="请选择公告类型">
          <el-option  label="公告" value="1"/>
          <el-option  label="政策通知" value="2"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
        <el-button size="small" type="primary" icon="el-icon-plus" @click="handleEdit()">添加</el-button>
      </el-form-item>
    </el-form>


    <!--列表-->
    <el-table size="small" :data="list" highlight-current-row v-loading="loading" border element-loading-text="拼命加载中" style="width: 100%;">
      <el-table-column label="序号" type="index" width="80" align="center">
        <template slot-scope="scope">
            {{(formInline.page - 1) * formInline.limit + scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column align="center"  prop="title" label="标题" >
      </el-table-column>
      <el-table-column align="center"  prop="content" label="内容" >
      </el-table-column>

      <el-table-column align="center"  prop="recommendState" label="公告类型" >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.type==1" type="success">公告</el-tag>
          <el-tag v-if="scope.row.type==2" type="warning">政策通知</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center"  prop="createTime" label="创建时间" min-width="120">
      </el-table-column>
      <el-table-column label="操作" width="160">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="deleteUser(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination>


    <!-- 编辑界面 -->
    <el-dialog
    :title="title"
    :visible.sync="editFormVisible"
    width="40%"
    @click='closeDialog("edit")'>
      <el-form label-width="120px" ref="editForm" :model="editForm" :rules="rules">
        <el-form-item label="标题" prop="title">
          <el-input size="small" v-model="editForm.title" auto-complete="off" placeholder="请输入标题"></el-input>
        </el-form-item>
        <el-form-item label="公告内容" prop="content">
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入公告内容"
            v-model="editForm.content">
          </el-input>
        </el-form-item>
        <el-form-item label="公告类型" prop="type">
          <el-select v-model="editForm.type" placeholder="请选择公告类型">
            <el-option  label="公告" value="1"/>
            <el-option  label="政策通知" value="2"/>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click='closeDialog'>取消</el-button>
        <el-button size="small" type="primary" :loading="loading" class="title" @click="submitForm('editForm')">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// 导入请求方法
import {list,save,deleteById,update } from '@/api/notice'
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
      // 分页参数
      pageparm: {
        currentPage: 1,
        pageSize: 10,
        total: 10
      },
      updateKey:true,
      typeKey:true
    }
  },
  /**
   * 创建完毕
   */
  created() {
   //获取信息
   this.getData(this.formInline)
  },

  /**
   * 里面的方法只有被调用才会执行
   */
  methods: {
    // 获取数据方法
    getData(parameter) {
      this.loading = true
      // 获取用户列表
      list(parameter).then(res => {
        console.log(res);
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
    // 上传头像成功
    fileHandleSuccess(response) {
      this.editForm.image = response.row;
    },
    //搜索事件
    search() {
      this.getData(this.formInline)
    },
    //显示编辑界面
    handleEdit: function(row) {
      if(row){
        this.title = '修改信息';
        this.editForm = row;
      }else{
        this.title = '添加信息';
        this.editForm = {
          image:""
        }
        this.updateKey = false;
      }
      this.editFormVisible = true;

    },

    // 编辑、添加提交方法
    submitForm(editData) {
      this.$refs[editData].validate(valid => {
        if (valid) {
          if(this.editForm.id){
            update(this.editForm).then(res=>{
              this.$message({
                type: 'success',
                message: '修改成功!'
              })
              this.editFormVisible = false;
              this.getData(this.formInline)
            })
          }else{
            save(this.editForm).then(res=>{
              this.$message({
                type: 'success',
                message: '添加成功!'
              })
              this.editFormVisible = false;
              this.getData(this.formInline)
            })
          }

        } else {
          return false
        }
      })
    },
    // 关闭编辑、增加弹出框
    closeDialog() {
        this.editFormVisible = false

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
</style>
