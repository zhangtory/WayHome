<template>

</template>

<script>
    import router from "@/router";

    export default {
        name: "Exit",
      mounted: function () {
        localStorage.removeItem("Authorization");
        router.replace({
          path: '/'
        })
      }
    }
</script>

<style scoped>

</style>
