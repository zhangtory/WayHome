<template>
  <div class="index">
    <Row type="flex" justify="center">
      <Col>
        <h1>WayHome 回家之路</h1>

        <div class="detail">
          <p>对浏览器支持的协议，它比DDNS更方便。</p>
          <p>如果你在家搭建了NAS和HomeAssistant，那么WayHome是你最佳的选择。</p>
          <p>无需注册域名，无需自建服务器，一个书签即可踏上回家之路。</p>
        </div>

        <div v-if="token">
          <router-link to="/dashboard"><Button type="success" size="large" ghost>进入后台</Button></router-link>
        </div>
        <div v-else>
          <router-link to="/login"><Button type="success" size="large" ghost>登录</Button></router-link>
          <router-link to="/register"><Button type="info" size="large" ghost>注册</Button></router-link>
        </div>

        <div class="link">
          <a href="https://github.com/zhangtory/WayHome/blob/dev/README.md" target="view_window">使用说明</a>
          <span> &nbsp; | &nbsp; </span>
          <a href="https://github.com/zhangtory/WayHome" target="view_window">View On GitHub</a>
        </div>
      </Col>
    </Row>
  </div>
</template>

<script>
  export default {
    name: "Index",
    data() {
      return {
        token: localStorage.getItem("Authorization")
      }
    }
  }
</script>

<style scoped>
  h1 {
    font-size: 5em;
    color: #e6e6e6;
    margin-bottom: 1em;
  }
  p {
    font-size: 2em;
    color: #e6e6e6;
    margin-bottom: 0.3em;
  }
  Button {
    width: 20%;
  }
  a {
    font-size: 1.2em;
  }
  .link {
    margin-top: 2em;
  }
  .detail {
    margin-bottom: 4em;
  }
  .index {
    width: 100vw;
    height: 100vh;
    background-image: url("../../static/stars_bg.jpg");
    background-size: cover;
    background-position: center;

    text-align: center;

    display: flex;
    align-items: center;
    justify-content: center;
  }
</style>
