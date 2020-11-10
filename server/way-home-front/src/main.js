// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios';
// import ViewUI from 'view-design';
// import 'view-design/dist/styles/iview.css';

// Vue.use(ViewUI);

Vue.config.productionTip = false
Vue.prototype.axios = axios

let API_HOST = "http://127.0.0.1:8001"
// let API_HOST = "https://wayhome.zhangtory.com"

/* 请求拦截器 */
axios.interceptors.request.use(function (config) {
  // 设置api地址
  config.url = API_HOST + config.url;
  // 每次请求时会从localStorage中获取token
  let token = localStorage.getItem("Authorization");
  if (token) {
    config.headers['Authorization'] = token
  }
  return config
}, function (error) {
  console.log(error);
})

/* 响应拦截器 */
axios.interceptors.response.use(function (response) {
  if (response.data.code === -204 || response.data.code === -205) {
    localStorage.removeItem("Authorization");
    router.replace({
      path: '/login' // 到登录页重新获取token
    })
  }
  return response
}, function (error) {
  return Promise.reject(error)
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
