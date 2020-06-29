<template>
  <el-container>

    <el-header>
      <el-menu :default-active="activeIndex" class="el-menu" mode="horizontal" router
               background-color="#375274" text-color="#fff" active-text-color="#ffd04b">
        <el-menu-item index="/index"><el-link class="main_title">WayHome</el-link></el-menu-item>
        <el-menu-item index="/keyList">钥匙列表</el-menu-item>
        <el-menu-item><el-link href="https://github.com/zhangtory/WayHome" :underline="false" target="_blank">GitHub</el-link></el-menu-item>
      </el-menu>
    </el-header>

    <el-main>
      <router-view/>
    </el-main>

    <el-footer>© {{year}} WayHome - ZhangTory's Fantastic Studio</el-footer>

  </el-container>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      activeIndex: this.$route.path,
      year: new Date().getFullYear(),
    };
  }
}
</script>

<style>
  html,body,.el-container{
    /*设置内部填充为0，几个布局元素之间没有间距*/
    padding: 0rem;
    /*外部间距也是如此设置*/
    margin: 0rem;
    /*统一设置高度为100%*/
    height: 100%;
  }
  .main_title {
    font-size: 2rem;
  }
  .el-header {
    background-color: #375274;
    color: #333;
    text-align: center;
  }
  .el-footer {
    background-color: #375274;
    color: #b7c5d5;
    text-align: center;
    line-height: 3.6rem;
  }
  .el-main {
    padding: 0rem;
    margin: 0rem;
  }
</style>
