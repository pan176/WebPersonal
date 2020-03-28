<template>
  <div class="block">
    <el-timeline>
      <el-timeline-item v-for="(item,index) of timeline" :key="index" :timestamp="item.timestamp" placement="top">
        <el-card>
          <a href="http://www.pan176.top/webpersonal_doc" target="_blank"><h4>{{ item.title }}</h4></a>
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
          content: '新增权限管理、图片管理，完善个人简历、主页。'
        },
        {
          timestamp: '2020/3/23',
          title: '代码重构',
          content: '重构改善既有代码。'
        }
      ]
    }
  }
}
</script>
