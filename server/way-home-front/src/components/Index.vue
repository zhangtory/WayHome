<template>
  <div class="index">
    <h1>{{msg}}</h1>
    <router-link to="/register">马上注册</router-link>
    <router-link to="/login">马上登录</router-link>
  </div>
</template>

<script>
export default {
  name: "Index",
  data () {
    return {
      msg: '123'
    }
  }
}
</script>

<style scoped>

</style>
