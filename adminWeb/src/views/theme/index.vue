<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>主题信息</el-breadcrumb-item>
    </el-breadcrumb>


    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="主题名称">
        <el-input clearable size="small" v-model="formInline.title" placeholder="请输入主题名称"></el-input>
      </el-form-item>
      <el-form-item label="推荐项目">
        <el-select v-model="formInline.recommendState" placeholder="请选择推荐主题">
          <el-option  label="是" value="1"/>
          <el-option  label="否" value="0"/>
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
      <el-table-column align="center"  prop="image" label="主题封面">
        <template slot-scope="scope">
          <el-image
            style="width: 50px; height: 50px"
            :src="scope.row.image"></el-image>
        </template>
      </el-table-column>
      <el-table-column align="center"  prop="title" label="主题名称" >
      </el-table-column>

      <el-table-column align="center"  prop="recommendState" label="推荐主题" >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.recommendState==1" type="success">是</el-tag>
          <el-tag v-if="scope.row.recommendState==0" type="warning">否</el-tag>
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
        <el-form-item label="主题名称" prop="title">
          <el-input size="small" v-model="editForm.title" auto-complete="off" placeholder="请输入主题名称"></el-input>
        </el-form-item>

        <el-form-item label="推荐主题" prop="recommendState">
          <el-select v-model="editForm.recommendState" placeholder="请选择推荐主题">
            <el-option  label="是" value="1"/>
            <el-option  label="否" value="0"/>
          </el-select>
        </el-form-item>
        <el-form-item label="主题封面" >
          <el-upload
            ref="upload"
            :file-list="fileList"
            action="http://localhost:8081/files/file"
            :on-success="fileHandleSuccess"
            accept="image/*"
            class="avatar-uploader"
          >
            <img  v-if="editForm.image" :src="editForm.image" class="avatar">
            <i  v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="主题介绍">
          <tinymce :height="300" v-model="editForm.intro"/>
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
import {list,save,deleteById,update } from '@/api/theme'
import Pagination from '../../components/Pagination'
import Tinymce from '../../components/Tinymce'
export default {
  // 注册组件
  components: {
    Pagination,Tinymce
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
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
  border-style: dotted;
  margin: 10px 0;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
