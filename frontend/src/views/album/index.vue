<template>
  <div class="components-container">
    <div class="viewer-wrapper">

      <div class="post">
        <div class="user-block">
          <img class="img-circle" :src="'https://myshopsite.oss-cn-hangzhou.aliyuncs.com/avatar/3c4e671b-4ebc-4187-b379-441d8bebc86d.png'">
          <span class="username text-muted">滑稽.jpg</span>
          <span class="description">图片共 {{images.length}} 张</span>
        </div>
        <img-inputer
          v-if="checkPermission(['SYSTEM', 'EDITOR'])"
          auto-upload
          :action="url"
          :extra-data="params"
          :on-success="onSuccess"
          :on-error="onError"
          upload-key="multipartFile"
          size="small"
          class="upload"
        />
        <p>
          分享一些有趣的图片和我觉得好看哒图片 (●'◡'●)，需要可以拿走哈！
        </p>
      </div>

      <viewer ref="viewer" :images="images" class="images clearfix" @inited="inited">
        <template slot-scope="scope">
          <div v-for="(src, index) in scope.images" :key="src" class="image-wrapper">
            <img
              class="image"
              :src="src"
            >
            <span class="mask">
              <span class="mask-icon">
                <i class="el-icon-search" @click="show(index)" />
              </span>
              <span class="mask-icon delete">
                <i class="el-icon-delete" @click="del(index, src)" />
              </span>
            </span>
          </div>
        </template>
      </viewer>
    </div>
  </div>
</template>

<script>
import ImgInputer from 'vue-img-inputer'
import 'vue-img-inputer/dist/index.css'
import 'viewerjs/dist/viewer.css'
import { add, list, del, aliyunDel } from '../../api/album'
import { getToken } from '../../utils/auth'
import checkPermission from '@/utils/permission'

export default {
  name: 'Album',
  components: { ImgInputer },
  data() {
    return {
      images: [],
      url: process.env.VUE_APP_BASE_API + '/upload?folder=album',
      params: {
        access_token: getToken()
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    checkPermission,
    fetchData() {
      list().then(response => {
        this.images = response.data
      })
    },
    onError() {
      this.$router.push({
        path: `/403`
      })
    },
    onSuccess(success, file) {
      console.log('onSuccess -> file', file)
      console.log('onSuccess -> success', success)
      // 成功后添加到数据库
      add({
        path: success.data.path
      })
      this.images.push(success.data.path)
    },
    show(index) {
      console.log(index)
      this.$viewer.view(index)
    },
    del(index, src) {
      // 数据库操作
      del(src).then(response => {
        if (response.code === 20000) {
          // 阿里云操作
          aliyunDel(src).then(response => {
            this.$message({
              message: response.message,
              type: 'success'
            })
          })
          this.images.splice(index, 1)
        }
      }).catch(() => {
        this.$router.push({
          path: `/403`
        })
      })
    },
    inited(viewer) {
      this.$viewer = viewer
    }
  }
}
</script>

<style lang="scss" scoped>
  .upload {
    position: absolute;
    top: 10px;
    right: 20px;
  }
  .user-block {

    .username,
    .description {
      display: block;
      margin-left: 50px;
      padding: 2px 0;
    }

    .username{
      font-size: 16px;
      color: #000;
    }

    :after {
      clear: both;
    }

    .img-circle {
      border-radius: 50%;
      width: 40px;
      height: 40px;
      float: left;
    }

    span {
      font-weight: 500;
      font-size: 12px;
    }
  }

  .post {
    font-size: 14px;
    border-bottom: 1px solid #d2d6de;
    margin-bottom: 15px;
    padding-bottom: 15px;
    color: #666;

    .image {
      width: 100%;
      height: 100%;

    }

    .user-images {
      padding-top: 20px;
    }
  }

  .viewer-wrapper {
    margin: 10px;
    padding: 10px;
    min-height: 860px;
  }
  .image-wrapper {
    position: relative;
    width: 400px;
    height: 225px;
    border-radius: 5px;
    overflow: hidden;
    display: inline-block;
    margin: 5px;
  }
  .image {
    width: 100%;
  }
  .mask {
    display: block;
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    transition: opacity .3s;
    background-color: rgba(0,0,0,.5);
    color: #fff;
    text-align: center;
    opacity: 0;
  }
  .image-wrapper:hover .mask {
    opacity: 1;
  }
  .mask-icon {
    display: inline-block;
    font-size: 25px;
    margin: 100px 60px;
    cursor: pointer;
  }
  .mask-icon:hover {
    color: dodgerblue;
    transition: all .3s;
    transform: scale(1.2);
  }
  .delete:hover {
    color: red;
  }
</style>
