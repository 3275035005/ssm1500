<template>
    <div>
        <app-head></app-head>
        <app-body>
            <div class="order-page-container">
                <div class="idle-info-container" @click="toDetails(orderInfo.id)">
                    <el-image
                            style="width: 150px; height: 150px;"
                            :src="orderInfo.image"
                            fit="cover"></el-image>
                    <div class="idle-info-title">买到的：{{orderInfo.goodsName}}</div>
                    <div class="idle-info-price">￥{{orderInfo.price}}</div>

                </div>
                <div class="address-container" :style="orderInfo?'cursor: pointer;':''">
                    <div class="address-detials">收货地址: {{orderInfo.address}}</div>
                    <el-button v-if="!orderInfo.address" @click.stop="selectAddressDialog" type="primary" plain>选择收货地址</el-button>
                </div>
                <el-dialog
                        title="选择地址"
                        :visible.sync="addressDialogVisible"
                        width="800px">
                    <el-table
                            stripe
                            empty-text="无地址信息，请先在个人中心添加地址"
                            :data="addressData"
                            style="width: 100%">
                        <el-table-column
                                prop="name"
                                label="收货人姓名"
                                width="120">
                        </el-table-column>
                        <el-table-column
                                prop="phone"
                                label="手机号"
                                width="140">
                        </el-table-column>
                        <el-table-column
                                prop="address"
                                label="地址">
                        </el-table-column>
                        <el-table-column label=" " width="120">
                            <template slot-scope="scope">
                                <el-button
                                        size="mini"
                                        @click="selectAddress(scope.row)">选择
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-dialog>
                <div class="order-info-container">
                  <div class="order-info-title">订单号：{{orderInfo.orderNo}}</div>
                    <div class="order-info-title">订单状态：{{orderStatus[parseInt(orderInfo.status)]}}</div>
                    <div class="order-info-item">创建时间：{{orderInfo.createTime}}
                    </div>
                </div>
                <div class="menu">
                    <el-button v-if="orderInfo.status=='0'" type="primary" plain @click="changeOrderStatus()">立即支付</el-button>
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
  getAddress, getOrderById, updateOrder
} from '@/api';
    export default {
        name: "order",
        components: {
            AppHead,
            AppBody,
            AppFoot
        },
        data() {
            return {
                addressDialogVisible:false,
                addressData: [],
                orderStatus: ['未支付', '未发货', '已发货'],
                orderInfo: {},
                addressInfo: {
                    name: '',
                    phone: '',
                    address: ''
                },
                userId:'',
                orderId:''
            };
        },
        created() {
          this.userId = localStorage.getItem('username');
          this.orderId=this.$route.query.id;
          this.getOrderById();
        },
        methods: {
          getOrderById(){
            getOrderById(this.orderId).then(res => {
              if (res.code === 200) {
                this.orderInfo = res.row
              }
            });
          },
          getAddress(){
            getAddress(this.userId).then(res => {
              if (res.code === 200) {
                this.addressData = res.list
              }
            });
          },


            toDetails(id) {
                this.$router.replace({path: 'details', query: {id: id}});
            },
            selectAddressDialog(){
              this.addressDialogVisible=true;
              this.getAddress();
            },
            selectAddress(item){
                this.addressDialogVisible=false;
                this.orderInfo.address=item.address;
              updateOrder({
                id: this.orderInfo.id,
                address: item.address,
                name: item.name,
                phone: item.phone
              }).then(res => {
              });
            },
            changeOrderStatus() {
              if(!this.orderInfo.address){
                this.$message.error('请选择地址！')
              }else{
                this.$confirm('订单支付，是否确认订单支付', '支付订单', {
                  confirmButtonText: '支付',
                  cancelButtonText: '取消',
                  type: 'warning'
                }).then(() => {
                  // updateOrder({
                  //   id: this.orderInfo.id,
                  //   status: '1'
                  // }).then(res => {
                  //   if(res.code === 200) {
                  //     this.$message.success('支付成功');
                  //   }
                  //   this.getOrderById();
                  // });
                  location.href = 'http://localhost:8081/alipay?orderNo='+this.orderInfo.orderNo
                })
              }


            },
        }

    }
</script>

<style scoped>
    .order-page-container {
        min-height: 85vh;
    }

    .idle-info-container {
        width: 100%;
        display: flex;
        border-bottom: 20px solid #f6f6f6;
        padding: 20px;
        cursor: pointer;
    }

    .idle-info-title {
        font-size: 18px;
        font-weight: 600;
        max-width: 750px;
        margin-left: 10px;
    }

    .idle-info-price {
        font-size: 18px;
        color: red;
        margin-left: 10px;
    }

    .address-container {
        min-height: 60px;
        padding: 20px;
        border-bottom: 20px solid #f6f6f6;

    }

    .address-title {
        font-size: 18px;
        font-weight: 600;
        margin-bottom: 10px;
    }

    .address-detials {
        font-size: 16px;
        color: #444444;
    }

    .order-info-container {
        padding: 20px;
    }

    .order-info-item {
        margin: 10px 0;
        font-size: 14px;
        color: #444444;
    }

    .menu {
        margin-left: 20px;
    }
</style>
