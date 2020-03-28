<template>
  <div class="user-activity">
    <div class="post">
      <div class="user-block">
        <img class="img-circle" :src="'https://myshopsite.oss-cn-hangzhou.aliyuncs.com/avatar/6FFA22D5D7B1D817C9689C66BF6F4446.jpg'+avatarPrefix">
        <span class="username text-muted">Pan</span>
        <span class="description">2020.3.20</span>
      </div>
      <p>
        感谢您的浏览，本站主要分享个人的生活和学习哟！
      </p>
      <div class="user-images">
        <el-carousel :interval="6000" type="card">
          <el-carousel-item v-for="item in carouselImages" :key="item">
            <img :src="item+carouselPrefix" class="image">
          </el-carousel-item>
        </el-carousel>
      </div>
    </div>
  </div>
</template>

<script>
const avatarPrefix = '?imageView2/1/w/80/h/80'
const carouselPrefix = '?imageView2/2/h/440'
import { rand } from '../../../api/album'

export default {
  data() {
    return {
      carouselImages: [],
      avatarPrefix,
      carouselPrefix
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      rand(4).then(response => {
        this.carouselImages = response.data
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.user-activity {
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
      border: 1px solid gray;
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

  .list-inline {
    padding-left: 0;
    margin-left: -5px;
    list-style: none;

    li {
      display: inline-block;
      padding-right: 5px;
      padding-left: 5px;
      font-size: 13px;
    }

    .link-black {

      &:hover,
      &:focus {
        color: #999;
      }
    }
  }

}

.box-center {
  margin: 0 auto;
  display: table;
}

.text-muted {
  color: #777;
}
</style>
