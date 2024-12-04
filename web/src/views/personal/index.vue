<template>
  <div>
    <app-head></app-head>
    <app-body>
      <div v-show="!eidtAddress">
        <div class="user-info-container">
          <div class="user-info-container">
            <div class="user-info-details">
              <el-image
                  style="width: 120px; height: 120px;border-radius: 10px;"
                  :src="userInfo.image"
                  fit="contain"></el-image>
              <div class="user-info-details-text">
                <div class="user-info-details-text-nickname">{{ userInfo.name }}</div>
                <div class="user-info-details-text-time">年龄：{{ userInfo.age }}</div>
                <div class="user-info-details-text-time">性别：{{ userInfo.sex === 0?"女":"男" }}</div>
                <div class="user-info-details-text-edit">
                  <el-button type="primary" plain @click="userInfoDialogVisible = true">编辑个人信息</el-button>
                </div>
              </div>
            </div>
          </div>
          <div style="margin-right: 50px">
            <el-button type="primary" plain @click="eidtAddress=true">编辑收货地址</el-button>
          </div>
        </div>
        <div class="idle-container">
          <el-tabs v-model="activeName">
            <el-tab-pane label="我的收藏" name="1"></el-tab-pane>
            <el-tab-pane label="购买记录" name="2"></el-tab-pane>
          </el-tabs>
          <div class="idle-container-list">
            <div v-if="activeName == 1" v-for="item in favoriteList" class="idle-container-list-item">
              <div class="idle-container-list-item-detile" @click="toDetails(item.projectId)">
                <el-image
                    style="width: 100px; height: 100px;"
                    :src="item.image"
                    fit="cover">
                  <div slot="error" class="image-slot">
                    <i class="el-icon-picture-outline">无图</i>
                  </div>
                </el-image>
                <div class="idle-container-list-item-text">
                  <div class="idle-container-list-title">
                    {{item.projectName}}
                  </div>
                  <div class="idle-container-list-idle-details">
                    项目传承人：{{item.inheritorName}}
                  </div>
                  <div class="idle-container-list-idle-details">
                    项目分类：{{item.classifyName}}
                  </div>
                  <div class="idle-container-list-idle-time">{{item.createTime}}</div>

                  <div class="idle-item-foot">
                    <div  class="idle-prive">

                    </div>
                    <el-button type="danger" size="mini" slot="reference"
                               plain @click.stop="favoriteButton(item.projectId)">取消收藏
                    </el-button>
                  </div>
                </div>
              </div>
            </div>

            <div v-if="activeName == 2" v-for="item in orderList" class="idle-container-list-item">
              <div class="idle-container-list-item-detile" @click="toDetails(item.orderNo)">
                <el-image
                    style="width: 100px; height: 100px;"
                    :src="item.image"
                    fit="cover">
                  <div slot="error" class="image-slot">
                    <i class="el-icon-picture-outline">无图</i>
                  </div>
                </el-image>
                <div class="idle-container-list-item-text">
                  <div class="idle-container-list-title">
                    {{item.goodsName}}
                  </div>
                  <div class="idle-container-list-idle-time">{{item.createTime}}</div>

                  <div class="idle-item-foot">
                    <div class="idle-prive">￥{{item.price}}
                      {{orderStatus[parseInt(item.status)]}}
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <el-dialog
          title="编辑个人信息"
          :visible.sync="userInfoDialogVisible"
          width="50%">
        <el-form label-width="120px" ref="editForm" :model="updateInfo">
          <el-form-item label="账号" prop="press">
            <el-input :disabled="true" size="small" v-model="updateInfo.username" auto-complete="off" placeholder="请输入账号"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="press">
            <el-input type="password" size="small" v-model="updateInfo.password" auto-complete="off" placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="name">
            <el-input size="small" v-model="updateInfo.name" auto-complete="off" placeholder="请输入姓名"></el-input>
          </el-form-item>
          <el-form-item label="年龄" prop="age">
            <el-input-number v-model="updateInfo.age":min="1" :max="100"></el-input-number>
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-select v-model="updateInfo.sex" placeholder="请选择性别">
              <el-option  label="男" value="1"/>
              <el-option  label="女" value="0"/>
            </el-select>
          </el-form-item>
          <el-form-item label="头像" >
            <el-upload
                ref="upload"
                :file-list="fileList"
                action="http://localhost:8081/files/file"
                :on-success="fileHandleSuccess"
                accept="image/*"
                class="avatar-uploader"
            >
              <img  v-if="updateInfo.image" :src="updateInfo.image" class="avatar">
              <i  v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
          <el-form-item>
            <el-button size="small" type="primary" class="title" @click="update()">保存</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
      <div v-show="eidtAddress" class="address-container">
        <el-page-header class="address-container-back" @back="eidtAddress=false"
                        content="收货地址"></el-page-header>
        <div class="address-container-add">
          <div class="address-container-add-title">新增收货地址</div>
          <div class="address-container-add-item">
            <el-input placeholder="请输入收货人姓名" v-model="addressInfo.name" maxlength="10"
                      show-word-limit>
              <div slot="prepend">收货人姓名</div>
            </el-input>
          </div>
          <div class="address-container-add-item">
            <el-input placeholder="请输入收货人手机号" v-model="addressInfo.phone"
                      onkeyup="this.value= this.value.replace(/[^\d.]/g,'');" maxlength="11" show-word-limit>
              <div slot="prepend">您的手机号</div>
            </el-input>
          </div>
          <div class="address-container-add-item">
            <el-input placeholder="请输入收货详细地址" v-model="addressInfo.address"
                      maxlength="50" show-word-limit>
              <div slot="prepend">详细地址</div>
            </el-input>
          </div>
          <el-checkbox v-model="addressInfo.defaultFlag">设置为默认地址</el-checkbox>
          <el-button style="margin-left: 20px;" @click="saveAddress">保存</el-button>
        </div>
        <div class="address-container-list">
          <div style="color: #409EFF;font-size: 15px;padding-left: 10px;">已有收货地址</div>
          <el-table
              stripe
              :data="addressData"
              style="width: 100%">
            <el-table-column
                prop="name"
                label="收货人姓名"
                width="100">
            </el-table-column>
            <el-table-column
                prop="phone"
                label="手机号"
                width="120">
            </el-table-column>
            <el-table-column
                prop="address"
                label="地址"
                width="270">
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button
                    size="mini"
                    @click="handleEdit(scope.row)">编辑
                </el-button>
                <el-button
                    size="mini"
                    type="danger"
                    @click="handleDelete(scope.row)">删除
                </el-button>
              </template>
            </el-table-column>
            <el-table-column label="是否默认地址" width="110">
              <template slot-scope="scope">
                <el-button v-if="!scope.row.defaultFlag"
                           size="mini"
                           @click="handleSetDefault(scope.row)">设为默认
                </el-button>
                <div v-else style="padding-left: 10px;color: #409EFF;">默认地址</div>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
      <app-foot></app-foot>
    </app-body>
  </div>
