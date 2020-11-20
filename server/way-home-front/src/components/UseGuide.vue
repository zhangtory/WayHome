<template>
  <div class="useGuide">
    <h2 style="box-sizing: border-box; margin-top: 24px; margin-bottom: 16px; line-height: 1.25; padding-bottom: 0.3em; border-bottom: 1px solid rgb(234, 236, 239); color: rgb(36, 41, 46); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;; white-space: normal; background-color: rgb(255, 255, 255);">
      使用说明
    </h2>
    <p style="box-sizing: border-box; margin-top: 0px; margin-bottom: 16px; color: rgb(36, 41, 46); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;; white-space: normal; background-color: rgb(255, 255, 255);">
      WayHome可以让你更低成本、快捷的在外网访问有非固定公网IP的主机。<br/>类似与DDNS，但是不需要自己购买域名。<br/>不同于向日葵、ngork、frp，所有流量不会走WayHome服务器，可以最大限度使用主机的上行带宽。
    </p>
    <ol style="box-sizing: border-box; padding-left: 2em; margin-bottom: 16px; color: rgb(36, 41, 46); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;; white-space: normal; background-color: rgb(255, 255, 255);" class=" list-paddingleft-2">
      <li>
        <p style="box-sizing: border-box; margin-top: 16px; margin-bottom: 16px;">
          既然你看到这里我也不啰嗦了，首先创建一把钥匙，输入钥匙名点击创建按钮即可。
        </p>
        <img src="../../static/1.jpg">
      </li>
      <li>
        <p style="box-sizing: border-box; margin-top: 16px; margin-bottom: 16px;">
          然后需要下载客户端，现在有java和python两种客户端，基本满足了linux服务器的使用。
        </p>
        <img src="../../static/2.jpg">
      </li>
      <li>
        <p style="box-sizing: border-box; margin-top: 16px; margin-bottom: 16px;">
          根据钥匙信息配置客户端。用户名、钥匙名和秘钥是必填的。协议一般是http，也支持https和ftp等；端口是你自己服务器对外开放的端口，注意80/443/8080已被电信屏蔽；路径可不填。
        </p>
        <img src="../../static/3.jpg">
      </li>
      <li>
        <p style="box-sizing: border-box; margin-top: 16px; margin-bottom: 16px;">
          然后运行客户端就行了。java可以直接执行start.sh脚本。
          <br>
          有什么问题可以联系我：admin@zhangtory.com
          <br>
          能在GitHub上star一下就更好了~
        </p>
      </li>
    </ol>
    <p>
      <br/>
    </p>
  </div>
</template>

<script>
    export default {
        name: "UseGuide"
    }
</script>

<style scoped>
.useGuide {
  margin-left: 1vw;
}
</style>
