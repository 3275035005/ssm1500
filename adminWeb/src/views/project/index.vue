<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>非遗项目信息</el-breadcrumb-item>
    </el-breadcrumb>


    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="项目名称">
        <el-input clearable size="small" v-model="formInline.title" placeholder="请输入项目名称"></el-input>
      </el-form-item>
      <el-form-item label="传承人">
        <el-select v-model="formInline.inheritorId" placeholder="请选择传承人">
          <el-option  label="无" value=""/>
          <el-option  v-for="item in selectList"  :label="item.name" :value="item.id" :key="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item label="项目分类">
        <el-select v-model="formInline.classifyId" placeholder="请选择项目分类">
          <el-option  v-for="item in classifyList"  :label="item.title" :value="item.id" :key="item.id"/>
        </el-select>
      </el-form-item>
      <el-form-item label="推荐项目">
        <el-select v-model="formInline.recommendState" placeholder="请选择推荐项目">
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
      <el-table-column align="center"  prop="image" label="项目封面">
        <template slot-scope="scope">
          <el-image
            style="width: 50px; height: 50px"
            :src="scope.row.image"></el-image>
        </template>
      </el-table-column>
      <el-table-column align="center"  prop="title" label="项目名称" >
      </el-table-column>
      <el-table-column align="center"  prop="classifyName" label="项目分类" >
      </el-table-column>
      <el-table-column align="center"  prop="inheritorName" label="传承人" >
      </el-table-column>
      <el-table-column align="center" prop="videoPath" label="观看视频">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="el-icon-search" @click="videoDetails(scope.row.videoPath)">查看视频</el-button>
        </template>
      </el-table-column>
      <el-table-column align="center"  prop="area" label="发源地" ></el-table-column>

      <el-table-column align="center"  prop="originTime" label="发源时间"></el-table-column>

      <el-table-column align="center"  prop="recommendState" label="推荐项目" >
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
        <el-form-item label="项目名称" prop="name">
          <el-input size="small" v-model="editForm.title" auto-complete="off" placeholder="请输入项目名称"></el-input>
        </el-form-item>
        <el-form-item label="发源地区" prop="press">
          <el-input size="small" v-model="editForm.area" auto-complete="off" placeholder="请输入发源地区"></el-input>
        </el-form-item>
        <el-form-item label="传承人">
          <el-select v-model="editForm.inheritorId" placeholder="请选择传承人">
            <el-option  label="无" value=""/>
            <el-option  v-for="item in selectList" :label="item.name" :value="item.id" :key="item.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="项目分类">
          <el-select v-model="editForm.classifyId" placeholder="请选择项目分类">
            <el-option  v-for="item in classifyList"  :label="item.title" :value="item.id" :key="item.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="发源时间" prop="originTime">
          <el-input size="small" v-model="editForm.originTime" auto-complete="off" placeholder="请输入发源时间"></el-input>
        </el-form-item>
        <el-form-item label="推荐项目" prop="recommendState">
          <el-select v-model="editForm.recommendState" placeholder="请选择推荐项目">
            <el-option  label="是" value="1"/>
            <el-option  label="否" value="0"/>
          </el-select>
        </el-form-item>
        <el-form-item label="项目封面" >
          <el-upload
            ref="upload1"
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
        <el-form-item label="上传视频" >
          <el-upload
            ref="upload2"
            action="http://localhost:8081/files/file"
            :on-success="fileHandleFilmUrlSuccess"
            accept=".mp4"
            multiple
            :limit="1">
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="简介">
          <tinymce :height="300" v-model="editForm.intro" v-if="editFormVisible"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click='closeDialog'>取消</el-button>
        <el-button size="small" type="primary" :loading="loading" class="title" @click="submitForm('editForm')">保存</el-button>
      </div>
    </el-dialog>


    <el-dialog
      title="非遗项目视频"
      :visible.sync="dialogVisible"
      width="50%"
      @close="handleClose"
    >
      <div id="video-player">
        <video id="video" controls autoplay>
          <source :src="videoPath" type="video/mp4">
        </video>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// 导入请求方法
import {list,save,deleteById,update } from '@/api/project'
import {getAll} from '@/api/inheritor'
import {getClassifyAll} from "@/api/classify";
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
      classifyList:[],
      // 分页参数
      pageparm: {
        currentPage: 1,
        pageSize: 10,
        total: 10
      },
      updateKey:true,
      typeKey:true,
      dialogVisible: false,
      videoPath: ''
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
    videoDetails(videoPath) {
      this.videoPath = videoPath
      this.dialogVisible = true
    },
    handleClose(){
      this.$router.go(0)
    },
    // 回去筛选框数据
    selectAll(){
      getAll().then(res=>{
        this.selectList = res.data;
      })
      getClassifyAll().then(res=>{
        this.classifyList = res.list;
      })
    },
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
      this.$refs.upload1.clearFiles();
    },
    fileHandleFilmUrlSuccess(response) {
      this.editForm.videoPath = response.row;
      this.$refs.upload2.clearFiles();
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
        if(row.inheritorId != null &&  row.inheritorId  !== '' &&  row.inheritorId  !== undefined){
          this.editForm.inheritorId = parseInt(row.inheritorId)
        }
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

</style>