</template>

<script>
import AppHead from '../../components/common/AppHeader.vue';
import AppBody from '../../components/common/AppPageBody.vue';
import AppFoot from '../../components/common/AppFoot.vue';
import {
  updateUser,
  getUserInfo,
  getAddress,
  updateAddress,
  addAddress,
  deleteAddress,
  getProjectByUserId,
  getOneByProjectIdAndUserId,
  getOrderByUserId
} from '@/api';
export default {
  name: 'personal',
  components: {
    AppHead,
    AppBody,
    AppFoot
  },
  data() {
    return {
      favoriteList:[],
      orderList:[],
      orderStatus:['未支付','未发货','已发货'],
      activeName: '1',
      userInfo: {
        image: '',
        name: '',
        account: '',
        password: '',
        age: 0,
        sex: "0"
      },
      fileList:[],
      updateInfo:{},
      userId:"",
      userInfoDialogVisible: false,

      // 地址信息
      eidtAddress: false,
      addressInfo: {},
      addressData: [],

    };
  },
  created() {
    this.userId = localStorage.getItem('username');
    this.getInfo();
    this.getAddress();
    this.getFavorite();
    this.getOrder();
  },
  methods: {
    // 通过用户id获取用户信息
    getInfo(){
      getUserInfo(this.userId).then(res => {
        if (res.code === 200) {
          this.userInfo = res.row;
          this.updateInfo = res.row;
        }
      });
    },
    getFavorite(){
      getProjectByUserId(this.userId).then(res => {
        if (res.code === 200) {
          this.favoriteList = res.data;
        }
      });
    },
    getOrder() {
      getOrderByUserId(this.userId).then(res => {
        if (res.code === 200) {
          this.orderList = res.data;
        }
      })
    },
    // 跳转商品详情和订单详情
    toDetails(id) {
      if (this.activeName === '2') {
        this.$router.push({path: '/order', query: {id: id}});
      } else {
        this.$router.push({path: '/ipdetails', query: {id: id}});
      }
    },

    getAddress(){
      getAddress(this.userId).then(res => {
        if (res.code === 200) {
          this.addressData = res.list
        }
      });
    },

    favoriteButton(id){
      getOneByProjectIdAndUserId({
        userId: localStorage.getItem('username'),
        projectId: id
      }).then(res => {
        this.$message({
          message: res.message,
          type: 'success'
        });
        this.getFavorite();
      });
    },


    // 编辑地址
    handleEdit( row) {
      this.addressInfo = row;
    },

    // 删除地址
    handleDelete(row) {
      this.$confirm('是否确定删除该地址?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteAddress(row.id).then(res=>{
          if (res.code == 200) {
            this.$message({
              message: '修改成功！',
              type: 'success'
            });
            this.getAddress();
          } else {
            this.$message.error('系统异常，修改失败！');
          }
        })

      }).catch(() => {
      });

    },

    // 设置默认地址
    handleSetDefault( row) {
      row.defaultFlag = true;
      this.updateAddress(row);
    },
    updateAddress(row){
      updateAddress(row).then(res=>{
        if (res.code == 200) {
          this.$message({
            message: '修改成功！',
            type: 'success'
          });
          this.getAddress();
          this.addressInfo = {}
        } else {
          this.$message.error('系统异常，修改失败！');
        }
      })
    },
    saveAddress(){
      if(this.addressInfo.id){
        updateAddress(this.addressInfo).then(res=>{
          if (res.code == 200) {
            this.$message({
              message: '修改成功！',
              type: 'success'
            });
            this.getAddress();
            this.addressInfo = {}
          } else {
            this.$message.error('系统异常，修改失败！');
          }
        })
      }else{
        this.addressInfo.userId = this.userId;
        addAddress(this.addressInfo).then(res=>{
          if (res.code == 200) {
            this.$message({
              message: '添加成功！',
              type: 'success'
            });
            this.getAddress();
            this.addressInfo = {}
          } else {
            this.$message.error('系统异常，添加失败！');
          }
        })
      }
    },

    // 上传头像成功
    fileHandleSuccess(response) {
      this.updateInfo.image = response.row;
    },


    // 修改个人信息
    update() {
      updateUser(this.updateInfo).then(res => {
        if (res.code == 200) {
          this.$message({
            message: '修改成功！',
            type: 'success'
          });
          this.getInfo();

        } else {
          this.$message.error('系统异常，修改失败！');
        }
      }).catch(() => {
        this.$message.error('网络异常！');
      });
    },
  }
};
</script>

