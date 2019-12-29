<template>
  <p>开放API文档见:<a href="https://github.com/zhangtory/WayHome" target="view_window">GitHub</a></p>
</template>

<script>
export default {
    name: "OpenApi",
  mounted: function () {
    window.open("https://github.com/zhangtory/wayhome");
  }
}
</script>

<style scoped>
  p {
    margin-top: 2vh;
    margin-left: 1vw;
    font-size: large;
  }
</style>
