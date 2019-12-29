# API文档
version 0.2.0

## 地址相关  
用户可根据`设置回家地址`接口自己实现上报功能。

#### 跳转回家
 * HTTP请求  
 `GET` : `/go/{appId}`
 * 请求参数  
     appId : 申请获得的appId  
     
#### 设置回家地址
 * HTTP请求  
 `GET` : `/address/{appID}`
 * 请求参数: 
     appId : 申请获得的appId
