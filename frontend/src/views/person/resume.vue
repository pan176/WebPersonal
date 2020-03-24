<template>
  <div class="components-container">
    <div class="editor-container">
      <markdown-editor ref="markdownEditor" v-model="content" height="auto" :language="language" />
    </div>

    <el-button v-if="checkPermission(['SYSTEM', 'EDITOR'])" style="margin:10px;" type="primary" icon="el-icon-upload" @click="update">
      更新简历
    </el-button>

    <router-link target="_blank" to="/person/download">
      <el-button style="margin:10px;" type="primary" icon="el-icon-document">
        下载 PDF
      </el-button>
    </router-link>
  </div>
</template>

<script>
import MarkdownEditor from '@/components/MarkdownEditor'

import { update, resume } from '@/api/resume'
import checkPermission from '@/utils/permission'

export default {
  name: 'MarkdownDemo',
  components: { MarkdownEditor },
  data() {
    return {
      content: '',
      html: '',
      languageTypeList: {
        'en': 'en_US',
        'zh': 'zh_CN',
        'es': 'es_ES'
      }
    }
  },
  computed: {
    language() {
      return this.languageTypeList['en']
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    checkPermission,
    fetchData() {
      // 获得简历内容
      resume().then(response => {
        this.content = response.data.content
      })
    },
    update() {
      // 更新简历
      update({
        content: this.content
      }).then(response => {
        this.$message({
          message: response.message,
          type: 'success'
        })

        this.fetchData()
      }).catch(() => {
        this.$router.push({
          path: `/403`
        })
      })
    }
  }
}
</script>

<style scoped>
.editor-container{
  margin-bottom: 30px;
}
.tag-title{
  margin-bottom: 5px;
}
</style>
