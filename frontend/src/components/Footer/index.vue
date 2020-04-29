<template>
  <footer :style="'color:' + color">
    <p class="weak">© Copyright 2020 潘宏伟 - All Rights Reserved</p>
    <p class="weak">备案号：<a href="http://www.beian.miit.gov.cn/" target="_blank">赣 ICP 备 20003364 号 | <a
      target="_blank"
      href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=36042402000038"
      style="display:inline-block;text-decoration:none;height:20px;line-height:20px;"
    >
      <img src="./beian.png" style="float:left;">
      赣公网安备
      36042402000038号
    </a></a></p>
  </footer>
</template>

<script>
export default {
  name: 'Footer',
  props: {
    color: {
      type: String,
      default: 'white'
    }
  }
}
</script>

<style scoped>
  footer {
    position: absolute;
    color: white;
    bottom: 1em;
    width: 100%;
  }

  footer p {
    text-align: center;
  }

  footer p.weak {
    font-size: 14px;
    opacity: .6;
  }
</style>
