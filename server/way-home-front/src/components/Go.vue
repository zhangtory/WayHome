<template>
  <div class="go">
    <h1>{{msg}}</h1>
  </div>
</template>

<script>
  export default {
    name: "Go",
    data() {
      return {
        msg: '正在跳转...'
      }
    },
    mounted: function () {
      let userName = this.$route.params.userName;
      let keyName = this.$route.params.keyName;
      if (userName == null || keyName == null) {
        this.msg = '该钥匙不存在';
        return;
      }
      this.axios.get("/get/" + userName + "/" + keyName).then(response =>{
        let url = response.data.data['url'];
        console.log(url);
        if (url != null) {
          window.location.href = url;
        }
      }).catch(error => {
        console.log(error);
        this.msg = '该钥匙不存在或地址未上报';
      })
    },
  }
</script>

<style scoped>

</style>
