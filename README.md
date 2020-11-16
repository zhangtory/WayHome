# WayHome 回家之路
[![version](https://img.shields.io/badge/version-0.2.0-brightgreen)](https://wayhome.zhangtory.com/)
![license](https://img.shields.io/badge/license-MIT-blue)
[![download](https://img.shields.io/badge/download-client-red)](https://github.com/zhangtory/WayHome/releases/download/1.0/wayhome-client-1.0.zip)
  
对浏览器支持的协议，它比DDNS更方便。  
外网访问家中的服务器总是不方便。当我在家搭建了NAS和HomeAssistant后，我觉得我需要自己撸这个轮子了。

## 实现原理  
通过浏览器302重定向，跳转到设定的URL上。  

1. 客户端每隔一段时间向服务器发送更新报文，报文包含了跳转所需的协议、端口、路径参数以及钥匙信息。
2. 服务器根据收到的报文获取跳转所需的参数，同时获取客户端的公网IPv4地址。
3. 当浏览器访问服务器的跳转接口时，根据客户端上报的信息将请求重定向至客户端设定的地址上。  

与DDNS不同在于没有TTL，可实时同步地址，也不需要自己申请域名，对用户成本更低。  
与花生壳、frp、Ngrok相比，跳转后的所有流量不需要服务器参与，用户与自己的服务器直连，延迟更低，更好的用尽上下行带宽。所以，如果你在家搭建了NAS，那么WayHome是一个非常好的选择。

## 使用指南

  WayHome可以让你更低成本、快捷的在外网访问有非固定公网IP的主机。  
  类似与DDNS，但是不需要自己购买域名。  
  不同于向日葵、ngork、frp，所有流量不会走WayHome服务器，可以最大限度使用主机的上行带宽。  
  
  1. 注册一个WayHome账号([注册地址](https://wayhome.zhangtory.com/register))或登录。  
  您的WayHome账号名将作为访问地址的一部分，建议使用方便记忆的用户名。  
  在管理后台创建一个钥匙，创建时需要输入钥匙名，点击“创建钥匙”，之后就可以在钥匙列表看到钥匙信息。

  2. 和DDNS一样，您的主机需要有公网IP。  
  如果是电信的话，可以直接拨打10000转人工服务免费获取公网IP地址。  

  3. 在光猫或者拨号的路由器上做端口转发，或者设置为DMZ主机。  
  因为通过IP地址首先访问的是PPPoE拨号上网的光猫或者路由器，光猫或路由器接收到请求后，不知道将请求转发给内网的哪一台主机，所以我们需要做端口转发，如设置：将外网8123端口的访问请求转发给内网192.168.1.2的80端口。  
  当然也可以设置DMZ主机，将所有外网的请求转发到这台服务器上。  
  注意：电信可能屏蔽了80和443等特殊端口，做端口映射时需要避开这些端口。

  4. 下载Java版WayHome客户端或者python客户端。如果你有其他版本客户端的需要，可以根据API自行开发客户端或给我们提Issue。  
  下载完成后需要完成客户端配置。  
  Java客户端配置home.properties，python3客户端直接在脚本文件中配置  
      `home.server_url`: 服务器地址，默认为https://api.wayhome.zhangtory.com/address ，如果自己搭建有私服，可以修改为自己的地址。  
      `home.username` : 你的用户名。  
      `home.keyName`: 你的钥匙名。  
      `home.secretKey`: 网站控制台申请获取的secretKey，与钥匙对应。  
      `home.protocol`: 跳转的协议。如跳转到家中的路由器，一般为http，如果支持https可以设置为https。另外如果要跳转到ftp服务器，也可以设置为ftp。其他协议同理。  
      `home.port`: 跳转的端口，即在家中光猫or路由器上设置的转发端口。http默认为80端口，如果使用http跳转到80端口，则可以省略。其他协议同理。    
      `home.path`: 跳转请求的路径参数。如"/login?user=admin&password=123"。没有可以省略，如有需要以"/"开头。   
  Java客户端使用`nohup java -jar wayhome客户端jar包名 &`命令运行。运行时可以根据控制台的输出日志或nohup文件判断请求是否正常。  
      
  5. 网页控制台可以查看对应钥匙设置的跳转地址及其他信息。  
  访问https://wayhome.zhangtory.com/go/{username}/{keyName} 即可跳转到设置的地址上。  
  可以将跳转地址作为书签保存。  
  
## WayHome程序架构概览

#### 1. 前端way-home-front
使用vue + iView作为网页的前端显示。
主要为用户提供使用说明、后台管理等功能。

#### 2. 管理后台way-home-admin
实现用户注册、登录，用户钥匙创建等功能的接口，供前端调用。

#### 3. 地址核心way-home-core
实现地址获取、地址信息上报功能。
考虑到用户量足够大时，除了对redis进行扩展，还需要对way-home-core进行横向扩展。

![jg](https://raw.githubusercontent.com/zhangtory/WayHome/master/jg.png)

## API

* 地址更新上报接口  
  客户端定时上报跳转信息，需要username、keyName、protocol、port、path、timestamp参数，并签名sign。  
  `POST`请求，地址：https://wayhome.zhangtory.com/api/address
   参数以json形式放入body， 使用UTF-8编码，并设置header : 'Content-Type': 'application/json;charset=utf-8'。     
  `username` : 你的用户名。  
  `keyName` : 你的钥匙名。  
  `protocol` : 访问协议，如http、https、ftp。  
  `path` : 路径参数，可选，如?user=admin&password=123456。  
  `timestamp` : 毫秒级时间戳。  
  `sign` : 参数名按照ascii升序排序，并以key=value&key=value&secretKey=secret形式拼接成字符串，然后对字符串取MD5摘要，MD5不区分大小写。  

* 地址信息获取接口  
  获取钥匙对应的地址信息，以json形式返回。
  `GET`请求，https://wayhome.zhangtory.com/api/address/{username}/{keyName}
  `username` : 你的用户名。
  `keyName` ： 你的钥匙名。

## 开发中...
  如果你有什么想法，可以提Issue给我们，或者发邮件到i@zhangtory.com 。  
  后续我会根据我自己的使用进行优化。
