<template>
  <div class="app-container">
    <el-button v-if="checkPermission(['SYSTEM', 'EDITOR'])" type="primary" @click="handleAddRole">新增角色</el-button>

    <el-table :data="rolesList" style="width: 100%;margin-top:30px;" border>
      <el-table-column align="center" label="ID" width="220">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="角色" width="220">
        <template slot-scope="scope">
          {{ scope.row.enname }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="中文" width="220">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column align="header-center" label="角色描述">
        <template slot-scope="scope">
          {{ scope.row.description }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button v-if="checkPermission(['SYSTEM', 'EDITOR'])" type="primary" size="small" @click="handleUpdate(scope)">修改</el-button>
          <el-button v-if="checkPermission(['SYSTEM', 'EDITOR'])" type="danger" size="small" @click="del(scope)">删除</el-button>
          <span v-if="checkPermission(['VISITOR'])">只有管理员或编辑才能操作哟！</span>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :visible.sync="dialogVisible" :title="dialogType==='update'?'修改角色':'新增角色'">
      <el-form :model="role" label-width="80px" label-position="left">
        <el-form-item label="角色">
          <el-input v-model="role.enname" placeholder="角色名" />
        </el-form-item>
        <el-form-item label="中文">
          <el-input v-model="role.name" placeholder="中文名" />
        </el-form-item>
        <el-form-item label="描述">
          <el-input
            v-model="role.description"
            :autosize="{ minRows: 2, maxRows: 4}"
            type="textarea"
            placeholder="角色描述"
          />
        </el-form-item>
        <el-form-item label="选择权限" />
        <el-tree
          ref="tree"
          :data="routes"
          :props="defaultProps"
          show-checkbox
          node-key="id"
          :default-checked-keys="role.permissionList"
          class="permission-tree"
        />
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="cancelRole">取消</el-button>
        <el-button type="primary" @click="confirmRole">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { list, deleteRole, updateRole, addRole } from '@/api/role'
import { getPermissionList } from '@/api/permission'
import checkPermission from '@/utils/permission'

export default {
  data() {
    return {
      props: {
        label: 'name',
        children: 'zones'
      },
      rolesList: [],
      role: {},
      routes: [],
      dialogType: 'add',
      dialogVisible: false,
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      map: {}
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    checkPermission,
    fetchData() {
      list().then(response => {
        // console.log(response)
        this.rolesList = response.data
      })

      getPermissionList().then(response => {
        this.routes = response.data
      })
    },
    del({ $index, row }) {
      this.$confirm('确定删除这个角色', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async() => {
        await deleteRole(row.id).then(() => {
          this.rolesList.splice($index, 1)
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        }).catch(() => {
          this.$router.push({
            path: `/403`
          })
        })
      }).catch(err => { console.error(err) })
    },
    handleAddRole() {
      this.dialogType = 'add'
      this.dialogVisible = true

      // 清空选中
      this.$refs.tree.setCheckedKeys([])
    },
    handleUpdate(scope) {
      this.dialogType = 'update'
      this.dialogVisible = true
      this.role = scope.row
    },
    cancelRole() {
      this.dialogVisible = false

      // 清空选中
      this.$refs.tree.setCheckedKeys([])
    },
    async confirmRole() {
      const isUpdate = this.dialogType === 'update'

      let isOk = true
      // 封装数据
      this.map = {
        role: this.role,
        permissionList: this.$refs.tree.getCheckedKeys()
      }
      // 更新
      if (isUpdate) {
        await updateRole(this.map).catch(() => {
          isOk = false
          this.$router.push({
            path: `/403`
          })
        })
        for (let index = 0; index < this.rolesList.length; index++) {
          if (this.rolesList[index].id === this.role.id) {
            this.rolesList.splice(index, 1, Object.assign({}, this.role))
            break
          }
        }
      } else {
        const { data } = await addRole(this.map).catch(() => {
          isOk = false
          this.$router.push({
            path: `/403`
          })
        })
        this.role.id = data.id
        this.rolesList.push(this.role)
      }

      const { description, id, enname, name } = this.role
      this.dialogVisible = false

      if (isOk) {
        this.$notify({
          title: '成功',
          dangerouslyUseHTMLString: true,
          message: `
            <div>ID : ${id}</div>
            <div>角色 : ${enname}</div>
            <div>中文 : ${name}</div>
            <div>描述 : ${description}</div>
          `,
          type: 'success'
        })
      }
      // 更新完重新获取数据
      list().then(response => {
        this.rolesList = response.data
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.app-container {
  .roles-table {
    margin-top: 30px;
  }
  .permission-tree {
    margin-bottom: 30px;
  }
}
</style>
