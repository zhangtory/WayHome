<template>
  <div class="index">
    <el-row>
      <el-col>

        <div class="detail">
          <h1>WayHome 回家之路</h1>
          <p>对浏览器支持的协议，它比DDNS更方便。</p>
          <p>如果你在家搭建了NAS或HomeAssistant，那么WayHome是你最佳的选择。</p>
          <p>无需注册域名，无需自建服务器，一个书签即可踏上回家之路。</p>
        </div>
        <div class="head_page">
          <el-image src="static/stars_bg.jpg" fit="fill"/>
        </div>
      </el-col>

      <el-col>
        <div>
          <p>sdfsdf</p>
          <p>后面是使用说明</p>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
    export default {
      name: "Index",
    }
</script>

<style scoped>
  .index {
    width: 100%;
    text-align: center;
  }
  h1 {
    font-size: 4.5rem;
    color: #e6e6e6;
    margin-top: 33vh;
  }
  p {
    font-size: 1.7rem;
    color: #e6e6e6;
  }
  .detail {
    width: 100%;
    position: absolute;
    z-index: 1;
  }
  .el-image {
    height: 100vh;
    display: flex;
    z-index: 0;
  }
</style>
