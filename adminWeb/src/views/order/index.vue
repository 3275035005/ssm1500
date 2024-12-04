<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>订单信息</el-breadcrumb-item>
    </el-breadcrumb>


    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="物品名称">
        <el-input clearable size="small" v-model="formInline.goodsName" placeholder="请输入物品名称"></el-input>
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="formInline.status" placeholder="请选择状态">
          <el-option  label="已发货" value="2"/>
          <el-option  label="未发货" value="1"/>
          <el-option  label="未支付" value="0"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
      </el-form-item>
    </el-form>


    <!--列表-->
    <el-table size="small" :data="list" highlight-current-row v-loading="loading" border element-loading-text="拼命加载中" style="width: 100%;">
      <el-table-column label="序号" type="index" width="80" align="center">
        <template slot-scope="scope">
          {{(formInline.page - 1) * formInline.limit + scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column align="center"  prop="image" label="物品封面">
        <template slot-scope="scope">
          <el-image
            style="width: 50px; height: 50px"
            :src="scope.row.image"></el-image>
        </template>
      </el-table-column>
      <el-table-column align="center"  prop="goodsName" label="物品名称"></el-table-column>
      <el-table-column align="center"  prop="status" label="订单状态" >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.status==2" type="success">已发货</el-tag>
          <el-tag v-if="scope.row.status==1" type="success">未发货</el-tag>
          <el-tag v-if="scope.row.status==0" type="warning">未支付</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center"  prop="phone" label="收货手机号" min-width="120">
      </el-table-column>
      <el-table-column align="center"  prop="name" label="收货姓名" min-width="120">
      </el-table-column>
      <el-table-column align="center"  prop="address" label="收货地址" min-width="120">
      </el-table-column>
      <el-table-column align="center"  prop="price" label="订单总价" min-width="120">
      </el-table-column>
      <el-table-column align="center"  prop="createTime" label="创建时间" min-width="120">
      </el-table-column>
      <el-table-column label="操作" width="160">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)" v-if="scope.row.status == '1'">发货订单</el-button>
          <el-button size="mini" type="danger" @click="deleteUser(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination>
  </div>
</template>

<script>
// 导入请求方法
import {list,deleteById,update } from '@/api/order'
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
      selectList:[],
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

      this.$confirm('确定要发货吗?', '信息', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'success'
      }).then(() => {

        update({
          id: row.id,
          status: '2'
        }).then(res=>{
          this.$message({
            type: 'success',
            message: '发货成功!'
          })
          this.getData(this.formInline)
        })
      }).catch(() => {

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
