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

## 如何使用

#### 用前准备
  1. 公网ip。  
     如果没有，打电话让电信分配一个即可。
  2. 家中光猫or路由器做端口转发或设置DMZ主机。    
     一般来说电信屏蔽了80、8080和443等特殊端口，所以我们需要通过其他端口做转发，映射到内网的80或443端口。
  3. 登录WayHome网站，注册用户并在控制台创建一个钥匙，获得对应的secretKey。

#### 客户端配置
  1. 下载客户端。  
      目前实现了Java的客户端([点击下载](https://github.com/zhangtory/WayHome/releases/tag/1.1))，在client中有已经编译好的jar包。  
      或者使用python3客户端([查看]{https://github.com/zhangtory/WayHome/tree/master/client/way-home-client-py})
	  如果您觉得Java客户端不方便，也可以根据API自行开发客户端，或给我们提Issue。
  2. 配置home.properties  
      `home.server_url`: 服务器地址，默认为https://wayhome.zhangtory.com/api/address ，如果自己搭建有私服，可以修改为自己的地址。  
      `home.username` : 你的用户名。
	  `home.keyName`: 你的钥匙名。  
      `home.secretKey`: 网站控制台申请获取的secretKey，与钥匙对应。  
      `home.protocol`: 跳转的协议。如跳转到家中的路由器，一般为http，如果支持https可以设置为https。另外如果要跳转到ftp服务器，也可以设置为ftp。其他协议同理。  
      `home.port`: 跳转的端口，即在家中光猫or路由器上设置的转发端口。http默认为80端口，如果使用http跳转到80端口，则可以省略。其他协议同理。    
      `home.path`: 跳转请求的路径参数。如"/login?user=admin&password=123"。没有可以省略，如有需要以"/"开头。  
  3. 运行客户端  
      Java客户端使用java -jar命令运行。运行时可以根据控制台的输出日志判断请求是否正常。  
      
#### 使用
  网页控制台可以查看对应钥匙设置的跳转地址及其他信息。  
  访问https://wayhome.zhangtory.com/go/{username}/{keyName} 即可跳转到设置的地址上。  
  可以将跳转地址作为书签保存。  

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
