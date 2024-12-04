<template>
  <div>
    <app-head></app-head>

    <app-body>
      <div class="message-container" id="replyMessageLocation">
        <div>
          <el-empty description="暂无更多评论" v-if="list.length===0"></el-empty>
          <div v-else class="message-container-list" v-for="item in list">
            <div class="message-container-list-left">
              <el-image
                  style="width: 55px; height: 55px;border-radius: 5px;"
                  :src="item.image"
                  fit="contain"></el-image>
              <div class="message-container-list-text">
                <div class="message-nickname">{{ item.uname }}
                  {{
                    item.fname != null ? ' @' + item.fname + '：' +
                        item.fcontent.substring(0, 10) +
                        (item.fcontent.substring(0, 10).length > 10 ? '...' : '') : ''
                  }}
                </div>
                <div class="message-content">{{ item.content }}</div>
                <div class="message-time">{{ item.createTime }}</div>
              </div>
            </div>
            <div class="message-container-list-right">
              <el-button style="float: right;" plain @click="replyMessage(item)">回复</el-button>
            </div>
          </div>
        </div>

        <div style="margin: 10px 0 10px 0;  font-size: 20px;font-weight: 600;">
          <i class="el-icon-chat-dot-square">&nbsp;论坛评论</i>
        </div>
        <div class="message-send">
          <div v-if="isReply" style="padding-bottom: 10px;">
            <el-button type="info" @click="cancelReply">回复：{{ replyData.fcontent }} @{{ replyData.fname }}
              <i class="el-icon-close el-icon--right"></i></el-button>
          </div>
          <el-input
              type="textarea"
              :rows="5"
              placeholder="既然来了，那就留下些什么吧~ "
              v-model="messageContent"
              maxlength="200"
              show-word-limit>
          </el-input>
          <div class="message-send-button">
            <el-button plain @click="sendMessage">发送评论</el-button>
          </div>
        </div>
      </div>
      <app-foot>
      </app-foot>
    </app-body>
  </div>
</template>

<script>
import AppHead from '../../components/common/AppHeader.vue';
import AppBody from '../../components/common/AppPageBody.vue';
import AppFoot from '../../components/common/AppFoot.vue';
import { getForumById , sendForumComment} from '@/api';

export default {
  name: 'index',
  components: {
    AppHead,
    AppBody,
    AppFoot
  },
  data() {
    return {
      isReply: false,
      replyData: {},
      list: [],
      messageContent: '',
      id:"",
    };
  },
  created() {
    this.id = this.$route.query.id;
    this.getData();
  },
  methods: {

    // 发送留言
    sendMessage() {
      let content = this.messageContent.trim();

      if (content) {
        // 留言用户id
        this.replyData.uid =localStorage.getItem('username');
        // 留言内容
        this.replyData.content = content;
        this.replyData.forumId = this.id;
        sendForumComment(this.replyData).then(res =>{
          if(res.code === 200){
            this.$message.success('发布评论内容！');
            this.isReply = false;
            this.getData();
            this.messageContent = "";
          }else{
            this.$message.error('评论失败！');
          }

        })

      } else {
        this.$message.error('请输入评论内容！');
      }
    },

    cancelReply() {
      this.isReply = false;
      this.toUser = this.idleItemInfo.userId;
      this.toMessage = null;
      this.replyData.toUserNickname = '';
      this.replyData.toMessage = '';
    },
    // 留言回复
    replyMessage(item) {
      this.isReply = true; // 开启留言内容展示
      this.replyData.fname = item.uname; // 留言内容用户姓名
      this.replyData.fcontent = item.content;// 留言内容
      this.replyData.fid = item.uid; // 留言用户id
    },

    // 分页条件查询
    getData() {
      const loading = this.$loading({
        lock: true,
        text: '加载数据中',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0)'
      });
      getForumById(this.id).then(res => {
        this.list = res.data;
        loading.close();
      });
    }
  }
};
</script>

<style scoped>

.message-container {
  min-height: 100px;
  border-top: 10px solid #f6f6f6;
  padding: 20px;
}

.message-title {
  font-size: 20px;
  font-weight: 600;
  margin-bottom: 20px;
}

.message-send {
  min-height: 60px;
}

.message-send-button {
  margin-top: 10px;
  display: flex;
  justify-content: flex-end;
}

.message-container-list {
  min-height: 60px;
  border-top: 1px solid #eeeeee;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 0;
}

.message-container-list:first-child {
  border-top: none;
}

.message-container-list-left {
  width: 850px;
  display: flex;
}

.message-container-list-right {
  width: 100px;
}

.message-container-list-text {
  margin-left: 10px;
}

.message-nickname {
  font-weight: 600;
  font-size: 18px;
  padding-bottom: 5px;
}

.message-content {
  font-size: 16px;
  padding-bottom: 15px;
  color: #555555;
  width: 770px;
}

.message-time {
  font-size: 13px;
  color: #555555;
}


</style>
