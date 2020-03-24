<template>
  <el-card style="margin-bottom:20px;">
    <div slot="header" class="clearfix">
      <span>关于我</span>
    </div>

    <div class="user-profile">
      <div class="box-center">
        <img class="avatar" :src="'https://myshopsite.oss-cn-hangzhou.aliyuncs.com/avatar/6FFA22D5D7B1D817C9689C66BF6F4446.jpg'" width="100px" height="100px">
      </div>
      <div class="box-center">
        <div class="user-name text-center">潘宏伟</div>
        <div class="user-role text-center text-muted">努力！奋斗</div>
      </div>
    </div>

    <div class="user-bio">
      <div class="user-education user-bio-section">
        <div class="user-bio-section-header"><svg-icon icon-class="education" /><span>状态</span></div>
        <div class="user-bio-section-body">
          <div class="text-muted">
            最近在读《生活的哲学》
          </div>
        </div>
      </div>

      <div class="user-skills user-bio-section">
        <div class="user-bio-section-header"><svg-icon icon-class="skill" /><span>技能</span></div>
        <div class="user-bio-section-body">
          <div class="progress-item">
            <span>Java</span>
            <el-progress :percentage="100" status="success" />
          </div>
          <div class="progress-item">
            <span>Spring 开源框架</span>
            <el-progress :percentage="80" />
          </div>
          <div class="progress-item">
            <span>Vue</span>
            <el-progress :percentage="60" />
          </div>
          <div class="progress-item">
            <span>Linux</span>
            <el-progress :percentage="75" />
          </div>
        </div>
      </div>
    </div>
  </el-card>
</template>

<script>

export default {
  props: {
    user: {
      type: Object,
      default: () => {
        return {
          name: '',
          email: '',
          avatar: '',
          roles: ''
        }
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.box-center {
  margin: 0 auto;
  display: table;
}

.text-muted {
  color: #777;
}

.avatar {
  border-radius: 50px;
  border: 1px solid gray;
}

.user-profile {
  .user-name {
    font-weight: bold;
  }

  .box-center {
    padding-top: 10px;
  }

  .user-role {
    padding-top: 10px;
    font-weight: 400;
    font-size: 14px;
  }

  .box-social {
    padding-top: 30px;

    .el-table {
      border-top: 1px solid #dfe6ec;
    }
  }

  .user-follow {
    padding-top: 20px;
  }
}

.user-bio {
  margin-top: 20px;
  color: #606266;

  span {
    padding-left: 4px;
  }

  .user-bio-section {
    font-size: 14px;
    padding: 15px 0;

    .user-bio-section-header {
      border-bottom: 1px solid #dfe6ec;
      padding-bottom: 10px;
      margin-bottom: 10px;
      font-weight: bold;
    }
  }
}
</style>
