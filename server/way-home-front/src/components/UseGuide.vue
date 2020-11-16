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
          注册一个WayHome账号(<a href="https://wayhome.zhangtory.com/register" style="box-sizing: border-box; background-color: initial; color: rgb(3, 102, 214); text-decoration-line: none;">注册地址</a>)或登录。<br/>您的WayHome账号名将作为访问地址的一部分，建议使用方便记忆的用户名。<br/>在管理后台创建一个钥匙，创建时需要输入钥匙名，点击“创建钥匙”，之后就可以在钥匙列表看到钥匙信息。
        </p>
      </li>
      <li>
        <p style="box-sizing: border-box; margin-top: 16px; margin-bottom: 16px;">
          和DDNS一样，您的主机需要有公网IP。<br/>如果是电信的话，可以直接拨打10000转人工服务免费获取公网IP地址。
        </p>
      </li>
      <li>
        <p style="box-sizing: border-box; margin-top: 16px; margin-bottom: 16px;">
          在光猫或者拨号的路由器上做端口转发，或者设置为DMZ主机。<br/>因为通过IP地址首先访问的是PPPoE拨号上网的光猫或者路由器，光猫或路由器接收到请求后，不知道将请求转发给内网的哪一台主机，所以我们需要做端口转发，如设置：将外网8123端口的访问请求转发给内网192.168.1.2的80端口。<br/>当然也可以设置DMZ主机，将所有外网的请求转发到这台服务器上。<br/>注意：电信可能屏蔽了80和443等特殊端口，做端口映射时需要避开这些端口。
        </p>
      </li>
      <li>
        <p style="box-sizing: border-box; margin-top: 16px; margin-bottom: 16px;">
          下载Java版WayHome客户端或者python客户端。如果你有其他版本客户端的需要，可以根据API自行开发客户端或给我们提Issue。<br/>下载完成后需要完成客户端配置。<br/>Java客户端配置home.properties，python3客户端直接在脚本文件中配置<br/><code style="box-sizing: border-box; font-family: SFMono-Regular, Consolas, &quot;Liberation Mono&quot;, Menlo, monospace; font-size: 13.6px; padding: 0.2em 0.4em; margin: 0px; background-color: rgba(27, 31, 35, 0.05); border-radius: 6px;">home.server_url</code>: 不用修改。如果自己搭建有私服，可以修改为自己的地址。<br/><code style="box-sizing: border-box; font-family: SFMono-Regular, Consolas, &quot;Liberation Mono&quot;, Menlo, monospace; font-size: 13.6px; padding: 0.2em 0.4em; margin: 0px; background-color: rgba(27, 31, 35, 0.05); border-radius: 6px;">home.username</code>&nbsp;: 你的用户名。<br/><code style="box-sizing: border-box; font-family: SFMono-Regular, Consolas, &quot;Liberation Mono&quot;, Menlo, monospace; font-size: 13.6px; padding: 0.2em 0.4em; margin: 0px; background-color: rgba(27, 31, 35, 0.05); border-radius: 6px;">home.keyName</code>: 你的钥匙名。<br/><code style="box-sizing: border-box; font-family: SFMono-Regular, Consolas, &quot;Liberation Mono&quot;, Menlo, monospace; font-size: 13.6px; padding: 0.2em 0.4em; margin: 0px; background-color: rgba(27, 31, 35, 0.05); border-radius: 6px;">home.secretKey</code>: 网站控制台申请获取的secretKey，与钥匙对应。<br/><code style="box-sizing: border-box; font-family: SFMono-Regular, Consolas, &quot;Liberation Mono&quot;, Menlo, monospace; font-size: 13.6px; padding: 0.2em 0.4em; margin: 0px; background-color: rgba(27, 31, 35, 0.05); border-radius: 6px;">home.protocol</code>: 跳转的协议。如跳转到家中的路由器，一般为http，如果支持https可以设置为https。另外如果要跳转到ftp服务器，也可以设置为ftp。其他协议同理。<br/><code style="box-sizing: border-box; font-family: SFMono-Regular, Consolas, &quot;Liberation Mono&quot;, Menlo, monospace; font-size: 13.6px; padding: 0.2em 0.4em; margin: 0px; background-color: rgba(27, 31, 35, 0.05); border-radius: 6px;">home.port</code>: 跳转的端口，即在家中光猫or路由器上设置的转发端口。http默认为80端口，如果使用http跳转到80端口，则可以省略。其他协议同理。<br/><code style="box-sizing: border-box; font-family: SFMono-Regular, Consolas, &quot;Liberation Mono&quot;, Menlo, monospace; font-size: 13.6px; padding: 0.2em 0.4em; margin: 0px; background-color: rgba(27, 31, 35, 0.05); border-radius: 6px;">home.path</code>: 跳转请求的路径参数。如&quot;/login?user=admin&amp;password=123&quot;。没有可以省略，如有需要以&quot;/&quot;开头。<br/>Java客户端使用<code style="box-sizing: border-box; font-family: SFMono-Regular, Consolas, &quot;Liberation Mono&quot;, Menlo, monospace; font-size: 13.6px; padding: 0.2em 0.4em; margin: 0px; background-color: rgba(27, 31, 35, 0.05); border-radius: 6px;">nohup java -jar wayhome客户端jar包名 &amp;</code>命令运行。运行时可以根据控制台的输出日志或nohup文件判断请求是否正常。
        </p>
      </li>
      <li>
        <p style="box-sizing: border-box; margin-top: 16px; margin-bottom: 16px;">
          网页控制台可以查看对应钥匙设置的跳转地址及其他信息。<br/>访问<a href="https://wayhome.zhangtory.com/go/%7Busername%7D/%7BkeyName%7D" style="box-sizing: border-box; background-color: initial; color: rgb(3, 102, 214); text-decoration-line: none;">https://wayhome.zhangtory.com/go/{username}/{keyName}</a>&nbsp;即可跳转到设置的地址上。<br/>可以将跳转地址作为书签保存。
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
