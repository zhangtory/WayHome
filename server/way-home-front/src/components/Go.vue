<template>
  <div>2231</div>
</template>

<script>
  export default {
    name: "Go",
    mounted: function () {
      let userName = this.$route.params.userName;
      let keyName = this.$route.params.keyName;
      this.axios.get("http://127.0.0.1:8011/api/address/" + userName + "/" + keyName).then(response =>{
        let url = response.data.data['url'];
        window.location.href = url;
      }).catch(function (error) {
        console.log(error);
      })
    },
  }
</script>

<style scoped>

</style>
