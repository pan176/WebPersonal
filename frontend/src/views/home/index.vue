<template>
  <div class="app-container">
    <div v-if="user">
      <el-row :gutter="20">
        <el-col :span="6" :xs="24">
          <user-card :user="user" />
        </el-col>

        <el-col :span="18" :xs="24">
          <el-card>
            <el-tabs v-model="activeTab">
              <el-tab-pane label="介绍" name="activity">
                <activity />
              </el-tab-pane>
              <el-tab-pane label="日志" name="timeline">
                <timeline />
              </el-tab-pane>
              <el-tab-pane label="账号" name="account">
                <account :user="user" />
              </el-tab-pane>
            </el-tabs>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <Footer color="black"/>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

import UserCard from './components/UserCard'
import Activity from './components/Activity'
import Timeline from './components/Timeline'
import Account from './components/Account'
import Footer from '@/components/Footer'

export default {
  name: 'Home',
  components: { UserCard, Activity, Timeline, Account, Footer },
  data() {
    return {
      user: {},
      activeTab: 'activity'
    }
  },
  computed: {
    ...mapGetters([
      'name',
      'avatar',
      'roles',
      'email'
    ])
  },
  created() {
    this.getUser()
  },
  methods: {
    getUser() {
      this.user = {
        name: this.name,
        role: this.roles.join(' | '),
        email: this.email,
        avatar: this.avatar
      }
    }
  }
}
</script>

<style scoped>
</style>
