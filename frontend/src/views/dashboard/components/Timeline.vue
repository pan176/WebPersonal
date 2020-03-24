<template>
  <div class="block">
    <el-timeline>
      <el-timeline-item v-for="(item,index) of timeline" :key="index" :timestamp="item.timestamp" placement="top">
        <el-card>
          <h4>{{ item.title }}</h4>
          <p>{{ item.content }}</p>
        </el-card>
      </el-timeline-item>
    </el-timeline>
  </div>
</template>

<script>
export default {
  data() {
    return {
      timeline: [
        {
          timestamp: '2020/3/15',
          title: '网站更新',
          content: '新增个人简历，可使用阿里云 OSS 上传头像。'
        },
        {
          timestamp: '2020/3/20',
          title: '网站更新',
          content: '新增了权限管理功能，完善了个人简历、主页。'
        },
        {
          timestamp: '2020/3/23',
          title: '网站更新',
          content: '新增相册，完善了权限处理逻辑。'
        }
      ]
    }
  }
}
</script>
