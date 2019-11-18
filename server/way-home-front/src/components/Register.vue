<template>
  <div class="register">
    <h3>注册</h3>
    <form>
      <input type="text" placeholder="请输入用户名" v-model="username">
      <input type="text" placeholder="请输入密码" v-model="password">
      <input type="text" placeholder="请重复密码" v-model="repassword">
      <input type="text" placeholder="请输入邮箱地址" v-model="email">
      <input type="text" placeholder="请输入手机号码" v-model="mobile">
      <button @click="register">登录</button>
      <router-link to="/login">已有账号？马上登录</router-link>
    </form>
  </div>
</template>

<script>
import {checkEmail} from '../service/validate.js'
import {checkMobile} from '../service/validate.js'
export default {
  name: "Register",
  data () {
    return {
      username: '',
      password: '',
      repassword: '',
      email: '',
      mobile: ''
    }
  },
  methods: {
    register() {
      if (this.username === '') {
        alert('请输入用户名');
        return;
      }
      if (this.password === '') {
        alert('请输入密码');
        return;
      }
      if (this.repassword === '') {
        alert('请重复密码');
        return;
      }
      if (this.email === '') {
        alert('请输入邮箱地址');
        return;
      }
      if (!checkEmail(this.email)) {
        alert('请输入正确的邮箱地址');
        return;
      }
      if (this.mobile == '') {
        alert('请输入手机号码');
        return;
      }
      if (!checkMobile(this.mobile)) {
        alert('请输入正确的手机号码');
        return;
      }
      if (this.password !== this.repassword) {
        alert('两次密码不一致');
        return;
      }
      // 请求后端
      let fd = new FormData();
      fd.append('username', this.username);
      fd.append('password', this.password);
      fd.append('repassword', this.repassword);
      fd.append('email', this.email);
      fd.append('mobile', this.mobile);
      this.axios.post('https://wayhome.zhangtory.com/api/register', fd,{
        headers:{
          'Content-Type':'multipart/form-data'
        }
      }).then(function (response) {
        alert(response);
      }).catch(function (error) {
        alert(error);
      })
      alert('ok');
    }
  }
}
</script>

<style scoped>

</style>
