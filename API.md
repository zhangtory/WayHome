# API文档

## 用户相关

### 用户注册
 * path: `/register`
 * method:`POST` 
 * params:  
    用户名 : username 
    密码 : password  
    重复密码 : repassword 
    邮箱 : email   
    手机号码 : mobile  
 * return:  
    code : 0  
    msg : "success"
     
### 密码修改
 * path: `/password`
 * method:`POST` 
 * params:  
     旧密码 : oldPassword  
     新密码 : newPassword  
     重复新密码 : reNewPassword    
 * return:  
     code : 0  
     msg : "success"

### 控制台
 * path: `/dashboard`
 * method:`POST` 
 * params: 无
 * return:  
     code : 0  
     msg : "success"
     data :  
       userInfo :  
         用户id : userId  
         用户名 : username  
         邮箱 : email  
         手机号码 : mobile  
       userKeyList :   
         keyId : id  
         用户id : userId  
         appId : appId  
         秘钥 : secretKey   
         创建时间 : createTime  
         更新时间 : updateTime  
         addressList :   
           地址Id : id  
           appId : appId  
           外网协议 : protocol  
           外网ip : ip  
           外网端口 : port  
           内网协议 : innerProtocol  
           内网ip : innerIp  
           内网端口 : innerPort  
           创建时间 : createTime  
           更新时间 : updateTime  
 
## 秘钥相关

### 申请key
 * path: `/key`
 * method:`POST` 
 * params: 无  
 * return:  
     code : 0  
     msg : "success"  
     data :   
       用户id : userId  
       appId : appId  
       秘钥 : secretKey  
       创建时间 : createTime  
       修改时间 : updateTime  
 
### 删除key
 * path: `/key/{appId}`
 * method:`DELETE` 
 * params: 
     申请key时返回的appId : appId  
 * return:  
     code : 0  
     msg : "success"  
     
### 查询key
 * path: `/key`
 * method:`GET` 
 * params: 无  
 * return:  
     code : 0  
     msg : "success"  
     data :   
       用户id : userId  
       appId : appId  
       秘钥 : secretKey  
       创建时间 : createTime  
       修改时间 : updateTime  

## 地址相关

### 跳转回家
 * path: `/go/{appId}`
 * method:`GET` 
 * params: 
     appId : appId  
     
### 设置回家地址
 * path: `/address/{appID}`
 * method:`GET` 
 * params: 
     appId : appId
  * return:  
     code : 0  
     msg : "success"  

