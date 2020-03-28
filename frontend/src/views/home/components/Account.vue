<template>
  <el-form>
    <el-form-item label="用户名">
      <el-input v-model.trim="user.name" disabled/>
    </el-form-item>
    <el-form-item label="邮箱">
      <el-input v-model.trim="user.email" disabled/>
    </el-form-item>
    <el-form-item label="角色">
      <el-input v-model.trim="user.role" disabled/>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  props: {
    user: {
      type: Object,
      default: () => {
        return {
          name: '',
          email: ''
        }
      }
    }
  },
  methods: {
    submit() {
      this.$message({
        message: 'User information has been updated successfully',
        type: 'success',
        duration: 5 * 1000
      })
    }
  }
}
</script>
