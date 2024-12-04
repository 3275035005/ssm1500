<template>
  <el-form label-width="120px" ref="editForm" :model="editForm" :rules="rules">
    <el-form-item label="管理员账号" prop="username">
      <el-input :disabled="true" size="small" v-model="editForm.username" auto-complete="off" placeholder="请输入管理员账号"></el-input>
    </el-form-item>
    <el-form-item label="管理员姓名" prop="name">
      <el-input size="small" v-model="editForm.name" auto-complete="off" placeholder="请输入管理员姓名"></el-input>
    </el-form-item>
    <el-form-item label="管理员密码" prop="password">
      <el-input size="small" v-model="editForm.password" type="password" auto-complete="off" placeholder="请输入管理员密码"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button size="small" type="primary"  class="title" @click="submitForm">修改</el-button>
    </el-form-item>
  </el-form>

</template>

<script>
import {getInfo, adminUpdate} from "@/api/user";

export default {
  name: "personage",

  data() {
    return {
      editForm:{},
      rules: {
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      },
    }
  },

  created() {
    this.getInfoData();
  },
  methods:{
    getInfoData(){
      getInfo(localStorage.getItem('username')).then(res=>{
        this.editForm = res.row;
      })
    },
    submitForm(){
      adminUpdate(this.editForm).then(res=>{
        this.$message({
          type: 'success',
          message: '修改成功!'
        })
        this.getInfoData()
      })
    }

  }
}


</script>

<style scoped>

</style>
