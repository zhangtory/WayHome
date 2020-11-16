<template>
  <div class="clientDownload">
    <h2 style="box-sizing: border-box; margin-top: 24px; margin-bottom: 16px; line-height: 1.25; padding-bottom: 0.3em; border-bottom: 1px solid rgb(234, 236, 239); color: rgb(36, 41, 46); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;; white-space: normal; background-color: rgb(255, 255, 255);">
      客户端下载
    </h2>
    <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 16px; color: rgb(36, 41, 46); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;; white-space: normal; background-color: rgb(255, 255, 255);">
      目前提供java和python两种客户端，用于上报地址信息，下载后设置秘钥信息和外网协议端口即可。
    </p>

    <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 16px; color: rgb(36, 41, 46); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;; white-space: normal; background-color: rgb(255, 255, 255);">
      Python客户端，支持Python2和Python3。<a href="https://wayhome.zhangtory.com/client/wayhome-client.py">点击下载</a>
    </p>

    <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 16px; color: rgb(36, 41, 46); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;; white-space: normal; background-color: rgb(255, 255, 255);">
      Java客户端，配置好home.properties后，执行start.sh可启动客户端。执行stop.sh可停止客户端。<a href="https://wayhome.zhangtory.com/client/wayhome-client-java.zip">点击下载</a>
    </p>

    <p>
      <br/>
    </p>
  </div>
</template>

<script>
export default {
name: "ClientDownload"
}
</script>

<style scoped>
.clientDownload {
  margin-left: 1vw;
}
</style>
