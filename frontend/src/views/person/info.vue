<template>
  <div class="app-container">
    <el-form
      ref="form"
      v-loading="formLoading"
      :data="form"
      element-loading-text="加载中..."
      :model="form"
      label-width="120px"
    >
      <el-form-item label="头像">
        <img :src="form.icon" width="60" height="60" alt="头像">
        <el-button type="primary" icon="upload" style="position: absolute;bottom: 15px;margin-left: 40px;" @click="toggleShow">
          上传头像
        </el-button>

        <my-upload
          v-model="show"
          field="multipartFile"
          :width="300"
          :height="300"
          :url="url"
          :params="params"
          :headers="headers"
          lang-type="zh"
          img-format="png"
          @crop-upload-success="cropUploadSuccess"
        />
      </el-form-item>

      <el-form-item label="账号">
        <el-input v-model="form.username" :disabled="true" />
      </el-form-item>

      <el-form-item label="邮箱">
        <el-input v-model="form.email" />
      </el-form-item>

      <el-form-item label="昵称">
        <el-input v-model="form.nickName" />
      </el-form-item>

      <el-form-item label="备注">
        <el-input v-model="form.note" />
      </el-form-item>

      <el-form-item label="创建时间">
        <el-input v-model="form.createTime" :disabled="true" />
      </el-form-item>

      <el-form-item label="最后登录">
        <el-input v-model="form.updateTime" :disabled="true" />
      </el-form-item>

      <el-form-item label="是否启用">
        <el-radio-group v-model="form.status">
          <el-radio :label="0">禁用</el-radio>
          <el-radio :label="1">启用</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item>
        <el-button v-if="checkPermission(['SYSTEM', 'EDITOR'])" type="primary" @click="onSubmit">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { getInfo, update } from '@/api/user'
import { getToken } from '../../utils/auth'

import myUpload from 'vue-image-crop-upload'
import checkPermission from '@/utils/permission'

export default {
  name: 'Info',
  components: { myUpload },
  data() {
    return {
      formLoading: true,
      form: {},
      url: process.env.VUE_APP_BASE_API + '/upload?folder=avatar',
      show: false,
      params: {
        access_token: getToken()
      },
      headers: {
        smail: '*_~'
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getInfo(this.$store.getters.name).then(response => {
        this.form = response.data.user
        this.formLoading = false
      })
    },
    onSubmit() {
      this.formLoading = true

      // 更新用户
      update(this.form).then(response => {
        this.formLoading = false
        this.$message({
          message: response.message,
          type: 'success'
        })

        // 更新 vuex 中的头像
        this.$store.dispatch('user/setAvatar', this.form.icon)
        this.fetchData()
      }).catch(() => {
        this.formLoading = false
        this.$router.push({
          path: `/403`
        })
      })
    },
    toggleShow() {
      this.show = !this.show
    },
    cropUploadSuccess(jsonData, field) {
      this.form.icon = jsonData.data.path
    },
    checkPermission
  }
}
</script>

<style scoped>
</style>