<style scoped>
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

.address-container {
  padding: 10px 20px;
}

.address-container-back {
  margin-bottom: 10px;
}

.address-container-add-title {
  font-size: 15px;
  color: #409EFF;
  padding: 10px;
}

.address-container-add-item {
  margin-bottom: 20px;
}

.demonstration {
  color: #666666;
  font-size: 14px;
  padding: 10px;
}

.address-container-add {
  padding: 0 200px;
}

.address-container-list {
  padding: 30px 100px;
}
.idle-container {
  padding: 0 20px;
}

.idle-container-list {
  min-height: 55vh;
}

.idle-container-list-item {
  border-bottom: 1px solid #eeeeee;
  cursor: pointer;
}

.idle-container-list-item:last-child {
  border-bottom: none;
}

.idle-container-list-item-detile {
  height: 120px;
  display: flex;
  align-items: center;
}

.idle-container-list-item-text {
  margin-left: 10px;
  height: 100px;
  max-width: 800px;
}

.idle-container-list-title {
  font-weight: 600;
  font-size: 18px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.idle-container-list-idle-details {
  font-size: 14px;
  color: #555555;
  padding-top: 5px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.idle-container-list-idle-time {
  font-size: 13px;
  padding-top: 5px;
}

.idle-prive {
  font-size: 15px;
  padding-top: 5px;
  color: red;
}

.idle-item-foot {
  width: 800px;
  display: flex;
  justify-content: space-between;
}
</style>
